package wardentools.entity.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.utils.RenderToBufferFunction;
import wardentools.entity.utils.ScaleFunction;
import wardentools.entity.utils.SetUpAnimFunction;
import wardentools.entity.utils.getBobFunction;

import java.lang.reflect.Method;

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
        this.updateBoundingBoxSize();
    }

    private void updateBoundingBoxSize() {
        if (this.mimicEntity != null) {
            double width = this.mimicEntity.getBbWidth();
            double height = this.mimicEntity.getBbHeight();
            double depth = this.mimicEntity.getBbWidth();

            double centerX = this.getX();
            double centerY = this.getY();
            double centerZ = this.getZ();

            AABB newBox = new AABB(
                    centerX - width / 2.0, centerY, centerZ - depth / 2.0,
                    centerX + width / 2.0, centerY + height, centerZ + depth / 2.0
            );

            this.setBoundingBox(newBox);
        }
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
        if (this.mimicEntity == null) return null;
        EntityRenderer<? super LivingEntity> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
                .getRenderer(this.mimicEntity);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            return (poseStack, vertexConsumer, i, j)
                    -> livingRenderer.getModel().renderToBuffer(poseStack, vertexConsumer, i, j);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> SetUpAnimFunction<T> getSetUpAnimFunction() {
        if (this.mimicEntity == null) return null;
        T deadEntityCasted = (T) this.mimicEntity;
        EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
                .getRenderer(deadEntityCasted);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            return ((LivingEntityRenderer<T, ?>)livingRenderer).getModel()::setupAnim;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> getBobFunction<T> getGetBobFunction() {
        if (this.mimicEntity == null) return null;
        T deadEntityCasted = (T) this.mimicEntity;
        EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
                .getRenderer(deadEntityCasted);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            try {
                Method getBobMethod = LivingEntityRenderer.class
                        .getDeclaredMethod("getBob", LivingEntity.class, float.class);
                getBobMethod.setAccessible(true);
                return (getBobFunction<T>) (entity, partialTicks) -> {
                    if (deadEntityCasted instanceof Chicken || deadEntityCasted instanceof Parrot) return 0.0f;
                    try {
                        return (float)getBobMethod.invoke(livingRenderer, entity, partialTicks);
                    } catch (Exception e) {
                        System.out.println("Error invoking getBob method");
                        return 0.0f;
                    }
                };
            } catch (NoSuchMethodException e) {
                System.out.println("Error invoking getBob method: NoSuchMethodException");
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity> ScaleFunction<T> getScaleFunction() {
        if (this.mimicEntity == null) return null;
        T deadEntityCasted = (T) this.mimicEntity;
        EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
                .getRenderer(deadEntityCasted);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            try {
                Method scaleMethod = LivingEntityRenderer.class
                        .getDeclaredMethod("scale", LivingEntity.class, PoseStack.class, float.class);
                scaleMethod.setAccessible(true);
                return (ScaleFunction<T>) (entity, poseStack, v) -> {
                    try {
                        scaleMethod.invoke(livingRenderer, entity, poseStack, v);
                    } catch (Exception e) {
                        System.out.println("Error invoking scale method");
                    }
                };
            } catch (NoSuchMethodException e) {
                System.out.println("Error invoking getBob scale method: NoSuchMethodException");
            }
        }
        return null;
    }

    public LivingEntity getMimicEntity() {return mimicEntity;}

    public void setMimicEntity(LivingEntity mimicEntity) {
        this.mimicEntity = mimicEntity;
        this.entityData.set(MIMIC_ENTITY_ID, mimicEntity.getId());
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
