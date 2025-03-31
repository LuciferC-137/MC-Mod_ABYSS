package wardentools.datagen.loot;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import wardentools.ModMain;

import java.util.concurrent.CompletableFuture;


public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output,
                                          CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ModMain.MOD_ID);
    }

    @Override
    protected void start() {

    }
}