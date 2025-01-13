package wardentools.entity.custom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
            SynchedEntityData.defineId(ModChestBoatEntity.class, EntityDataSerializers.INT);

    public static final ModelLayerLocation DARKTREE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ModMain.MOD_ID, "chest_boat/darktree"), "main");
    public static final ModelLayerLocation WHITETREE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ModMain.MOD_ID, "chest_boat/whitetree"), "main");

    public ModChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.MOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public @NotNull Item getDropItem() {
        switch (getModVariant()) {
            case DARKTREE -> {
                return ItemRegistry.DARKTREE_CHEST_BOAT.get();
            }
            case WHITETREE -> {
                return ItemRegistry.WHITETREE_CHEST_BOAT.get();
            }
        }
        return super.getDropItem();
    }

    public void setVariant(ModBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_ID_TYPE, ModBoatEntity.Type.DARKTREE.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(ModBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public ModBoatEntity.Type getModVariant() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
