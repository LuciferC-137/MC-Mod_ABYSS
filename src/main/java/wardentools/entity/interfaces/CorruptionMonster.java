package wardentools.entity.interfaces;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
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
    public boolean hurtServer(@NotNull ServerLevel level, @NotNull DamageSource source, float v) {
        if (source.getMsgId().equals("corrupted")) {
            return false;
        }
        return super.hurtServer(level, source, v);
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity living) {
        return super.canAttack(living) && !(living instanceof CorruptionMonster);
    }


    @Override
    public boolean doHurtTarget(@NotNull ServerLevel level, Entity target) {
        float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        Level var5 = this.level();
        if (target.level().registryAccess().get(Registries.DAMAGE_TYPE).isEmpty()) return false;
        Holder<DamageType> corruptedDamageHolder
                = target.level().registryAccess().get(Registries.DAMAGE_TYPE).get().get()
                .wrapAsHolder(CustomDamageType.CORRUPTED_KEY.getOrThrow(level).get());
        DamageSource damageSource = new DamageSource(corruptedDamageHolder, target, this);
        if (var5 instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), target, damageSource, f);
        }
        boolean flag = target.hurtServer(level, damageSource, f);
        if (flag) {
            float f1 = this.getKnockback(target, damageSource);
            if (f1 > 0.0F && target instanceof LivingEntity livingentity) {
                livingentity.knockback((double)(f1 * 0.5F), (double)Mth.sin(this.getYRot() * 0.017453292F), (double)(-Mth.cos(this.getYRot() * 0.017453292F)));
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.6, 1.0, 0.6));
            }
            Level var7 = this.level();
            if (var7 instanceof ServerLevel serverLevel1) {
                EnchantmentHelper.doPostAttackEffects(serverLevel1, target, damageSource);
            }
            this.setLastHurtMob(target);
            this.playAttackSound();
        }

        return flag;
    }
}
