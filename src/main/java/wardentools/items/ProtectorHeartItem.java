package wardentools.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.ProtectorEntity;

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
		CompoundTag tag = stack.getOrCreateTag();
		tag.putFloat("ProtectorHealth", protector.getHealth());
	}
	
	public float readHealth(ItemStack stack) {
		CompoundTag tag = stack.getTag();
		if (tag!=null){
			if (tag.contains("ProtectorHealth")) {
				return tag.getFloat("ProtectorHealth");
			}
			return (float)ProtectorEntity.MAX_HEALTH;
		}
		return (float)ProtectorEntity.MAX_HEALTH;
	}
	
	public UUID getProtectorUUID(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("ProtectorUUID") ? tag.getUUID("ProtectorUUID") : null;
    }

	public int getProtectorID(ItemStack stack){
		CompoundTag tag = stack.getTag();
		return tag != null && tag.contains("ProtectorID") ? tag.getInt("ProtectorID") : 0;
	}
	
	public String getTextHealth(ItemStack stack) {
		return(int)this.readHealth(stack) + "/" + (int)ProtectorEntity.MAX_HEALTH;
	}
	
	@Override
    public void appendHoverText(@NotNull ItemStack stack, Level level,
								@NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal(this.getTextHealth(stack)));
    }
}
