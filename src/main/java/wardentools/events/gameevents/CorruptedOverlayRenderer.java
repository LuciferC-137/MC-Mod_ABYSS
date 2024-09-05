package wardentools.events.gameevents;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CorruptedOverlayRenderer {
    private static final Map<UUID, Integer> effectTotalDurations = new HashMap<>();
    private static final ResourceLocation CORRUPTED_OVERLAY
            = new ResourceLocation(ModMain.MOD_ID, "textures/misc/corruption_outline_screen.png");
    private static final float blurDurationTick = 200.0f; // Approximative
    private static final float glitterPulsation = 0.04f;
    private static final float transparancyMax = 0.75f;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent event) {
           Player player = Minecraft.getInstance().player;
           if (player != null && player.hasEffect(ModEffects.CORRUPTED.get())) {
               MobEffectInstance effectInstance = player.getEffect(ModEffects.CORRUPTED.get());
               if (effectInstance == null){
                   return;
               }
               int duration = effectInstance.getDuration();
               int totalDuration = effectTotalDurations.getOrDefault(player.getUUID(), duration);
               if (!effectTotalDurations.containsKey(player.getUUID())){
                   totalDuration = duration * 100;
               }

               int elapsedDuration = totalDuration - duration;
               float alpha = Math.min( (float)elapsedDuration / blurDurationTick * 0.05f, 1.0f);
               float color_intensity = elapsedDuration > blurDurationTick ?
                       Math.abs((float)Math.sin((duration - blurDurationTick) * glitterPulsation)) : 1f;

               RenderSystem.enableBlend();
               RenderSystem.defaultBlendFunc();
               RenderSystem.setShaderColor(color_intensity, color_intensity, color_intensity,
                       alpha * transparancyMax); // RGBA

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
        if (event.getEffectInstance().getEffect() == ModEffects.CORRUPTED.get()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (!effectTotalDurations.containsKey(player.getUUID())) {
                    effectTotalDurations.put(player.getUUID(), event.getEffectInstance().getDuration());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEffectInstance().getEffect() == ModEffects.CORRUPTED.get()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (effectTotalDurations.containsKey(player.getUUID())) {
                    effectTotalDurations.remove(player.getUUID());
                }
            }
        }
    }
}
