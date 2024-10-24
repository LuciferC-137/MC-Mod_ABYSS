package wardentools.events.gameevents;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ShadowEntity;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class SpawnShadowOnEntityDeath {

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof ShadowEntity) && (entity instanceof LivingEntity)) {
            ShadowEntity shadow = new ShadowEntity(ModEntities.SHADOW.get(), entity.level());
            shadow.copyPosition(entity);
            shadow.setHealth(shadow.getMaxHealth());
            if (entity instanceof LivingEntity living) shadow.setMimicEntity(living);
            entity.level().addFreshEntity(shadow);
        }
    }
}
