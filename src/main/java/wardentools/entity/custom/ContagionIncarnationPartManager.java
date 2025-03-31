package wardentools.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.interfaces.CorruptionMonster;

public class ContagionIncarnationPartManager extends CorruptionMonster implements Enemy {
    public final ContagionIncarnationPart[] subEntities;
    public final ContagionIncarnationPart head;
    public final ContagionIncarnationPart body;
    public final ContagionIncarnationPart tail1;
    public final ContagionIncarnationPart tail2;
    public final ContagionIncarnationPart tail3;
    private static final float HEAD_HITBOX_X = 2F;
    private static final float HEAD_HITBOX_Y = 4F;
    private static final float TAIL1_HITBOX_X = -3.1F;
    private static final float TAIL1_HITBOX_Y = 0F;
    private static final float TAIL2_HITBOX_X = -5.4F;
    private static final float TAIL2_HITBOX_Y = 0F;
    private static final float TAIL3_HITBOX_X = -6.7F;
    private static final float TAIL3_HITBOX_Y = 0F;
    private static final float BODY_HITBOX_X = 0F;
    private static final float BODY_HITBOX_Y = 2F;
    private static final EntityDataAccessor<Float> section_6_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_6_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_7_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_7_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_8_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_8_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_9_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_9_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_10_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_10_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_11_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_11_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_12_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_12_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_13_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_13_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_14_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_14_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_15_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_15_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_16_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_16_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_17_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_17_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_18_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> section_18_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> end_rotX=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> end_rotY=
            SynchedEntityData.defineId(ContagionIncarnationPartManager.class, EntityDataSerializers.FLOAT);
    private static final float rad = (float)Math.PI / 180f;
    private Vec3 oldPos = null;
    private float refAngleTail = 0f;
    private static final float UNFOLD_TAIL_MULTIPLICATOR = 0.4f;
    private static final float TAIL_LENGTH = 90f; // In pixel (1/16 of a block)
    private float traveledLength = 0f;
    private final Section section_6;
    private final Section section_7;
    private final Section section_8;
    private final Section section_9;
    private final Section section_10;
    private final Section section_11;
    private final Section section_12;
    private final Section section_13;
    private final Section section_14;
    private final Section section_15;
    private final Section section_16;
    private final Section section_17;
    private final Section section_18;
    private final Section end;

	public ContagionIncarnationPartManager(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
        this.head = new ContagionIncarnationPart(this, "head", 2.4F, 1.5F);
        this.body = new ContagionIncarnationPart(this, "body", 1.5F, 2F);
        this.tail1 = new ContagionIncarnationPart(this, "tail1", 3F, 1.6F);
        this.tail2 = new ContagionIncarnationPart(this, "tail2", 1.5F, 1.5F);
        this.tail3 = new ContagionIncarnationPart(this, "tail3", 1.3F, 1.3F);
        this.subEntities = new ContagionIncarnationPart[]{this.head, this.body,
                this.tail1, this.tail2, this.tail3};
        this.end = new Section(5f, null);
        //Tail sections (rotation manager)
        this.section_18 = new Section(5f, end);
        this.section_17 = new Section(6f, section_18);
        this.section_16 = new Section(6f, section_17);
        this.section_15 = new Section(6f, section_16);
        this.section_14 = new Section(6f, section_15);
        this.section_13 = new Section(6f, section_14);
        this.section_12 = new Section(6f, section_13);
        this.section_11 = new Section(6f, section_12);
        this.section_10 = new Section(6f, section_11);
        this.section_9 = new Section(6f, section_10);
        this.section_8 = new Section(13f, section_9);
        this.section_7 = new Section(13f, section_8);
        this.section_6 = new Section(8f, section_7);
	}

