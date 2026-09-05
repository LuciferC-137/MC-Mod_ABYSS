package wardentools.network;

import com.mojang.logging.LogUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.network.payloads.RequestStormStateFromServer;
import wardentools.network.payloads.SwitchAchievement;
import wardentools.network.payloads.TeleportPlayerTo;
import wardentools.network.payloads.datasync.SyncDataTaskToServer;
import wardentools.network.payloads.datasync.SyncKnownWhisperToServer;
import wardentools.playerdata.ModDataAttachments;
import wardentools.playerdata.serializables.CompletedTasks;
import wardentools.playerdata.serializables.KnownWindWhispers;
import wardentools.weather.AbyssWeatherEventServer;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

import java.lang.reflect.Method;
import java.util.Objects;

public class ServerPayloadHandler {
    private static final ResourceLocation CORRUPTION_ADVANCEMENT
            = new ResourceLocation(ModMain.MOD_ID, "corruption_vessel");
    private static final ResourceLocation RADIANCE_ADVANCEMENT
            = new ResourceLocation(ModMain.MOD_ID, "radiance_bringer");

    public static void teleportPlayerTo(TeleportPlayerTo msg, final ForgePayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                if (!(level instanceof ServerLevel serverLevel)) return;
                teleport(serverLevel, serverPlayer,
                        (int) msg.respawnPos().x, (int) msg.respawnPos().y, (int) msg.respawnPos().z);
            }
        }, ctx);
    }

    public static void switchAchievement(SwitchAchievement msg, final ForgePayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                if (msg.index() == 0) {
                    Advancement corruptionAdvancement = serverPlayer.server.getAdvancements()
                            .getAdvancement(CORRUPTION_ADVANCEMENT);
                    if (corruptionAdvancement != null) {
                        for (String criterion : corruptionAdvancement.getCriteria().keySet()) {
                            serverPlayer.getAdvancements().revoke(corruptionAdvancement, criterion);
                        }
                    }
                    ModCriteriaTriggers.RADIANCE_BRINGER.trigger(serverPlayer);
                }
                if (msg.index() == 1) {
                    Advancement radianceAdvancement = serverPlayer.server.getAdvancements()
                            .getAdvancement(RADIANCE_ADVANCEMENT);
                    if (radianceAdvancement != null) {
                        for (String criterion : radianceAdvancement.getCriteria().keySet()) {
                            serverPlayer.getAdvancements().revoke(radianceAdvancement, criterion);
                        }
                    }
                    ModCriteriaTriggers.CORRUPTION_VESSEL.trigger(serverPlayer);
                }
            }
        }, ctx);
    }

    public static void sendServerFogDistanceToPlayer(RequestStormStateFromServer msg, final ForgePayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                AbyssWeatherEventServer.WEATHER_MANAGER.sendServerFogDistanceToClient(serverPlayer);
            }
        }, ctx);
    }

    public static void syncTaskData(SyncDataTaskToServer msg, final ForgePayloadContext ctx) {
        handleDataOnNetwork(() -> {
            CompletedTasks data = getEntityData(ctx.player(), ModDataAttachments.COMPLETED_TASKS);
            if (msg.remove()) {
                data.removeCompletedTask(msg.taskId());
            } else {
                data.addCompletedTask(msg.taskId());
            }
            setEntityData(ctx.player(), ModDataAttachments.COMPLETED_TASKS, data);
        }, ctx);
    }

    public static void syncWindWhisperData(SyncKnownWhisperToServer msg, final ForgePayloadContext ctx) {
        handleDataOnNetwork(() -> {
            KnownWindWhispers data = getEntityData(ctx.player(), ModDataAttachments.KNOWN_WIND_WHISPERS);
            if (msg.remove()) {
                data.removeKnownWhisper(msg.whisperId());
            } else {
                data.addKnownWhisper(msg.whisperId());
            }
            setEntityData(ctx.player(), ModDataAttachments.KNOWN_WIND_WHISPERS, data);
        }, ctx);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getEntityData(Entity entity, Object attachmentKey) {
        try {
            Method method = entity.getClass().getMethod("getData", attachmentKey.getClass());
            return (T) method.invoke(entity, attachmentKey);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Entity data API not available during network sync", e);
        }
    }

    private static void setEntityData(Entity entity, Object attachmentKey, Object data) {
        for (Method method : entity.getClass().getMethods()) {
            if (method.getName().equals("setData") && method.getParameterCount() == 2) {
                try {
                    method.invoke(entity, attachmentKey, data);
                    return;
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException("Failed to set entity data during network sync", e);
                }
            }
        }
        throw new IllegalStateException("Entity data API not available during network sync");
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
            serverPlayer.changeDimension(targetLevel,
                    ModTeleporter.diveToAncientCity(targetLevel, targetPos, serverPlayer));
        } else if (!entity.level().isClientSide) {
            entity.changeDimension(targetLevel,
                    ModTeleporter.diveToAncientCity(targetLevel, targetPos, entity));
        }
    }

    private static void handleDataOnNetwork(Runnable run, final ForgePayloadContext ctx) {
        ctx.enqueueWork(run)
                .exceptionally(e -> {
                    LogUtils.getLogger().error("Dive Into the Abyss networking failed{}", e.getMessage());
                    ctx.disconnect(Component.literal("Dive Into the Abyss networking failed"));
                    return null;
                });
        ctx.setPacketHandled();
    }
}
