package wardentools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.CustomDamageType;

import java.util.List;

public class ScytheItem extends Item {

    public ScytheItem(Item.Properties properties, float attackDamage, float attackSpeed) {
        this(ToolMaterials.DEEPCRISTAL, attackDamage, attackSpeed, properties);
    }

    public ScytheItem(ToolMaterial material, float attackDamage, float attackSpeed,
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
            if (!player.isCreative() && !player.isSpectator()) hurtUser(user, 0.25F);
        } else hurtUser(user, 0.25F);
        return true;
    }

    private void hurtUser(@NotNull LivingEntity user, float damage) {
        Holder<DamageType> corruptedDamageTypeHolder = user.level().registryAccess()
                .get(Registries.DAMAGE_TYPE).get().get()
                .wrapAsHolder(CustomDamageType.CORRUPTED_KEY.getOrThrow(user.level()).get());
        user.hurt(new DamageSource(corruptedDamageTypeHolder), damage);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}
