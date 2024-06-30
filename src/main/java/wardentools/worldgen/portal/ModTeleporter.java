package wardentools.worldgen.portal;

import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;

public class ModTeleporter implements ITeleporter {
	
	    public static BlockPos thisPos = BlockPos.ZERO;
	    public static boolean insideDimension = true;

	    public ModTeleporter(BlockPos pos, boolean insideDim) {
	        thisPos = pos;
	        insideDimension = insideDim;
	    }

	    @Override
	    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
	                              float yaw, Function<Boolean, Entity> repositionEntity) {
	        entity = repositionEntity.apply(false);
	        int y = 61;

	        if (!insideDimension) {
	            y = thisPos.getY();
	        }

	        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

	        int tries = 0;
	        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
	                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
	                (destinationWorld.getBlockState(destinationPos.above()).getBlock()  != Blocks.AIR) &&
	                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
	            destinationPos = destinationPos.above(2);
	            tries++;
	        }

	        entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

	        return entity;
	    }

}
