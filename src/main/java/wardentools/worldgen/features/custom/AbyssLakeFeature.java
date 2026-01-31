package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

@SuppressWarnings("deprecation")
public class AbyssLakeFeature extends Feature<AbyssLakeConfiguration> {
    private static final BlockState AIR;

    public AbyssLakeFeature(Codec<AbyssLakeConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<AbyssLakeConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        AbyssLakeConfiguration config = context.config();
        if (!isAboveMinHeight(origin, world)) {
            return false;
        }
        BlockPos basePos = origin.below(4);
        boolean[] lakeShape = generateLakeShape(random);
        BlockState fluidState = config.fluid().getState(random, basePos);
        if (!canCarveLake(world, basePos, lakeShape, fluidState)) {
            return false;
        }
        carveLake(world, basePos, lakeShape, fluidState);
        placeBarrier(world, basePos, lakeShape, config, random);
        freezeSurfaceIfNeeded(world, basePos, fluidState);
        return true;
    }

    private boolean isAboveMinHeight(BlockPos pos, WorldGenLevel world) {
        return pos.getY() > world.getMinBuildHeight() + 4;
    }

    private boolean[] generateLakeShape(RandomSource random) {
        boolean[] shape = new boolean[2048];
        int ellipsoidCount = random.nextInt(4) + 4;
        for (int j = 0; j < ellipsoidCount; ++j) {
            double dx = random.nextDouble() * 6.0 + 3.0;
            double dy = random.nextDouble() * 4.0 + 2.0;
            double dz = random.nextDouble() * 6.0 + 3.0;
            double cx = random.nextDouble() * (16.0 - dx - 2.0) + 1.0 + dx / 2.0;
            double cy = random.nextDouble() * (8.0 - dy - 4.0) + 2.0 + dy / 2.0;
            double cz = random.nextDouble() * (16.0 - dz - 2.0) + 1.0 + dz / 2.0;
            for (int x = 1; x < 15; ++x) {
                for (int z = 1; z < 15; ++z) {
                    for (int y = 1; y < 7; ++y) {
                        double nx = (x - cx) / (dx / 2.0);
                        double ny = (y - cy) / (dy / 2.0);
                        double nz = (z - cz) / (dz / 2.0);
                        double dist = nx * nx + ny * ny + nz * nz;
                        if (dist < 1.0) {
                            shape[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }
        return shape;
    }

    private boolean canCarveLake(WorldGenLevel world, BlockPos basePos, boolean[] shape, BlockState fluidState) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 8; ++y) {
                    boolean edge = !shape[(x * 16 + z) * 8 + y] && (
                        (x < 15 && shape[((x + 1) * 16 + z) * 8 + y]) ||
                        (x > 0 && shape[((x - 1) * 16 + z) * 8 + y]) ||
                        (z < 15 && shape[(x * 16 + z + 1) * 8 + y]) ||
                        (z > 0 && shape[(x * 16 + (z - 1)) * 8 + y]) ||
                        (y < 7 && shape[(x * 16 + z) * 8 + y + 1]) ||
                        (y > 0 && shape[(x * 16 + z) * 8 + (y - 1)])
                    );
                    if (edge) {
                        BlockState state = world.getBlockState(basePos.offset(x, y, z));
                        if (y >= 4 && state.liquid()) {
                            return false;
                        }
                        if (y < 4 && !state.isSolid() && state != fluidState) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void carveLake(WorldGenLevel world, BlockPos basePos, boolean[] shape, BlockState fluidState) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 8; ++y) {
                    if (shape[(x * 16 + z) * 8 + y]) {
                        BlockPos pos = basePos.offset(x, y, z);
                        if (canReplaceBlock(world.getBlockState(pos))) {
                            boolean isAir = y >= 4;
                            world.setBlock(pos, isAir ? AIR : fluidState, 2);
                            if (isAir) {
                                world.scheduleTick(pos, AIR.getBlock(), 0);
                                markAboveForPostProcessing(world, pos);
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeBarrier(WorldGenLevel world, BlockPos basePos, boolean[] shape, AbyssLakeConfiguration config, RandomSource random) {
        BlockState barrierState = config.barrier().getState(random, basePos);
        if (barrierState.isAir()) return;
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 8; ++y) {
                    boolean edge = !shape[(x * 16 + z) * 8 + y] && (
                        (x < 15 && shape[((x + 1) * 16 + z) * 8 + y]) ||
                        (x > 0 && shape[((x - 1) * 16 + z) * 8 + y]) ||
                        (z < 15 && shape[(x * 16 + z + 1) * 8 + y]) ||
                        (z > 0 && shape[(x * 16 + (z - 1)) * 8 + y]) ||
                        (y < 7 && shape[(x * 16 + z) * 8 + y + 1]) ||
                        (y > 0 && shape[(x * 16 + z) * 8 + (y - 1)])
                    );
                    if (edge && (y < 4 || random.nextInt(2) != 0)) {
                        BlockState state = world.getBlockState(basePos.offset(x, y, z));
                        if (state.isSolid() && !state.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                            BlockPos pos = basePos.offset(x, y, z);
                            world.setBlock(pos, barrierState, 2);
                            markAboveForPostProcessing(world, pos);
                        }
                    }
                }
            }
        }
    }

    private void freezeSurfaceIfNeeded(WorldGenLevel world, BlockPos basePos, BlockState fluidState) {
        if (!fluidState.getFluidState().is(FluidTags.WATER)) return;
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int y = 4;
                BlockPos pos = basePos.offset(x, y, z);
                Biome biome = world.getBiome(pos).value();
                if (biome.shouldFreeze(world, pos, false) && canReplaceBlock(world.getBlockState(pos))) {
                    world.setBlock(pos, Blocks.ICE.defaultBlockState(), 2);
                }
            }
        }
    }

    private boolean canReplaceBlock(BlockState state) {
        return !state.is(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    static {
        AIR = Blocks.CAVE_AIR.defaultBlockState();
    }
}
