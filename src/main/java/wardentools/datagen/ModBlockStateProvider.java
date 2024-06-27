package wardentools.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.registries.BlockRegistry;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Registering blocks with item models
        registerBlockWithItem(BlockRegistry.DARKTREE_PLANKS);
        registerBlockWithItem(BlockRegistry.DEEPBLOCK);
        registerBlockWithItem(BlockRegistry.DARKDIRT);

        // Registering blocks without item models
        

        // Registering specific block models
        registerLeavesBlock(BlockRegistry.DARKTREE_LEAVES);
        registerCrossCutoutBlock(BlockRegistry.DARKTREE_SAPLING);
        registerCrossCutoutBlock(BlockRegistry.DEEP_CRISTAL);
        registerDarkGrassBlock(BlockRegistry.DARKGRASS_BLOCK);
        
        // Registering block model for block using another model name
        registerFromLocation(BlockRegistry.DARKTREE_WOOD, "block/darktree_log");
        registerFromLocation(BlockRegistry.STRIPPED_DARKTREE_WOOD, "block/stripped_darktree_log");

        // Registering block states for logs
        logBlock((RotatedPillarBlock) BlockRegistry.DARKTREE_LOG.get());
        logBlock((RotatedPillarBlock) BlockRegistry.STRIPPED_DARKTREE_LOG.get());
        
    }

    private void registerCrossCutoutBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void registerLeavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    

    private void registerBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    
    private void registerDarkGrassBlock(RegistryObject<Block> blockRegistryObject) {
        ResourceLocation sideTexture = new ResourceLocation(ModMain.MOD_ID, "block/darkgrass_block_side");
        ResourceLocation topTexture = new ResourceLocation(ModMain.MOD_ID,"block/darkgrass_block_top");
        ResourceLocation bottomTexture = new ResourceLocation(ModMain.MOD_ID, "block/darkdirt");
        ModelFile modelFile = models().cubeBottomTop(
        		ForgeRegistries.BLOCKS.getKey(
        				blockRegistryObject.get()).getPath(), sideTexture, bottomTexture, topTexture);
        simpleBlockWithItem(blockRegistryObject.get(), modelFile);
    }
    
    private void registerFromLocation(RegistryObject<Block> blockRegistryObject, String location) {
        	simpleBlockWithItem(blockRegistryObject.get(),
        	    models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
        	    		new ResourceLocation(ModMain.MOD_ID, location)));
    }
}
