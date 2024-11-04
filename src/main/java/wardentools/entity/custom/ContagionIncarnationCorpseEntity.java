package wardentools.entity.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class ContagionIncarnationCorpseEntity extends LivingEntity {
    private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
    private static final Predicate<Entity> RIDABLE_MINECARTS = (p_31582_) -> {
        return p_31582_ instanceof AbstractMinecart && ((AbstractMinecart)p_31582_).canBeRidden();
    };
    public long lastHit;

	public ContagionIncarnationCorpseEntity(EntityType<? extends LivingEntity> entity, Level level) {
		super(entity, level);
	}

    public static AttributeSupplier.Builder createAttribute(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.JUMP_STRENGTH, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return this.armorItems;
    }

    @Override
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot slot) {
        return this.armorItems.get(slot.getIndex());
    }

    public void handleEntityEvent(byte b) {
        if (b == 32) {
            if (this.level().isClientSide) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                        SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3F,
                        1.0F, false);
                this.lastHit = this.level().getGameTime();
            }
        } else {
            super.handleEntityEvent(b);
        }
    }

    public boolean hurt(@NotNull DamageSource source, float damage) {
        if (!this.level().isClientSide && !this.isRemoved()) {
            if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                this.kill();
                return false;
            } else if (!this.isInvulnerableTo(source)) {
                if (source.is(DamageTypeTags.IS_EXPLOSION)) {
                    this.brokenByAnything(source);
                    this.kill();
                    return false;
                } else if (source.is(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
                    if (this.isOnFire()) {
                        this.causeDamage(source, 0.15F);
                    } else {
                        this.setSecondsOnFire(5);
                    }
                    return false;
                } else if (source.is(DamageTypeTags.BURNS_ARMOR_STANDS) && this.getHealth() > 0.5F) {
                    this.causeDamage(source, 4.0F);
                    return false;
                } else {
                    boolean flag = source.is(DamageTypeTags.CAN_BREAK_ARMOR_STAND);
                    boolean flag1 = source.is(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS);
                    if (!flag && !flag1) {
                        return false;
                    } else {
                        Entity entity = source.getEntity();
                        if (entity instanceof Player player) {
                            if (!player.getAbilities().mayBuild) {
                                return false;
                            }
                        }
                        if (source.isCreativePlayer()) {
                            this.playBrokenSound();
                            this.showBreakingParticles();
                            this.kill();
                        } else {
                            long i = this.level().getGameTime();
                            if (i - this.lastHit > 5L && !flag1) {
                                this.level().broadcastEntityEvent(this, (byte)32);
                                this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
                                this.lastHit = i;
                            } else {
                                this.brokenByPlayer(source);
                                this.showBreakingParticles();
                                this.kill();
                            }
                        }
                        return true;
                    }
                }
            } else return false;
        } else return false;
    }

    private void causeDamage(DamageSource damageSource, float damage) {
        float f = this.getHealth();
        f -= damage;
        if (f <= 0.5F) {
            this.brokenByAnything(damageSource);
            this.kill();
        } else {
            this.setHealth(f);
            this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());
        }
    }

    private void brokenByPlayer(DamageSource damageSource) {
        /*
        ItemStack itemstack = new ItemStack(Items.ARMOR_STAND);
        if (this.hasCustomName()) {
            itemstack.setHoverName(this.getCustomName());
        }
        Block.popResource(this.level(), this.blockPosition(), itemstack);*/
        this.brokenByAnything(damageSource);
    }

    private void showBreakingParticles() {
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()),
                    this.getX(), this.getY(0.6666666666666666D),
                    this.getZ(), 10, (this.getBbWidth() / 4.0F),
                    (this.getBbHeight() / 4.0F), (this.getBbWidth() / 4.0F), 0.05D);
        }

    }

    private void brokenByAnything(DamageSource p_31654_) {
        this.playBrokenSound();
        this.dropAllDeathLoot(p_31654_);
        for(int j = 0; j < this.armorItems.size(); ++j) {
            ItemStack itemStack = this.armorItems.get(j);
            if (!itemStack.isEmpty()) {
                Block.popResource(this.level(), this.blockPosition().above(), itemStack);
                this.armorItems.set(j, ItemStack.EMPTY);
            }
        }

    }

    @Override
    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
        this.gameEvent(GameEvent.ENTITY_DIE);
    }

    private void playBrokenSound() {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ARMOR_STAND_BREAK, this.getSoundSource(), 1.0F, 1.0F);
    }

    @Override
    public void setItemSlot(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {
        this.armorItems.set(slot.getIndex(), stack);
    }

    public boolean isPushable() {
        return false;
    }

    @Override
    public @NotNull HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    protected void doPush(@NotNull Entity entity) {
    }

    protected void pushEntities() {
        for(Entity entity : this.level().getEntities(this, this.getBoundingBox(), RIDABLE_MINECARTS)) {
            if (this.distanceToSqr(entity) <= 0.2D) {
                entity.push(this);
            }
        }
    }

}