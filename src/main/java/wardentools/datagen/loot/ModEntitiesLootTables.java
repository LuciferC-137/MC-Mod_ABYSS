package wardentools.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;

import java.util.stream.Stream;

public class ModEntitiesLootTables extends EntityLootSubProvider {

    public ModEntitiesLootTables(HolderLookup.Provider provider) {
        this(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    protected ModEntitiesLootTables(FeatureFlagSet flagSet, HolderLookup.Provider provider) {
        super(flagSet, provider);
    }

    @Override
    public void generate() {
        this.add(ModEntities.CONTAGION_INCARNATION.get(), LootTable.lootTable());

        this.add(ModEntities.SHADOW.get(), LootTable.lootTable());

        this.add(ModEntities.PALE_WANDERER.get(), LootTable.lootTable().withPool(
                rareDropPool(ItemRegistry.WANDERER_PAW.get(), 1, 1, 0.5F)
        ));

        this.add(ModEntities.DEEPLURKER.get(), LootTable.lootTable().withPool(
                rareDropPool(ItemRegistry.LURKER_EYE.get(), 1, 2, 0.6F)
        ));

        this.add(ModEntities.TEMPER.get(), LootTable.lootTable().withPool(
                simpleLootPool(ItemRegistry.PALE_SHARD.get(), 0, 3)
        ));

        this.add(ModEntities.PARASYTE.get(), LootTable.lootTable());

        this.add(ModEntities.PROTECTOR.get(), LootTable.lootTable());

        this.add(ModEntities.NOCTILURE.get(), LootTable.lootTable().withPool(
                simpleLootPool(ItemRegistry.NOCTILURE_FEATHER.get(), 1, 2)
        ));
    }

    private LootPool.Builder simpleLootPool(ItemLike item, int minCount, int maxCount) {
        return LootPool.lootPool()
                .add(LootItem.lootTableItem(item)
                        .setWeight(1)
                        .apply(SetItemCountFunction
                                .setCount(UniformGenerator.between(minCount, maxCount)))
                );
    }

    private LootPool.Builder rareDropPool(ItemLike item, int minCount, int maxCount, float dropChance) {
        return LootPool.lootPool()
                .add(LootItem.lootTableItem(item)
                        .setWeight(1)
                        .apply(SetItemCountFunction
                                .setCount(UniformGenerator.between(minCount, maxCount)))
                )
                .when(LootItemRandomChanceCondition.randomChance(dropChance));
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return Stream.of(
                ModEntities.CONTAGION_INCARNATION.get(),
                ModEntities.SHADOW.get(),
                ModEntities.PALE_WANDERER.get(),
                ModEntities.DEEPLURKER.get(),
                ModEntities.TEMPER.get(),
                ModEntities.PARASYTE.get(),
                ModEntities.PROTECTOR.get(),
                ModEntities.NOCTILURE.get()
        );
    }
}

