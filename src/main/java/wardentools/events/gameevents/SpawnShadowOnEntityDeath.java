package wardentools.events.gameevents;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ShadowEntity;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class SpawnShadowOnEntityDeath {

    @SubscribeEvent
    public static void entityDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        String damageTypeId = event.getSource().getMsgId();
        if (!(entity instanceof ShadowEntity)
                && (entity instanceof LivingEntity)
                && (damageTypeId.equals("corrupted"))) {
            if (entity instanceof Player || entity instanceof Villager) {
                ShadowEntity shadow = new ShadowEntity(ModEntities.SHADOW.get(), entity.level());
                shadow.copyPosition(entity);
                shadow.setHealth(shadow.getMaxHealth());
                entity.level().addFreshEntity(shadow);
                shadow.setStasis(true);
            } else {
                ShadowEntity shadow = new ShadowEntity(ModEntities.SHADOW.get(), entity.level());
                shadow.copyPosition(entity);
                shadow.setHealth(shadow.getMaxHealth());
                if (entity instanceof LivingEntity living) shadow.setMimicEntity(living);
                entity.level().addFreshEntity(shadow);
            }
        }
    }
}
