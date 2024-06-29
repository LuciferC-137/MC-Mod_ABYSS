package wardentools.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import wardentools.ModMain;
import wardentools.datagen.tags.ModTags;
import wardentools.worldgen.biome.ModBiomes;

public class ModBiomeTagGenerator extends BiomeTagsProvider {

	public ModBiomeTagGenerator(PackOutput packOuput,
			CompletableFuture<Provider> provider, ExistingFileHelper existingFileHelper) {
		super(packOuput, provider, ModMain.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider Provider) {
		this.tag(ModTags.Biomes.HAS_EXPLORER_HOUSE)
		.add(ModBiomes.DEEP_FOREST)
		.add(ModBiomes.WASTE_LAND);
		
		this.tag(BiomeTags.HAS_ANCIENT_CITY)
		.add(ModBiomes.DEEP_FOREST)
		.add(ModBiomes.WASTE_LAND);
	}

}
