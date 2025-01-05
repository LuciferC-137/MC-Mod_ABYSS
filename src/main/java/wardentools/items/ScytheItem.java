package wardentools.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.CustomDamageType;

public class ScytheItem extends Item implements Vanishable {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ScytheItem(int attackDamage, float attackSpeed, Item.Properties properties) {
        super(properties);
        this.attackDamage = (float)attackDamage;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,
                "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID,
                "Weapon modifier", (double)attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, @NotNull ItemStack stack1) {
        return stack.is(ItemRegistry.DEEPINGOTS.get()) || stack1.is(ItemRegistry.DEEPINGOTS.get());
    }

    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {
        return true;
    }

    public float getDamage() {
        return this.attackDamage;
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level,
                                  @NotNull BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (book.getAllEnchantments().containsKey(Enchantments.SWEEPING_EDGE)) return true;
        return super.isBookEnchantable(stack, book);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.SWEEPING_EDGE) return true;
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, BlockState blockState) {
        if (blockState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return blockState.is(BlockTags.MINEABLE_WITH_HOE) ? 1.5F : 1.0F;
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity user) {
        stack.hurtAndBreak(1, user, (livingEntity) -> {
            livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        if (user instanceof Player player) {
            if (!player.isCreative() && !player.isSpectator()) hurtUser(user, 0.25F);
        } else hurtUser(user, 0.25F);
        return true;
    }

    private void hurtUser(@NotNull LivingEntity user, float damage) {
        Holder<DamageType> corruptedDamageTypeHolder = user.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);
        user.hurt(new DamageSource(corruptedDamageTypeHolder), damage);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, @NotNull Level level, BlockState blockState,
                             @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        if (blockState.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(2, entity, (livingEntity) -> {
                livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(
            @NotNull EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}
