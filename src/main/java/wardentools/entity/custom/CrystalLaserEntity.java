package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.block.CrystalBlock;
import wardentools.misc.Crystal;
import wardentools.particle.ModParticleUtils;
import wardentools.particle.options.ShineParticleOptions;

import java.util.ArrayList;
import java.util.List;

public class CrystalLaserEntity extends Entity {
    private static final EntityDataAccessor<Integer> CRYSTAL =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> TARGET_1 =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TARGET_2 =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TARGET_3 =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TARGET_4 =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TARGET_5 =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.BLOCK_POS);

    private static final EntityDataAccessor<Integer> SIZE =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> ACTIVE_SEGMENT =
            SynchedEntityData.defineId(CrystalLaserEntity.class, EntityDataSerializers.INT);

    private static final int TICK_BETWEEN_SEGMENT_CHANGE = 40;
    private static final int PARTICLE_PER_TICK = 5;
    private static final int STATIC_PARTICLE_PER_TICK = 1;

    private static final int SEARCH_RADIUS = 10;
    private static final int SEARCH_HEIGHT = 2;

    private int ticksExisted = 0;
    private boolean firstCrystalHasBeenLit = false;

    private List<Vec3> cachedTargets = List.of();

    public CrystalLaserEntity(EntityType<? extends CrystalLaserEntity> entityType, Level level) {
        super(entityType, level);
        this.noCulling = true;
    }

    public void tick() {
        super.tick();
        this.ticksExisted++;
        if (!firstCrystalHasBeenLit) {
            if (this.getTarget(0) != BlockPos.ZERO) {
                this.litCrystal(this.getTarget(0), true);
                firstCrystalHasBeenLit = true;
            }
        }
        if (this.ticksExisted > this.getLifeSpan()) {
            this.remove(RemovalReason.DISCARDED);
            return;
        }
        if (this.ticksExisted % TICK_BETWEEN_SEGMENT_CHANGE == 0) {
            this.updateActiveSegment();
            this.litCrystals();
        }
        if (this.level().isClientSide) {
            this.handleParticleAnimation();
        }
    }

    private void handleParticleAnimation() {
        int activeSegment = this.getActiveSegment();
        if (activeSegment >= this.getSize()) return;
        if (activeSegment >= cachedTargets.size() - 1) return;

        this.dynamicParticleForSegment(activeSegment);
        for (int segment = activeSegment; segment < cachedTargets.size() - 1; segment++) {
            this.staticParticleForSegment(segment);
        }
    }

    private void dynamicParticleForSegment(int segment) {
        if (segment >= cachedTargets.size() - 1) return;

        Vec3 start = cachedTargets.get(segment);
        Vec3 end = cachedTargets.get(segment + 1);

        if (start == null || end == null) return;

        for (int i = 0; i < PARTICLE_PER_TICK; i++) {
            Vec3 originAlong = start.add(end.subtract(start)
                            .scale(level().getRandom().nextFloat() * 0.9F))
                    .offsetRandom(level().getRandom(), 0.2F);
            Vec3 target = end.offsetRandom(level().getRandom(), 0.2F);
            ModParticleUtils.addClientParticle(this.level(),
                    new ShineParticleOptions(Vec3.ZERO, this.getCrystalType().getColor(),
                            true, true), originAlong, target, 0.03F);
        }
    }

    private void staticParticleForSegment(int segment) {
        if (segment >= cachedTargets.size() - 1) return;

        Vec3 start = cachedTargets.get(segment);
        Vec3 end = cachedTargets.get(segment + 1);

        if (start == null || end == null) return;

        for (int i = 0; i < STATIC_PARTICLE_PER_TICK; i++) {
            Vec3 originAlong = start.add(end.subtract(start)
                            .scale(level().getRandom().nextFloat() * 0.9F))
                    .offsetRandom(level().getRandom(), 0.2F);
            ModParticleUtils.addStaticClientParticle(this.level(),
                    new ShineParticleOptions(Vec3.ZERO, this.getCrystalType().getColor(),
                            true, false), originAlong);
        }
    }

    private int getLifeSpan() {
        return TICK_BETWEEN_SEGMENT_CHANGE * this.getSize() + 1;
    }

    private void updateActiveSegment() {
        if (this.getActiveSegment() < this.getSize()) {
            this.setActiveSegment(this.getActiveSegment() + 1);
        }
    }

    private void litCrystals() {
        for (int i = 0; i < 5; i++) {
            BlockPos pos = this.getTarget(i);
            boolean isLit = this.getActiveSegment() == i || this.getActiveSegment() == i + 1;
            this.litCrystal(pos, isLit);
        }
    }

    private void unlitAllCrystals() {
        for (int i = 0; i < 5; i++) {
            BlockPos pos = this.getTarget(i);
            this.litCrystal(pos, false);
        }
    }

    private void litCrystal(BlockPos pos, boolean lit) {
        if (pos != null && pos != BlockPos.ZERO) {
            BlockState state = this.level().getBlockState(pos);
            if (state.getBlock() instanceof CrystalBlock) {
                this.level().setBlock(pos, state.setValue(CrystalBlock.OVERCHARGED, lit),
                        CrystalBlock.UPDATE_ALL);
            }
        }
    }

    public void buildCrystalChain() {
        List<BlockPos> chain = findCrystalChain(5, SEARCH_RADIUS, SEARCH_HEIGHT);
        for (int i = 0; i < chain.size(); i++) {
            BlockPos pos = chain.get(i);
            switch (i) {
                case 0 -> this.entityData.set(TARGET_1, pos);
                case 1 -> this.entityData.set(TARGET_2, pos);
                case 2 -> this.entityData.set(TARGET_3, pos);
                case 3 -> this.entityData.set(TARGET_4, pos);
                case 4 -> this.entityData.set(TARGET_5, pos);
            }
            if (pos != null && pos != BlockPos.ZERO) {
                this.setSize(i + 1);
            }
        }
    }

    public List<BlockPos> findCrystalChain(int maxTargets, int maxRadius, int maxHeight) {
        List<BlockPos> result = new ArrayList<>();
        BlockPos currentOrigin = this.blockPosition();

        for (int i = 0; i < maxTargets; i++) {
            BlockPos next = findClosestCrystal(currentOrigin, maxRadius, maxHeight, result);
            if (next == null) break;
            result.add(next);
            currentOrigin = next;
        }
        return result;
    }

    private BlockPos findClosestCrystal(BlockPos origin, int radius, int height,
                                        List<BlockPos> alreadyFound) {
        for (int r = 1; r <= radius; r++) {
            for (int y = -height; y <= height; y++) {
                for (int x = -r; x <= r; x++) {
                    for (int z = -r; z <= r; z++) {
                        if (Math.abs(x) != r && Math.abs(z) != r) continue;

                        BlockPos pos = origin.offset(x, y, z);
                        if (alreadyFound.contains(pos)) continue;
                        if (isLaserCompatible(this.level(), pos, this.getCrystalType())) {
                            return pos;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Vec3> getAllTargets() {
        if (this.cachedTargets.isEmpty()) {
            List<Vec3> targets = new ArrayList<>();
            targets.add(this.position());
            for (int i = 0; i < 5; i++) {
                BlockPos target = this.getTarget(i);
                if (target != BlockPos.ZERO) {
                    targets.add(target.getCenter());
                }
            }
            this.cachedTargets = List.copyOf(targets);
        }
        return this.cachedTargets;
    }

    public BlockPos getTarget(int index) {
        return switch (index) {
            case 0 -> this.entityData.get(TARGET_1);
            case 1 -> this.entityData.get(TARGET_2);
            case 2 -> this.entityData.get(TARGET_3);
            case 3 -> this.entityData.get(TARGET_4);
            case 4 -> this.entityData.get(TARGET_5);
            default -> BlockPos.ZERO;
        };
    }

    public int getActiveSegment() {return this.entityData.get(ACTIVE_SEGMENT);}

    private void setActiveSegment(int i) {this.entityData.set(ACTIVE_SEGMENT, i);}

    public int getSize() {return this.entityData.get(SIZE);}

    private void setSize(int size) {this.entityData.set(SIZE, size);}

    public static boolean isLaserCompatible(Level level, BlockPos pos, Crystal crystal) {
        return level.getBlockState(pos).is(crystal.getCrystalBud());
    }

    public void setCrystalType(Crystal crystal) {
        this.entityData.set(CRYSTAL, crystal.getIndex());
    }

    public Crystal getCrystalType() {
        return Crystal.fromIndex(this.entityData.get(CRYSTAL));
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    @Override
    public void onRemovedFromWorld() {
        this.unlitAllCrystals();
        super.onRemovedFromWorld();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(CRYSTAL, Crystal.getDefault().getIndex());
        builder.define(TARGET_1, BlockPos.ZERO);
        builder.define(TARGET_2, BlockPos.ZERO);
        builder.define(TARGET_3, BlockPos.ZERO);
        builder.define(TARGET_4, BlockPos.ZERO);
        builder.define(TARGET_5, BlockPos.ZERO);
        builder.define(SIZE, 0);
        builder.define(ACTIVE_SEGMENT, 0);
    }

    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

}
