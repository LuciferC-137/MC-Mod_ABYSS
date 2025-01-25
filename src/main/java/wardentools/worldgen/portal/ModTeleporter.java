package wardentools.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class ModTeleporter {

	public ModTeleporter() {}

	public static DimensionTransition diveTo(ServerLevel targetWorld, Vec3 targetPos, float yRot, float xRot) {
		return new DimensionTransition(targetWorld, targetPos, Vec3.ZERO,
				yRot, xRot, DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveTo(ServerLevel targetWorld, Vec3 targetPos) {
		return new DimensionTransition(targetWorld, targetPos, Vec3.ZERO,
				0, 0, DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveTo(ServerLevel targetWorld, Vec3 targetPos, Entity entity) {
		return new DimensionTransition(targetWorld, targetPos, Vec3.ZERO,
				entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveTo(ServerLevel targetWorld, Vec3 targetPos, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		return new DimensionTransition(targetWorld, targetPos, Vec3.ZERO,
				player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveSamePlace(ServerLevel targetWorld, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		BlockPos targetPos = findValidSpawn(targetWorld, player.blockPosition(), false);
		return new DimensionTransition(targetWorld, targetPos.getCenter(), Vec3.ZERO,
				player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveToAncientCity(ServerLevel targetWorld, BlockPos targetPos, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		BlockPos validSpawn = findValidSpawn(targetWorld, targetPos, true);
		return new DimensionTransition(targetWorld, validSpawn.getCenter(), Vec3.ZERO,
				player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING);
	}

	public static DimensionTransition diveToAncientCity(ServerLevel targetWorld, BlockPos targetPos, Entity entity) {
		entity.stopRiding();
		BlockPos validSpawn = findValidSpawn(targetWorld, targetPos, true);
		return new DimensionTransition(targetWorld, validSpawn.getCenter(), Vec3.ZERO,
				entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING);
	}

	private static BlockPos findValidSpawn(ServerLevel level, BlockPos targetPos, boolean findAncientCity){
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
		int random1 = level.getRandom().nextBoolean() ? -1 : 1;
		int random2 = level.getRandom().nextBoolean() ? -1 : 1;
		return targetPos.offset(random1, -1, random2);
	}
}
