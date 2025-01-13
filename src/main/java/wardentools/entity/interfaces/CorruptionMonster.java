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

    public boolean doHurtTarget(@NotNull Entity entity) {
        float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        if (entity instanceof LivingEntity) {
            f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), entity.getType());
            f1 += (float)EnchantmentHelper.getKnockbackBonus(this);
        }
        int i = EnchantmentHelper.getFireAspect(this);
        if (i > 0) {
            entity.igniteForSeconds(i * 4);
        }
        Holder<DamageType> corruptedDamageHolder
                = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);
        boolean flag = entity.hurt(new DamageSource(corruptedDamageHolder, entity, this), f);
        if (flag) {
            if (f1 > 0.0F && entity instanceof LivingEntity) {
                ((LivingEntity)entity).knockback((double)(f1 * 0.5F), (double)Mth.sin(this.getYRot() * 0.017453292F), (double)(-Mth.cos(this.getYRot() * 0.017453292F)));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6, 1.0, 0.6));
            }
            this.doEnchantDamageEffects(this, entity);
            this.setLastHurtMob(entity);
        }
        return flag;
    }
}
