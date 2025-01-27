package wardentools.entity.custom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import wardentools.ModMain;
import wardentools.entity.ModEntities;

import java.util.function.Supplier;

public class WhitetreeChestBoat extends ChestBoat {
    public static final ModelLayerLocation WHITETREE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chest_boat/whitetree"), "main");

    public WhitetreeChestBoat(EntityType<? extends ChestBoat> pEntityType, Level pLevel, Supplier<Item> item) {
        super(pEntityType, pLevel, item);
    }

    public WhitetreeChestBoat(Level pLevel, double pX, double pY, double pZ, Supplier<Item> item) {
        this(ModEntities.WHITETREE_CHEST_BOAT.get(), pLevel, item);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

}
