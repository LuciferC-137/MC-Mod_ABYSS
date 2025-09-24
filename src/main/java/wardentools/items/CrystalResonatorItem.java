package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.items.utils.ItemUtils;
import wardentools.misc.Crystal;
import wardentools.worldgen.structure.ModStructures;
import wardentools.worldgen.structure.StructureUtils;

import javax.annotation.Nullable;

public class CrystalResonatorItem extends Item {
    public static final String NBT_TARGET_X = "target_x";
    public static final String NBT_TARGET_Y = "target_y";
    public static final String NBT_TARGET_Z = "target_z";
    public static final String NBT_CRYSTAL_TYPE = "crystal_type";

    public CrystalResonatorItem(Item.Properties properties) {
        super(properties);
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

    public static @Nullable BlockPos getNearestTemplePos(ServerLevel level, BlockPos pos) {
        return StructureUtils.findNearestStructure(level,
                ModStructures.SURFACE_ANCIENT_CITY, pos);
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

    public void setCrystal(ItemStack stack, Crystal crystal) {
        CompoundTag tag = ItemUtils.customTag(stack);
        tag.putInt(NBT_CRYSTAL_TYPE, crystal.getIndex());
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level,
                                                           @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        this.setCrystal(stack, Crystal.fromIndex((getCrystal(stack).getIndex() + 1) % 6));
        return super.use(level, player, hand);
    }

    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
                              @NotNull Entity entity, int i, boolean b) {
        if ((int)level.getGameTime() % 100 != 0) return; // Check every 5 seconds
        if (level instanceof ServerLevel serverLevel) {
            CompoundTag tag = ItemUtils.customTag(stack);
            BlockPos targetPos = getNearestTemplePos(serverLevel, entity.blockPosition());
            if (targetPos == null) return;
            tag.putInt(NBT_TARGET_X, targetPos.getX());
            tag.putInt(NBT_TARGET_Y, targetPos.getY());
            tag.putInt(NBT_TARGET_Z, targetPos.getZ());
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

            if (entity instanceof ServerPlayer player) {
                player.getInventory().setChanged();
            }
        }
    }
}
