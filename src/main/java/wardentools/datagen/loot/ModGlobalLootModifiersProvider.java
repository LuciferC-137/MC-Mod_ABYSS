package wardentools.datagen.loot;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

import java.util.concurrent.CompletableFuture;


public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output,
                                          CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, ModMain.MOD_ID, lookupProvider);
    }

    @Override
    protected void start(HolderLookup.@NotNull Provider provider) {
    	
       
    }
}