package wardentools.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import wardentools.AbyssConfig;
import wardentools.ModMain;
import wardentools.items.armors.ModMaterials;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigEvent {

    @SubscribeEvent
    public static void onConfigLoading(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == AbyssConfig.COMMON.SPEC) {
            ModMaterials.refreshArmorProtection();
        }
    }

    @SubscribeEvent
    public static void onConfigReloading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == AbyssConfig.COMMON.SPEC) {
            ModMaterials.refreshArmorProtection();
        }
    }
}
