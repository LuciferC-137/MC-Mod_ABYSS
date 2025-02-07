package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionImplosion;
import wardentools.tags.ModTags;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

import java.util.Set;

public class AbyssDiverItem extends Item {

	public AbyssDiverItem(Properties prop) {
        super(prop);
	}
		
	@Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player==null) {return InteractionResult.PASS;}
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedBlockState = level.getBlockState(clickedPos);

        if (clickedBlockState.is(ModTags.Blocks.ABYSS_TELEPORTABLE)) {
        	context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        	if (player.level() instanceof ServerLevel) {
                PacketHandler.sendToAllClient(
                        new ParticleContagionImplosion(player.getPosition(0.1f)
                                .add(0, 1, 0)));
                teleportPlayer(player);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    private static void teleportPlayer(Player player){
        ServerLevel serverLevel = (ServerLevel)player.level();
        if (player instanceof ServerPlayer) {
            MinecraftServer minecraftserver = serverLevel.getServer();
            ResourceKey<Level> resourcekey = ModDimensions.ABYSS_LEVEL_KEY;
            if (player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) resourcekey = Level.OVERWORLD;
            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                BlockPos targetPos = ModTeleporter.findValidSpawn(portalDimension,
                        player.blockPosition(), false);
                player.teleportTo(portalDimension, targetPos.getX() + 0.5D,
                        targetPos.getY() + 1.5D, targetPos.getZ() + 0.5D,
                        Set.of(), player.getYRot(), player.getXRot(), false);
            }
        }
    }

}


