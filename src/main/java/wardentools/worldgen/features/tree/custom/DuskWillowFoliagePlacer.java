package wardentools.worldgen.features.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.features.tree.ModFoliagePlacers;

public class DuskWillowFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<DuskWillowFoliagePlacer> CODEC
            = RecordCodecBuilder.mapCodec(placerInstance
            -> foliagePlacerParts(placerInstance)
            .and(Codec.intRange(0, 16).fieldOf("height").forGetter(fp -> fp.height))
            .apply(placerInstance, DuskWillowFoliagePlacer::new));

    private final int height;

    // Canopy parameters
    private static final float VERTICAL_SQUASH = 0.6f; // Vertical squashing for the half-sphere
    private static final float LOWER_EXTENSION = 0.33f; // Foliage extends below the equator
    private static final float CUTOUT_RADIUS_MULTIPLIER = 1.8f; // Radius factor of the cutout sphere
    private static final float CUTOUT_CENTER_OFFSET = 1.2f; // Distance of cutout sphere center
    private static final float HANGING_LEAVES_CHANCE = 0.25f; // Probability to create hanging leaves at canopy edge
    private static final int MIN_HANGING_LENGTH = 1;
    private static final int MAX_HANGING_LENGTH = 3;
    public static final int MAX_FOLIAGE_RADIUS = 9;
    public static final int MIN_FOLIAGE_RADIUS = 3;

    public DuskWillowFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<DuskWillowFoliagePlacer> type() {
        return ModFoliagePlacers.DUSK_WILLOW_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader levelSimulatedReader,
                                 @NotNull FoliageSetter foliageSetter,
                                 @NotNull RandomSource randomSource,
                                 @NotNull TreeConfiguration treeConfiguration,
                                 int freeTreeHeight, @NotNull FoliageAttachment foliageAttachment,
                                 int foliageHeight, int foliageRadius, int offset) {

        BlockPos center = foliageAttachment.pos().above(offset);

        int baseRadius = Math.max(3, Math.min(5, foliageAttachment.radiusOffset()));

        // Create squashed half-sphere canopy with cutout
        createDomeCanopy(levelSimulatedReader, foliageSetter, randomSource,
                treeConfiguration, center, baseRadius);

        // Add hanging leaves under the canopy edge
        createHangingLeaves(levelSimulatedReader, foliageSetter, randomSource,
                treeConfiguration, center, baseRadius);
    }

    private void createDomeCanopy(LevelSimulatedReader level, FoliageSetter foliageSetter,
                                  RandomSource random, TreeConfiguration config,
                                  BlockPos center, int radius) {

        float verticalRadius = radius * VERTICAL_SQUASH;
        int lowerExtension = (int)(radius * LOWER_EXTENSION);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        // Parsing a squared volume around the center
        // Method shouldPlaceLeafAt determines sphere cutout and squashing
        for (int y = -lowerExtension; y <= (int)Math.ceil(verticalRadius); y++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    pos.setWithOffset(center, x, y, z);

                    if (shouldPlaceLeafAt(x, y, z, radius)) {
                        tryPlaceLeaf(level, foliageSetter, random, config, pos);
                    }
                }
            }
        }
    }

    private boolean shouldPlaceLeafAt(int x, int y, int z, int radius) {
        // Distance to center with vertical squash
        float dx = x;
        float dy = y / VERTICAL_SQUASH;
        float dz = z;
        float distFromCenter = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Check within the main sphere
        if (distFromCenter > radius) {
            return false;
        }

        // Above or on the equator : always place
        if (y >= 0) {
            return true;
        }

        // Under the equator : check for the spherical cutout
        float cutoutCenterY = -radius * CUTOUT_CENTER_OFFSET;
        float cutoutRadius = radius * CUTOUT_RADIUS_MULTIPLIER;

        // Distance to cutout center with vertical squash
        float cutDx = x;
        float cutDy = (y - cutoutCenterY) / VERTICAL_SQUASH;
        float cutDz = z;
        float distFromCutCenter = (float)Math.sqrt(cutDx * cutDx + cutDy * cutDy + cutDz * cutDz);

        return distFromCutCenter >= cutoutRadius;
    }

    private void createHangingLeaves(LevelSimulatedReader level, FoliageSetter foliageSetter,
                                     RandomSource random, TreeConfiguration config,
                                     BlockPos center, int radius) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int lowerExtension = (int)(radius * LOWER_EXTENSION);

        // Parse canopy edge area
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                float distFromCenterXZ = (float)Math.sqrt(x * x + z * z);

                // Place only at the edge
                if (distFromCenterXZ >= radius * 0.7f && distFromCenterXZ <= radius + 0.5f) {

                    // Hanging leaves chance
                    if (random.nextFloat() < HANGING_LEAVES_CHANCE) {
                        // Find the lowest leaf position at this (x, z)
                        Integer startY = null;
                        for (int y = -lowerExtension; y <= (int)(radius * VERTICAL_SQUASH); y++) {
                            if (shouldPlaceLeafAt(x, y, z, radius)) {
                                startY = y;
                            }
                        }

                        // Create hanging leaves downwards
                        if (startY != null) {
                            int hangingLength = MIN_HANGING_LENGTH + random.nextInt(MAX_HANGING_LENGTH - MIN_HANGING_LENGTH + 1);

                            for (int i = 1; i <= hangingLength; i++) {
                                pos.setWithOffset(center, x, startY - i, z);

                                // Decreasing chance to continue hanging leaves
                                float continueProbability = 1.0f - ((float)i / MAX_HANGING_LENGTH * 0.3f);
                                if (random.nextFloat() < continueProbability) {
                                    tryPlaceLeaf(level, foliageSetter, random, config, pos);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource randomSource, int i,
                             @NotNull TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource randomSource,
                                         int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}