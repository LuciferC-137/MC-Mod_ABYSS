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
                .add(biomeLookup.getOrThrow(ModBiomes.BLINDING_DEPTH).key())
                .add(biomeLookup.getOrThrow(ModBiomes.AMETHYST_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.RUBY_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.CITRINE_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.MALACHITE_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.ECHO_CAVE).key())
                .add(biomeLookup.getOrThrow(ModBiomes.PALE_CAVE).key());
        this.tag(ModTags.Biomes.DEEPFOREST)
                .add(biomeLookup.getOrThrow(ModBiomes.DEEP_FOREST).key());
        this.tag(ModTags.Biomes.WHITE_FOREST)
                .add(biomeLookup.getOrThrow(ModBiomes.WHITE_FOREST).key());
        this.tag(ModTags.Biomes.CRYSTAL_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.CRYSTAL_CAVE).key());
        this.tag(ModTags.Biomes.AMETHYST_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.AMETHYST_CAVE).key());
        this.tag(ModTags.Biomes.RUBY_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.RUBY_CAVE).key());
        this.tag(ModTags.Biomes.CITRINE_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.CITRINE_CAVE).key());
        this.tag(ModTags.Biomes.MALACHITE_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.MALACHITE_CAVE).key());
        this.tag(ModTags.Biomes.ECHO_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.ECHO_CAVE).key());
        this.tag(ModTags.Biomes.PALE_CAVES)
                .add(biomeLookup.getOrThrow(ModBiomes.PALE_CAVE).key());
    }
}
