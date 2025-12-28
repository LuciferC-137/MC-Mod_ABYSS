package wardentools.entity.thryssaryn.community;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import wardentools.ModMain;


@EventBusSubscriber(modid = ModMain.MOD_ID)
public class CommunityTickHandler {

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        for (ServerLevel level : event.getServer().getAllLevels()) {
            CommunityData data = CommunityData.get(level);
            data.tickAll();
        }
    }
}

