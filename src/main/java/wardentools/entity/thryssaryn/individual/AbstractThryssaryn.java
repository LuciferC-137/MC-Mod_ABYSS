package wardentools.entity.thryssaryn.individual;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.entity.thryssaryn.community.Community;
import wardentools.entity.thryssaryn.community.CommunityData;

import java.util.*;


public class AbstractThryssaryn extends PathfinderMob {

    public static final EntityDataAccessor<Integer> AGE =
            SynchedEntityData.defineId(AbstractThryssaryn.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EYES_COLOR =
            SynchedEntityData.defineId(AbstractThryssaryn.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Optional<UUID>> COMMUNITY_ID =
            SynchedEntityData.defineId(AbstractThryssaryn.class, EntityDataSerializers.OPTIONAL_UUID);
    public static final EntityDataAccessor<Integer> HUNGER =
            SynchedEntityData.defineId(AbstractThryssaryn.class, EntityDataSerializers.INT);
    private boolean isPaused;

    // SERVER VARIABLES
    private boolean canCreateCommunity = true;

    public static final int[] BASE_EYE_COLORS = new int[] {
            0xFF0000, // Red
            0x00FF00, // Green
            0x0000FF, // Blue
            0xFFFF00, // Yellow
            0xFF00FF, // Magenta
            0x00FFFF, // Cyan
            0xFFA500  // Orange
    };
    public static final int ADULT_AGE = 36000; // 30 minutes in ticks
    public static final int MAX_HUNGER = 144000; // 2h

    protected AbstractThryssaryn(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        if (this.isPaused) return;
        super.tick();
        if (!this.level().isClientSide) {
            if (this.getAge() == -1) {
                this.setAge(ADULT_AGE); // Allow spawning as adult by default
            }
            if (this.getAge() < ADULT_AGE) {
                this.ageUp(1);
            }
            if (!this.hasCommunity() && tickCount % 200 == 0) {
                this.joinOrCreateCommunity();
            }
            if (this.getHunger() > 0) {
                this.setHunger(this.getHunger() - 1);
            }
        }
    }

    @Override
    public void aiStep() {
        if  (this.isPaused) return;
        super.aiStep();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public float getSizeFactor() {
        return 0.5F + 0.5F * Math.min(1.0F, (float)this.getAge() / ADULT_AGE);
    }

    public float getHeadSizeOffsetFactor() {
        return 0.3F - 0.3F * Math.min(1.0F, (float)this.getAge() / ADULT_AGE);
    }

    public void ageUp(int ticks) {
        int newAge = this.getAge() + ticks;
        this.setAge(newAge);
    }

    @Override
    public void setBaby(boolean baby) {
        super.setBaby(baby);
        this.setAge(0);
    }

    @Override
    public boolean isBaby() {
        return this.getAge() < ADULT_AGE;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(EYES_COLOR, 0);
        entityData.define(COMMUNITY_ID, Optional.empty());
        entityData.define(AGE, -1);
        entityData.define(HUNGER, MAX_HUNGER);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("eyesColor", this.getEyesColor());
        if (this.getCommunityId().isPresent()) {
            compound.putUUID("communityId", this.getCommunityId().get());
        }
        compound.putInt("age", this.getAge());
        compound.putFloat("hunger", this.getHunger());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("eyesColor")) {
            this.setEyesColor(compound.getInt("eyesColor"));
        }
        if (compound.contains("communityId")) {
            this.setCommunityId(compound.getUUID("communityId"));
        }
        if (compound.contains("age")) {
            this.setAge(compound.getInt("age"));
        }
    }

    @Override
    public void onAddedToLevel() {
        super.onAddedToLevel();
        Community community = this.getCommunity();
        if (community != null) {
            community.registerLoadedMember(this.getUUID());
        }
    }

    @Override
    public void onRemovedFromLevel() {
        super.onRemovedFromLevel();
        Community community = this.getCommunity();
        if (community != null) {
            community.unregisterLoadedMember(this.getUUID());
        }
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        super.die(damageSource);
        Community community = this.getCommunity();
        if (community != null) {
            community.removeMember(this.getUUID());
        }
    }

    public int getEyesColor() {return this.entityData.get(EYES_COLOR);}

    public void setEyesColor(int color) {this.entityData.set(EYES_COLOR, color);}

    public Optional<UUID> getCommunityId() {return this.entityData.get(COMMUNITY_ID);}

    public void setCommunityId(@Nullable UUID communityId) {this.entityData.set(COMMUNITY_ID, Optional.ofNullable(communityId));}

    public boolean hasCommunity() {return this.getCommunityId().isPresent();}

    public int getAge() {return this.entityData.get(AGE);}

    public void setAge(int age) {this.entityData.set(AGE, age);}

    public int getHunger() {return this.entityData.get(HUNGER);}

    public void setHunger(int hunger) {this.entityData.set(HUNGER, hunger);}

    public @Nullable Community getCommunity() {
        if (this.level() instanceof ServerLevel serverLevel) {
            CommunityData data = CommunityData.get(serverLevel);
            UUID communityId = this.getCommunityId().orElse(null);
            if (communityId != null) {
                return data.getCommunity(communityId);
            }
        }
        return null;
    }

    private void joinOrCreateCommunity() {
        if (this.level().isClientSide) return;
        if (this.getCommunityId().isPresent()) return;
        CommunityData communities = CommunityData.get((ServerLevel) this.level());
        Community closestCommunity = communities.getClosestCommunity(this.blockPosition());
        if (closestCommunity != null && closestCommunity.distanceTo(this.blockPosition()) <= 100) {
            this.addToCommunity(closestCommunity);
        } else if (this.canCreateCommunity) {
            Community community = communities.createCommunity(Set.of(this.getUUID()),
                    this.blockPosition(), 50);
            this.addToCommunity(community);
        }
    }

    public void addToCommunity(@NotNull Community community) {
        if (this.level().isClientSide) return;
        community.addMember(this.getUUID());
        this.setCommunityId(community.uuid());
    }

}
