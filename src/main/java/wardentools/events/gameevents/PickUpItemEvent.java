package wardentools.events.gameevents;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import wardentools.ModMain;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.misc.Crystal;

import java.util.List;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class PickUpItemEvent {

	@SubscribeEvent
    public static void onItemPickUp(ItemEntityPickupEvent.Post event) {
        ItemStack stack = event.getItemEntity().getItem();
        if (stack.isEmpty()) return;
        if (Crystal.isCrystalJewel(stack.getItem())) {
            Level level = event.getItemEntity().level();
            if (!level.isClientSide) {
                List<CrystalGolemEntity> list = level.getEntitiesOfClass(CrystalGolemEntity.class,
                        event.getItemEntity().getBoundingBox().inflate(30.0D, 10.0D, 30.0D));
                for (CrystalGolemEntity golem : list) {
                    golem.choseViolence();
                }
            }
        }
    }
}