    @Override
    public void setId(int id) {
        super.setId(id);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(id + i + 1);
    }

	
	@Override
	public void tick() {
		if (!level().isClientSide) {
            if (this.oldPos == null) {
                this.oldPos = this.position();
            }
            this.resetTail();
            this.animateTail(this.yBodyRot, oldPos, this.position());
            this.oldPos = this.position();
            this.setSection_6_rotX(this.section_6.getRotationX());
            this.setSection_6_rotY(this.section_6.getRotationY());
            this.setSection_7_rotX(this.section_7.getRotationX());
            this.setSection_7_rotY(this.section_7.getRotationY());
            this.setSection_8_rotX(this.section_8.getRotationX());
            this.setSection_8_rotY(this.section_8.getRotationY());
            this.setSection_9_rotX(this.section_9.getRotationX());
            this.setSection_9_rotY(this.section_9.getRotationY());
            this.setSection_10_rotX(this.section_10.getRotationX());
            this.setSection_10_rotY(this.section_10.getRotationY());
            this.setSection_11_rotX(this.section_11.getRotationX());
            this.setSection_11_rotY(this.section_11.getRotationY());
            this.setSection_12_rotX(this.section_12.getRotationX());
            this.setSection_12_rotY(this.section_12.getRotationY());
            this.setSection_13_rotX(this.section_13.getRotationX());
            this.setSection_13_rotY(this.section_13.getRotationY());
            this.setSection_14_rotX(this.section_14.getRotationX());
            this.setSection_14_rotY(this.section_14.getRotationY());
            this.setSection_15_rotX(this.section_15.getRotationX());
            this.setSection_15_rotY(this.section_15.getRotationY());
            this.setSection_16_rotX(this.section_16.getRotationX());
            this.setSection_16_rotY(this.section_16.getRotationY());
            this.setSection_17_rotX(this.section_17.getRotationX());
            this.setSection_17_rotY(this.section_17.getRotationY());
            this.setSection_18_rotX(this.section_18.getRotationX());
            this.setSection_18_rotY(this.section_18.getRotationY());
            this.setEnd_rotX(this.end.getRotationX());
            this.setEnd_rotY(this.end.getRotationY());
        }
		super.tick();
	}

    @Override
    public void aiStep() {
        super.aiStep();
        this.handleTailSubParts();
    }

    public void handleTailSubParts() {
        this.yBodyRot = this.getYRot();
        for (ContagionIncarnationPart subEntity : this.subEntities) {
            subEntity.xo = subEntity.getX();
            subEntity.yo = subEntity.getY();
            subEntity.zo = subEntity.getZ();
            subEntity.xOld = subEntity.getX();
            subEntity.yOld = subEntity.getY();
            subEntity.zOld = subEntity.getZ();
        }
        this.tickPart(this.body, - BODY_HITBOX_X * Mth.sin(this.getYRot() * rad),
                BODY_HITBOX_Y, BODY_HITBOX_X * Mth.cos(this.getYRot() * rad));
        this.tickPart(this.head, - HEAD_HITBOX_X * Mth.sin(this.getYRot() * rad),
                HEAD_HITBOX_Y, HEAD_HITBOX_X * Mth.cos(this.getYRot() * rad));
        float r1 = (this.getSection_6_rotY() + this.getSection_7_rotY() + this.getSection_8_rotY()) / 2f;
        float r2 = r1 + (this.getSection_9_rotY() + this.getSection_10_rotY()
                + this.getSection_11_rotY()) / 2.2f;
        float r3 = r2 + (this.getSection_12_rotY() + this.getSection_13_rotY()
                + this.getSection_14_rotY()) / 2.2f;
        this.tickPart(this.tail1,
                - TAIL1_HITBOX_X * Mth.sin(this.getYRot() * rad + r1),
                TAIL1_HITBOX_Y,
                TAIL1_HITBOX_X * Mth.cos(this.getYRot() * rad + r1));
        this.tickPart(this.tail2,
                - TAIL2_HITBOX_X * Mth.sin(this.getYRot() * rad + r2),
                TAIL2_HITBOX_Y,
                TAIL2_HITBOX_X * Mth.cos(this.getYRot() * rad + r2));
        float length_factor = 1f;
        if (this.getSection_12_rotY() != 0f) {
            length_factor = 1f
                    - Math.max((Mth.abs((
                    this.getSection_12_rotY() + this.getSection_13_rotY() + this.getSection_14_rotY())
                    / rad) % 360f) / 60f, 0f);
            length_factor = (length_factor + 3f) / 4f;
        }
        this.tickPart(this.tail3,
                - length_factor * TAIL3_HITBOX_X * Mth.sin(this.getYRot() * rad + r3),
                TAIL3_HITBOX_Y,
                length_factor * TAIL3_HITBOX_X * Mth.cos(this.getYRot() * rad + r3));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(section_6_rotX, 0f);
        entityData.define(section_6_rotY, 0f);
        entityData.define(section_7_rotX, 0f);
        entityData.define(section_7_rotY, 0f);
        entityData.define(section_8_rotX, 0f);
        entityData.define(section_8_rotY, 0f);
        entityData.define(section_9_rotX, 0f);
        entityData.define(section_9_rotY, 0f);
        entityData.define(section_10_rotX, 0f);
        entityData.define(section_10_rotY, 0f);
        entityData.define(section_11_rotX, 0f);
        entityData.define(section_11_rotY, 0f);
        entityData.define(section_12_rotX, 0f);
        entityData.define(section_12_rotY, 0f);
        entityData.define(section_13_rotX, 0f);
        entityData.define(section_13_rotY, 0f);
        entityData.define(section_14_rotX, 0f);
        entityData.define(section_14_rotY, 0f);
        entityData.define(section_15_rotX, 0f);
        entityData.define(section_15_rotY, 0f);
        entityData.define(section_16_rotX, 0f);
        entityData.define(section_16_rotY, 0f);
        entityData.define(section_17_rotX, 0f);
        entityData.define(section_17_rotY, 0f);
        entityData.define(section_18_rotX, 0f);
        entityData.define(section_18_rotY, 0f);
        entityData.define(end_rotX, 0f);
        entityData.define(end_rotY, 0f);
    }

