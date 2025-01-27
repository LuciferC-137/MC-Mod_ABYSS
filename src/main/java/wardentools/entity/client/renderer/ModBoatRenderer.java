package wardentools.entity.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import wardentools.entity.custom.DarktreeBoat;
import wardentools.entity.custom.DarktreeChestBoat;
import wardentools.entity.custom.WhitetreeBoat;
import wardentools.entity.custom.WhitetreeChestBoat;
import wardentools.items.BoatItem;

public class ModBoatRenderer extends BoatRenderer {

    public ModBoatRenderer(EntityRendererProvider.Context pContext, BoatItem.Type type) {
        super(pContext, getModelLayer(type));
    }

    private static ModelLayerLocation getModelLayer(BoatItem.Type type) {
        switch (type) {
            case DARKTREE_BOAT -> {
                return DarktreeBoat.DARKTREE_BOAT_LAYER;
            }
            case DARKTREE_CHEST_BOAT -> {
                return DarktreeChestBoat.DARKTREE_CHEST_BOAT_LAYER;
            }
            case WHITETREE_BOAT -> {
                return WhitetreeBoat.WHITETREE_BOAT_LAYER;
            }
            case WHITETREE_CHEST_BOAT -> {
                return WhitetreeChestBoat.WHITETREE_CHEST_BOAT_LAYER;
            }
        }
        return DarktreeBoat.DARKTREE_BOAT_LAYER;
    }

}