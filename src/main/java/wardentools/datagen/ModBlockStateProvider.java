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
import wardentools.block.BlockRegistry;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Registering blocks with item models
        registerBlockWithItem(BlockRegistry.DARKTREE_PLANKS);
        registerBlockWithItem(BlockRegistry.WHITETREE_PLANKS);
        registerBlockWithItem(BlockRegistry.DEEPBLOCK);
        registerBlockWithItem(BlockRegistry.DARKDIRT);

        // Registering blocks without item models
        

        // Registering specific block models
        registerLeavesBlock(BlockRegistry.DARKTREE_LEAVES);
        registerLeavesBlock(BlockRegistry.WHITETREE_LEAVES);
        registerCrossCutoutBlock(BlockRegistry.DARKTREE_SAPLING);
        registerCrossCutoutBlock(BlockRegistry.WHITETREE_SAPLING);
        registerDarkGrassBlock(BlockRegistry.DARKGRASS_BLOCK);
        
        // Registering block model for block using another model name
        registerFromLocation(BlockRegistry.DARKTREE_WOOD, "block/darktree_log");
        registerFromLocation(BlockRegistry.STRIPPED_DARKTREE_WOOD, "block/stripped_darktree_log");
        registerFromLocation(BlockRegistry.WHITETREE_WOOD, "block/whitetree_log");
        registerFromLocation(BlockRegistry.STRIPPED_WHITETREE_WOOD, "block/stripped_whitetree_log");

        // Registering block states for logs
        logBlock((RotatedPillarBlock) BlockRegistry.DARKTREE_LOG.get());
        logBlock((RotatedPillarBlock) BlockRegistry.STRIPPED_DARKTREE_LOG.get());
        logBlock((RotatedPillarBlock) BlockRegistry.WHITETREE_LOG.get());
        logBlock((RotatedPillarBlock) BlockRegistry.STRIPPED_WHITETREE_LOG.get());
        
        // Registering block states for planks derivates
        stairsBlock(((StairBlock)BlockRegistry.DARKTREE_STAIR.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        slabBlock(((SlabBlock)BlockRegistry.DARKTREE_SLAB.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        buttonBlock(((ButtonBlock)BlockRegistry.DARKTREE_BUTTON.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock)BlockRegistry.DARKTREE_PRESSURE_PLATE.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        fenceBlock(((FenceBlock)BlockRegistry.DARKTREE_FENCE.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock)BlockRegistry.DARKTREE_FENCE_GATE.get()),
        		blockTexture(BlockRegistry.DARKTREE_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock)BlockRegistry.DARKTREE_DOOR.get()),
        		modLoc("block/darktree_door_bottom"), modLoc("block/darktree_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock)BlockRegistry.DARKTREE_TRAPDOOR.get()),
        		modLoc("block/darktree_trap_door"), true, "cutout");
        
        stairsBlock(((StairBlock)BlockRegistry.WHITETREE_STAIR.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        slabBlock(((SlabBlock)BlockRegistry.WHITETREE_SLAB.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        buttonBlock(((ButtonBlock)BlockRegistry.WHITETREE_BUTTON.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock)BlockRegistry.WHITETREE_PRESSURE_PLATE.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        fenceBlock(((FenceBlock)BlockRegistry.WHITETREE_FENCE.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock)BlockRegistry.WHITETREE_FENCE_GATE.get()),
        		blockTexture(BlockRegistry.WHITETREE_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock)BlockRegistry.WHITETREE_DOOR.get()),
        		modLoc("block/whitetree_door_bottom"), modLoc("block/whitetree_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock)BlockRegistry.WHITETREE_TRAPDOOR.get()),
        		modLoc("block/whitetree_trap_door"), true, "cutout");
        
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
