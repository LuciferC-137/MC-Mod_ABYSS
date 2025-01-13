package wardentools.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.ProtectorEntity;

public class ProtectorHeartItem extends Item {

	public ProtectorHeartItem(Properties prop) {
		super(prop);
	}
	
	public void setProtector(ItemStack stack, ProtectorEntity protector) {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("ProtectorUUID", protector.getUUID());
		tag.putInt("ProtectorID", protector.getId());
		stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

	public void saveHealth(ItemStack stack, ProtectorEntity protector){
		CompoundTag tag = new CompoundTag();
		tag.putFloat("ProtectorHealth", protector.getHealth());
		stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
	}
	
	public float readHealth(ItemStack stack) {
		CompoundTag tag = this.customTag(stack);
        if (tag.contains("ProtectorHealth")) {
            return tag.getFloat("ProtectorHealth");
        }
        return (float)ProtectorEntity.MAX_HEALTH;
    }
	
	public UUID getProtectorUUID(ItemStack stack) {
		CompoundTag tag = this.customTag(stack);
        return tag.contains("ProtectorUUID") ? tag.getUUID("ProtectorUUID") : null;
    }

	public int getProtectorID(ItemStack stack){
		CompoundTag tag = this.customTag(stack);
		return tag.contains("ProtectorID") ? tag.getInt("ProtectorID") : 0;
	}
	
	public String getTextHealth(ItemStack stack) {
		return(int)this.readHealth(stack) + "/" + (int)ProtectorEntity.MAX_HEALTH;
	}

	private CompoundTag customTag(ItemStack stack) {
		CustomData data = stack.get(DataComponents.CUSTOM_DATA);
		if (data == null) return new CompoundTag();
		return data.copyTag();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext ctx,
								@NotNull List<Component> components, @NotNull TooltipFlag flag) {
		super.appendHoverText(stack, ctx, components, flag);
		components.add(Component.literal(this.getTextHealth(stack)));
	}
}
