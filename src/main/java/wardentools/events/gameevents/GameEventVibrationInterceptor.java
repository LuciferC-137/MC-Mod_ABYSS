package wardentools.events.gameevents;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.items.enchantment.EnchantmentRegistry;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class GameEventVibrationInterceptor {

    @SubscribeEvent
    public static void onGameEvent(VanillaGameEvent event) {
        if (event.getCause() instanceof Player player) {
            if (EnchantmentRegistry.STEALTH.getHolder().isEmpty()) return;
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (isStealthEnchanted(boots) && isStealthEnchanted(helmet)
                    && isStealthEnchanted(leggings) && isStealthEnchanted(chestplate)) {
                event.setCanceled(true);
                return;
            }
            if (event.getVanillaEvent() == GameEvent.HIT_GROUND.get()) {
                if (isStealthEnchanted(leggings)) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.BLOCK_PLACE.get()
                    || event.getVanillaEvent() == GameEvent.BLOCK_DESTROY.get()
                    || event.getVanillaEvent() == GameEvent.BLOCK_CHANGE.get()) {
                if (isStealthEnchanted(chestplate)) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.STEP.get()) {
                if (isStealthEnchanted(boots)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isStealthEnchanted(ItemStack stack) {
        return EnchantmentHelper.getItemEnchantmentLevel(
                EnchantmentRegistry.STEALTH.getHolder().get(), stack) > 0;
    }

}
