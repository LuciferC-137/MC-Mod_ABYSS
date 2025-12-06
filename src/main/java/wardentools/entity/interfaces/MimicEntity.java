package wardentools.entity.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.utils.*;

import java.util.Objects;

public class MimicEntity extends CorruptionMonster {
    private LivingEntity mimicEntity = null;
    private static final EntityDataAccessor<Integer> MIMIC_ENTITY_ID
            = SynchedEntityData.defineId(MimicEntity.class, EntityDataSerializers.INT);

    protected MimicEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            int id = this.entityData.get(MIMIC_ENTITY_ID);
            if (id != -1) {
                Entity entity = this.level().getEntity(id);
                if (entity instanceof LivingEntity) {
                    this.mimicEntity = (LivingEntity) entity;
                }
            }
        }
    }

    @Override
    public @NotNull EntityDimensions getDefaultDimensions(@NotNull Pose pose) {
        return Objects.requireNonNullElse(mimicEntity, this).getType().getDimensions();
    }

    @Override
    public boolean isBaby() {
        if (this.mimicEntity == null) return super.isBaby();
        return this.mimicEntity.isBaby();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(MIMIC_ENTITY_ID, -1);
    }

    @Override
    public double getEyeY() {
        if (this.mimicEntity == null) return super.getEyeY();
        return this.mimicEntity.getEyeY();
    }

    public RenderToBufferFunction getRenderToBufferFunction() {
        if (!level().isClientSide || mimicEntity == null) return null;
        return ClientMimicRenderingUtils.getRenderToBufferFunction(mimicEntity);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> SetUpAnimFunction<T> getSetUpAnimFunction() {
        if (this.mimicEntity == null) return null;
        return ClientMimicRenderingUtils.getSetUpAnimFunction((T) this.mimicEntity);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> getBobFunction<T> getGetBobFunction() {
        if (!level().isClientSide || mimicEntity == null) return null;
        return ClientMimicRenderingUtils.getGetBobFunction((T) this.mimicEntity);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> ScaleFunction<T> getScaleFunction() {
        if (!level().isClientSide || mimicEntity == null) return null;
        return ClientMimicRenderingUtils.getScaleFunction((T) this.mimicEntity);
    }

    public LivingEntity getMimicEntity() {return mimicEntity;}

    public void setMimicEntity(LivingEntity mimicEntity) {
        this.mimicEntity = mimicEntity;
        this.entityData.set(MIMIC_ENTITY_ID, mimicEntity.getId());
        this.refreshDimensions();
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.mimicEntity != null) {
            tag.putInt("MimicEntityId", this.mimicEntity.getId());
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("MimicEntityId")) {
            int id = tag.getInt("MimicEntityId");
            this.entityData.set(MIMIC_ENTITY_ID, id);
        }
    }

}
