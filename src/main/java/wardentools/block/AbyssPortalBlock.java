package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.AbyssPortalBlockEntity;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;
import wardentools.worldgen.structure.ModStructures;

import java.util.Objects;

public class AbyssPortalBlock extends Block implements EntityBlock {
    public static final MapCodec<AbyssPortalBlock> CODEC = simpleCodec(AbyssPortalBlock::new);

    public AbyssPortalBlock(Properties prop) {
        super(prop);
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AbyssPortalBlockEntity(pos, state);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level,
                                                @NotNull BlockPos pos, @NotNull BlockState state) {
        return ItemStack.EMPTY;
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

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
        if (!isValidNeighbor(level, neighbor)) {
            if (level instanceof Level) {
                ((Level) level).removeBlock(pos, true);
            }
        }
    }
  
    public boolean isValidNeighbor(LevelReader level, BlockPos pos){
        return level.getBlockState(pos).is(Blocks.REINFORCED_DEEPSLATE)
                || level.getBlockState(pos).is(BlockRegistry.ABYSS_PORTAL_BLOCK.get());
    }
}
