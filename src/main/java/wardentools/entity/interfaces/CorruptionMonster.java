package wardentools.entity.interfaces;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.CustomDamageType;

public class CorruptionMonster extends Monster {

    protected CorruptionMonster(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float v) {
        if (source.getMsgId().equals("corrupted")) {
            return false;
        }
        return super.hurt(source, v);
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity living) {
        return super.canAttack(living) && !(living instanceof CorruptionMonster);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        if (target instanceof LivingEntity) {
            f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity)target).getMobType());
            f1 += (float)EnchantmentHelper.getKnockbackBonus(this);
        }
        int i = EnchantmentHelper.getFireAspect(this);
        if (i > 0) {
            target.setSecondsOnFire(i * 4);
        }
        Holder<DamageType> corruptedDamageHolder
                = target.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);
        boolean flag = target.hurt(
                new DamageSource(corruptedDamageHolder, target, this, this.getPosition(1f)), f);
        if (flag) {
            if (f1 > 0.0F && target instanceof LivingEntity) {
                ((LivingEntity)target).knockback((double)(f1 * 0.5F),
                        (double) Mth.sin(this.getYRot() * ((float)Math.PI / 180F)),
                        (double)(-Mth.cos(this.getYRot() * ((float)Math.PI / 180F))));
                this.setDeltaMovement(this.getDeltaMovement()
                        .multiply(0.6D, 1.0D, 0.6D));
            }
            if (target instanceof Player player) {
                this.maybeDisableShield(player, this.getMainHandItem(),
                        player.isUsingItem() ? player.getUseItem() : ItemStack.EMPTY);
            }
            this.doEnchantDamageEffects(this, target);
            this.setLastHurtMob(target);
        }
        return flag;
    }

    private void maybeDisableShield(Player player, ItemStack stack, ItemStack stack1) {
        if (!stack.isEmpty() && !stack1.isEmpty() && stack.getItem()
                instanceof AxeItem && stack1.is(Items.SHIELD)) {
            float f = 0.25F + (float)EnchantmentHelper.getBlockEfficiency(this) * 0.05F;
            if (this.random.nextFloat() < f) {
                player.getCooldowns().addCooldown(Items.SHIELD, 100);
                this.level().broadcastEntityEvent(player, (byte)30);
            }
        }
    }
}
