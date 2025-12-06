package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.CustomDamageType;

import java.util.List;

public class ScytheItem extends Item {

    public ScytheItem(Item.Properties properties) {
        super(properties.component(DataComponents.TOOL, createToolProperties()));
    }

    private static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F),
                Tool.Rule.overrideSpeed(BlockTags.MINEABLE_WITH_HOE, 1.5F)),
                1.0F, 2);
    }

    public static ItemAttributeModifiers createAttributes(int attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                                (float)attackDamage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID,
                                attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, @NotNull ItemStack stack1) {
        return stack.is(ItemRegistry.DEEPINGOTS.get()) || stack1.is(ItemRegistry.DEEPINGOTS.get());
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level,
                                  @NotNull BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity user) {
        Holder<Enchantment> sweepingHolder =
                target.level().registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(Enchantments.SWEEPING_EDGE);

        int sweeping = stack.getEnchantmentLevel(sweepingHolder);

        if (sweeping > 0) {
            float extraDamage = 1.5F * sweeping;
            target.hurt(user.damageSources().playerAttack((Player) user), extraDamage);
        }
        if (user instanceof Player player) {
            if (!player.isCreative() && !player.isSpectator()) hurtUser(user, 0.25F);
        } else hurtUser(user, 0.25F);

        stack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
        return true;
    }

    private void hurtUser(@NotNull LivingEntity user, float damage) {
        Holder<DamageType> corruptedDamageTypeHolder = user.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);
        user.hurt(new DamageSource(corruptedDamageTypeHolder), damage);
    }

    @Override
    public int getEnchantmentValue(@NotNull ItemStack stack) {
        return 15;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean supportsEnchantment(@NotNull ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.is(Enchantments.SWEEPING_EDGE)
                || enchantment.is(Enchantments.MENDING)
                || enchantment.is(Enchantments.UNBREAKING)
                || enchantment.is(Enchantments.LOOTING);
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
        return false;
    }
}
