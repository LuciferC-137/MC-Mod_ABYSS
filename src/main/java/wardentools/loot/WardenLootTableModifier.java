package wardentools.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.items.ItemRegistry;

@Mod.EventBusSubscriber(modid = "wardentools")
public class WardenLootTableModifier {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation wardenLootTable = new ResourceLocation("minecraft", "entities/warden");
        if (event.getName().equals(wardenLootTable)) {
            LootTable table = event.getTable();

            // 70% chance to get "warden_heart"
            LootPool.Builder poolBuilder1 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ItemRegistry.WARDEN_HEART.get())
                            .setWeight(7))
                    .add(EmptyLootItem.emptyItem()
                            .setWeight(3));

            // 50% chance to get "deepblock"
            LootPool.Builder poolBuilder2 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ItemRegistry.DEEPBLOCK.get())
                            .setWeight(5))
                    .add(EmptyLootItem.emptyItem()
                            .setWeight(5));

            // 100% chance to get between 1 and 3 "deepcristal"
            LootPool.Builder poolBuilder3 = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ItemRegistry.DEEPCRISTAL.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));

            // Add the pools to the loot table
            table.addPool(poolBuilder1.build());
            table.addPool(poolBuilder2.build());
            table.addPool(poolBuilder3.build());
        }
    }
}
