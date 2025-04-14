package wardentools.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.ModMain;

@OnlyIn(Dist.CLIENT)
public class ModBoat {

    public static final ModelLayerLocation DARKTREE_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "boat/darktree"), "main");
    public static final ModelLayerLocation WHITETREE_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "boat/whitetree"), "main");


    public static final ModelLayerLocation DARKTREE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chest_boat/darktree"), "main");
    public static final ModelLayerLocation WHITETREE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chest_boat/whitetree"), "main");

}
