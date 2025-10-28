package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.items.utils.ItemUtils;
import wardentools.misc.Crystal;
import wardentools.worldgen.structure.StructureUtils;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalResonatorItem extends Item {
    public static final String NBT_TARGET_X = "target_x";
    public static final String NBT_TARGET_Y = "target_y";
    public static final String NBT_TARGET_Z = "target_z";
    public static final String NBT_CRYSTAL_TYPE = "crystal_type";

    public CrystalResonatorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        Crystal crystal;
        if (ItemUtils.customTag(stack).contains(NBT_CRYSTAL_TYPE)) {
            crystal = getCrystal(stack);
        } else {
            crystal = Crystal.getDefault();
        }
        components.add(Component
                .translatable("tooltip.wardentools.crystal_resonator.infused")
                .append(Component.translatable("tooltip.wardentools.crystal_resonator."
                                + crystal.getSerializedName())
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(crystal.getColor()))))
        );
        super.appendHoverText(stack, context, components, flag);
    }

    public static @Nullable GlobalPos getTargetPosition(Level level, ItemStack stack) {
        CompoundTag tag = ItemUtils.customTag(stack);
        if (tag.contains(NBT_TARGET_X) && tag.contains(NBT_TARGET_Y)
                && tag.contains(NBT_TARGET_Z)) {
            int x = tag.getInt(NBT_TARGET_X);
            int y = tag.getInt(NBT_TARGET_Y);
            int z = tag.getInt(NBT_TARGET_Z);
            return GlobalPos.of(level.dimension(), new BlockPos(x, y, z));
        }
        return null;
    }

    public static @Nullable BlockPos getNearestTemplePos(ServerLevel level,
                                                         Crystal crystal, BlockPos pos) {
        return StructureUtils.findNearestStructure(level, crystal.getTempleKey(), pos);
    }

    public static Crystal getCrystal(ItemStack stack) {
        CompoundTag tag = ItemUtils.customTag(stack);
        if (!tag.contains(NBT_CRYSTAL_TYPE)) {
            return Crystal.getDefault();
        }
        int index = tag.getInt(NBT_CRYSTAL_TYPE);
        return Crystal.fromIndex(index);
    }

    public static int getColor(ItemStack stack, int index) {
        if (index == 0) return 0xFFFFFFFF;
        if (index == 1) return getCrystal(stack).getColorARGB();
        return -1;
    }

    public static void setCrystal(ItemStack stack, Crystal crystal) {
        CompoundTag tag = ItemUtils.customTag(stack);
        tag.putInt(NBT_CRYSTAL_TYPE, crystal.getIndex());
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
                              @NotNull Entity entity, int i, boolean b) {
        if ((int)level.getGameTime() % 100 != 0) return; // Check every 5 seconds
        if (level instanceof ServerLevel serverLevel) {
            CompoundTag tag = ItemUtils.customTag(stack);
            Crystal crystal = getCrystal(stack);
            BlockPos targetPos = getNearestTemplePos(serverLevel, crystal, entity.blockPosition());
            if (targetPos == null) {
                tag.putInt(NBT_TARGET_X, 0);
                tag.putInt(NBT_TARGET_Y, 0);
                tag.putInt(NBT_TARGET_Z, 0);
            } else {
                tag.putInt(NBT_TARGET_X, targetPos.getX());
                tag.putInt(NBT_TARGET_Y, targetPos.getY());
                tag.putInt(NBT_TARGET_Z, targetPos.getZ());
            }
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

            if (entity instanceof ServerPlayer player) {
                player.getInventory().setChanged();
            }
        }
    }
}
