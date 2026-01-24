package wardentools.worldgen.features.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.worldgen.features.tree.ModFoliagePlacers;

import java.util.ArrayList;
import java.util.List;

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
    private static final float HANGING_VINES_CHANCE = 0.5f; // Probability to create hanging vines
    private static final int MIN_VINE_LENGTH = 3;
    private static final int MAX_VINE_LENGTH = 7;
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

        // Collect edge positions for hanging elements
        List<EdgePosition> edgePositions = collectCanopyEdgePositions(center, baseRadius);

        // Add hanging leaves under the canopy edge
        createHangingLeaves(levelSimulatedReader, foliageSetter, randomSource,
                treeConfiguration, edgePositions);

        // Add hanging vines from the canopy edge
        createHangingVines(levelSimulatedReader, foliageSetter, randomSource,
                treeConfiguration, edgePositions);
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
                        placeLeafProtectingVines(level, foliageSetter, random, config, pos);
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

    /**
     * Collect all edge positions of the canopy (70%-105% of radius)
     * These will be used as starting points for hanging elements
     */
    private List<EdgePosition> collectCanopyEdgePositions(BlockPos center, int radius) {
        List<EdgePosition> edgePositions = new ArrayList<>();
        int lowerExtension = (int)(radius * LOWER_EXTENSION);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                float distFromCenterXZ = (float)Math.sqrt(x * x + z * z);

                // Only consider positions at the canopy edge
                // Slightly wider range to get more vine positions
                if (distFromCenterXZ >= radius * 0.6f && distFromCenterXZ <= radius * 1.1f) {
                    // Find the lowest leaf position at this (x, z)
                    Integer lowestY = findLowestLeafY(x, z, radius, lowerExtension);

                    if (lowestY != null) {
                        // Determine which direction the vine should face (towards nearest full block)
                        Direction attachDirection = findAttachmentDirection(x, z);
                        BlockPos worldPos = center.offset(x, lowestY, z);
                        edgePositions.add(new EdgePosition(worldPos, attachDirection));
                    }
                }
            }
        }

        return edgePositions;
    }

    /**
     * Find the lowest Y coordinate where a leaf exists at position (x, z)
     */
    private Integer findLowestLeafY(int x, int z, int radius, int lowerExtension) {
        Integer lowestY = null;
        for (int y = -lowerExtension; y <= (int)(radius * VERTICAL_SQUASH); y++) {
            if (shouldPlaceLeafAt(x, y, z, radius)) {
                lowestY = y;
            }
        }
        return lowestY;
    }

    /**
     * Determine the direction where the vine should attach based on position relative to center
     * This is the direction of the nearest canopy block (towards center)
     */
    private Direction findAttachmentDirection(int x, int z) {
        int absX = Math.abs(x);
        int absZ = Math.abs(z);

        // The vine attaches on the side facing inward (towards center)
        if (absX > absZ) {
            return x > 0 ? Direction.WEST : Direction.EAST;
        } else {
            return z > 0 ? Direction.NORTH : Direction.SOUTH;
        }
    }

    private void createHangingLeaves(LevelSimulatedReader level, FoliageSetter foliageSetter,
                                     RandomSource random, TreeConfiguration config,
                                     List<EdgePosition> edgePositions) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (EdgePosition edge : edgePositions) {
            // Hanging leaves chance
            if (random.nextFloat() < HANGING_LEAVES_CHANCE) {
                int hangingLength = MIN_HANGING_LENGTH + random.nextInt(MAX_HANGING_LENGTH - MIN_HANGING_LENGTH + 1);

                for (int i = 1; i <= hangingLength; i++) {
                    pos.set(edge.pos.getX(), edge.pos.getY() - i, edge.pos.getZ());

                    // Don't place if position is already occupied
                    if (foliageSetter.isSet(pos)) {
                        break;
                    }

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

    private void createHangingVines(LevelSimulatedReader level, FoliageSetter foliageSetter,
                                    RandomSource random, TreeConfiguration config,
                                    List<EdgePosition> edgePositions) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (EdgePosition edge : edgePositions) {
            // Hanging vines chance
            if (random.nextFloat() < HANGING_VINES_CHANCE) {
                int vineLength = MIN_VINE_LENGTH + random.nextInt(MAX_VINE_LENGTH - MIN_VINE_LENGTH + 1);

                for (int i = 1; i <= vineLength; i++) {
                    pos.set(edge.pos.getX(), edge.pos.getY() - i, edge.pos.getZ());

                    // Check if position is already occupied (by leaf from another canopy)
                    if (foliageSetter.isSet(pos)) {
                        // Stop this vine column - can't grow through existing blocks
                        break;
                    }

                    // Decreasing chance to continue growing the vine
                    float continueProbability = 1.0f - ((float)i / MAX_VINE_LENGTH * 0.4f);
                    if (random.nextFloat() < continueProbability) {
                        placeVine(level, foliageSetter, pos.immutable(), edge.attachDirection);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Place a single vine block with the correct facing direction
     * All vines in a column share the same side property (like vanilla vines)
     */
    private void placeVine(LevelSimulatedReader level, FoliageSetter foliageSetter,
                           BlockPos pos, Direction attachDirection) {
        if (!TreeFeature.validTreePos(level, pos)) {
            return;
        }

        // Create vine with the attachment direction property set to true
        BlockState vineState = BlockRegistry.VALLEY_IVY.get().defaultBlockState();
        BooleanProperty directionProperty = VineBlock.getPropertyForFace(attachDirection);
        vineState = vineState.setValue(directionProperty, true);

        foliageSetter.set(pos, vineState);
    }

    /**
     * Place a leaf block while protecting existing vines
     * If replacing a vine, preserve it by not placing the leaf
     */
    private void placeLeafProtectingVines(LevelSimulatedReader level, FoliageSetter foliageSetter,
                                          RandomSource random, TreeConfiguration config,
                                          BlockPos pos) {
        // Don't place leaf if there's already something here (could be a vine from another canopy)
        if (foliageSetter.isSet(pos)) {
            return;
        }

        // Place the leaf normally
        tryPlaceLeaf(level, foliageSetter, random, config, pos);
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

    /**
         * Helper class to store edge position information
         */
        private record EdgePosition(BlockPos pos, Direction attachDirection) {
    }
}