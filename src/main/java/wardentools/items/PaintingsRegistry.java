package wardentools.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class PaintingsRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTINGS
    	= DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, ModMain.MOD_ID);

    public static final RegistryObject<PaintingVariant> QUITE_LIFE = PAINTINGS.register("quiet_life",
            () -> new PaintingVariant(32, 32,
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/painting/quiet_life.png")));

    public static final RegistryObject<PaintingVariant> INCARNATION = PAINTINGS.register("incarnation",
            () -> new PaintingVariant(16, 16,
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/painting/incarnation.png")));

    public static final RegistryObject<PaintingVariant> ABYSS = PAINTINGS.register("abyss",
            () -> new PaintingVariant(32, 16,
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/painting/abyss.png")));

}