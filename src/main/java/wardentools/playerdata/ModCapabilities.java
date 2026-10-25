package wardentools.playerdata;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import wardentools.playerdata.serializables.CompletedTasks;
import wardentools.playerdata.serializables.KnownWindWhispers;


public class ModCapabilities {

    public static final Capability<CompletedTasks> COMPLETED_TASKS =
            CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<KnownWindWhispers> KNOWN_WIND_WHISPERS =
            CapabilityManager.get(new CapabilityToken<>() {});

    private ModCapabilities() {}
}
