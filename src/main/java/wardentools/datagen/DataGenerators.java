package wardentools.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import wardentools.ModMain;
import wardentools.advancement.ModAdvancementProvider;
import wardentools.datagen.loot.ModGlobalLootModifiersProvider;
import wardentools.datagen.loot.ModLootTableProvider;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput, lookupProvider));
        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModRecipesGenerator(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModDataBuilderProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), ModAdvancementProvider.create(packOutput, lookupProvider, existingFileHelper));

    }
}