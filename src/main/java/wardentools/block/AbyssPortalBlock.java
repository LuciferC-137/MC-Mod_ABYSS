package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;
import wardentools.worldgen.structure.ModStructures;

import java.util.Objects;

public class AbyssPortalBlock  extends Block {
    public static final MapCodec<AbyssPortalBlock> CODEC = simpleCodec(AbyssPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final int AABB_OFFSET = 2;
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D,
            0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0D,
            0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public AbyssPortalBlock(Properties prop) {
        super(prop);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state,
                                        @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        switch ((Direction.Axis)state.getValue(AXIS)) {
            case Z:
                return Z_AXIS_AABB;
            case X:
            default:
                return X_AXIS_AABB;
        }
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state1, Direction dir, @NotNull BlockState state2,
                                           @NotNull LevelAccessor level, @NotNull BlockPos pos1,
                                           @NotNull BlockPos pos2) {
        Direction.Axis direction$axis = dir.getAxis();
        Direction.Axis direction$axis1 = state1.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && !state2.is(this) && !(new PortalShape(level, pos1, direction$axis1))
                .isComplete() ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state1, dir, state2, level, pos1, pos2);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level,
                                                @NotNull BlockPos pos, @NotNull BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull BlockState rotate(@NotNull BlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.getValue(AXIS)) {
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AXIS);
    }

    @Override
    public void entityInside(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (!level.isClientSide && entity.canChangeDimensions()) {
            ServerLevel currentLevel = (ServerLevel) level;
            if (currentLevel.dimension() == Level.OVERWORLD) {
                ServerLevel abyssLevel = currentLevel.getServer().getLevel(ModDimensions.ABYSS_LEVEL_KEY);
                if (abyssLevel != null) {
                    BlockPos ancientCityPos
                            = findNearestStructure(abyssLevel, ModStructures.SURFACE_ANCIENT_CITY, pos);
                    if (ancientCityPos != null) {
                        teleportToDimension(entity, ModDimensions.ABYSS_LEVEL_KEY, ancientCityPos);
                    }
                }
            } else if (currentLevel.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
                ServerLevel overworldLevel = currentLevel.getServer().getLevel(Level.OVERWORLD);
                if (overworldLevel != null) {
                    BlockPos ancientCityPos
                            = findNearestStructure(overworldLevel, BuiltinStructures.ANCIENT_CITY, pos);
                    if (ancientCityPos != null) {
                        teleportToDimension(entity, Level.OVERWORLD, ancientCityPos);
                    }
                }
            }
        }
    }

    private BlockPos findNearestStructure(ServerLevel level, ResourceKey<Structure> structureKey, BlockPos pos) {
        Holder<Structure> structureHolder = level.registryAccess()
                .registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(structureKey);
        HolderSet<Structure> structureHolderSet = HolderSet.direct(structureHolder);
        return Objects.requireNonNull(level.getChunkSource().getGenerator()
                        .findNearestMapStructure(level, structureHolderSet, pos, 10000, false))
                .getFirst();
    }

    private void teleportToDimension(Entity entity, ResourceKey<Level> targetDimension, BlockPos targetPos) {
        if (entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.changeDimension(Objects.requireNonNull(
                    Objects.requireNonNull(serverPlayer.getServer()).getLevel(targetDimension)),
                    new ModTeleporter(targetPos, true));
        } else if (!entity.level().isClientSide) {
            ServerLevel targetLevel = Objects.requireNonNull(entity.getServer()).getLevel(targetDimension);
            if (targetLevel != null) {
                entity.changeDimension(targetLevel, new ModTeleporter(targetPos, true));
            }
        }
    }

}
