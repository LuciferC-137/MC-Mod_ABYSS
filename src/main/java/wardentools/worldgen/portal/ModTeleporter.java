package wardentools.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ModTeleporter implements ITeleporter {

	private final Vec3 targetPos;
	private final float yRot;
	private final float xRot;

	private ModTeleporter(Vec3 targetPos, float yRot, float xRot) {
		this.targetPos = targetPos;
		this.yRot = yRot;
		this.xRot = xRot;
	}

	public static ModTeleporter diveTo(ServerLevel targetWorld, Vec3 targetPos, float yRot, float xRot) {
		return new ModTeleporter(targetPos, yRot, xRot);
	}

	public static ModTeleporter diveTo(ServerLevel targetWorld, Vec3 targetPos) {
		return new ModTeleporter(targetPos, 0, 0);
	}

	public static ModTeleporter diveTo(ServerLevel targetWorld, Vec3 targetPos, Entity entity) {
		return new ModTeleporter(targetPos, entity.getYRot(), entity.getXRot());
	}

	public static ModTeleporter diveTo(ServerLevel targetWorld, Vec3 targetPos, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		return new ModTeleporter(targetPos, player.getYRot(), player.getXRot());
	}

	public static ModTeleporter diveSamePlace(ServerLevel targetWorld, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		BlockPos targetPos = findValidSpawn(targetWorld, player.blockPosition(), false);
		return new ModTeleporter(targetPos.getCenter(), player.getYRot(), player.getXRot());
	}

	public static ModTeleporter diveToAncientCity(ServerLevel targetWorld, BlockPos targetPos, ServerPlayer player) {
		player.setCamera(player);
		player.stopRiding();
		BlockPos validSpawn = findValidSpawn(targetWorld, targetPos, true);
		return new ModTeleporter(validSpawn.getCenter(), player.getYRot(), player.getXRot());
	}

	public static ModTeleporter diveToAncientCity(ServerLevel targetWorld, BlockPos targetPos, Entity entity) {
		entity.stopRiding();
		BlockPos validSpawn = findValidSpawn(targetWorld, targetPos, true);
		return new ModTeleporter(validSpawn.getCenter(), entity.getYRot(), entity.getXRot());
	}

	@Override
	@Nullable
	public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld,
							Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		return new PortalInfo(this.targetPos, Vec3.ZERO, this.yRot, this.xRot);
	}

	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw,
						  Function<Boolean, Entity> repositionEntity) {
		// We do custom placement and do not want vanilla portal generation/repositioning.
		return repositionEntity.apply(false);
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
