package wardentools.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.WindWhisper;
import wardentools.worldgen.dimension.ModDimensions;

public class WindWhispererItem extends BlockItem {
	
	private Player currentPlayer;
    private final WindWhisper windWhisper;
    private final RandomSource rand;
	private static final int PROB_WHISPER = 5000;

	public WindWhispererItem(Block block, Properties prop) {
		super(block, prop);
	    this.windWhisper = new WindWhisper();
	    this.rand = RandomSource.create();
	    MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
							  @NotNull Entity entity, int slot, boolean selected) {
		if (entity instanceof Player player){
			this.currentPlayer = player;
		} else {
			this.currentPlayer = null;
		}
        super.inventoryTick(stack, level, entity, slot, selected);
        if (entity instanceof Player player) {
	        if (player.level().isClientSide()) {
	            if (isInHotbar(player) && isInAbyss(player)) {
	                if (this.rand.nextInt(PROB_WHISPER) == 1) {
	                	Minecraft minecraft = Minecraft.getInstance();
	                    LanguageManager languageManager = minecraft.getLanguageManager();
	                    String currentLanguage = languageManager.getSelected();
	                    if ("fr_fr".equals(currentLanguage)) {
	                        sendMessage(player, "<Vent> " + this.windWhisper.getWhisperFr());
	                    }
	                    else {
	                        sendMessage(player, "<Wind> " + this.windWhisper.getWhisperEn());
	                    }
					}
	            }
	        }
        }
        
        
	}

    private static boolean isInHotbar(Player player) {
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i).getItem() instanceof WindWhispererItem) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isInAbyss(Player player) {
    	if (player==null) return false;
    	return player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY;
    }
    
    private static void sendMessage(Player player, String message) {
        MutableComponent chatComponent = Component.literal(message);
        player.displayClientMessage(chatComponent, false);
    }
    
    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return currentPlayer != null && isInHotbar(this.currentPlayer) && isInAbyss(this.currentPlayer);
    }
}
