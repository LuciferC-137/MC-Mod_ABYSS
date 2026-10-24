package wardentools.datagen.loot;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.items.enchantment.EnchantmentRegistry;
import wardentools.misc.Crystal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {
    public static final ResourceLocation ANCIENT_CITADEL
            = new ResourceLocation(ModMain.MOD_ID, "chests/ancient_citadel");
    public static final ResourceLocation ABANDONED_EXPLORER_HOUSE
            = new ResourceLocation(ModMain.MOD_ID, "chests/abandoned_explorer_house");
    public static final ResourceLocation CRYSTAL_TEMPLE_AMETHYST
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_amethyst");
    public static final ResourceLocation CRYSTAL_TEMPLE_RUBY
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_ruby");
    public static final ResourceLocation CRYSTAL_TEMPLE_CITRINE 
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_citrine");
    public static final ResourceLocation CRYSTAL_TEMPLE_MALACHITE
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_malachite");
    public static final ResourceLocation CRYSTAL_TEMPLE_ECHO 
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_echo");
    public static final ResourceLocation CRYSTAL_TEMPLE_PALE 
            = new ResourceLocation(ModMain.MOD_ID, "chests/crystal_temple_pale");

    private final Map<ResourceLocation, LootTable.Builder> lootTables = new HashMap<>();
    protected final HolderLookup.Provider registries;


    public ModChestLootTables(HolderLookup.Provider provider) {
        this.registries = provider;
    }

    private void generate() {
        this.ancientCitadelChest();
        this.abandonedExplorerHouseChest();
        this.crystalTempleChest(CRYSTAL_TEMPLE_AMETHYST, Crystal.AMETHYST);
        this.crystalTempleChest(CRYSTAL_TEMPLE_RUBY, Crystal.RUBY);
        this.crystalTempleChest(CRYSTAL_TEMPLE_CITRINE, Crystal.CITRINE);
        this.crystalTempleChest(CRYSTAL_TEMPLE_MALACHITE, Crystal.MALACHITE);
        this.crystalTempleChest(CRYSTAL_TEMPLE_ECHO, Crystal.ECHO);
        this.crystalTempleChest(CRYSTAL_TEMPLE_PALE, Crystal.PALE);
    }

    private void crystalTempleChest(ResourceLocation key, Crystal crystal) {
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(UniformGenerator.between(4, 8))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                .add(LootItem.lootTableItem(Items.POTION)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(SetPotionFunction.setPotion(Potions.STRONG_REGENERATION)))
                .add(LootItem.lootTableItem(Items.POTION)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(SetPotionFunction.setPotion(Potions.LONG_NIGHT_VISION)))
                .add(LootItem.lootTableItem(Items.LINGERING_POTION)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(SetPotionFunction.setPotion(Potions.HEALING)))
                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))

                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(3)
                        .apply(EnchantRandomlyFunction.randomEnchantment()
                                .withEnchantment(Enchantments.SWIFT_SNEAK)))
                .add(this.stealthBook()
                        .setWeight(3)
                )

                .add(LootItem.lootTableItem(crystal.getShard())
                        .setWeight(10)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.DEPTH_BERRIES.get())
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(Items.SOUL_TORCH)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.CANDLE)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 10))))
                .add(LootItem.lootTableItem(Items.BONE)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))));

        this.addLootPools(key, pool);
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
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(30, 50))))
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
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(30, 50))))
                .add(LootItem.lootTableItem(Items.BOOK)
                        .setWeight(3)
                        .apply(EnchantRandomlyFunction.randomEnchantment()
                                .withEnchantment(Enchantments.SWIFT_SNEAK)))
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
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
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
                .add(LootItem.lootTableItem(ItemRegistry.CRYSTAL_RESONATOR.get())
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
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(30, 50))))
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
                        .apply(EnchantRandomlyFunction.randomEnchantment()
                                .withEnchantment(Enchantments.SWIFT_SNEAK)))
                .add(LootItem.lootTableItem(Items.SCULK)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 10))))
                .add(LootItem.lootTableItem(Items.SCULK_SENSOR)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(LootItem.lootTableItem(ItemRegistry.DEEPCRISTAL.get())
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                .add(this.stealthBook().setWeight(3))
                .add(LootItem.lootTableItem(Items.GLOW_BERRIES)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 15))))
                .add(LootItem.lootTableItem(Items.IRON_LEGGINGS)
                        .setWeight(3)
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 39))))
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
                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
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

    public LootPoolSingletonContainer.Builder<?> stealthBook() {
        Holder<Enchantment> stealth = this.registries
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(EnchantmentRegistry.STEALTH);

        return LootItem.lootTableItem(Items.BOOK)
                .apply(EnchantRandomlyFunction.randomEnchantment()
                        .withEnchantment(stealth.get()));
    }

    public void addLootPools(ResourceLocation location, LootPool.Builder lootTable1, LootPool.Builder lootTable2) {
        this.lootTables.put(location, LootTable.lootTable()
                .withPool(lootTable1)
                .withPool(lootTable2)
                .setRandomSequence(location));
    }

    public void addLootPools(ResourceLocation location, LootPool.Builder lootTable1) {
        this.lootTables.put(location, LootTable.lootTable()
                .withPool(lootTable1)
                .setRandomSequence(location));
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        this.generate();
        for (Map.Entry<ResourceLocation, LootTable.Builder> entry : this.lootTables.entrySet()) {
            consumer.accept(entry.getKey(), entry.getValue());
        }
    }
}
