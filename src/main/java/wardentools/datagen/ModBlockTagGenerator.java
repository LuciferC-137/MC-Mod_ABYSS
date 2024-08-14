package wardentools.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ModMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(BlockRegistry.DEEP_CRISTAL.get())
            .add(BlockRegistry.DEEPBLOCK.get())
            .add(BlockRegistry.PALE_SHARD.get())
            .add(BlockRegistry.GRAVESTONE.get());


        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
        	.add(BlockRegistry.DEEP_CRISTAL.get())
        	.add(BlockRegistry.DEEPBLOCK.get())
        	.add(BlockRegistry.PALE_SHARD.get());
        
        
        this.tag(BlockTags.LOGS_THAT_BURN)
	        .add(BlockRegistry.DARKTREE_LOG.get())
	        .add(BlockRegistry.DARKTREE_WOOD.get())
	        .add(BlockRegistry.STRIPPED_DARKTREE_LOG.get())
	        .add(BlockRegistry.STRIPPED_DARKTREE_WOOD.get())
	        .add(BlockRegistry.WHITETREE_LOG.get())
	        .add(BlockRegistry.WHITETREE_WOOD.get())
	        .add(BlockRegistry.STRIPPED_WHITETREE_LOG.get())
	        .add(BlockRegistry.STRIPPED_WHITETREE_WOOD.get());

		this.tag(BlockTags.PLANKS)
		    .add(BlockRegistry.DARKTREE_PLANKS.get())
		    .add(BlockRegistry.WHITETREE_PLANKS.get());
		
		this.tag(BlockTags.DIRT)
			.add(BlockRegistry.DARKDIRT.get())
			.add(BlockRegistry.DARKGRASS_BLOCK.get());
		
		this.tag(BlockTags.FENCES)
			.add(BlockRegistry.DARKTREE_FENCE.get())
			.add(BlockRegistry.WHITETREE_FENCE.get());
		
		this.tag(BlockTags.FENCE_GATES)
			.add(BlockRegistry.DARKTREE_FENCE_GATE.get())
			.add(BlockRegistry.WHITETREE_FENCE_GATE.get());
		
		this.tag(BlockTags.ANIMALS_SPAWNABLE_ON)
			.add(BlockRegistry.DARKGRASS_BLOCK.get())
			.add(BlockRegistry.DARKDIRT.get());
		
		this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
			.add(BlockRegistry.GRAVESTONE.get());
		
		this.tag(BlockTags.ANCIENT_CITY_REPLACEABLE)
			.add(BlockRegistry.GRAVESTONE.get());
		
		this.tag(BlockTags.SCULK_REPLACEABLE)
			.add(BlockRegistry.GRAVESTONE.get());
		
		this.tag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
			.add(BlockRegistry.GRAVESTONE.get());
		
		this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
		.add(BlockRegistry.GRAVESTONE.get());

    }
}
