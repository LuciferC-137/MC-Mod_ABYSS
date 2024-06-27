package wardentools.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import wardentools.ModMain;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(ModMain.MOD_ID, "overworld"), 5));
    }
}