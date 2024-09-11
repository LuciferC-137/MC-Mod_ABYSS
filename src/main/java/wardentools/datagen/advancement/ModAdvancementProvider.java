package wardentools.datagen.advancement;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModAdvancementProvider extends ForgeAdvancementProvider {

    public ModAdvancementProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> registries,
                                  ExistingFileHelper existingFileHelper,
                                  List<AdvancementGenerator> subProviders) {
        super(output, registries, existingFileHelper, subProviders);
    }

    public static ModAdvancementProvider create(PackOutput output,
                                                CompletableFuture<HolderLookup.Provider> registries,
                                                ExistingFileHelper existingFileHelper) {
        // Initialize the list of advancement generators
        List<AdvancementGenerator> generators = List.of(
                new ModAdvancementGenerator()
        );
        return new ModAdvancementProvider(output, registries, existingFileHelper, generators);
    }

}
