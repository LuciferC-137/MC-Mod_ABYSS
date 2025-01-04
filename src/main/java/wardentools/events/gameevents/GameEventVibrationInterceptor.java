package wardentools.events.gameevents;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
            if (event.getVanillaEvent() == GameEvent.HIT_GROUND) {
                ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
                if (leggings.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.BLOCK_PLACE
                    || event.getVanillaEvent() == GameEvent.BLOCK_DESTROY
                    || event.getVanillaEvent() == GameEvent.BLOCK_CHANGE) {
                ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                if (chestplate.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                    event.setCanceled(true);
                }
            } else if (event.getVanillaEvent() == GameEvent.STEP) {
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                if (boots.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                    event.setCanceled(true);
                }
            }
        }
    }

}
