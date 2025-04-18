package wardentools.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.level.storage.LevelData;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

import java.util.Objects;

public class TeleportPlayerTo {
    private final int x, y, z;

    public TeleportPlayerTo(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TeleportPlayerTo(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    public TeleportPlayerTo(FriendlyByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public static void encode(TeleportPlayerTo msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.x);
        buffer.writeInt(msg.y);
        buffer.writeInt(msg.z);
    }

    public static TeleportPlayerTo decode(FriendlyByteBuf buffer) {
        return new TeleportPlayerTo(buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context.getSender()));
        context.setPacketHandled(true);
    }

    public static void handlePacket(TeleportPlayerTo msg, ServerPlayer serverPlayer) {
        if (serverPlayer != null) {
            Level level = serverPlayer.level();
            if (!(level instanceof ServerLevel serverLevel)) return;
            teleport(serverLevel, serverPlayer, msg.x, msg.y, msg.z);
        } else {
            System.out.println("Warning! Teleportation logic attempted on client side " +
                    "in TeleportPlayerTo.handlePacket");
        }
    }

    private static void teleport(ServerLevel level, Entity entity, int x, int y, int z) {
        if (level.dimension() == Level.OVERWORLD) {
            ServerLevel abyssLevel = level.getServer().getLevel(ModDimensions.ABYSS_LEVEL_KEY);
            if (abyssLevel != null) {
                BlockPos ancientCityPos = new BlockPos(x, y, z);
                teleportToDimension(entity, ModDimensions.ABYSS_LEVEL_KEY, ancientCityPos);
            }
        } else if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
            ServerLevel overworldLevel = level.getServer().getLevel(Level.OVERWORLD);
            if (overworldLevel != null) {
                BlockPos ancientCityPos = new BlockPos(x, y, z);
                teleportToDimension(entity, Level.OVERWORLD, ancientCityPos);
            }
        }
    }

    private static void teleportToDimension(Entity entity, ResourceKey<Level> targetDimension,
                                            BlockPos targetPos) {
        ServerLevel targetLevel = Objects.requireNonNull(entity.getServer()).getLevel(targetDimension);
        if (targetLevel == null) return;
        if (entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.revive();
            serverPlayer.changeDimension(ModTeleporter.diveToAncientCity(targetLevel, targetPos, serverPlayer));
        } else if (!entity.level().isClientSide) {
            entity.changeDimension(ModTeleporter.diveToAncientCity(targetLevel, targetPos, entity));
        }
    }
}
