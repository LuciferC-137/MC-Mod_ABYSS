package wardentools.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.block.BlueBush;

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
        registerBlockWithItem(BlockRegistry.PALE_CRISTAL_BLOCK);
        registerBlockWithItem(BlockRegistry.CITRINE_BLOCK);
        registerBlockWithItem(BlockRegistry.ECHO_BLOCK);
        registerBlockWithItem(BlockRegistry.RUBY_BLOCK);
        registerBlockWithItem(BlockRegistry.MALACHITE_BLOCK);
        registerBlockWithItem(BlockRegistry.SOLID_CORRUPTION);

        // Registering blocks with top and bottom textures (no Orientation)
        registerTopBottomSideBlock(BlockRegistry.CHISELED_ABYSSALITE,
                "chiseled_abyssalite", "chiseled_abyssalite_top", "chiseled_abyssalite_top");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE,
                "abyssalite", "abyssalite_top", "abyssalite_top");
        registerTopBottomSideBlock(BlockRegistry.DARKGRASS_BLOCK,
                "darkgrass_block_side", "darkgrass_block_top", "darkdirt");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE_COAL_ORE, "abyssalite_coal_ore",
                "abyssalite_coal_ore_top", "abyssalite_coal_ore_top");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE_LAPIS_ORE,
                "abyssalite_lapis_ore", "abyssalite_lapis_ore_top", "abyssalite_lapis_ore_top");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE_DIAMOND_ORE,
                "abyssalite_diamond_ore", "abyssalite_diamond_ore_top", "abyssalite_diamond_ore_top");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE_DEEP_ORE,
                "abyssalite_deep_ore", "abyssalite_deep_ore_top", "abyssalite_deep_ore_top");
        registerTopBottomSideBlock(BlockRegistry.ABYSSALITE_BRICKS,
                "abyssalite_bricks", "abyssalite_bricks_top", "abyssalite_bricks_top");
        registerTopBottomSideBlock(BlockRegistry.CRACKED_ABYSSALITE_BRICKS,
                "cracked_abyssalite_bricks", "cracked_abyssalite_bricks_top", "cracked_abyssalite_bricks_top");

        // Register simple transparent blocks with item model
        registerCutoutBlock(BlockRegistry.SOUL_SPAWNER);
        registerTranslucentBlock(BlockRegistry.REINFORCED_GLASS);

        // Registering specific block models
        registerLeavesBlock(BlockRegistry.DARKTREE_LEAVES);
        registerLeavesBlock(BlockRegistry.WHITETREE_LEAVES);
        registerCrossCutoutBlock(BlockRegistry.DARKTREE_SAPLING);
        registerCrossCutoutBlock(BlockRegistry.WHITETREE_SAPLING);
        registerCrossCutoutBlock(BlockRegistry.WHITE_GRASS);
        registerCrossCutoutBlock(BlockRegistry.WHITE_TORCHFLOWER);
        registerCrossCutoutBlock(BlockRegistry.DARK_GRASS);
        simpleBlockWithItem(BlockRegistry.POTTED_WHITE_TORCHFLOWER.get(), models()
        		.singleTexture("potted_white_torchflower",
                        ResourceLocation.withDefaultNamespace("flower_pot_cross"), "plant",
                blockTexture(BlockRegistry.WHITE_TORCHFLOWER.get())).renderType("cutout"));
        registerBlueBushBlock(BlockRegistry.BLUE_BUSH);
        registerCustomSidesDirectionalPoweredBlock(BlockRegistry.SONIC_BLASTER,
                "sonic_blaster_left", "sonic_blaster_right", "sonic_blaster_top", "sonic_blaster_top",
                "sonic_blaster_top", "sonic_blaster_front", "sonic_blaster_left_off", "sonic_blaster_left_off",
                "sonic_blaster_top_off", "sonic_blaster_top_off", "sonic_blaster_top_off", "sonic_blaster_front_off");
        registerCrossCutoutBlockWithBerries(BlockRegistry.DEPTH_VINES,
                "depth_vines", "depth_vines_lit");
        registerCrossCutoutBlockWithBerries(BlockRegistry.DEPTH_VINES_PLANT,
                "depth_vines_plant", "depth_vines_plant_lit");
        
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
        
        // Registering block states for planks derivatives
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
        
        stairsBlock(((StairBlock)BlockRegistry.ABYSSALITE_BRICKS_STAIRS.get()),
        		blockTexture(BlockRegistry.ABYSSALITE_BRICKS.get()));
        slabBlock(((SlabBlock)BlockRegistry.ABYSSALITE_BRICKS_SLAB.get()),
        		blockTexture(BlockRegistry.ABYSSALITE_BRICKS.get()),
        		blockTexture(BlockRegistry.ABYSSALITE_BRICKS.get()));
        wallBlock(((WallBlock)BlockRegistry.ABYSSALITE_BRICKS_WALL.get()),
        		blockTexture(BlockRegistry.ABYSSALITE_BRICKS.get()));
        stairsBlock(((StairBlock)BlockRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR.get()),
        		blockTexture(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get()));
        slabBlock(((SlabBlock)BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get()),
        		blockTexture(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get()),
        		blockTexture(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get()));
        wallBlock(((WallBlock)BlockRegistry.CRACKED_ABYSSALITE_BRICKS_WALL.get()),
                blockTexture(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get()));
        
    }

    private void registerCrossCutoutBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void registerCutoutBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
        simpleBlockItem(blockRegistryObject.get(), models().getExistingFile(blockTexture(blockRegistryObject.get())));
    }

    public void registerBlueBushBlock(RegistryObject<Block> blockRegistryObject) {
        getVariantBuilder(blockRegistryObject.get())
                .partialState().with(BlueBush.BERRY_STATE, BlueBush.BerryState.NONE)
                .modelForState().modelFile(models().cross(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        modLoc("block/blue_bush")).renderType("cutout")).addModel()
                .partialState().with(BlueBush.BERRY_STATE, BlueBush.BerryState.BLUE_BERRY)
                .modelForState().modelFile(models().cross(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + "_berry",
                        modLoc("block/blue_bush_berry")).renderType("cutout")).addModel();
    }

    private void registerTranslucentBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("translucent"));
        simpleBlockItem(blockRegistryObject.get(), models().getExistingFile(blockTexture(blockRegistryObject.get())));
    }

    private void registerLeavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.withDefaultNamespace("block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    

    private void registerBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void registerTopBottomSideBlock(RegistryObject<? extends Block> blockRegistryObject,
                                            String side, String top, String bottom) {
        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + side);
        ResourceLocation topTexture = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + top);
        ResourceLocation bottomTexture = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + bottom);
        ModelFile modelFile = models().cubeBottomTop(
        		ForgeRegistries.BLOCKS.getKey(
        				blockRegistryObject.get()).getPath(), sideTexture, bottomTexture, topTexture);
        simpleBlockWithItem(blockRegistryObject.get(), modelFile);
    }

    private void registerCustomSidesDirectionalBlock(RegistryObject<? extends Block> block,
                                                     String left, String right, String top,
                                                     String bottom, String back, String front) {
        String name = ForgeRegistries.BLOCKS.getKey(block.get()).getPath();

        ResourceLocation leftTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + left);
        ResourceLocation rightTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + right);
        ResourceLocation topTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + top);
        ResourceLocation bottomTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + bottom);
        ResourceLocation backTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + back);
        ResourceLocation frontTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + front);

        ModelFile model = models().cube(name, bottomTex, topTex, frontTex, backTex, leftTex, rightTex)
                .texture("particle", frontTex);

        getVariantBuilder(block.get())
                .forAllStates(state -> {
                    Direction facing = state.getValue(BlockStateProperties.FACING);
                    int xRot = 0;
                    int yRot = 0;

                    switch (facing) {
                        case DOWN -> xRot = 90;
                        case UP -> xRot = -90;
                        case NORTH -> yRot = 0;
                        case SOUTH -> yRot = 180;
                        case WEST -> yRot = 270;
                        case EAST -> yRot = 90;
                    }

                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationX(xRot)
                            .rotationY(yRot)
                            .build();
                });

        itemModels().withExistingParent(name, modLoc("block/" + name));
    }

    private void registerCrossCutoutBlockWithBerries(RegistryObject<Block> blockRegistryObject,
                                                     String noBerriesTexture,
                                                     String berriesTexture) {
        getVariantBuilder(blockRegistryObject.get())
                .partialState().with(BlockStateProperties.BERRIES, false)
                .modelForState().modelFile(models().cross(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        modLoc("block/" + noBerriesTexture)).renderType("cutout")).addModel()
                .partialState().with(BlockStateProperties.BERRIES, true)
                .modelForState().modelFile(models().cross(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + "_berries",
                        modLoc("block/" + berriesTexture)).renderType("cutout")).addModel();
    }

    private void registerCustomSidesDirectionalPoweredBlock(RegistryObject<? extends Block> block,
                                                            String left, String right, String top,
                                                            String bottom, String back, String front,
                                                            String left_off, String right_off,
                                                            String top_off, String bottom_off,
                                                            String back_off, String front_off) {
        String name = ForgeRegistries.BLOCKS.getKey(block.get()).getPath();

        ResourceLocation leftTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + left);
        ResourceLocation rightTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + right);
        ResourceLocation topTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + top);
        ResourceLocation bottomTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + bottom);
        ResourceLocation backTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + back);
        ResourceLocation frontTex = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + front);

        ResourceLocation leftTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "block/" + left_off);
        ResourceLocation rightTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + right_off);
        ResourceLocation topTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + top_off);
        ResourceLocation bottomTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + bottom_off);
        ResourceLocation backTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + back_off);
        ResourceLocation frontTex_off = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,"block/" + front_off);

        ModelFile model = models().cube(name, bottomTex, topTex, frontTex, backTex, leftTex, rightTex)
                .texture("particle", frontTex);

        ModelFile model_off = models().cube(name + "_off", bottomTex_off, topTex_off,
                frontTex_off, backTex_off, leftTex_off, rightTex_off).texture("particle", frontTex_off);

        getVariantBuilder(block.get())
                .forAllStates(state -> {
                    Direction facing = state.getValue(BlockStateProperties.FACING);
                    boolean powered = state.getValue(BlockStateProperties.POWERED);

                    int xRot = 0;
                    int yRot = 0;

                    switch (facing) {
                        case DOWN -> xRot = 90;
                        case UP -> xRot = -90;
                        case NORTH -> yRot = 0;
                        case SOUTH -> yRot = 180;
                        case WEST -> yRot = 270;
                        case EAST -> yRot = 90;
                    }

                    return ConfiguredModel.builder()
                            .modelFile(powered ? model : model_off)
                            .rotationX(xRot)
                            .rotationY(yRot)
                            .build();
                });

        itemModels().withExistingParent(name, modLoc("block/" + name));
    }


    private void registerFromLocation(RegistryObject<Block> blockRegistryObject, String location) {
        	simpleBlockWithItem(blockRegistryObject.get(),
        	    models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
        	    		ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, location)));
    }
}