    public float getSection_6_rotX() {return entityData.get(section_6_rotX);}
    public float getSection_6_rotY() {return entityData.get(section_6_rotY);}
    public float getSection_7_rotX() {return entityData.get(section_7_rotX);}
    public float getSection_7_rotY() {return entityData.get(section_7_rotY);}
    public float getSection_8_rotX() {return entityData.get(section_8_rotX);}
    public float getSection_8_rotY() {return entityData.get(section_8_rotY);}
    public float getSection_9_rotX() {return entityData.get(section_9_rotX);}
    public float getSection_9_rotY() {return entityData.get(section_9_rotY);}
    public float getSection_10_rotX() {return entityData.get(section_10_rotX);}
    public float getSection_10_rotY() {return entityData.get(section_10_rotY);}
    public float getSection_11_rotX() {return entityData.get(section_11_rotX);}
    public float getSection_11_rotY() {return entityData.get(section_11_rotY);}
    public float getSection_12_rotX() {return entityData.get(section_12_rotX);}
    public float getSection_12_rotY() {return entityData.get(section_12_rotY);}
    public float getSection_13_rotX() {return entityData.get(section_13_rotX);}
    public float getSection_13_rotY() {return entityData.get(section_13_rotY);}
    public float getSection_14_rotX() {return entityData.get(section_14_rotX);}
    public float getSection_14_rotY() {return entityData.get(section_14_rotY);}
    public float getSection_15_rotX() {return entityData.get(section_15_rotX);}
    public float getSection_15_rotY() {return entityData.get(section_15_rotY);}
    public float getSection_16_rotX() {return entityData.get(section_16_rotX);}
    public float getSection_16_rotY() {return entityData.get(section_16_rotY);}
    public float getSection_17_rotX() {return entityData.get(section_17_rotX);}
    public float getSection_17_rotY() {return entityData.get(section_17_rotY);}
    public float getSection_18_rotX() {return entityData.get(section_18_rotX);}
    public float getSection_18_rotY() {return entityData.get(section_18_rotY);}
    public float getEnd_rotX() {return entityData.get(end_rotX);}
    public float getEnd_rotY() {return entityData.get(end_rotY);}

