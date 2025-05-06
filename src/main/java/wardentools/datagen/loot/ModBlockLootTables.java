package wardentools.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.block.BlueBush;
import wardentools.items.ItemRegistry;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(CompletableFuture<HolderLookup.Provider> lookupProvider) throws ExecutionException, InterruptedException {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider.get());
    }

    @Override
    protected void generate() {
    	//Drop self blocks
    	this.addDropSelf(BlockRegistry.DEEPBLOCK);
    	this.addDropSelf(BlockRegistry.DARKDIRT);
    	this.addDropSelf(BlockRegistry.DARKGRASS_BLOCK);
    	this.addDropSelf(BlockRegistry.ABYSSALITE);
    	this.addDropSelf(BlockRegistry.CHISELED_ABYSSALITE);
    	this.addDropSelf(BlockRegistry.ABYSSALITE_BRICKS);
    	this.addDropSelf(BlockRegistry.ABYSSALITE_BRICKS_STAIRS);
    	this.addDropSelf(BlockRegistry.ABYSSALITE_BRICKS_SLAB);
    	this.addDropSelf(BlockRegistry.ABYSSALITE_BRICKS_WALL);
    	this.addDropSelf(BlockRegistry.CRACKED_ABYSSALITE_BRICKS);
		this.addDropSelf(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR);
		this.addDropSelf(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_WALL);
		this.addDropSelf(BlockRegistry.PALE_CRISTAL_BLOCK);
		this.addDropSelf(BlockRegistry.CITRINE_BLOCK);
		this.addDropSelf(BlockRegistry.ECHO_BLOCK);
		this.addDropSelf(BlockRegistry.RUBY_BLOCK);
		this.addDropSelf(BlockRegistry.MALACHITE_BLOCK);
		this.addDropSelf(BlockRegistry.SOLID_CORRUPTION);
		this.addDropSelf(BlockRegistry.WIND_WHISPERER);
    	
    	this.addDropSelf(BlockRegistry.DARKTREE_LOG);
    	this.addDropSelf(BlockRegistry.DARKTREE_PLANKS);
    	this.addDropSelf(BlockRegistry.DARKTREE_SAPLING);
    	this.addDropSelf(BlockRegistry.DARKTREE_WOOD);
    	this.addDropSelf(BlockRegistry.STRIPPED_DARKTREE_LOG);
    	this.addDropSelf(BlockRegistry.STRIPPED_DARKTREE_WOOD);
    	this.addDropSelf(BlockRegistry.DARKTREE_STAIR);
    	this.addDropSelf(BlockRegistry.DARKTREE_BUTTON);
    	this.addDropSelf(BlockRegistry.DARKTREE_PRESSURE_PLATE);
    	this.addDropSelf(BlockRegistry.DARKTREE_FENCE);
    	this.addDropSelf(BlockRegistry.DARKTREE_FENCE_GATE);
    	this.addDropSelf(BlockRegistry.DARKTREE_TRAPDOOR);
    	
    	this.addDropSelf(BlockRegistry.WHITETREE_LOG);
    	this.addDropSelf(BlockRegistry.WHITETREE_SAPLING);
    	this.addDropSelf(BlockRegistry.WHITETREE_WOOD);
    	this.addDropSelf(BlockRegistry.STRIPPED_WHITETREE_LOG);
    	this.addDropSelf(BlockRegistry.STRIPPED_WHITETREE_WOOD);
    	this.addDropSelf(BlockRegistry.WHITETREE_PLANKS);
    	this.addDropSelf(BlockRegistry.WHITETREE_STAIR);
    	this.addDropSelf(BlockRegistry.WHITETREE_BUTTON);
    	this.addDropSelf(BlockRegistry.WHITETREE_PRESSURE_PLATE);
    	this.addDropSelf(BlockRegistry.WHITETREE_FENCE);
    	this.addDropSelf(BlockRegistry.WHITETREE_FENCE_GATE);
    	this.addDropSelf(BlockRegistry.WHITETREE_TRAPDOOR);
    	this.addDropSelf(BlockRegistry.WHITE_TORCHFLOWER);

    	this.addDropSelf(BlockRegistry.PROTECTOR_INVOKER);
		this.addDropSelf(BlockRegistry.CONTAGION_INCARNATION_SKULL);
		this.addDropSelf(BlockRegistry.GRAMOPHONE);

		// Planks derivatives special drops
    	this.add(BlockRegistry.DARKTREE_SLAB.get(), 
    			block -> createSlabItemTable(BlockRegistry.DARKTREE_SLAB.get()));
    	this.add(BlockRegistry.DARKTREE_DOOR.get(), 
    			block -> createDoorTable(BlockRegistry.DARKTREE_DOOR.get()));
    	this.add(BlockRegistry.WHITETREE_SLAB.get(), 
    			block -> createSlabItemTable(BlockRegistry.WHITETREE_SLAB.get()));
    	this.add(BlockRegistry.WHITETREE_DOOR.get(), 
    			block -> createDoorTable(BlockRegistry.WHITETREE_DOOR.get()));
		this.add(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get(),
				block -> createSlabItemTable(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get()));

		// Vegetation drop
    	this.add(BlockRegistry.TALL_WHITE_GRASS.get(),
    			block -> createDoublePlantShearsDrop(BlockRegistry.WHITE_GRASS.get()));
    	this.add(BlockRegistry.WHITE_GRASS.get(),
    			block -> createShearsOnlyDrop(BlockRegistry.WHITE_GRASS.get()));
    	this.add(BlockRegistry.DEEPFLOWER.get(),
                block -> createDoubleBlockSingleItemDrop(ItemRegistry.DEEPFLOWER));
    	this.add(BlockRegistry.TALL_DARK_GRASS.get(),
    			block -> createDoublePlantShearsDrop(BlockRegistry.DARK_GRASS.get()));
    	this.add(BlockRegistry.DARK_GRASS.get(),
    			block -> createShearsOnlyDrop(BlockRegistry.DARK_GRASS.get()));

		// Ore drop
		this.add(BlockRegistry.ABYSSALITE_COAL_ORE.get(),
				block -> this.createOreDrop(BlockRegistry.ABYSSALITE_COAL_ORE.get(), Items.COAL));
		this.add(BlockRegistry.ABYSSALITE_LAPIS_ORE.get(),
				block -> this.createLapisOreDrops(BlockRegistry.ABYSSALITE_LAPIS_ORE.get()));
		this.add(BlockRegistry.ABYSSALITE_DIAMOND_ORE.get(),
				block -> this.createOreDrop(BlockRegistry.ABYSSALITE_DIAMOND_ORE.get(), Items.DIAMOND));
		this.add(BlockRegistry.ABYSSALITE_DEEP_ORE.get(),
				block -> this.createNumberBasedOreDrop(BlockRegistry.ABYSSALITE_DEEP_ORE.get(),
						ItemRegistry.DEEP_FRAGMENT.get(), 1, 3));

		// Cristal drop
		this.add(BlockRegistry.CITRINE.get(),
				block -> this.cristalLoot(BlockRegistry.CITRINE.get(), ItemRegistry.CITRINE_FRAGMENT.get()));
		this.add(BlockRegistry.ECHO_CRISTAL.get(),
				block -> this.cristalLoot(BlockRegistry.ECHO_CRISTAL.get(), Items.ECHO_SHARD));
		this.add(BlockRegistry.RUBY.get(),
				block -> this.cristalLoot(BlockRegistry.RUBY.get(), ItemRegistry.RUBY_FRAGMENT.get()));
		this.add(BlockRegistry.MALACHITE.get(),
				block -> this.cristalLoot(BlockRegistry.MALACHITE.get(), ItemRegistry.MALACHITE_FRAGMENT.get()));
		this.add(BlockRegistry.PALE_CRISTAL.get(),
				block -> this.cristalLoot(BlockRegistry.PALE_CRISTAL.get(), ItemRegistry.PALE_SHARD.get()));
		this.add(BlockRegistry.DEEP_CRISTAL.get(),
				block -> this.cristalLoot(BlockRegistry.DEEP_CRISTAL.get(), ItemRegistry.DEEP_FRAGMENT.get()));
		this.add(BlockRegistry.RADIANCE_CRISTAL.get(),
				block -> this.cristalLoot(BlockRegistry.RADIANCE_CRISTAL.get(), ItemRegistry.RADIANCE_FRAGMENT.get()));

		// Leaves drop
		this.add(BlockRegistry.DARKTREE_LEAVES.get(),
				block -> this.createCustomLeaveDrop(BlockRegistry.DARKTREE_LEAVES.get(),
						BlockRegistry.DARKTREE_SAPLING.get(), ItemRegistry.DEEP_FRUIT.get(), 0.05F));
		this.add(BlockRegistry.WHITETREE_LEAVES.get(),
				block -> this.createCustomLeaveDrop(BlockRegistry.WHITETREE_LEAVES.get(),
						BlockRegistry.WHITETREE_SAPLING.get(), ItemRegistry.WHITE_SEED.get(), 0.05F));

		// Special drop
		this.add(BlockRegistry.POTTED_WHITE_TORCHFLOWER.get(),
				createPotFlowerItemTable(BlockRegistry.WHITE_TORCHFLOWER.get()));
		this.add(BlockRegistry.BLACK_LANTERN.get(),
				block -> createBlackLanternItemDrop(BlockRegistry.BLACK_LANTERN));
		this.add(BlockRegistry.REINFORCED_GLASS.get(),
				block -> createReinforcedGlassItemDrop(BlockRegistry.REINFORCED_GLASS));
		this.add(BlockRegistry.BLUE_BUSH.get(),
				block -> createBlueBushLoot(BlockRegistry.BLUE_BUSH, ItemRegistry.BLUE_GLOW_BERRIES));
    }

    private void addDropSelf(RegistryObject<Block> block) {
        if (block.isPresent()) {
            this.dropSelf(block.get());
        }
    }
    
    private LootTable.Builder createDoubleBlockSingleItemDrop(RegistryObject<Item> item) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(item.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.5f))));
    }

	private LootTable.Builder createBlueBushLoot(RegistryObject<Block> bushBlock, RegistryObject<Item> berryItem) {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(bushBlock.get()))
						.when(HAS_SHEARS))

				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(berryItem.get()))
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(bushBlock.get())
								.setProperties(StatePropertiesPredicate.Builder.properties()
										.hasProperty(BlueBush.BERRY_STATE, BlueBush.BerryState.BLUE_BERRY))));
	}


	private LootTable.Builder createBlackLanternItemDrop(RegistryObject<Block> block) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(block.get()).when(this.hasSilkTouch()))
				)
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(ItemRegistry.PALE_SHARD.get())
										.apply(SetItemCountFunction.setCount(
												UniformGenerator.between(1, 3)))
										.apply(ApplyBonusCount.addUniformBonusCount(
												registrylookup.getOrThrow(Enchantments.FORTUNE)))
										.when(this.doesNotHaveSilkTouch())
								))
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.ECHO_SHARD)
										.apply(SetItemCountFunction.setCount(
												UniformGenerator.between(1, 3)))
										.apply(ApplyBonusCount.addUniformBonusCount(
												registrylookup.getOrThrow(Enchantments.FORTUNE))))
								.when(this.doesNotHaveSilkTouch())
				);
	}

	private LootTable.Builder createReinforcedGlassItemDrop(RegistryObject<Block> block) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(block.get()).when(this.hasSilkTouch()))
				)
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(ItemRegistry.PALE_SHARD.get())
										.apply(SetItemCountFunction.setCount(
												UniformGenerator.between(0, 2)))
										.apply(ApplyBonusCount.addUniformBonusCount(
												registrylookup.getOrThrow(Enchantments.FORTUNE)))
										.when(this.doesNotHaveSilkTouch()))
								.add(LootItem.lootTableItem(Items.ECHO_SHARD)
										.apply(SetItemCountFunction.setCount(
												UniformGenerator.between(0, 2)))
										.apply(ApplyBonusCount.addUniformBonusCount(
												registrylookup.getOrThrow(Enchantments.FORTUNE)))
										.when(this.doesNotHaveSilkTouch())
								))
				.withPool(
						LootPool.lootPool()
								.setRolls(UniformGenerator.between(0, 1))
								.add(LootItem.lootTableItem(Items.IRON_NUGGET)
										.apply(SetItemCountFunction.setCount(
												UniformGenerator.between(0, 3)))
										.apply(ApplyBonusCount.addUniformBonusCount(
												registrylookup.getOrThrow(Enchantments.FORTUNE))))
								.when(this.doesNotHaveSilkTouch())
				);
	}

	protected LootTable.Builder createNumberBasedOreDrop(Block block, ItemLike drop , float min, float max) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return createSilkTouchDispatchTable(block,
				this.applyExplosionDecay(block,
						LootItem.lootTableItem(drop)
						.apply(SetItemCountFunction
						.setCount(UniformGenerator.between(min, max)))
						.apply(ApplyBonusCount.addOreBonusCount(
								registrylookup.getOrThrow(Enchantments.FORTUNE)))));
	}

	private LootTable.Builder cristalLoot(Block block, ItemLike cristal) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return createSilkTouchDispatchTable(block,
				this.applyExplosionDecay(block,
						LootItem.lootTableItem(cristal)
						.apply(SetItemCountFunction
						.setCount(UniformGenerator.between(1, 2)))
						.apply(ApplyBonusCount.addUniformBonusCount(
								registrylookup.getOrThrow(Enchantments.FORTUNE)))));
	}

	protected LootTable.Builder createCustomLeaveDrop(Block leave, Block sapling, ItemLike fruit, float... f) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return this.createLeavesDrops(leave, sapling, f)
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.when(this.doesNotHaveShearsOrSilkTouch())
						.add(((LootPoolSingletonContainer.Builder<?>)
								this.applyExplosionCondition(leave, LootItem.lootTableItem(fruit)))
								.when(BonusLevelTableCondition
										.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE),
												new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
	}

	private LootItemCondition.Builder hasShearsOrSilkTouch() {
		return HAS_SHEARS.or(this.hasSilkTouch());
	}

	private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
		return this.hasShearsOrSilkTouch().invert();
	}

	@Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
    	Set<Block> blocksToIgnore = Set.of(
    	        BlockRegistry.RADIANCE_CATALYST.get(),
				BlockRegistry.LIQUID_CORRUPTION_BLOCK.get(),
				BlockRegistry.ABYSS_PORTAL_BLOCK.get(),
				BlockRegistry.DYSFUNCTIONNING_CATALYST.get(),
				BlockRegistry.SOUL_SPAWNER.get()
    	);
    	List<Block> knownBlocks = BlockRegistry.REGISTAR.getEntries().stream()
    	        .map(RegistryObject::get)
    	        .filter(block -> !blocksToIgnore.contains(block))
    	        .collect(Collectors.toList());
        return knownBlocks;
    }
}