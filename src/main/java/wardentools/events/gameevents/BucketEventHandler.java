package wardentools.events.gameevents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class BucketEventHandler {

    @SubscribeEvent
    public static void onFillBucket(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack item = event.getItemStack();

        if (state.getBlock() == BlockRegistry.LIQUID_CORRUPTION_BLOCK.get()
                && item.is(Items.BUCKET)) {
            event.setUseItem(Event.Result.DENY);
            event.setUseBlock(Event.Result.DENY);
            event.setCanceled(true);
        }
    }
}
