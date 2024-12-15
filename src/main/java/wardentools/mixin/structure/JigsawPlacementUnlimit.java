package wardentools.mixin.structure;

import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import wardentools.ModMain;

@Mixin(value = JigsawPlacement.class, priority = 999)
public class JigsawPlacementUnlimit {

    @ModifyConstant(method = "generateJigsaw", constant = @Constant(intValue = 128), require = 0, remap = false)
    private static int changeMaxGenDistance(int value) {
        return ModMain.NEW_STRUCTURE_SIZE;
    }
}
