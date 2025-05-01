package wardentools.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {
    public static final ResourceKey<LootTable> ANCIENT_CITADEL = ResourceKey.create(Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chests/ancient_citadel"));
    public static final ResourceKey<LootTable> ABANDONED_EXPLORER_HOUSE = ResourceKey.create(Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chests/abandoned_explorer_house"));

    private final Map<ResourceKey<LootTable>, LootTable.Builder> lootTables = new HashMap<>();
    protected final HolderLookup.Provider registries;


    public ModChestLootTables(HolderLookup.Provider provider) {
        this.registries = provider;
    }

    private void ancientCitadelChest() {
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(UniformGenerator.between(5, 10))
                .add(LootItem.lootTableItem(ItemRegistry.MALACHITE_FRAGMENT.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(ItemRegistry.RUBY_FRAGMENT.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(ItemRegistry.CITRINE_FRAGMENT.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(ItemRegistry.PALE_SHARD.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE))
                .add(LootItem.lootTableItem(ItemRegistry.CORRUPTED_ESSENCE.get())
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.SCULK_CATALYST)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(2))
                .add(LootItem.lootTableItem(Items.DIAMOND_HOE)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f)))
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(30, 50))))
                .add(LootItem.lootTableItem(Items.LEAD)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.SADDLE)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(ItemRegistry.DEEP_FOREST_MUSIC_DISC.get()).setWeight(2))
                .add(LootItem.lootTableItem(ItemRegistry.ABYSS_MUSIC_DISC.get()).setWeight(2))
                .add(LootItem.lootTableItem(ItemRegistry.WHITE_FOREST_MUSIC_DISC.get()).setWeight(2))
                .add(LootItem.lootTableItem(ItemRegistry.INCARNATION_MUSIC_DISC.get()).setWeight(1))
                .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS)
                        .setWeight(2)
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(30, 50))))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(3)
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)
                                .withEnchantment(this.registries.lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.SWIFT_SNEAK))))
                .add(LootItem.lootTableItem(Items.SCULK)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                .add(LootItem.lootTableItem(Items.SCULK_SENSOR)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.CANDLE)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                .add(LootItem.lootTableItem(Items.AMETHYST_SHARD)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.BLUE_GLOW_BERRIES.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.ECHO_SHARD)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.DISC_FRAGMENT_5)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.POTION)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(SetPotionFunction.setPotion(Potions.STRONG_REGENERATION)))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(5)
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                .add(LootItem.lootTableItem(Items.BONE)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.SOUL_LANTERN)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.REINFORCED_GLASS.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                .add(LootItem.lootTableItem(ItemRegistry.BLACK_LANTERN.get())
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.SOLID_CORRUPTION.get())
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.DEEP_FRAGMENT.get())
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                .add(LootItem.lootTableItem(ItemRegistry.RADIANCE_FRAGMENT.get())
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))));

        LootPool.Builder secondPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(EmptyLootItem.emptyItem().setWeight(75))
                .add(LootItem.lootTableItem(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(4))
                .add(LootItem.lootTableItem(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));

        this.addLootPools(ANCIENT_CITADEL, pool, secondPool);
    }

    private void abandonedExplorerHouseChest() {
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(UniformGenerator.between(5, 10))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE))
                .add(LootItem.lootTableItem(Items.COMPASS)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(ItemRegistry.DEEPINGOTS.get())
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.SCULK_CATALYST)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(2))
                .add(LootItem.lootTableItem(Items.DIAMOND_HOE)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f)))
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(30, 50))))
                .add(LootItem.lootTableItem(Items.LEAD)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.SADDLE)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(2))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(2))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(3)
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)
                                .withEnchantment(this.registries.lookupOrThrow(Registries.ENCHANTMENT)
                                        .getOrThrow(Enchantments.SWIFT_SNEAK))))
                .add(LootItem.lootTableItem(Items.SCULK)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                .add(LootItem.lootTableItem(Items.SCULK_SENSOR)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.DEEPCRISTAL.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.GLOW_BERRIES)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.IRON_LEGGINGS)
                        .setWeight(3)
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.registries, UniformGenerator.between(20, 39))))
                .add(LootItem.lootTableItem(Items.ECHO_SHARD)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.DISC_FRAGMENT_5)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.POTION)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(SetPotionFunction.setPotion(Potions.STRONG_REGENERATION)))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(5)
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                .add(LootItem.lootTableItem(Items.BONE)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.SOUL_TORCH)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.COAL)
                        .setWeight(7)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 15))));

        this.addLootPools(ABANDONED_EXPLORER_HOUSE, pool);
    }

    private void generate() {
        this.ancientCitadelChest();
        this.abandonedExplorerHouseChest();
    }

    public void addLootPools(ResourceKey<LootTable> key, LootPool.Builder lootTable1, LootPool.Builder lootTable2) {
        this.lootTables.put(key, LootTable.lootTable()
                .withPool(lootTable1)
                .withPool(lootTable2)
                .setRandomSequence(key.location()));
    }

    public void addLootPools(ResourceKey<LootTable> key, LootPool.Builder lootTable1) {
        this.lootTables.put(key, LootTable.lootTable()
                .withPool(lootTable1)
                .setRandomSequence(key.location()));
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        this.generate();
        for (Map.Entry<ResourceKey<LootTable>, LootTable.Builder> entry : this.lootTables.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }
}
