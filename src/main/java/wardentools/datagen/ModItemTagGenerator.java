package wardentools.datagen;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import wardentools.ModMain;
import wardentools.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;

public class ModItemTagGenerator extends ItemTagsProvider {

	public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<Provider> p_275729_,
			CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
		super(p_275343_, p_275729_, p_275322_, ModMain.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags(HolderLookup.Provider Provider) {
		this.tag(ItemTags.TRIMMABLE_ARMOR).add(
				ArmorRegistry.DEEPCRISTAL_HELMET.get(),
				ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get(),
				ArmorRegistry.DEEPCRISTAL_LEGGINGS.get(),
				ArmorRegistry.DEEPCRISTAL_BOOTS.get());
		
		this.tag(ItemTags.LOGS_THAT_BURN)
	        .add(BlockRegistry.DARKTREE_LOG.get().asItem())
	        .add(BlockRegistry.DARKTREE_WOOD.get().asItem())
	        .add(BlockRegistry.STRIPPED_DARKTREE_WOOD.get().asItem())
	        .add(BlockRegistry.STRIPPED_DARKTREE_LOG.get().asItem());
		
		 this.tag(ItemTags.PLANKS)
         	.add(BlockRegistry.DARKTREE_PLANKS.get().asItem());
		 
		 this.tag(ItemTags.DIRT)
		 	.add(BlockRegistry.DARKDIRT.get().asItem());
	}

}
