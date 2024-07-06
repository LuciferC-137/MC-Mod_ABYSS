package wardentools.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.effects.WindWhisper;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WindWhisperer extends Item {
	
	private Player currentPlayer;
    private WindWhisper windWhisper;
    private RandomSource rand;

	public WindWhisperer(Properties prop) {
		super(prop);
	    this.windWhisper = new WindWhisper();
	    this.rand = RandomSource.create();
	    MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		this.currentPlayer = event.player;
        if (event.phase == TickEvent.Phase.END) {
            if (event.player.level().isClientSide) {
                if (isInHotbar(event.player)) {
                    if (this.rand.nextInt(10000) == 1) {
                    	Minecraft minecraft = Minecraft.getInstance();
                        LanguageManager languageManager = minecraft.getLanguageManager();
                        String currentLanguage = languageManager.getSelected();
                        if ("fr_fr".equals(currentLanguage)) {
                            sendMessage(event.player, "<Vent> " + this.windWhisper.getWhisperFr());
                        }
                        else {
                            sendMessage(event.player, "<Wind> " + this.windWhisper.getWhisperEn());
                        }
                    }
                }
            }
        }
    }

    private static boolean isInHotbar(Player player) {
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i).getItem() instanceof WindWhisperer) {
                return true;
            }
        }
        return false;
    }
    
    private static void sendMessage(Player player, String message) {
        MutableComponent chatComponent = Component.literal(message);
        player.sendSystemMessage(chatComponent);
    }
    
    @Override
    public boolean isFoil(ItemStack itemStack) {
        return currentPlayer != null && isInHotbar(this.currentPlayer);
    }
}
