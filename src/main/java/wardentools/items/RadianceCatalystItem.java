package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.RadianceCatalystBlockEntity;

public class RadianceCatalystItem extends BlockItem {
    public RadianceCatalystItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull InteractionResult place(@NotNull BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        if (result.consumesAction()) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            ItemStack stack = context.getItemInHand();
            BlockItem.updateCustomBlockEntityTag(level, context.getPlayer(), pos, stack);
            RadianceCatalystBlockEntity blockEntity = (RadianceCatalystBlockEntity) level.getBlockEntity(pos);
            CompoundTag tag = stack.getTag();
            if (blockEntity == null) return result;
            if (tag == null) {blockEntity.setEnergy(0); return result;}
            if (tag.contains("Energy")) {
            	blockEntity.setEnergy(tag.getInt("Energy"));
            } else {
            	blockEntity.setEnergy(0);
            }
            
        }
        return result;
    }
}
