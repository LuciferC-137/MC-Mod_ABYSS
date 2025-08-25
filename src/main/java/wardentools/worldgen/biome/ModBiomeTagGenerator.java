package wardentools.worldgen.biome;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;
import wardentools.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends TagsProvider<Biome> {

    @SuppressWarnings("deprecation")
    public ModBiomeTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, Registries.BIOME, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        HolderLookup.RegistryLookup<Biome> biomeLookup = provider.lookupOrThrow(Registries.BIOME);
        this.tag(ModTags.Biomes.IS_ABYSS)
                .add(biomeLookup.getOrThrow(ModBiomes.DEEP_FOREST).key())
                .add(biomeLookup.getOrThrow(ModBiomes.WHITE_FOREST).key())
                .add(biomeLookup.getOrThrow(ModBiomes.CRYSTAL_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.BLINDING_DEPTH).key());
        this.tag(ModTags.Biomes.DEEPFOREST)
                .add(biomeLookup.getOrThrow(ModBiomes.DEEP_FOREST).key());
        this.tag(ModTags.Biomes.WHITE_FOREST)
                .add(biomeLookup.getOrThrow(ModBiomes.WHITE_FOREST).key());
        this.tag(ModTags.Biomes.CRYSTAL_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.CRYSTAL_CAVE).key());
    }
}
