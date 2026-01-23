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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class DuskWillowTrunkPlacer extends TrunkPlacer {

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
        int branchStartY = mainTrunkHeight - 2 - random.nextInt(2); // starting height of branch
        for (DiagonalDirection dir : branchDirections) {
            int branchHeight = (int)(mainTrunkHeight / 2) + random.nextInt(2); // branch height
            BlockPos branchStart = blockPos.above(branchStartY);
            this.placeBranch(levelSimulatedReader, biConsumer, random, branchHeight, branchStart, dir, treeConfiguration);
        }

        // Return foliage placement points
        return branchDirections.stream()
                .map(dir -> new FoliageAttachment(
                        blockPos.above(branchStartY)
                                .offset(dir.getStepX() * (3 + 1), 1, dir.getDir1().getStepZ() * (3 + 1)),
                        0, false))
                .toList();
    }

    public void placeRoots(@NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer, @NotNull RandomSource random,
            @NotNull BlockPos startPos, @NotNull TreeConfiguration treeConfiguration) {
        HashSet<Direction> roots = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            roots.add(Direction.Plane.HORIZONTAL.getRandomDirection(random));
        }
        for (Direction dir : roots) {
            int length = 2 + random.nextInt(3);
            for (int l = 0; l < length; l++) {
                BlockPos rootPos = startPos.offset(dir.getStepX(), 0, dir.getStepZ());
                if (dir == Direction.EAST) {
                    rootPos.offset(dir.getStepX(), 0, dir.getStepZ());
                }
                if (dir == Direction.SOUTH) {
                    rootPos.offset(dir.getStepX(), 0, dir.getStepZ());
                }
                this.placeWood(levelSimulatedReader, biConsumer, rootPos);
            }
        }
    }

    public void placeBranch(
            @NotNull LevelSimulatedReader levelSimulatedReader,
            @NotNull BiConsumer<BlockPos, BlockState> biConsumer,
            @NotNull RandomSource random, int branchHeight,
            @NotNull BlockPos startPos, @NotNull DiagonalDirection direction,
            @NotNull TreeConfiguration treeConfiguration) {
        BlockPos lastPos = startPos;
        lastPos = lastPos.offset(direction.getStepX(), -1, direction.getStepZ());
        if (direction.has(Direction.SOUTH)) lastPos= lastPos.south();
        if (direction.has(Direction.EAST)) lastPos= lastPos.east();
        int heightOffset = 0;

        while (heightOffset < branchHeight) {
            heightOffset++;

            int targetHorizontalDistance = (int)Mth.sqrt(heightOffset);
            int currentHorizontalDistance = (int)Mth.sqrt(heightOffset - 1);

            int diagonalSteps = targetHorizontalDistance - currentHorizontalDistance;

            for (int i = 0; i < diagonalSteps; i++) {
                lastPos = lastPos.offset(direction.getStepX(), 0, direction.getStepZ());
                placeWood(levelSimulatedReader, biConsumer, lastPos);
            }
            lastPos = lastPos.above();
            placeWood(levelSimulatedReader, biConsumer, lastPos);
        }
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
        return DiagonalDirection.randomSet(random, 2);
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
