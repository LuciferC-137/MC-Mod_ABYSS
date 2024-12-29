package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.SoulSpawnerBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ShadowEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoulSpawner extends Block implements EntityBlock {
    private static final int SPAWN_REDUCER = 2;
    private static final int MAX_SHADOWS = 6;
    private static final float MAX_SHADOW_CHECK_DISTANCE = 10.0F;

    public SoulSpawner(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (level.isClientSide()) return;
        if (random.nextInt(SPAWN_REDUCER) == 0) {
            if (checkForManyShadowsAround(level, blockPos)) return;
            BlockPos spawnPos = findSpawnPos(level, blockPos);
            if (spawnPos == null) return;
            ShadowEntity shadow = new ShadowEntity(ModEntities.SHADOW.get(), level);
            shadow.setStasis(true);
            shadow.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(),
                    spawnPos.getZ() + 0.5, 0.0F, 0.0F);
            level.addFreshEntityWithPassengers(shadow);
        }
    }

    private boolean checkForManyShadowsAround(@NotNull Level level, @NotNull BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof SoulSpawnerBlockEntity soulSpawner) {
            if (soulSpawner.getShadowEntity() == null) {
                ShadowEntity shadowEntity = ModEntities.SHADOW.get().create(level);
                if (shadowEntity != null) {
                    shadowEntity.moveTo(soulSpawner.getBlockPos(), 0.0F, 0.0F);
                    soulSpawner.setShadowEntity(shadowEntity);
                }
            }
            return level.getEntities(soulSpawner.getShadowEntity(),
                    this.getShape(this.defaultBlockState(), level, pos, CollisionContext.empty())
                            .bounds().move(pos).inflate(MAX_SHADOW_CHECK_DISTANCE)).size() > MAX_SHADOWS;
        }
        return false;
    }

    private @Nullable BlockPos findSpawnPos(@NotNull Level level, @NotNull BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int size = 6;
        List<BlockPos> positions = new ArrayList<>();
        for (int dx = -size; dx <= size; dx++) {
            for (int dy = -size / 2; dy <= size / 2; dy++) {
                for (int dz = -size; dz <= size; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    BlockPos newPos = new BlockPos(x + dx, y + dy, z + dz);
                    positions.add(newPos);
                }
            }
        }
        Collections.shuffle(positions);
        for (BlockPos newPos : positions) {
            if (level.getBlockState(newPos).isAir()
                    && level.getBlockState(newPos.below()).isCollisionShapeFullBlock(level, newPos.below())
                    && level.getBlockState(newPos.above()).isAir()) {
                return newPos;
            }
        }
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityRegistry.SOUL_SPAWNER_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader world,
                          RandomSource randomSource, BlockPos pos, int fortune, int silktouch) {
        return 15 + randomSource.nextInt(15) + randomSource.nextInt(15);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource source) {
        for (int i = 0; i < level.random.nextInt(4) + 1; i++) {
            double offsetX = source.nextDouble();
            double offsetY = source.nextDouble();
            double offsetZ = source.nextDouble();
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, pos.getX() + offsetX,
                    pos.getY() + offsetY, pos.getZ() + offsetZ,
                    0.0D, 0.0D, 0.0D);
        }
    }
}
