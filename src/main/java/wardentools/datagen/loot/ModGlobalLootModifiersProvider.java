package wardentools.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

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
        add("stealth_book_from_ancient_city", new AddStealthBookModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(
                                ResourceLocation.withDefaultNamespace("chests/ancient_city")
                        ).build()
                }
        ));
    }
}