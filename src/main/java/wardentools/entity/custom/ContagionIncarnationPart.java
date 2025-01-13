package wardentools.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ContagionIncarnationPart extends PartEntity<ContagionIncarnationPartManager> {
    public final ContagionIncarnationPartManager parentMob;
    public final String name;
    private final EntityDimensions size;

    public ContagionIncarnationPart(ContagionIncarnationPartManager incarnation, String name, float x, float y) {
        super(incarnation);
        this.size = EntityDimensions.scalable(x, y);
        this.refreshDimensions();
        this.parentMob = incarnation;
        this.name = name;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder data) {
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Nullable
    public ItemStack getPickResult() {return this.parentMob.getPickResult();}

    @Override
    public boolean hurt(@NotNull DamageSource source, float damage) {
        return !this.isInvulnerableTo(source) && this.parentMob.hurt(this, source, damage);
    }

    @Override
    public boolean is(@NotNull Entity entity) {
        return this == entity || this.parentMob == entity;
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        return this.size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
