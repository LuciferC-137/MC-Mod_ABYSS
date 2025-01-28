package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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
import wardentools.network.PacketHandler;
import wardentools.network.ShowWinScreen;
import wardentools.particle.ParticleRegistry;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;
import wardentools.worldgen.structure.ModStructures;

import java.util.Objects;
import java.util.Set;

public class AbyssPortalBlock extends Block implements EntityBlock {
    public static final MapCodec<AbyssPortalBlock> CODEC = simpleCodec(AbyssPortalBlock::new);

    public AbyssPortalBlock(Properties prop) {
        super(prop);
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AbyssPortalBlockEntity(pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader level,
                                                @NotNull BlockPos pos, @NotNull BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound((double)pos.getX() + 0.5D,
                    (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS,
                    0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + random.nextDouble();
            double d1 = (double)pos.getY() + random.nextDouble();
            double d2 = (double)pos.getZ() + random.nextDouble();
            double d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this)
                    && !level.getBlockState(pos.east()).is(this)) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(random.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(random.nextFloat() * 2.0F * (float)j);
            }
            level.addParticle(ParticleRegistry.ABYSS_PORTAL.get(), d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public void entityInside(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AbyssPortalBlockEntity abyssPortalBlockEntity) {
                if (abyssPortalBlockEntity.shouldShowWinScreen()) {
                    this.showWinScreen(player, pos);
                } else {
                    if (player.isPassenger()) {
                        player.stopRiding();
                    }
                    teleport((ServerLevel)level, pos, player);
                }
            }
        } else if (!level.isClientSide) {
            for (Entity passengers : entity.getPassengers()){
                teleport((ServerLevel) level, pos, passengers);
            }
            teleport((ServerLevel) level, pos, entity);
        }
    }

    private void teleport(ServerLevel level, BlockPos pos, Entity entity) {
        if (level.dimension() == Level.OVERWORLD) {
            ServerLevel abyssLevel = level.getServer().getLevel(ModDimensions.ABYSS_LEVEL_KEY);
            if (abyssLevel != null) {
                BlockPos ancientCityPos
                        = findNearestStructure(abyssLevel, ModStructures.SURFACE_ANCIENT_CITY, pos);
                teleportToAncientCity(entity, ModDimensions.ABYSS_LEVEL_KEY, ancientCityPos);
            }
        } else if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
            ServerLevel overworldLevel = level.getServer().getLevel(Level.OVERWORLD);
            if (overworldLevel != null) {
                BlockPos ancientCityPos
                        = findNearestStructure(overworldLevel, BuiltinStructures.ANCIENT_CITY, pos);
                teleportToAncientCity(entity, Level.OVERWORLD, ancientCityPos);
            }
        }
    }

    private BlockPos findNearestStructure(ServerLevel level, ResourceKey<Structure> structureKey,
                                          BlockPos pos) {
        // WARNING: if the dimension does not correspond to the wanted structure, this method returns 0,0,0
        if (level.registryAccess().get(Registries.STRUCTURE).isEmpty()) return new BlockPos(0, 0, 0);
        Holder<Structure> structureHolder = level.registryAccess()
                .get(Registries.STRUCTURE).get().get().wrapAsHolder(structureKey.getOrThrow(level).get());
        HolderSet<Structure> structureHolderSet = HolderSet.direct(structureHolder);
        var result = level.getChunkSource().getGenerator()
                .findNearestMapStructure(level, structureHolderSet, pos, 10000, false);
        return result != null ? result.getFirst()
                : new BlockPos(0, 0, 0);
    }

    private void teleportToAncientCity(Entity entity, ResourceKey<Level> targetDimension, BlockPos targetPos) {
        ServerLevel targetLevel = Objects.requireNonNull(entity.getServer()).getLevel(targetDimension);
        targetPos = ModTeleporter.findValidSpawn(targetLevel, targetPos, true);
        if (entity instanceof ServerPlayer serverPlayer) {
            if (targetLevel == null) return;
            serverPlayer.teleportTo(targetLevel,
                    targetPos.getX() + 0.5D, targetPos.getY() + 1.0D, targetPos.getZ() + 0.5D,
                    Set.of(), serverPlayer.getXRot(), serverPlayer.getYRot(), false);
        } else if (!entity.level().isClientSide) {
            if (targetLevel != null) {
                entity.teleportTo(targetLevel,
                        targetPos.getX() + 0.5D, targetPos.getY() + 1.0D, targetPos.getZ() + 0.5D,
                        Set.of(),
                        entity.getYRot(), entity.getXRot(), false);
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

    private void showWinScreen(Player player, BlockPos pos) {
        if (player == null || player.level().isClientSide) return;
        ServerLevel level = (ServerLevel) player.level();
        if (level.dimension() == Level.OVERWORLD) {
            ServerLevel abyssLevel = level.getServer().getLevel(ModDimensions.ABYSS_LEVEL_KEY);
            if (abyssLevel != null) {
                BlockPos ancientCityPos
                        = findNearestStructure(abyssLevel, ModStructures.SURFACE_ANCIENT_CITY, pos);
                sendScreenPacket(player, ancientCityPos);
            }
        } else if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
            ServerLevel overworldLevel = level.getServer().getLevel(Level.OVERWORLD);
            if (overworldLevel != null) {
                BlockPos ancientCityPos
                        = findNearestStructure(overworldLevel, BuiltinStructures.ANCIENT_CITY, pos);
                sendScreenPacket(player, ancientCityPos);
            }
        }
    }

    private void sendScreenPacket(Player player, BlockPos pos){
        ((ServerLevel)player.level()).removePlayerImmediately((ServerPlayer)player, Entity.RemovalReason.CHANGED_DIMENSION);
        PacketHandler.sendToClient(new ShowWinScreen(pos), (ServerPlayer) player);
    }
}
