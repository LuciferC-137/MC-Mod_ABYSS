package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import wardentools.blockentity.RadianceCatalystBlockEntity;

public class RadianceCatalystItem extends BlockItem {
    public RadianceCatalystItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        if (result.consumesAction()) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            ItemStack stack = context.getItemInHand();
            BlockItem.updateCustomBlockEntityTag(level, context.getPlayer(), pos, stack);
            RadianceCatalystBlockEntity blockEntity = (RadianceCatalystBlockEntity) level.getBlockEntity(pos);
            if (stack.getTagElement("Energy")!=null) {
            	blockEntity.setEnergy(stack.getTagElement("Energy").getInt("Energy"));
            } else {
            	blockEntity.setEnergy(0);
            }
            
        }
        return result;
    }
}
