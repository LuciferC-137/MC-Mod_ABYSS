package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.structure.ModStructures;
import wardentools.worldgen.structure.StructureUtils;

public class AncientCitadelMap extends Item {

    public AncientCitadelMap(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level,
                                                           Player player,
                                                           @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            BlockPos playerPos = player.blockPosition();

            BlockPos structurePos = StructureUtils
                    .findNearestStructure(serverLevel, ModStructures.ANCIENT_CITADEL, playerPos);
            if (structurePos != null) {
                ItemStack mapStack = MapItem.create(serverLevel,
                        structurePos.getX(), structurePos.getZ(),
                        (byte) 2, true, true);
                MapItem.renderBiomePreviewMap(serverLevel, mapStack);
                MapItemSavedData.addTargetDecoration(mapStack, structurePos,
                        "+", MapDecorationTypes.RED_X);

                player.setItemInHand(hand, mapStack);

                return InteractionResultHolder.success(itemstack);
            }
        }

        return InteractionResultHolder.fail(itemstack);
    }
}

