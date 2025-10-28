package wardentools.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ModMain.MOD_ID);

    public static final RegistryObject<StructureType<CrystalTemple>> CRYSTAL_TEMPLE =
            STRUCTURE_TYPES.register("crystal_temple", () -> () -> CrystalTemple.MAP_CODEC);

}

