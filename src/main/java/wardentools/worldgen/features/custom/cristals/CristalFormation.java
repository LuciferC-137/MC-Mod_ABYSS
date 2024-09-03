package wardentools.worldgen.features.custom.cristals;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class CristalFormation extends Feature<CristalFormationConfiguration>  {

    public CristalFormation(Codec<CristalFormationConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CristalFormationConfiguration> context) {
        Vec3 direction = findAttachDirection(context.level(), context.origin());
        float length_multiplier = context.level().getRandom().nextFloat() / 2f + 0.5f;
        float length = (float)freeSpace(context, direction) * length_multiplier;
        float radius = length / context.config().lengthToRadiusRatio;
        ArrayList<BlockPos> list = mainBranch(context.origin(), direction, length, radius);
        for (BlockPos blockPos : list){
            context.level().setBlock(blockPos, context.config().cristalBlock, 3);
        }
        for (BlockPos blockPos : list){
            if (context.level().getRandom().nextInt(1, context.config().cristalBudRarity) == 1){
                placeSmallCristal(context.level(), context.config().cristalBud.getBlock(), blockPos);
            }
        }

        return true;
    }

    private void placeSmallCristal(LevelAccessor level, Block block, BlockPos anchor){
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = anchor.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            if (adjacentState.isAir()) {
                BlockState crystalBlock = block.defaultBlockState()
                        .setValue(BlockStateProperties.FACING, direction);
                level.setBlock(adjacentPos, crystalBlock, 3);
            }
        }
    }

    private int freeSpace(FeaturePlaceContext<CristalFormationConfiguration> context, Vec3 direction){
        int i = 1;
        int xoffset = 0;
        int yoffset = 0;
        int zoffset = 0;
        while (i <= context.config().maxLength){
            i++;
            xoffset += (int)(direction.x() * i);
            yoffset += (int)(direction.y() * i);
            zoffset += (int)(direction.z() * i);
            if (!context.level().getBlockState(context.origin().offset(xoffset, yoffset, zoffset)).isAir()){
                return i;
            }
        }
        return context.config().maxLength;
    }

    private Vec3 findAttachDirection(LevelAccessor level, BlockPos pos) {
        Vec3 directionSum = new Vec3(0, 0, 0);
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    if (level.getBlockState(checkPos).isAir()) {
                        directionSum = directionSum.add(new Vec3(x, y, z));
                    }
                }
            }
        }
        return directionSum.normalize();
    }

    private ArrayList<BlockPos> mainBranch(BlockPos startPos, Vec3 direction, float length, float radius) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        for (float i = 0; i <= length; i += 0.5f) {
            Vec3i currentPosVec = new Vec3i(
                    startPos.getX() + (int)(direction.x * i),
                    startPos.getY() + (int)(direction.y * i),
                    startPos.getZ() + (int)(direction.z * i)
            );
            BlockPos currentPos = new BlockPos(currentPosVec);
            float shrinking_radius = radius * (length - i) / length + 1f;
            list.addAll(placeSphereAt(currentPos, shrinking_radius));
        }
        return list;
    }

    private ArrayList<BlockPos> placeSphereAt(BlockPos center, float relativeRadius) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        int radius = (int)(relativeRadius + 0.5f);
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = center.offset(x, y, z);
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= radius) {
                        list.add(blockPos);
                    }
                }
            }
        }
        return list;
    }
}
