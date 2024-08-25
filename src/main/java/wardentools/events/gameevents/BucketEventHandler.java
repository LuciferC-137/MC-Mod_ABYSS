package wardentools.events.gameevents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class BucketEventHandler {

    @SubscribeEvent
    public static void onFillBucket(FillBucketEvent event) {
        // Cancel event if the targeted fluid is LiquidCorruption
        Level level = event.getLevel();
        HitResult target = event.getTarget();
        if (target != null){
            Vec3 pos = target.getLocation();
            int x = ((int)pos.x()) - (pos.x() < 0 ? 1 : 0);
            int y = ((int)pos.y()) - (pos.y() < 0 ? 1 : 0);
            int z = ((int)pos.z()) - (pos.z() < 0 ? 1 : 0);
            BlockState state = level.getBlockState(new BlockPos(x, y, z));
            System.out.println(x + ", " + y + ", " + z);
            if (state.getBlock() == BlockRegistry.LIQUID_CORRUPTION_BLOCK.get()) {
                event.setCanceled(true);
            }
        }
    }
}
