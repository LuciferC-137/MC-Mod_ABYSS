package wardentools.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModAdvancementLootTables implements LootTableSubProvider {
    public static final ResourceKey<LootTable> WIND_JOURNAL_LT = ResourceKey.create(Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "advancements/wind_journal"));

    private final Map<ResourceKey<LootTable>, LootTable.Builder> lootTables = new HashMap<>();
    protected final HolderLookup.Provider registries;

    public ModAdvancementLootTables(HolderLookup.Provider provider) {
        this.registries = provider;
    }

    private void generate() {
        this.addSimpleLootTable(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemRegistry.WIND_JOURNAL.get())),
                WIND_JOURNAL_LT);
    }

    private void addSimpleLootTable(LootPool.Builder lootTable, ResourceKey<LootTable> key) {
        this.lootTables.put(key, LootTable.lootTable().withPool(lootTable));
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        this.generate();
        for (Map.Entry<ResourceKey<LootTable>, LootTable.Builder> entry : lootTables.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }
}

