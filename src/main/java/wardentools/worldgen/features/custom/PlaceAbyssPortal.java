package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;

import java.util.ArrayList;
import java.util.List;

public class PlaceAbyssPortal extends Feature<NoneFeatureConfiguration> {
    public static final Block FRAME_BLOCK = Blocks.REINFORCED_DEEPSLATE;
    public static final Block PORTAL_BLOCK = BlockRegistry.ABYSS_PORTAL_BLOCK.get();
    public static final int MAX_LENGTH = 30;
    public static final int MAX_HEIGHT = 20;
    private final List<BlockPos> toPlace = new ArrayList<>();

    public PlaceAbyssPortal(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        if (!isFrame(ctx, ctx.origin().below())){return false;}
        int i = 0;
        Direction direction = getPortalOrientation(ctx);
        while (!isFrame(ctx, ctx.origin().offset(0, i, 0))){
            if (i>MAX_HEIGHT) {return false;}
            if(!placeLine(ctx, i, direction,
                    i==0 || isFrame(ctx, ctx.origin().offset(0, i+1,0))))
                {return false;}
            i++;
        }
        if (!checkCorrectFrame(this.toPlace)) {return false;}
        createPortal(ctx);
        return true;
    }

    private Direction getPortalOrientation(FeaturePlaceContext<NoneFeatureConfiguration> ctx){
        if (isFrame(ctx, ctx.origin().offset(-1, -1, 0))
                || isFrame(ctx, ctx.origin().offset(1, -1, 0))){
            return Direction.NORTH;
        } else {
            return Direction.EAST;
        }
    }

    private boolean placeLine(FeaturePlaceContext<NoneFeatureConfiguration> ctx,
                              int verticalOffset, Direction direction, boolean firstLine){
        if (direction == Direction.NORTH){
            int i = 1;
            while (!isFrame(ctx, ctx.origin().offset(i, verticalOffset, 0))){
                if (i>MAX_LENGTH) return false;
                if (firstLine && !hasFrameUnderOrAbove(ctx,
                        ctx.origin().offset(i, verticalOffset, 0))) return false;
                savePortalPos(ctx.origin().offset(i, verticalOffset, 0));
                i++;
            }
            i = 0;
            while (!isFrame(ctx, ctx.origin().offset(i, verticalOffset, 0))){
                if (i<-MAX_LENGTH) return false;
                if (firstLine && !hasFrameUnderOrAbove(ctx,
                        ctx.origin().offset(i, verticalOffset, 0))) return false;
                savePortalPos(ctx.origin().offset(i, verticalOffset, 0));
                i--;
            }
        } else {
            int i = 1;
            while (!isFrame(ctx, ctx.origin().offset(0, verticalOffset, i))){
                if (i>=MAX_LENGTH) return false;
                if (firstLine && !hasFrameUnderOrAbove(ctx,
                        ctx.origin().offset(0, verticalOffset, i))) return false;
                savePortalPos(ctx.origin().offset(0, verticalOffset, i));
                i++;
            }
            i = 0;
            while (!isFrame(ctx, ctx.origin().offset(0, verticalOffset, i))){
                if (i<-MAX_LENGTH) return false;
                if (firstLine && !hasFrameUnderOrAbove(ctx,
                        ctx.origin().offset(0, verticalOffset, i))) return false;
                savePortalPos(ctx.origin().offset(0, verticalOffset, i));
                i--;
            }
        }
        return true;
    }

    private static boolean isFrame(FeaturePlaceContext<NoneFeatureConfiguration> ctx, BlockPos pos){
        return (ctx.level().getBlockState(pos).is(FRAME_BLOCK));
    }

    private void savePortalPos(BlockPos pos){
        this.toPlace.add(pos);
    }

    private void createPortal(FeaturePlaceContext<NoneFeatureConfiguration> ctx){
        for (BlockPos pos:this.toPlace){
            ctx.level().setBlock(pos, PORTAL_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    private static boolean checkCorrectFrame(List<BlockPos> pos){
        int minX = pos.stream().mapToInt(BlockPos::getX).min().orElse(0);
        int maxX = pos.stream().mapToInt(BlockPos::getX).max().orElse(0);
        int minY = pos.stream().mapToInt(BlockPos::getY).min().orElse(0);
        int maxY = pos.stream().mapToInt(BlockPos::getY).max().orElse(0);
        int minZ = pos.stream().mapToInt(BlockPos::getZ).min().orElse(0);
        int maxZ = pos.stream().mapToInt(BlockPos::getZ).max().orElse(0);
        return pos.size() == (maxX - minX + 1) * (maxY - minY + 1) * (maxZ - minZ + 1);
    }

    private static boolean hasFrameUnderOrAbove(FeaturePlaceContext<NoneFeatureConfiguration> ctx, BlockPos pos){
        return isFrame(ctx, pos.above()) || isFrame(ctx, pos.below());
    }
}
