package wardentools.playerdata;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AttachCapabilities {
    public static final String whisperDataId = "known_whispers";
    private static final ResourceLocation PLAYER_DATA_ID = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, whisperDataId);

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(KnownWhispersDataProvider.PLAYER_DATA).isPresent()) {
                event.addCapability(PLAYER_DATA_ID, new KnownWhispersDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }
        event.getOriginal().getCapability(KnownWhispersDataProvider.PLAYER_DATA).ifPresent(oldStore -> {
            event.getEntity().getCapability(KnownWhispersDataProvider.PLAYER_DATA).ifPresent(newStore -> {
                newStore.copy(oldStore.getAll());
            });
        });
    }

    @SubscribeEvent
    @SuppressWarnings("removal")
    public static void onRegisterCapability(RegisterCapabilitiesEvent event) {
        event.register(KnownWhispers.class);
    }

}
