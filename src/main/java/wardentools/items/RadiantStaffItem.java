package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceExplosion;
import wardentools.worldgen.features.custom.PlaceAbyssPortal;

public class RadiantStaffItem extends Item {

	public RadiantStaffItem(Properties prop) {
		super(prop);
	}

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player==null) {return InteractionResult.PASS;}
        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedBlockState = level.getBlockState(clickedPos);

        if (clickedBlockState.is(Blocks.REINFORCED_DEEPSLATE)) {
            BlockPos abovePos = clickedPos.above();
            if (!level.getBlockState(abovePos).isAir()) return InteractionResult.FAIL;
            if (level instanceof ServerLevel serverLevel) {
                ConfiguredFeature<?, ?> portal
                        = new ConfiguredFeature<>(new PlaceAbyssPortal(NoneFeatureConfiguration.CODEC),
                        new NoneFeatureConfiguration());
                if (portal.place(serverLevel, serverLevel.getChunkSource().getGenerator(),
                        serverLevel.random, abovePos)){
                    ModCriteriaTriggers.ABYSS_PORTAL_OPEN.trigger((ServerPlayer)player);
                    PacketHandler.sendToAllClient(new ParticleRadianceExplosion(context.getClickLocation()));
                    context.getItemInHand()
                            .hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }
}


