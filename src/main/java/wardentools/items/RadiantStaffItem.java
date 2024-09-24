package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
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
            context.getItemInHand()
                    .hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            BlockPos abovePos = clickedPos.above();
            if (level instanceof ServerLevel serverLevel) {
                ConfiguredFeature<?, ?> portal
                        = new ConfiguredFeature<>(new PlaceAbyssPortal(NoneFeatureConfiguration.CODEC),
                        new NoneFeatureConfiguration());
                portal.place(serverLevel, serverLevel.getChunkSource().getGenerator(),
                        serverLevel.random, abovePos);
                ModCriteriaTriggers.ABYSS_PORTAL_OPEN.trigger((ServerPlayer)player);
                }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
	
	@Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ItemRegistry.RADIANCE_FRAGMENT.get();
    }

}


