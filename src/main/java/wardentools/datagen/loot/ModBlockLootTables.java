package wardentools.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
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

    	this.dropSelf(BlockRegistry.PROTECTOR_INVOKER.get());
		this.dropSelf(BlockRegistry.BLACK_LANTERN.get());
		this.dropSelf(BlockRegistry.CONTAGION_INCARNATION_SKULL.get());

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
    	this.add(BlockRegistry.BLUE_BUSH.get(),
			block -> createShearsOnlyDrop(BlockRegistry.BLUE_BUSH.get())
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.add(LootItem.lootTableItem(ItemRegistry.BLUE_GLOW_BERRIES.get())
						.when(LootItemRandomChanceCondition.randomChance(0.2f)))));
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

		// Special drop
		this.add(BlockRegistry.POTTED_WHITE_TORCHFLOWER.get(),
				createPotFlowerItemTable(BlockRegistry.WHITE_TORCHFLOWER.get()));
		this.add(BlockRegistry.REINFORCED_GLASS.get(),
				createSilkTouchDispatchTable(BlockRegistry.REINFORCED_GLASS.get(),
						LootItem.lootTableItem(Items.AIR)));
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

	protected LootTable.Builder createNumberBasedOreDrop(Block block, ItemLike drop , float min, float max) {
		return createSilkTouchDispatchTable(block,
				this.applyExplosionDecay(block,
						LootItem.lootTableItem(drop)
						.apply(SetItemCountFunction
						.setCount(UniformGenerator.between(min, max)))
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
	}

	@Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
    	Set<Block> blocksToIgnore = Set.of(
    	        BlockRegistry.RADIANCE_CRISTAL.get(),
    	        BlockRegistry.DARKTREE_LEAVES.get(),
    	        BlockRegistry.WHITETREE_LEAVES.get(),
    	        BlockRegistry.RADIANCE_CATALYST.get(),
				BlockRegistry.DEEP_CRISTAL.get(),
				BlockRegistry.LIQUID_CORRUPTION_BLOCK.get(),
				BlockRegistry.CITRINE.get(),
				BlockRegistry.ECHO_CRISTAL.get(),
				BlockRegistry.RUBY.get(),
				BlockRegistry.MALACHITE.get(),
				BlockRegistry.PALE_CRISTAL.get(),
				BlockRegistry.ABYSS_PORTAL_BLOCK.get(),
				BlockRegistry.DYSFUNCTIONNING_CATALYST.get()
    	);
    	List<Block> knownBlocks = BlockRegistry.REGISTAR.getEntries().stream()
    	        .map(RegistryObject::get)
    	        .filter(block -> !blocksToIgnore.contains(block))
    	        .collect(Collectors.toList());
        return knownBlocks;
    }
}