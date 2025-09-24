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
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.items.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;

public class ModItemTagGenerator extends ItemTagsProvider {

	public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<Provider> p_275729_,
			CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
		super(p_275343_, p_275729_, p_275322_, ModMain.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags(HolderLookup.@NotNull Provider Provider) {
		this.tag(ItemTags.TRIMMABLE_ARMOR).add(
				ArmorRegistry.DEEPCRISTAL_HELMET.get(),
				ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get(),
				ArmorRegistry.DEEPCRISTAL_LEGGINGS.get(),
				ArmorRegistry.DEEPCRISTAL_BOOTS.get(),
				ArmorRegistry.RADIANCE_CRISTAL_HELMET.get(),
				ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE.get(),
				ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS.get(),
				ArmorRegistry.RADIANCE_CRISTAL_BOOTS.get());
		
		this.tag(ItemTags.LOGS_THAT_BURN)
	        .add(BlockRegistry.DARKTREE_LOG.get().asItem())
	        .add(BlockRegistry.DARKTREE_WOOD.get().asItem())
	        .add(BlockRegistry.STRIPPED_DARKTREE_WOOD.get().asItem())
	        .add(BlockRegistry.STRIPPED_DARKTREE_LOG.get().asItem())
	        .add(BlockRegistry.WHITETREE_LOG.get().asItem())
	        .add(BlockRegistry.WHITETREE_WOOD.get().asItem())
	        .add(BlockRegistry.STRIPPED_WHITETREE_LOG.get().asItem())
	        .add(BlockRegistry.STRIPPED_WHITETREE_WOOD.get().asItem());
		
		this.tag(ItemTags.PLANKS)
         	.add(BlockRegistry.DARKTREE_PLANKS.get().asItem())
         	.add(BlockRegistry.WHITETREE_PLANKS.get().asItem());
		 
		this.tag(ItemTags.DIRT)
		 	 .add(BlockRegistry.DARKDIRT.get().asItem());

		this.tag(ItemTags.DAMPENS_VIBRATIONS)
				.add(ItemRegistry.REINFORCED_GLASS.get());

		this.tag(ItemTags.FOOT_ARMOR)
				.add(ArmorRegistry.DEEPCRISTAL_BOOTS.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_BOOTS.get());
		this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
				.add(ArmorRegistry.DEEPCRISTAL_BOOTS.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_BOOTS.get());
		this.tag(ItemTags.HEAD_ARMOR)
				.add(ArmorRegistry.DEEPCRISTAL_HELMET.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_HELMET.get());
		this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
				.add(ArmorRegistry.DEEPCRISTAL_HELMET.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_HELMET.get());
		this.tag(ItemTags.CHEST_ARMOR)
				.add(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE.get());
		this.tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
				.add(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_CHESTPLATE.get());
		this.tag(ItemTags.LEG_ARMOR)
				.add(ArmorRegistry.DEEPCRISTAL_LEGGINGS.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS.get());
		this.tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
				.add(ArmorRegistry.DEEPCRISTAL_LEGGINGS.get())
				.add(ArmorRegistry.RADIANCE_CRISTAL_LEGGINGS.get());

		this.tag(ItemTags.COMPASSES)
			.add(ItemRegistry.CRYSTAL_RESONATOR.get());
	}

}
