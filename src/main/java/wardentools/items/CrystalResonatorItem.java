package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
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
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.misc.Crystal;
import wardentools.worldgen.structure.StructureUtils;

import java.util.List;

public class CrystalResonatorItem extends Item {

    public static final String NBT_TARGET_X = "target_x";
    public static final String NBT_TARGET_Y = "target_y";
    public static final String NBT_TARGET_Z = "target_z";
    public static final String NBT_CRYSTAL_TYPE = "crystal_type";

    public CrystalResonatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level,
                                @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        Crystal crystal = getCrystal(stack);
        components.add(Component.translatable(
                "tooltip.wardentools.crystal_resonator.infused").append(Component.translatable(
                                "tooltip.wardentools.crystal_resonator."
                                        + crystal.getSerializedName()).setStyle(
                                Style.EMPTY.withColor(TextColor.fromRgb(crystal.getColor()))))
        );
        super.appendHoverText(stack, level, components, flag);
    }

    public static @Nullable GlobalPos getTargetPosition(Level level, ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains(NBT_TARGET_X) || !tag.contains(NBT_TARGET_Y) || !tag.contains(NBT_TARGET_Z)) {
            return null;
        }

        int x = tag.getInt(NBT_TARGET_X);
        int y = tag.getInt(NBT_TARGET_Y);
        int z = tag.getInt(NBT_TARGET_Z);

        return GlobalPos.of(level.dimension(), new BlockPos(x, y, z));
    }

    public static @Nullable BlockPos getNearestTemplePos(ServerLevel level, Crystal crystal, BlockPos pos) {
        return StructureUtils.findNearestStructure(level, crystal.getTempleKey(), pos);
    }

    public static Crystal getCrystal(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains(NBT_CRYSTAL_TYPE)) {
            return Crystal.getDefault();
        }

        return Crystal.fromIndex(tag.getInt(NBT_CRYSTAL_TYPE));
    }

    public static int getColor(ItemStack stack, int index) {
        if (index == 0) return 0xFFFFFFFF;
        if (index == 1) return getCrystal(stack).getColorARGB();
        return -1;
    }

    public static void setCrystal(ItemStack stack, Crystal crystal) {
        stack.getOrCreateTag().putInt(
                NBT_CRYSTAL_TYPE,
                crystal.getIndex()
        );
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
            @NotNull Entity entity, int slot, boolean selected) {
        if (level.getGameTime() % 100 != 0) return;
        if (!(level instanceof ServerLevel serverLevel)) return;

        Crystal crystal = getCrystal(stack);

        BlockPos targetPos = getNearestTemplePos(serverLevel, crystal, entity.blockPosition());

        CompoundTag tag = stack.getOrCreateTag();

        if (targetPos == null) {
            tag.remove(NBT_TARGET_X);
            tag.remove(NBT_TARGET_Y);
            tag.remove(NBT_TARGET_Z);
        } else {
            tag.putInt(NBT_TARGET_X, targetPos.getX());
            tag.putInt(NBT_TARGET_Y, targetPos.getY());
            tag.putInt(NBT_TARGET_Z, targetPos.getZ());
        }

        if (entity instanceof ServerPlayer player) {
            player.getInventory().setChanged();
        }
    }
}
