package wardentools.events.gameevents;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wardentools.ModMain;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.misc.Crystal;

import java.util.List;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class PickUpItemEvent {

	@SubscribeEvent
    public static void onItemPickUp(PlayerEvent.ItemPickupEvent event) {
        ItemStack stack = event.getStack();
        if (stack.isEmpty()) return;
        if (Crystal.isCrystalJewel(stack.getItem())) {
            Level level = event.getEntity().level();
            if (!level.isClientSide) {
                List<CrystalGolemEntity> list = level.getEntitiesOfClass(CrystalGolemEntity.class,
                        event.getEntity().getBoundingBox().inflate(30.0D, 10.0D, 30.0D));
                for (CrystalGolemEntity golem : list) {
                    if (!golem.hasGrief()) {
                        golem.choseViolence();
                    }
                }
            }
        }
    }
}
