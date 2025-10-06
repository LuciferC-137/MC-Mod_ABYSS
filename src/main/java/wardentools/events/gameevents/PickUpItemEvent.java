package wardentools.events.gameevents;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.misc.Crystal;

import java.util.List;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class PickUpItemEvent {

	@SubscribeEvent
    public static void onItemPickUp(EntityItemPickupEvent event) {
        ItemStack stack = event.getItem().getItem();
        if (stack.isEmpty()) return;
        if (Crystal.isCrystalJewel(stack.getItem())) {
            Level level = event.getEntity().level();
            if (!level.isClientSide) {
                List<CrystalGolemEntity> list = level.getEntitiesOfClass(CrystalGolemEntity.class,
                        event.getItem().getBoundingBox().inflate(30.0D, 4.0D, 30.0D));
                for (CrystalGolemEntity golem : list) {
                    golem.choseViolence();
                }
            }
        }
    }
}
