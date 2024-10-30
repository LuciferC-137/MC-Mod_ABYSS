package wardentools.events.gameevents;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.ModMain;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class WardenTargetFilter {

	@SubscribeEvent
    public static void onLivingUpdate(LivingTickEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Warden warden) {
            Entity target = warden.getTarget();
            if (target instanceof DeepLurkerEntity || target instanceof ContagionIncarnationEntity) {
                warden.setTarget(null);
                warden.setAggressive(false);
                warden.setLastHurtByMob(null);
                makeWardenBurrow(warden);
            }
        }
    }
	
	private static void makeWardenBurrow(Warden warden) {
        Level level = warden.level();
        if (!level.isClientSide) {
        	warden.getBrain().stopAll((ServerLevel) level, warden);
        	warden.getBrain().clearMemories();
        }
    }
}
