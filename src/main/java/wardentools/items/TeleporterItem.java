package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

public class TeleporterItem extends Item {

	public TeleporterItem(Properties prop) {
		super(prop);
	}
		
	@Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pPos = player.getOnPos();
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedBlockState = level.getBlockState(clickedPos);

        if (clickedBlockState.getBlock() == Blocks.SCULK) {
        	context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
        	if (player.level() instanceof ServerLevel serverlevel) {
                MinecraftServer minecraftserver = serverlevel.getServer();
                
                ResourceKey<Level> resourcekey = ModDimensions.ABYSS_LEVEL_KEY;
                if (player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
                	resourcekey = Level.OVERWORLD;
                }
                
                ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
                if (portalDimension != null && !player.isPassenger()) {
                    if(resourcekey == ModDimensions.ABYSS_LEVEL_KEY) {
                        player.changeDimension(portalDimension, new ModTeleporter(pPos, true));
                    } else {
                        player.changeDimension(portalDimension, new ModTeleporter(pPos, false));
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
	
	@Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ItemRegistry.PALE_FRAGMENT.get();
    }

}


