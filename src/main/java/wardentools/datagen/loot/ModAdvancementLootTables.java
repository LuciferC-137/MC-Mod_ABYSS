package wardentools.datagen.loot;

import net.minecraft.data.loot.LootTableSubProvider;
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

    public static final ResourceLocation WIND_JOURNAL_LT =
            new ResourceLocation(ModMain.MOD_ID, "advancements/wind_journal");

    private final Map<ResourceLocation, LootTable.Builder> lootTables =
            new HashMap<>();

    private void generate() {
        this.addSimpleLootTable(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(
                                ItemRegistry.WIND_JOURNAL.get()
                        )),
                WIND_JOURNAL_LT
        );
    }

    private void addSimpleLootTable(LootPool.Builder lootPool, ResourceLocation key) {
        this.lootTables.put(key, LootTable.lootTable().withPool(lootPool));
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        this.generate();
        for (Map.Entry<ResourceLocation, LootTable.Builder> entry : this.lootTables.entrySet()) {
            consumer.accept(entry.getKey(), entry.getValue());
        }
    }
}


