package wardentools.network;

import com.mojang.logging.LogUtils;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.network.PayloadsRecords.RequestFogDistanceFromServer;
import wardentools.network.PayloadsRecords.SwitchAchievement;
import wardentools.network.PayloadsRecords.TeleportPlayerTo;
import wardentools.weather.AbyssWeatherEvent;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

import java.util.Objects;

public class ServerPayloadHandler {
    private static final ResourceLocation CORRUPTION_ADVANCEMENT
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corruption_vessel");
    private static final ResourceLocation RADIANCE_ADVANCEMENT
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_bringer");

    public static void teleportPlayerTo(TeleportPlayerTo msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                if (!(level instanceof ServerLevel serverLevel)) return;
                teleport(serverLevel, serverPlayer,
                        (int)msg.respawnPos().x, (int)msg.respawnPos().y, (int)msg.respawnPos().z);
            }
        }, ctx);
    }

    public static void switchAchievement(SwitchAchievement msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                if (msg.index() == 0) {
                    AdvancementHolder corruptionAdvancement = serverPlayer.server.getAdvancements()
                            .get(CORRUPTION_ADVANCEMENT);
                    if (corruptionAdvancement != null) {
                        for (String criterion : corruptionAdvancement.value().criteria().keySet()) {
                            serverPlayer.getAdvancements().revoke(corruptionAdvancement, criterion);
                        }
                    }
                    ModCriteriaTriggers.RADIANCE_BRINGER.trigger(serverPlayer);
                }
                if (msg.index() == 1) {
                    AdvancementHolder radianceAdvancement = serverPlayer.server.getAdvancements()
                            .get(RADIANCE_ADVANCEMENT);
                    if (radianceAdvancement != null) {
                        for (String criterion : radianceAdvancement.value().criteria().keySet()) {
                            serverPlayer.getAdvancements().revoke(radianceAdvancement, criterion);
                        }
                    }
                    ModCriteriaTriggers.CORRUPTION_VESSEL.trigger(serverPlayer);
                }
            }
        }, ctx);
    }

    public static void sendServerFogDistanceToPlayer(RequestFogDistanceFromServer msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof ServerPlayer serverPlayer) {
                AbyssWeatherEvent.WEATHER_MANAGER.sendServerFogDistanceToClient(serverPlayer);
            }
        }, ctx);
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

    private static void handleDataOnNetwork(Runnable run, final IPayloadContext ctx) {
        ctx.enqueueWork(run)
                .exceptionally(e -> {
                    LogUtils.getLogger().error("Dive Into the Abyss networking failed{}", e.getMessage());
                    ctx.disconnect(Component.literal("Dive Into the Abyss networking failed"));
                    return null;
                });
    }
}
