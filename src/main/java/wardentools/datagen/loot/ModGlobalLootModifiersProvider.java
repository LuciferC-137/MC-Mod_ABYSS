package wardentools.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
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
        add("stealth_book_from_ancient_city", new AddStealthBookModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(
                                ResourceLocation.withDefaultNamespace("chests/ancient_city")
                        ).build()
                }
        ));
    }
}