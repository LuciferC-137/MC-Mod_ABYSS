package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkBehaviour;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class AbyssSculkPatch extends Feature<AbyssSculkPatchConfiguration> {
    private static final SculkTendrilsEmergenceConfiguration TENDRIL_CONFIG =
            new SculkTendrilsEmergenceConfiguration(15, 0.4F,
                    5, 0.7F, 0.3F);

    public AbyssSculkPatch(Codec<AbyssSculkPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<AbyssSculkPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        if (!this.canSpreadFrom(level, origin)) {
            return false;
        } else {
            AbyssSculkPatchConfiguration config = context.config();
            RandomSource random = context.random();
            SculkSpreader sculkSpreader = SculkSpreader.createWorldGenSpreader();
            int radius = config.spreadRounds() + config.growthRounds();

            int i;
            int j;
            for(int r = 0; r < radius; ++r) {
                for(i = 0; i < config.chargeCount(); ++i) {
                    sculkSpreader.addCursors(origin, config.amountPerCharge());
                }
                boolean in = r < config.spreadRounds();
                for(j = 0; j < config.spreadAttempts(); ++j) {
                    sculkSpreader.updateCursors(level, origin, random, in);
                }
                sculkSpreader.clear();
            }
            BlockPos below = origin.below();
            if (random.nextFloat() <= config.catalystChance()
                    && level.getBlockState(below).isCollisionShapeFullBlock(level, below)) {
                if (config.canHaveTendril()) {
                    placeTendril(origin, level, random);
                } else {
                    level.setBlock(origin, Blocks.SCULK_CATALYST.defaultBlockState(), 3);
                }
            }

            i = config.extraRareGrowths().sample(random);

            for(j = 0; j < i; ++j) {
                BlockPos offset = origin.offset(random
                        .nextInt(5) - 2, 0, random.nextInt(5) - 2);
                if (level.getBlockState(offset).isAir()
                        && level.getBlockState(offset.below())
                        .isFaceSturdy(level, offset.below(), Direction.UP)) {
                    level.setBlock(offset, Blocks.SCULK_SHRIEKER.defaultBlockState()
                            .setValue(SculkShriekerBlock.CAN_SUMMON, true), 3);
                }
            }

            return true;
        }
    }

    private void placeTendril(BlockPos pos, WorldGenLevel level, RandomSource random) {
        ConfiguredFeature<?, ?> emergence
                = new ConfiguredFeature<>(new SculkTendrilsEmergence(
                SculkTendrilsEmergenceConfiguration.CODEC), TENDRIL_CONFIG);
        if (level.getChunkSource() instanceof  ServerChunkCache chunkCache) {
            emergence.place(level, chunkCache.getGenerator(), random, pos);
        }
    }

    private boolean canSpreadFrom(LevelAccessor level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof SculkBehaviour) {
            return true;
        } else if (!state.isAir() && (!state.is(Blocks.WATER) || !state.getFluidState().isSource())) {
            return false;
        } else {
            Stream<Direction> directions = Direction.stream();
            Objects.requireNonNull(pos);
            return directions.map(pos::relative)
                    .anyMatch((pos1) -> level.getBlockState(pos1).isCollisionShapeFullBlock(level, pos1));
        }
    }
}

