package wardentools.worldgen.portal;

import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

public class ModTeleporter implements ITeleporter {
	private final BlockPos targetPos;
	private boolean findAncientCity = false;

	public ModTeleporter(BlockPos pos) {
		this.targetPos = pos;
	}

	public ModTeleporter(BlockPos pos, boolean findAncientCity){
		this.targetPos = pos;
		this.findAncientCity = findAncientCity;
	}

	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
							  float yaw, Function<Boolean, Entity> repositionEntity) {
		entity = repositionEntity.apply(false);

		BlockPos destinationPos = findValidSpawn(destinationWorld, this.targetPos);
		entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());
		return entity;
	}

	@Override
	public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld,
											  Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		BlockPos destination = findValidSpawn(destWorld, this.targetPos);
		return destination == null ? ITeleporter.super.getPortalInfo(entity, destWorld, defaultPortalInfo) :
				new PortalInfo(new Vec3(
						destination.getX() + 0.5D, destination.getY(), destination.getZ() + 0.5D),
						entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
	}

	private BlockPos findValidSpawn(ServerLevel level, BlockPos targetPos){
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
			return tries==maxTries ? targetPos : destinationPos;
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

	private BlockPos inFrontOfAncientPortal(ServerLevel level, BlockPos targetPos){
		int random1 = level.getRandom().nextBoolean() ? -1 : 1;
		int random2 = level.getRandom().nextBoolean() ? -1 : 1;
		return targetPos.offset(random1, -1, random2);
	}
}
