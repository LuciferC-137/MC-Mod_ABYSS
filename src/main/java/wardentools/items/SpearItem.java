package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SpearItem extends Item {

    public SpearItem(Item.Properties properties, float attackDamage, float attackSpeed) {
        this(ToolMaterials.RADIANCE_CRISTAL, attackDamage, attackSpeed, properties);
    }

    public SpearItem(ToolMaterial material, float attackDamage, float attackSpeed,
                      Item.Properties properties) {
        super(material.applySwordProperties(properties, attackDamage, attackSpeed));
    }


    @Override
    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level,
                                  @NotNull BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity user) {
        stack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
        if (user instanceof Player player) {
            if (!player.isCreative() && !player.isSpectator()) healUser(user, 0.25F);
        } else healUser(user, 0.25F);
        return true;
    }

    private void healUser(@NotNull LivingEntity user, float heal) {
        user.heal(heal);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.SPEAR;
    }
}
