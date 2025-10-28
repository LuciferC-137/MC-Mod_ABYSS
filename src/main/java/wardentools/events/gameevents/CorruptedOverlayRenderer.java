package wardentools.events.gameevents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = ModMain.MOD_ID, value = Dist.CLIENT)
public class CorruptedOverlayRenderer {
    private static final Map<UUID, Integer> effectTotalDurations = new HashMap<>();
    private static final ResourceLocation CORRUPTED_OVERLAY
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/misc/corruption_outline_screen.png");
    private static final float blurDurationTick = 200.0f; // Approximate
    private static final float glitterPulsation = 0.04f;
    private static final float transparentMax = 0.9f;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiLayerEvent.Pre event) {
           Player player = Minecraft.getInstance().player;
           if (player != null && player.hasEffect(ModEffects.CORRUPTED)
               && !player.hasEffect(ModEffects.PURIFIED)) {
               MobEffectInstance effectInstance = player.getEffect(ModEffects.CORRUPTED);
               if (effectInstance == null) return;

               int duration = effectInstance.getDuration();
               int totalDuration = effectTotalDurations.getOrDefault(player.getUUID(), duration);

               // This condition allows to display the overlay when the game reloads.
               if (!effectTotalDurations.containsKey(player.getUUID())){
                   totalDuration = duration * 100;
               }

               int elapsedDuration = Math.max(totalDuration - duration, 0);
               float alpha = Math.min((float)elapsedDuration / blurDurationTick, 1.0f);
               float color_intensity = elapsedDuration > blurDurationTick ?
                       Math.abs((float)Math.sin((duration - blurDurationTick) * glitterPulsation)) : 1f;

               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               RenderSystem.setShaderColor(color_intensity, color_intensity, color_intensity,
                       alpha * transparentMax); // RGBA

               GuiGraphics guiGraphics = event.getGuiGraphics();
               int screenWidth = guiGraphics.guiWidth();
               int screenHeight =guiGraphics.guiHeight();
               guiGraphics.blit(CORRUPTED_OVERLAY, 0, 0,
                       0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

               RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
               RenderSystem.disableBlend();
           }

    }

    @SubscribeEvent
    public static void onEffectApplied(MobEffectEvent.Added event) {
        if (event.getEffectInstance() == null) return;
        if (event.getEffectInstance().getEffect() == ModEffects.CORRUPTED) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                if (!effectTotalDurations.containsKey(player.getUUID())
                        || !player.hasEffect(ModEffects.CORRUPTED)) {
                    effectTotalDurations.put(player.getUUID(), event.getEffectInstance().getDuration());
                } else if (player.getEffect(ModEffects.CORRUPTED) != null) {
                    int totalDuration = event.getEffectInstance().getDuration() +
                            effectTotalDurations.get(player.getUUID())
                            - player.getEffect(ModEffects.CORRUPTED).getDuration();
                    effectTotalDurations.put(player.getUUID(), totalDuration);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() == null) return;
        if (event.getEffectInstance().getEffect()
                == ModEffects.CORRUPTED) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                effectTotalDurations.remove(player.getUUID());
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) return;
        if (event.getEffectInstance().getEffect()
                == ModEffects.CORRUPTED) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                effectTotalDurations.remove(player.getUUID());
            }
        }
    }
}
