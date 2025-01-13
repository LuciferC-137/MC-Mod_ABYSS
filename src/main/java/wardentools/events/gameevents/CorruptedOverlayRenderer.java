package wardentools.events.gameevents;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CorruptedOverlayRenderer {
    private static final Map<UUID, Integer> effectTotalDurations = new HashMap<>();
    private static final ResourceLocation CORRUPTED_OVERLAY
            = new ResourceLocation(ModMain.MOD_ID, "textures/misc/corruption_outline_screen.png");
    private static final float blurDurationTick = 200.0f; // Approximate
    private static final float glitterPulsation = 0.04f;
    private static final float transparentMax = 0.99f;

    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent event) {
           Player player = Minecraft.getInstance().player;
           if (ModEffects.CORRUPTED.getHolder().isEmpty()) return;
           if (player != null && player.hasEffect(ModEffects.CORRUPTED.getHolder().get())) {
               MobEffectInstance effectInstance = player.getEffect(ModEffects.CORRUPTED.getHolder().get());
               if (effectInstance == null){
                   return;
               }
               int duration = effectInstance.getDuration();
               int totalDuration = effectTotalDurations.getOrDefault(player.getUUID(), duration);

               // This condition allows to display the overlay when the game reloads.
               if (!effectTotalDurations.containsKey(player.getUUID())){
                   totalDuration = duration * 100;
               }

               int elapsedDuration = Math.max(totalDuration - duration, 0);
               float alpha = Math.min( (float)elapsedDuration / blurDurationTick * 0.05f, 1.0f);
               float color_intensity = elapsedDuration > blurDurationTick ?
                       Math.abs((float)Math.sin((duration - blurDurationTick) * glitterPulsation)) : 1f;

               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               RenderSystem.setShaderColor(color_intensity, color_intensity, color_intensity,
                       alpha * transparentMax); // RGBA

               GuiGraphics guiGraphics = event.getGuiGraphics();
               Window window = event.getWindow();
               int screenWidth = window.getGuiScaledWidth();
               int screenHeight = window.getGuiScaledHeight();
               guiGraphics.blit(CORRUPTED_OVERLAY, 0, 0,
                       0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

               RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
               RenderSystem.disableBlend();
           }

    }

    @SubscribeEvent
    public static void onEffectApplied(MobEffectEvent.Added event) {
        if (ModEffects.CORRUPTED.getHolder().isEmpty()) return;
        if (event.getEffectInstance().getEffect() == ModEffects.CORRUPTED.getHolder().get()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                if (!effectTotalDurations.containsKey(player.getUUID())
                        || !player.hasEffect(ModEffects.CORRUPTED.getHolder().get())) {
                    effectTotalDurations.put(player.getUUID(), event.getEffectInstance().getDuration());
                } else {
                    int totalDuration = event.getEffectInstance().getDuration() +
                            effectTotalDurations.get(player.getUUID())
                            - Objects.requireNonNull(player
                            .getEffect(ModEffects.CORRUPTED.getHolder().get())).getDuration();
                    effectTotalDurations.put(player.getUUID(), totalDuration);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (ModEffects.CORRUPTED.getHolder().isEmpty()) return;
        if (Objects.requireNonNull(event.getEffectInstance()).getEffect()
                == ModEffects.CORRUPTED.getHolder().get()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                effectTotalDurations.remove(player.getUUID());
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (ModEffects.CORRUPTED.getHolder().isEmpty()) return;
        if (Objects.requireNonNull(event.getEffectInstance()).getEffect()
                == ModEffects.CORRUPTED.getHolder().get()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
                effectTotalDurations.remove(player.getUUID());
            }
        }
    }
}
