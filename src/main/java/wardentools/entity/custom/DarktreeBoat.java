package wardentools.entity.custom;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import wardentools.ModMain;
import wardentools.entity.ModEntities;

import java.util.function.Supplier;

public class DarktreeBoat extends Boat {
    public static final ModelLayerLocation DARKTREE_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "boat/darktree"), "main");

    public DarktreeBoat(EntityType<? extends Boat> entityType, Level level, Supplier<Item> item) {
        super(entityType, level, item);
    }

    public DarktreeBoat(Level level, double pX, double pY, double pZ, Supplier<Item> item) {
        this(ModEntities.DARKTREE_BOAT.get(), level, item);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

}
