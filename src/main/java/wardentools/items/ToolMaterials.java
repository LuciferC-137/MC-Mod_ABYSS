package wardentools.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import wardentools.tags.ModTags;

public class ToolMaterials {
    public static final ToolMaterial DEEPCRISTAL
            = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031, 4, 3f,
            20, ModTags.Items.DEEPCRISTAL_REPAIRS);

    public static final ToolMaterial RADIANCE_CRISTAL
            = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031, 4, 3f,
            20, ModTags.Items.RADIANCE_CRISTAL_REPAIRS);
}
