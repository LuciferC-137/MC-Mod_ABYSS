package wardentools.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public class ModTeleporter {

	public ModTeleporter() {}

	public static TeleportTransition getTeleportTransition(ServerLevel targetLevel,
														   Entity entity, Vec3 pos) {
		return new TeleportTransition(targetLevel, pos, Vec3.ZERO,
				entity.getYRot(), entity.getXRot(), TeleportTransition.PLAY_PORTAL_SOUND);
	}

	public static BlockPos findValidSpawn(ServerLevel level, BlockPos targetPos, boolean findAncientCity){
		int maxTries = 300;
		if (!findAncientCity){
			BlockPos destinationPos = targetPos;
			int tries = 0;
			while ((level.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
					!level.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
					(level.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR) &&
					!level.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < maxTries)) {
				destinationPos = destinationPos.above(2);
				tries++;
			}
			return tries==maxTries ? targetPos : destinationPos.above();
		} else {
			BlockPos destinationPos = new BlockPos(targetPos.getX(), -60, targetPos.getZ());
			int tries = 0;
			while (!(level.getBlockState(destinationPos.above(tries)).is(Blocks.REINFORCED_DEEPSLATE))
					&& (tries < maxTries)){
				tries++;
			}
			return tries==maxTries ? targetPos
					: inFrontOfAncientPortal(level, destinationPos.above(tries + 1));
		}
	}

	private static BlockPos inFrontOfAncientPortal(ServerLevel level, BlockPos targetPos){
		int random1 = level.getRandom().nextBoolean() ? -2 : 2;
		int random2 = level.getRandom().nextBoolean() ? -2 : 2;
		return targetPos.offset(random1, -1, random2);
	}
}
