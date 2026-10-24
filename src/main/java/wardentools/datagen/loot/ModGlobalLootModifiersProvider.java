package wardentools.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import wardentools.ModMain;


public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ModMain.MOD_ID);
    }

    @Override
    protected void start() {
        add("stealth_book_from_ancient_city", new AddStealthBookModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(
                                new ResourceLocation("chests/ancient_city")
                        ).build()
                }
        ));
    }
}