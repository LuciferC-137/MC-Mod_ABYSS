package wardentools.events;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.items.armors.ArmorRegistry;
import wardentools.items.armors.ConfigurableDefenseItem;
import wardentools.items.armors.ModMaterials;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class ServerEvent {

    @SubscribeEvent
    public static void serverStart(ServerAboutToStartEvent event) {
        for (ConfigurableDefenseItem armorItem : ArmorRegistry.getDeepcristalArmorSet()) {
            armorItem.updateDefense(ModMaterials.getAbyssArmorProtection(armorItem.getType()));
        }
        for (ConfigurableDefenseItem armorItem : ArmorRegistry.getRadianceCristalArmorSet()) {
            armorItem.updateDefense(ModMaterials.getAbyssArmorProtection(armorItem.getType()));
        }
    }
}
