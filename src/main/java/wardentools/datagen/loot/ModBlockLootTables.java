package wardentools.datagen.loot;



import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import wardentools.registries.BlockRegistry;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
    	
    	this.addDrop(BlockRegistry.DARKTREE_LEAVES);
    	this.addDrop(BlockRegistry.DARKTREE_LOG);
    	this.addDrop(BlockRegistry.DARKTREE_PLANKS);
    	this.addDrop(BlockRegistry.DARKTREE_SAPLING);
    	this.addDrop(BlockRegistry.DARKTREE_WOOD);
    	this.addDrop(BlockRegistry.DEEP_CRISTAL);
    	this.addDrop(BlockRegistry.DEEPBLOCK);
    	this.addDrop(BlockRegistry.STRIPPED_DARKTREE_LOG);
    	this.addDrop(BlockRegistry.STRIPPED_DARKTREE_WOOD);
    	this.addDrop(BlockRegistry.DARKDIRT);
    	this.addDrop(BlockRegistry.DARKGRASS_BLOCK);
    	
    	//Temporary blocks
    	//this.dropSelf(BlockRegistry.ABYSS_PORTAL.get());
    	
        
        this.add(BlockRegistry.DARKTREE_LEAVES.get(), block -> 
        		createLeavesDrops(block, BlockRegistry.DARKTREE_SAPLING.get(),
        				NORMAL_LEAVES_SAPLING_CHANCES));
    }
    
    private void addDrop(RegistryObject<Block> block) {
        if (block.isPresent()) {
            this.dropSelf(block.get());
        } else {
            System.out.println("Error: Block not present: " + block.getId());
        }
    }
 

    @Override
    protected Iterable<Block> getKnownBlocks() {
    	
        return BlockRegistry.REGISTAR.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}