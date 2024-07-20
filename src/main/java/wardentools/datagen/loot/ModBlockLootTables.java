package wardentools.datagen.loot;




import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
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
    	
    	
    	this.addDropSelf(BlockRegistry.DEEPBLOCK);
    	this.addDropSelf(BlockRegistry.DARKDIRT);
    	this.addDropSelf(BlockRegistry.DARKGRASS_BLOCK);
    	this.addDropSelf(BlockRegistry.DEEP_CRISTAL);
    	
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
    	   	
    	this.add(BlockRegistry.DARKTREE_SLAB.get(), 
    			block -> createSlabItemTable(BlockRegistry.DARKTREE_SLAB.get()));
    	this.add(BlockRegistry.DARKTREE_DOOR.get(), 
    			block -> createDoorTable(BlockRegistry.DARKTREE_DOOR.get()));
    	
    	this.add(BlockRegistry.WHITETREE_SLAB.get(), 
    			block -> createSlabItemTable(BlockRegistry.WHITETREE_SLAB.get()));
    	this.add(BlockRegistry.WHITETREE_DOOR.get(), 
    			block -> createDoorTable(BlockRegistry.WHITETREE_DOOR.get()));
    	
    	this.add(BlockRegistry.TALL_WHITE_GRASS.get(),
    			block -> createDoublePlantShearsDrop(BlockRegistry.WHITE_GRASS.get()));
    	
    	this.add(BlockRegistry.WHITE_GRASS.get(),
    			block -> createShearsOnlyDrop(BlockRegistry.WHITE_GRASS.get()));
    	
   	
        //Special leave drop
    	//this.add(BlockRegistry.DARKTREE_LEAVES.get(), block -> 
		//createLeavesDrops(block, BlockRegistry.DARKTREE_SAPLING.get(),
		//		NORMAL_LEAVES_SAPLING_CHANCES));
    	//this.add(BlockRegistry.DARKTREE_LEAVES.get(), this::dropDarktreeLeaves);

    }

    private void addDropSelf(RegistryObject<Block> block) {
        if (block.isPresent()) {
            this.dropSelf(block.get());
        }
    }
    
    @SuppressWarnings("unused")
	private LootTable.Builder dropDarktreeLeaves(Block block) {
        LootItemCondition.Builder shearsCondition = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
        LootItemCondition.Builder silkTouchCondition = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .when(shearsCondition)
                                .when(silkTouchCondition)
                        )
                        .add(LootItem.lootTableItem(BlockRegistry.DARKTREE_SAPLING.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                        .add(LootItem.lootTableItem(ItemRegistry.DEEP_FRUIT.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        )
                );
    }
 

	@Override
    protected Iterable<Block> getKnownBlocks() {
    	Set<Block> blocksToIgnore = Set.of(
    	        BlockRegistry.PALE_SHARD.get(),
    	        BlockRegistry.DARKTREE_LEAVES.get(),
    	        BlockRegistry.WHITETREE_LEAVES.get(),
    	        BlockRegistry.RADIANCE_CATALYST.get()
    	);
    	List<Block> knownBlocks = BlockRegistry.REGISTAR.getEntries().stream()
    	        .map(RegistryObject::get)
    	        .filter(block -> !blocksToIgnore.contains(block))
    	        .collect(Collectors.toList());
        return knownBlocks;
    }
}