package wardentools.advancement;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModAdvancementProvider extends AdvancementProvider {

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
