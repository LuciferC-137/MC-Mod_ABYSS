package wardentools.events.gameevents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleWardenDeathPacket;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class WardenDeathEvent {
	
	@SubscribeEvent
	public static void wardenDeath(LivingDeathEvent event) {
        if ((event.getEntity() instanceof Warden)) {
        	Level level = event.getEntity().level();
        	BlockPos pos = event.getEntity().blockPosition();
   
        	if (!level.isClientSide) {
        		        	
        		PacketHandler.sendToAllClient(new ParticleWardenDeathPacket(pos));
        	        		   	        		
        		BlockPos deathPos = event.getEntity().blockPosition();
        		int radius = 6;
        		int numberOfBlocks = 9;
        		int numberOfTry = 100;
        		int placed = 0;
        		int tried = 0;
        		
        		while ((tried<numberOfTry)&&(placed<numberOfBlocks)) {
        			tried = tried + 1;
                    int offsetX = level.random.nextInt(radius * 2 + 1) - radius;
                    int offsetY = level.random.nextInt(radius * 2 + 1) - radius;
                    int offsetZ = level.random.nextInt(radius * 2 + 1) - radius;

                    BlockPos randomPos = deathPos.offset(offsetX, offsetY, offsetZ);
                    
                    if (level.getBlockState(randomPos).isAir()) {
                    	 placed = placed + placeDeepCristal(level, randomPos);
                    	
                    		}
                    	}
                    }
        		}
                    
        	}
	private static int placeDeepCristal(Level level, BlockPos pos) {
        BlockState deepCristalState = BlockRegistry.DEEP_CRISTAL.get().defaultBlockState();
        if (level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.DOWN), 3);
            return 1;
        }
        else if (level.getBlockState(pos.north()).isFaceSturdy(level, pos.north(), Direction.SOUTH)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.SOUTH), 3);
            return 1;
        }
        else if (level.getBlockState(pos.south()).isFaceSturdy(level, pos.south(), Direction.NORTH)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.NORTH), 3);
            return 1;
        }
        else if (level.getBlockState(pos.west()).isFaceSturdy(level, pos.west(), Direction.EAST)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.EAST), 3);
            return 1;
        }
        else if (level.getBlockState(pos.east()).isFaceSturdy(level, pos.east(), Direction.WEST)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.WEST), 3);
            return 1;
        }
        else if (level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
            level.setBlock(pos, deepCristalState.setValue(BlockStateProperties.FACING, Direction.UP), 3);
            return 1;
        }
        return 0;
    }
        	
}