    public void setSection_6_rotX(float value) {entityData.set(section_6_rotX, value);}
    public void setSection_6_rotY(float value) {entityData.set(section_6_rotY, value);}
    public void setSection_7_rotX(float value) {entityData.set(section_7_rotX, value);}
    public void setSection_7_rotY(float value) {entityData.set(section_7_rotY, value);}
    public void setSection_8_rotX(float value) {entityData.set(section_8_rotX, value);}
    public void setSection_8_rotY(float value) {entityData.set(section_8_rotY, value);}
    public void setSection_9_rotX(float value) {entityData.set(section_9_rotX, value);}
    public void setSection_9_rotY(float value) {entityData.set(section_9_rotY, value);}
    public void setSection_10_rotX(float value) {entityData.set(section_10_rotX, value);}
    public void setSection_10_rotY(float value) {entityData.set(section_10_rotY, value);}
    public void setSection_11_rotX(float value) {entityData.set(section_11_rotX, value);}
    public void setSection_11_rotY(float value) {entityData.set(section_11_rotY, value);}
    public void setSection_12_rotX(float value) {entityData.set(section_12_rotX, value);}
    public void setSection_12_rotY(float value) {entityData.set(section_12_rotY, value);}
    public void setSection_13_rotX(float value) {entityData.set(section_13_rotX, value);}
    public void setSection_13_rotY(float value) {entityData.set(section_13_rotY, value);}
    public void setSection_14_rotX(float value) {entityData.set(section_14_rotX, value);}
    public void setSection_14_rotY(float value) {entityData.set(section_14_rotY, value);}
    public void setSection_15_rotX(float value) {entityData.set(section_15_rotX, value);}
    public void setSection_15_rotY(float value) {entityData.set(section_15_rotY, value);}
    public void setSection_16_rotX(float value) {entityData.set(section_16_rotX, value);}
    public void setSection_16_rotY(float value) {entityData.set(section_16_rotY, value);}
    public void setSection_17_rotX(float value) {entityData.set(section_17_rotX, value);}
    public void setSection_17_rotY(float value) {entityData.set(section_17_rotY, value);}
    public void setSection_18_rotX(float value) {entityData.set(section_18_rotX, value);}
    public void setSection_18_rotY(float value) {entityData.set(section_18_rotY, value);}
    public void setEnd_rotX(float value) {entityData.set(end_rotX, value);}
    public void setEnd_rotY(float value) {entityData.set(end_rotY, value);}

