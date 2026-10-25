package wardentools.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.ProtectorEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ProtectorHeartItem extends Item {

	public ProtectorHeartItem(Properties prop) {
		super(prop);
	}
	
	public void setProtector(ItemStack stack, ProtectorEntity protector) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putUUID("ProtectorUUID", protector.getUUID());
		tag.putInt("ProtectorID", protector.getId());
    }

	public void saveHealth(ItemStack stack, ProtectorEntity protector){
		this.saveHealth(stack, protector.getHealth());
	}

	public void saveHealth(ItemStack stack, float health) {
		CompoundTag tag = stack.getOrCreateTag();
		tag.putFloat("ProtectorHealth", health);
	}
	
	public float readHealth(ItemStack stack) {
		CompoundTag tag = stack.getTag();
		if (tag == null) return 0.0f;
        if (tag.contains("ProtectorHealth")) {
            return tag.getFloat("ProtectorHealth");
        }
        return (float)ProtectorEntity.MAX_HEALTH;
    }
	
	public @Nullable UUID getProtectorUUID(ItemStack stack) {
		CompoundTag tag = stack.getTag();
		if (tag == null) return null;
        return tag.contains("ProtectorUUID") ? tag.getUUID("ProtectorUUID") : null;
    }

	public int getProtectorID(ItemStack stack){
		CompoundTag tag = stack.getTag();
		if (tag == null) return -1;
		return tag.contains("ProtectorID") ? tag.getInt("ProtectorID") : 0;
	}
	
	public String getTextHealth(ItemStack stack) {
		return(int)this.readHealth(stack) + "/" + (int)ProtectorEntity.MAX_HEALTH;
	}


	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
								@NotNull List<Component> components, @NotNull TooltipFlag flag) {
		super.appendHoverText(stack, level, components, flag);
		components.add(Component.literal(this.getTextHealth(stack)));
	}
}
