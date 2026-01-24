package wardentools.worldgen.features.tree.custom;

import com.google.common.base.Function;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.misc.DiagonalDirection;
import wardentools.worldgen.features.tree.ModTrunkPlacerTypes;

import java.util.*;
import java.util.function.BiConsumer;

public class DuskWillowTrunkPlacer extends TrunkPlacer {
    private static final float BRANCH_MIN_CURVATURE = 0.07f;
    private static final float BRANCH_MAX_CURVATURE = 0.095f;
    private static final int MIN_BRANCH_HEIGHT = 3;

    public static final MapCodec<DuskWillowTrunkPlacer> CODEC = RecordCodecBuilder
            .mapCodec(placerInstance ->
                    trunkPlacerParts(placerInstance)
                            .apply(placerInstance, DuskWillowTrunkPlacer::new));

    public DuskWillowTrunkPlacer(int baseHeight, int pHeightRandA, int pHeightRandB) {
        super(baseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.DUSK_WILLOW_TRUNK_PLACER.get();
    }

    public int maxBaseTrunkSize() {
        return this.baseHeight + this.heightRandA + this.heightRandB;
    }

    public int maxBranchHeight() {
        return this.maxBaseTrunkSize() - 2;
    }

    @Override
    public @NotNull List<FoliageAttachment> placeTrunk(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int freeTreeHeight,
            @NotNull BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {
        // Height of main 4 logs wide trunk
        int mainTrunkHeight = freeTreeHeight + random.nextInt(heightRandA) + random.nextInt(heightRandB);

        // Placing main trunk
        this.placeMainTrunk(levelSimulatedReader, biConsumer, random, mainTrunkHeight, blockPos, treeConfiguration);
        this.placeRoots(levelSimulatedReader, biConsumer, random, blockPos, treeConfiguration);

        // Deciding branches
        Set<DiagonalDirection> branchDirections = getBranches(random, mainTrunkHeight);

        // Branch Palcement
        ArrayList<FoliageAttachment> foliagePositions = new ArrayList<>();
        for (DiagonalDirection dir : branchDirections) {
            int branchHeight = Math.max(mainTrunkHeight
                            - random.nextInt(2, 4) + random.nextInt(2), MIN_BRANCH_HEIGHT);
            BlockPos branchStart = blockPos.above(mainTrunkHeight).below();
            if (dir.has(Direction.SOUTH)) branchStart = branchStart.south();
            if (dir.has(Direction.EAST)) branchStart = branchStart.east();
            List<FoliageAttachment> foliagePos = this.placeBranch(levelSimulatedReader,
                    biConsumer, random, branchHeight, mainTrunkHeight,
                    branchStart, dir, treeConfiguration);
            foliagePositions.addAll(foliagePos);
        }

        // Return foliage placement points
        return foliagePositions;
    }

    public void placeRoots(@NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer, @NotNull RandomSource random,
            @NotNull BlockPos startPos, @NotNull TreeConfiguration treeConfiguration) {
       for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos rootPos = startPos.relative(dir);
            if (dir == Direction.EAST || dir == Direction.SOUTH) {
                rootPos = rootPos.relative(dir);
            }
            if (dir == Direction.NORTH || dir == Direction.EAST) {
                if (random.nextBoolean()) {
                    this.placeWood(levelSimulatedReader, biConsumer, rootPos);
                }
                rootPos = rootPos.relative(dir.getClockWise());
            } else {
                if (random.nextBoolean()) {
                    this.placeWood(levelSimulatedReader, biConsumer, rootPos);
                }
                rootPos = rootPos.relative(dir.getCounterClockWise());
            }
            if (random.nextInt(3) == 0) {
                this.placeWood(levelSimulatedReader, biConsumer, rootPos);
            }
        }
    }

    public List<FoliageAttachment> placeBranch(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int branchHeight, int mainTrunkHeight,
            @NotNull BlockPos startPos, @NotNull DiagonalDirection direction,
            @NotNull TreeConfiguration treeConfiguration) {
        List<FoliageAttachment> foliagePositions = new ArrayList<>();

        // If the tree is too big, it needs branches more centered above the trunk
        if (mainTrunkHeight >= this.maxBaseTrunkSize() * 0.6F) {
            BlockPos midFoliagePos = this.placeMiddleBranch(levelSimulatedReader,
                    biConsumer, random, (int)(branchHeight * 0.8F),
                    startPos, direction, treeConfiguration);
            int midFoliageRadius = Math.max(5, (int)(branchHeight * 0.6F));
            foliagePositions.add(new FoliageAttachment(midFoliagePos, midFoliageRadius, true));
        }

        // In any case, place the regular branches that go further out
        startPos = direction.apply(startPos)
                .below(random.nextInt(2, Math.max(3, mainTrunkHeight / 2 + 1)));
        BlockPos farFoliagePos = this.placeRegularBranch(levelSimulatedReader,
                biConsumer, random, branchHeight, startPos, direction, treeConfiguration);

        int foliageRadius = Math.max(2, (int)(branchHeight * 0.4F));
        foliagePositions.add(new FoliageAttachment(farFoliagePos, foliageRadius, false));

        return foliagePositions;
    }


    public BlockPos placeMiddleBranch(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int branchHeight,
            @NotNull BlockPos startPos, @NotNull DiagonalDirection direction,
            @NotNull TreeConfiguration treeConfiguration) {
        BlockPos lastPos = startPos;

        lastPos = this.placeBlockArc(levelSimulatedReader, biConsumer, random,
                this.branchCurvatureFactor(branchHeight) * 0.75F,
                branchHeight + random.nextInt(1, 3),
                lastPos, direction, treeConfiguration, 10);

        return lastPos;
    }

    public BlockPos placeRegularBranch(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int branchHeight,
            @NotNull BlockPos startPos, @NotNull DiagonalDirection direction,
            @NotNull TreeConfiguration treeConfiguration) {
        BlockPos lastPos = startPos;

        // Placing branch curvature
        float curvatureFactor = this.branchCurvatureFactor(branchHeight);
        lastPos = this.placeBlockArc(levelSimulatedReader, biConsumer, random,
                curvatureFactor, branchHeight, lastPos, direction,
                treeConfiguration, 3);

        // Placing short straight end
        int straightLength = (int)((float)branchHeight / 2.5F);
        for (int i = 0; i < straightLength; i++) {
            lastPos = lastPos.relative(direction.getDir1());
            placeWood(levelSimulatedReader, biConsumer, lastPos);
            lastPos = lastPos.relative(direction.getDir2());
            placeWood(levelSimulatedReader, biConsumer, lastPos);
        }

        // Final leaf attachment point
        lastPos = lastPos.above();
        placeLog(levelSimulatedReader, biConsumer, random, lastPos, treeConfiguration);

        return lastPos;
    }

    public BlockPos placeBlockArc(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, float curvatureFactor, int branchHeight,
            @NotNull BlockPos startPos, @NotNull DiagonalDirection dir,
            @NotNull TreeConfiguration treeConfiguration, int randomOffsetRarity) {
        BlockPos lastPos = startPos;
        int heightOffset = 0;
        while (heightOffset < branchHeight) {
            heightOffset++;

            int targetHorizontalDistance = (int)(Mth.square(heightOffset) * curvatureFactor);
            int currentHorizontalDistance = (int)(Mth.square(heightOffset - 1) * curvatureFactor);

            int diagonalSteps = targetHorizontalDistance - currentHorizontalDistance;

            for (int i = 0; i < diagonalSteps; i++) {
                lastPos = dir.apply(lastPos);
                placeWood(levelSimulatedReader, biConsumer, lastPos);
                if (random.nextInt(randomOffsetRarity) == 0) {
                    if (random.nextBoolean()) {
                        lastPos = lastPos.relative(dir.getDir1());
                    } else {
                        lastPos = lastPos.relative(dir.getDir2());
                    }
                    placeWood(levelSimulatedReader, biConsumer, lastPos);
                }
            }

            lastPos = lastPos.above();
            placeWood(levelSimulatedReader, biConsumer, lastPos);
        }
        return lastPos;
    }

    private float branchCurvatureFactor(int branchHeight) {
        return BRANCH_MIN_CURVATURE +
                (BRANCH_MAX_CURVATURE - BRANCH_MIN_CURVATURE) *
                        (1F - (float)branchHeight / (float)this.maxBranchHeight());
    }

    public void placeMainTrunk(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int mainTrunkHeight,
            @NotNull BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {

        for (int y = 0; y < mainTrunkHeight; y++) {
            BlockPos pos = blockPos.above(y);
            this.placeLog(levelSimulatedReader, biConsumer, random, pos, treeConfiguration);
            this.placeLog(levelSimulatedReader, biConsumer, random, pos.east(), treeConfiguration);
            this.placeLog(levelSimulatedReader, biConsumer, random, pos.south(), treeConfiguration);
            this.placeLog(levelSimulatedReader, biConsumer, random, pos.south().east(), treeConfiguration);
        }
    }

    private Set<DiagonalDirection> getBranches(RandomSource random, int mainTrunkHeight) {
        if (mainTrunkHeight > this.baseHeight + this.heightRandA / 2 + this.heightRandB / 2) {
            return Set.copyOf(Arrays.stream(DiagonalDirection.values()).toList());
        }
        if (mainTrunkHeight > this.baseHeight + this.heightRandA / 2) {
            return DiagonalDirection.randomSet(random, 3);
        }
        return DiagonalDirection.randomOppositeSet(random);
    }

    @Override
    protected boolean placeLog(@NotNull LevelSimulatedReader level,
                               @NotNull BiConsumer<BlockPos, BlockState> setter,
                               @NotNull RandomSource random, @NotNull BlockPos pos,
                               @NotNull TreeConfiguration config) {
        return this.placeLog(level, setter, random, pos, config, Direction.UP);
    }

    protected boolean placeLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter,
                               RandomSource random, BlockPos pos, TreeConfiguration config,
                               Direction direction) {
        Function<BlockState, BlockState> orientLog = (logState) -> {
            if (logState == null) return null;
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                return logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
            } else if (direction == Direction.EAST || direction == Direction.WEST) {
                return logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
            }
            return logState;
        };
        return this.placeLog(level, setter, random, pos, config, orientLog);
    }

    protected boolean placeLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter,
                               RandomSource random, BlockPos pos, TreeConfiguration config,
                               Function<BlockState, BlockState> orientLog) {
        if (this.validTreePos(level, pos)) {
            setter.accept(pos, orientLog.apply(config.trunkProvider.getState(random, pos)));
            return true;
        } else {
            return false;
        }
    }

    protected boolean placeWood(LevelSimulatedReader level,
                                BiConsumer<BlockPos, BlockState> setter,BlockPos pos) {
        if (this.validTreePos(level, pos)) {
            setter.accept(pos, BlockRegistry.DUSK_WILLOW_WOOD.get().defaultBlockState());
            return true;
        } else {
            return false;
        }
    }
}
