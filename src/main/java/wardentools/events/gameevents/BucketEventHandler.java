package wardentools.events.gameevents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class BucketEventHandler {

    @SubscribeEvent
    public static void onFillBucket(UseItemOnBlockEvent event) {
        // Cancel event if the targeted fluid is LiquidCorruption with a bucket in hand
        Level level = event.getLevel();
        BlockPos pos = event.getUseOnContext().getClickedPos();
        BlockState state = level.getBlockState(pos);
        ItemStack item = event.getItemStack();
        if (state.getBlock() == BlockRegistry.LIQUID_CORRUPTION_BLOCK.get() && item.is(Tags.Items.BUCKETS)) {
            event.setCanceled(true);
        }
    }
}
