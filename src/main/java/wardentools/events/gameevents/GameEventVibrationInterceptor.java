package wardentools.events.gameevents;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wardentools.ModMain;
import wardentools.items.enchantment.EnchantmentRegistry;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class GameEventVibrationInterceptor {

    @SubscribeEvent
    public static void onGameEvent(VanillaGameEvent event) {
        if (event.getCause() instanceof Player player) {
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (isStealthEnchanted(player, boots) && isStealthEnchanted(player, helmet)
                    && isStealthEnchanted(player, leggings) && isStealthEnchanted(player, chestplate)) {
                event.setCanceled(true);
                return;
            }
            if (event.getVanillaEvent() == GameEvent.HIT_GROUND) {
                if (isStealthEnchanted(player, leggings)) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.BLOCK_PLACE
                    || event.getVanillaEvent() == GameEvent.BLOCK_DESTROY
                    || event.getVanillaEvent() == GameEvent.BLOCK_CHANGE) {
                if (isStealthEnchanted(player, chestplate)) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.STEP) {
                if (isStealthEnchanted(player, boots)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isStealthEnchanted(Player player, ItemStack stack) {
        if (stack.isEmpty()) return false;
        return EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.STEALTH.get(), stack) > 0;
    }

}
