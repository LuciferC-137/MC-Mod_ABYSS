package wardentools.items;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class PaintingsRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTINGS
    	= DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, ModMain.MOD_ID);

    public static final RegistryObject<PaintingVariant> QUITE_LIFE = PAINTINGS.register("quiet_life",
            () -> new PaintingVariant(32, 32)); 

}