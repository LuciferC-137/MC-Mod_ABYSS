package wardentools.playerdata;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import wardentools.ModMain;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class AttachCapabilities {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ModDataAttachments.COMPLETED_TASKS)) {
            event.getEntity().getData(ModDataAttachments.COMPLETED_TASKS)
                    .copy(event.getOriginal().getData(ModDataAttachments.COMPLETED_TASKS));
        }
        if (event.isWasDeath() && event.getOriginal().hasData(ModDataAttachments.KNOWN_WIND_WHISPERS)) {
            event.getEntity().getData(ModDataAttachments.KNOWN_WIND_WHISPERS)
                    .copy(event.getOriginal().getData(ModDataAttachments.KNOWN_WIND_WHISPERS));
        }
    }

}
