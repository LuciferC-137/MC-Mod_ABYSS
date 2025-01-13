package wardentools.weather.lightning;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import wardentools.sounds.ModSounds;

public class AbyssLightningEntity extends Entity {
    private static final int START_LIFE = 2;
    private int life;
    public long seed;
    private int flashes;
    private static final EntityDataAccessor<Boolean> LEGACY
            = SynchedEntityData.defineId(AbyssLightningEntity.class, EntityDataSerializers.BOOLEAN);

    public AbyssLightningEntity(EntityType<? extends AbyssLightningEntity> entityType, Level level) {
        super(entityType, level);
        this.noCulling = true;
        this.life = START_LIFE;
        this.seed = this.random.nextLong();
        this.flashes = this.random.nextInt(3) + 1;
    }

    public @NotNull SoundSource getSoundSource() {
        return SoundSource.WEATHER;
    }

    public void tick() {
        super.tick();
        if (this.life == 2) {
            if (this.level().isClientSide()) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                        ModSounds.SHOCKWAVE_THUNDER.get(), SoundSource.WEATHER,
                        2.0F, 0.5F + this.random.nextFloat() * 0.2F, false);
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(),
                        SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER,
                        10000.0F, 0.4F + this.random.nextFloat() * 0.2F, false);

            } else {
                this.gameEvent(GameEvent.LIGHTNING_STRIKE);
            }
        }
        --this.life;
        if (this.life < 0) {
            if (this.flashes == 0) {
                this.discard();
            } else if (this.life < -this.random.nextInt(10)) {
                --this.flashes;
                this.life = 1;
                this.seed = this.random.nextLong();
            }
        }
        if (this.life >= 0) {
            if (!(this.level() instanceof ServerLevel)) {
                this.level().setSkyFlashTime(2);
            }
        }

    }

    public void setIsLegacyLightning(boolean isLegacyLightning) {this.entityData.set(LEGACY, isLegacyLightning);}
    public boolean isLegacyLightning() {return this.entityData.get(LEGACY);}

    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        entityData.define(LEGACY, false);
    }

    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
    }

}
