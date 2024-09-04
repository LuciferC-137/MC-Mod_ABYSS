package wardentools.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
            generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
            ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
            generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
            generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput));
            generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
            generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
            generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
            
    }
}