    private void tickPart(ContagionIncarnationPart part, double x, double y, double z) {
        part.setPos(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    public boolean hurt(ContagionIncarnationPart part, DamageSource source, float damage) {
        if (this.isDeadOrDying() || source.getMsgId().equals("corrupted")) {
            return false;
        } else {
            if (source.getEntity() instanceof Player || source.getEntity() instanceof ProtectorEntity) {
                return this.reallyHurt(source, damage);
            }
            return true;
        }
    }

    protected boolean reallyHurt(DamageSource source, float damage) {
        return super.hurt(source, damage);
    }

    public ContagionIncarnationPart[] getSubEntities() {
        return this.subEntities;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public PartEntity<?> @NotNull [] getParts() {
        return this.subEntities;
    }

    private void resetTail() {
        this.section_6.absoluteRotateX(0f);
        this.section_6.absoluteRotateY(0f);
        this.section_7.absoluteRotateX(0f);
        this.section_7.absoluteRotateY(0f);
        this.section_8.absoluteRotateX(0f);
        this.section_8.absoluteRotateY(0f);
        this.section_9.absoluteRotateX(0f);
        this.section_9.absoluteRotateY(0f);
        this.section_10.absoluteRotateX(0f);
        this.section_10.absoluteRotateY(0f);
        this.section_11.absoluteRotateX(0f);
        this.section_11.absoluteRotateY(0f);
        this.section_12.absoluteRotateX(0f);
        this.section_12.absoluteRotateY(0f);
        this.section_13.absoluteRotateX(0f);
        this.section_13.absoluteRotateY(0f);
        this.section_14.absoluteRotateX(0f);
        this.section_14.absoluteRotateY(0f);
        this.section_15.absoluteRotateX(0f);
        this.section_15.absoluteRotateY(0f);
        this.section_16.absoluteRotateX(0f);
        this.section_16.absoluteRotateY(0f);
        this.section_17.absoluteRotateX(0f);
        this.section_17.absoluteRotateY(0f);
        this.section_18.absoluteRotateX(0f);
        this.section_18.absoluteRotateY(0f);
        this.end.absoluteRotateX(0f);
        this.end.absoluteRotateY(0f);
    }

    private void animateTail(float bodyRot, Vec3 oldPos, Vec3 newPos) {
        // This call makes the tail lie on the ground.
        this.tailLie();
        // This call makes the tail fold and unfold according to the speed of the entity and its rotation.
        this.tailFolding(bodyRot, oldPos, newPos);
    }

    private void tailLie() {
        this.section_8.rotateX(-2.5f);
        this.section_9.rotateX(-7.5f);
        this.section_10.rotateX(-5f);
        this.section_11.rotateX(10f);
        this.section_12.rotateX(2.5f);
        this.section_13.rotateX(2.5f);
        this.section_14.rotateX(-2.5f);
        this.section_15.rotateX(-2.5f);
        this.section_16.rotateX(2.5f);
        this.section_17.rotateX(0f);
        this.section_18.rotateX(-5f);
        this.end.rotateX(7.5f);
    }

    private void tailFolding(float bodyRot, Vec3 oldPos, Vec3 newPos) {
        float speed = (float)Math.sqrt((newPos.x - oldPos.x) * (newPos.x - oldPos.x)
                + (newPos.z - oldPos.z) * (newPos.z - oldPos.z));
        this.traveledLength += speed  * UNFOLD_TAIL_MULTIPLICATOR;
        if (this.traveledLength >= TAIL_LENGTH) {
            this.traveledLength = 0f;
        }
        float speedCompensationFactor = (TAIL_LENGTH - this.traveledLength) / TAIL_LENGTH;
        this.refAngleTail = this.section_7.compensateRotation(bodyRot, this.refAngleTail);
        if (speed > 0) {
            this.refAngleTail = this.refAngleTail * speedCompensationFactor
                    + bodyRot * (1f - speedCompensationFactor);
        }
    }

    private static class Section {
        public static final float MAX_ANGLE = 20F;
        public float length;
        private float rotX;
        private float rotY;
        private final ContagionIncarnationPartManager.Section nextSection;
        private float rotYOld = 0f;

        public Section(float length, ContagionIncarnationPartManager.Section nextSection) {
            this.length = length;
            this.nextSection = nextSection;
            this.rotYOld = this.getRotationY();
        }

        public float compensateRotation(float previousPartRot, float refAngleTail) {
            // This function is specifically designed to mimic the behaviour of something
            // drag on the ground when its attachment point rotates or moves.
            float rotDiff = previousPartRot - refAngleTail;
            float rotToDo = - rotDiff;
            if (Math.abs(rotDiff) < MAX_ANGLE) this.absoluteRotateY(rotToDo);
            else {
                rotToDo = - MAX_ANGLE * Mth.sign(rotDiff);
                this.absoluteRotateY(rotToDo);
                if (this.nextSection!=null) {
                    return this.nextSection.compensateRotation(previousPartRot + rotToDo, refAngleTail);
                } else return previousPartRot + rotToDo;
            }
            return refAngleTail;
        }

        public void homogeneousRotation(float yaw, float pitch) {
            // Create a rotation equally shared among all the children. This creates a simple curvature effect.
            this.rotateX(pitch);
            this.rotateY(yaw);
            if (this.nextSection != null) {
                this.nextSection.homogeneousRotation(yaw, pitch);
            }
        }

        public float getCumulativeAngleY() {
            if (this.nextSection == null) return this.getRotationY();
            return this.getRotationY() + this.nextSection.getCumulativeAngleY();
        }

        public float getRotationX() {
            // NB : return the ABSOLUTE rotation in space, not with respect to its parents
            return this.rotX;
        }
        public float getRotationY() {
            // NB : return the ABSOLUTE rotation in space, not with respect to its parents
            return this.rotY;
        }
        public void absoluteRotateX(float angle) {
            // NB : sets the RELATIVE rotation with respect to its parent
            this.rotX = angle * rad;
        }
        public void absoluteRotateY(float angle) {
            // NB : sets the RELATIVE rotation with respect to its parent
            this.rotYOld = this.getRotationY();
            this.rotY = angle * rad;
        }
        public void rotateX(float angle) {
            this.rotX = this.rotX + angle * rad;
        }

        public void rotateY(float angle) {
            this.rotYOld = this.getRotationY();
            this.rotY = this.rotY + angle * rad;
        }
    }

}
