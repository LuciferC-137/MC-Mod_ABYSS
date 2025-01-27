package wardentools.block;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.fluid.FluidRegistry;
import wardentools.worldgen.features.ModConfiguredFeatures;

public class BlockRegistry {
	public static final DeferredRegister<Block> REGISTAR = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
	private static final int LIGHT_DARK_TREE = 6;
	private static final int LIGHT_WHITE_TREE = 10;
	private static final int LIGHT_WHITE_VEGETATION = 8;
	private static final int LIGHT_DARK_VEGETATION = 5;
	
	public static final RegistryObject<Block> DEEPBLOCK = REGISTAR.register("deepblock",
			()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepblock")))
					));
	
	
	public static final RegistryObject<Block> DEEP_CRISTAL = REGISTAR.register("deepcristal.json",
			()->new CristalBlock(7.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.strength(30.0F, 900.0F)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal.json")))
					));
	
	public static final RegistryObject<Block> RADIANCE_CRISTAL = REGISTAR.register("radiance_cristal",
			() -> new RadianceCristalBlock(10.0F, 5.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 11)
					.strength(20.0F, 700.0F)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal")))
			));
	
	
	public static final RegistryObject<Block> DARKTREE_LOG = REGISTAR.register("darktree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_log")))
			));
	
	public static final RegistryObject<Block> DARKTREE_WOOD = REGISTAR.register("darktree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_wood")))
			));
	
	public static final RegistryObject<Block> STRIPPED_DARKTREE_LOG = REGISTAR.register("stripped_darktree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_darktree_log")))
			));
	
	public static final RegistryObject<Block> STRIPPED_DARKTREE_WOOD = REGISTAR.register("stripped_darktree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_darktree_wood")))
			));
	
	public static final RegistryObject<Block> DARKTREE_PLANKS = REGISTAR.register("darktree_planks", 
		    () -> new Block(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_PLANKS)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_planks")))
			) {
		    	
		    	@Override
		    	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    			return true;
		    	}
		    	@Override
		    	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    		return 20;
		    	}
		    	@Override
		    	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    		return 5;
		    	}
		    	
		    	
		    });
	
	public static final RegistryObject<Block> DARKTREE_STAIR = REGISTAR.register("darktree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
							.lightLevel((state) -> LIGHT_DARK_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_stair")))
			));
	
	public static final RegistryObject<Block> DARKTREE_SLAB = REGISTAR.register("darktree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
					.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_slab")))
			));
	
	public static final RegistryObject<Block> DARKTREE_BUTTON = REGISTAR.register("darktree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
							.lightLevel((state) -> LIGHT_DARK_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_button")))
            		));
	
	public static final RegistryObject<Block> DARKTREE_PRESSURE_PLATE = REGISTAR.register("darktree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
							.lightLevel((state) -> LIGHT_DARK_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_pressure_plate")))
					));
	
	public static final RegistryObject<Block> DARKTREE_FENCE = REGISTAR.register("darktree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
					.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_fence")))
			));
	
	public static final RegistryObject<Block> DARKTREE_FENCE_GATE = REGISTAR.register("darktree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
					.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_fence_gate")))
			));
	
	public static final RegistryObject<Block> DARKTREE_DOOR = REGISTAR.register("darktree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
					.lightLevel((state) -> LIGHT_DARK_TREE)
            		.noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_door")))
			));
	
	public static final RegistryObject<Block> DARKTREE_TRAPDOOR = REGISTAR.register("darktree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
					.lightLevel((state) -> LIGHT_DARK_TREE)
            		.noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_trapdoor")))
			));
	
 
	public static final RegistryObject<Block> DARKTREE_LEAVES = REGISTAR.register("darktree_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
					.lightLevel((state) -> LIGHT_DARK_TREE + 2)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_leaves")))
			) {
				
				@Override
				public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
						return true;
				}
				@Override
				public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 60;
				}
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 30;
				}
				
				
			});
	
	public static final RegistryObject<Block> DARKTREE_SAPLING = REGISTAR.register("darktree_sapling",
			()-> new SaplingBlock(new TreeGrower("darktree",
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY),
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY),
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY)),
					BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
					.lightLevel((state) -> LIGHT_DARK_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_sapling")))
			));
	
	
	public static final RegistryObject<Block> DARKGRASS_BLOCK = REGISTAR.register("darkgrass_block",
			() -> new DarkGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.9F)
					.sound(SoundType.GRASS).randomTicks()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darkgrass_block")))
					));
	
	public static final RegistryObject<Block> DARKDIRT = REGISTAR.register("darkdirt",
			() -> new DarkDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.8F).randomTicks()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darkdirt")))
					));
	
	public static final RegistryObject<Block> WHITETREE_LOG = REGISTAR.register("whitetree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_log")))
			));
	
	public static final RegistryObject<Block> WHITETREE_WOOD = REGISTAR.register("whitetree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_wood")))
			));
	
	public static final RegistryObject<Block> STRIPPED_WHITETREE_LOG = REGISTAR.register("stripped_whitetree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_whitetree_log")))
			));
	
	public static final RegistryObject<Block> STRIPPED_WHITETREE_WOOD = REGISTAR.register("stripped_whitetree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_whitetree_wood")))
			));
	
	public static final RegistryObject<Block> WHITETREE_LEAVES = REGISTAR.register("whitetree_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
					.lightLevel((state) -> LIGHT_WHITE_TREE + 2)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_leaves")))
			) {
				
				@Override
				public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
						return true;
				}
				@Override
				public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 60;
				}
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 30;
				}
				
				
			});
	
	public static final RegistryObject<Block> WHITETREE_SAPLING = REGISTAR.register("whitetree_sapling",
			()-> new SaplingBlock(new TreeGrower("whitetree",
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY),
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY),
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY)),
					BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
					.lightLevel((state) -> 12)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_sapling")))
			));
	
	public static final RegistryObject<Block> WHITETREE_PLANKS = REGISTAR.register("whitetree_planks", 
		    () -> new Block(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_PLANKS)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_planks")))
			) {
		    	@Override
		    	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    			return true;
		    	}
		    	@Override
		    	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    		return 20;
		    	}
		    	@Override
		    	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		    		return 5;
		    	}
		    	
		    	
		    });
	
	public static final RegistryObject<Block> WHITETREE_STAIR = REGISTAR.register("whitetree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
							.lightLevel((state) -> LIGHT_WHITE_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_stair")))
			));
	
	public static final RegistryObject<Block> WHITETREE_SLAB = REGISTAR.register("whitetree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_slab")))
			));
	
	public static final RegistryObject<Block> WHITETREE_BUTTON = REGISTAR.register("whitetree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
							.lightLevel((state) -> LIGHT_WHITE_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_button")))
            		));
	
	public static final RegistryObject<Block> WHITETREE_PRESSURE_PLATE = REGISTAR.register("whitetree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
							.lightLevel((state) -> LIGHT_WHITE_TREE)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_pressure_plate")))
							));
	
	public static final RegistryObject<Block> WHITETREE_FENCE = REGISTAR.register("whitetree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_fence")))
			));
	
	public static final RegistryObject<Block> WHITETREE_FENCE_GATE = REGISTAR.register("whitetree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_fence_gate")))
			));
	
	public static final RegistryObject<Block> WHITETREE_DOOR = REGISTAR.register("whitetree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_door")))
			));
	
	public static final RegistryObject<Block> WHITETREE_TRAPDOOR = REGISTAR.register("whitetree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties
					.ofFullCopy(Blocks.OAK_TRAPDOOR)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_trapdoor")))
			));
	
	public static final RegistryObject<Block> TALL_WHITE_GRASS = REGISTAR.register("tall_white_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "tall_white_grass")))
			));
	
	public static final RegistryObject<Block> WHITE_GRASS = REGISTAR.register("white_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_grass")))
			));
	
	public static final RegistryObject<Block> WHITE_TORCHFLOWER = REGISTAR.register("white_torchflower",
			() -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_TREE)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_torchflower")))
			));

	public static final RegistryObject<Block> POTTED_WHITE_TORCHFLOWER = REGISTAR.register("potted_white_torchflower",
			() -> new FlowerPotBlock(BlockRegistry.WHITE_TORCHFLOWER.get(),
			BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)
			.noOcclusion()
			.lightLevel((state) -> LIGHT_WHITE_TREE)
			.setId(ResourceKey.create(Registries.BLOCK,
					ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "potted_white_torchflower")))
			));
	
	
	public static final RegistryObject<RadianceCatalystBlock> RADIANCE_CATALYST =
			REGISTAR.register("radiance_catalyst",
			() -> new RadianceCatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					.strength(5.0f, 15.0f)
					.noOcclusion()
					.lightLevel((state) -> 10)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_catalyst")))
			));
	
	public static final RegistryObject<Block> DEEPFLOWER = REGISTAR.register("deepflower",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepflower")))
			));
	
	public static final RegistryObject<Block> BLUE_BUSH = REGISTAR.register("blue_bush",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blue_bush")))
			));
	
	public static final RegistryObject<Block> TALL_DARK_GRASS = REGISTAR.register("tall_dark_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "tall_dark_grass")))
			));
	
	public static final RegistryObject<Block> DARK_GRASS = REGISTAR.register("dark_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dark_grass")))
			));
	
	public static final RegistryObject<Block> PROTECTOR_INVOKER = REGISTAR.register("protector_invoker",
			 () -> new ProtectorInvokerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					 .strength(5.0f, 15.0f)
					 .noOcclusion()
					 .lightLevel((state) -> 10)
					 .setId(ResourceKey.create(Registries.BLOCK,
							 ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_invoker")))
			 ));
	
	public static final RegistryObject<Block> ABYSSALITE = REGISTAR.register("abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite")))
					));
	
	public static final RegistryObject<Block> CHISELED_ABYSSALITE = REGISTAR.register("chiseled_abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chiseled_abyssalite")))
			));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS = REGISTAR.register("abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks")))
			));
	
	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS = REGISTAR.register("cracked_abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks")))
			));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_STAIRS = REGISTAR.register("abyssalite_bricks_stair",
            () -> new StairBlock(Blocks.BLACKSTONE_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS)
							.strength(4,7)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_stair")))
			));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_SLAB = REGISTAR.register("abyssalite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_slab")))
			));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_WALL = REGISTAR.register("abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_wall")))
			));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_COAL_ORE = REGISTAR.register("abyssalite_coal_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 3),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
							.strength(5,8)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_coal_ore")))
			));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_LAPIS_ORE = REGISTAR.register("abyssalite_lapis_ore",
			() -> new DropExperienceBlock(UniformInt.of(3,6),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
							.strength(5,8)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_lapis_ore")))
			));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DIAMOND_ORE = REGISTAR.register("abyssalite_diamond_ore",
			() -> new DropExperienceBlock(UniformInt.of(4,8),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
							.strength(5,8)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_diamond_ore")))
			));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DEEP_ORE = REGISTAR.register("abyssalite_deep_ore",
			() -> new DropExperienceBlock(UniformInt.of(7,10),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
							.strength(6,9)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_deep_ore")))
			));

	public static final RegistryObject<LiquidCorruptionBlock> LIQUID_CORRUPTION_BLOCK
			= REGISTAR.register("liquid_corruption_block",
				() -> new LiquidCorruptionBlock(FluidRegistry.SOURCE_LIQUID_CORRUPTION,
						BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).speedFactor(0.5f)
								.setId(ResourceKey.create(Registries.BLOCK,
										ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "liquid_corruption_block")))
				));

	public static final RegistryObject<Block> PALE_CRISTAL
			= REGISTAR.register("pale_cristal",
			() -> new CristalBlock(11.0F, 4.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(3, 6)
							.lightLevel((state) -> 8)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_cristal")))
			));

	public static final RegistryObject<Block> PALE_CRISTAL_BLOCK
			= REGISTAR.register("pale_cristal_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
						.strength(4,7)
						.requiresCorrectToolForDrops()
						.lightLevel((state) -> 8)
						.setId(ResourceKey.create(Registries.BLOCK,
								ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_cristal_block")))
				));

	public static final RegistryObject<Block> CITRINE
			= REGISTAR.register("citrine",
				() -> new CristalBlock(9.0F, 4.0F,
						BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
						.requiresCorrectToolForDrops()
						.strength(3, 6)
								.lightLevel((state) -> 8)
								.setId(ResourceKey.create(Registries.BLOCK,
										ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine")))
				));

	public static final RegistryObject<Block> CITRINE_BLOCK
			= REGISTAR.register("citrine_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 8)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine_block")))
				));

	public static final RegistryObject<Block> ECHO_CRISTAL
			= REGISTAR.register("echo_cristal",
			() -> new CristalBlock(10.0F, 5.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(3, 6)
							.lightLevel((state) -> 3)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "echo_cristal")))
			));

	public static final RegistryObject<Block> ECHO_BLOCK
			= REGISTAR.register("echo_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 3)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "echo_block")))
				));

	public static final RegistryObject<Block> RUBY
			= REGISTAR.register("ruby",
			() -> new CristalBlock(7.0F, 3.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(4, 7)
							.lightLevel((state) -> 6)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby")))
			));

	public static final RegistryObject<Block> RUBY_BLOCK
			= REGISTAR.register("ruby_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby_block")))
			));

	public static final RegistryObject<Block> MALACHITE
			= REGISTAR.register("malachite",
			() -> new CristalBlock(7.0F, 3.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(4, 7)
							.lightLevel((state) -> 6)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite")))
			));

	public static final RegistryObject<Block> MALACHITE_BLOCK
			= REGISTAR.register("malachite_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite_block")))
			));

	public static final RegistryObject<AbyssPortalBlock> ABYSS_PORTAL_BLOCK
			= REGISTAR.register("abyss_portal",
			() -> new AbyssPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					.noCollission()
					.pushReaction(PushReaction.BLOCK)
					.lightLevel((state) -> 10)
					.strength(-1.0F, 1.0F)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss_portal")))
			));

	public static final RegistryObject<Block> SOLID_CORRUPTION
			= REGISTAR.register("solid_corruption",
			() -> new SolidCorruptionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					.lightLevel((state) -> 3)
					.randomTicks()
					.strength(4F, 7F)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "solid_corruption")))
			));

	public static final RegistryObject<Block> DYSFUNCTIONNING_CATALYST
			= REGISTAR.register("dysfunctionning_catalyst",
			() -> new DysfunctionningCatalystBlock(BlockBehaviour.Properties
					.ofFullCopy(BlockRegistry.RADIANCE_CATALYST.get())
					.lightLevel((state) -> 3)
					.strength(-1F, -1F)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dysfunctionning_catalyst")))
			));

	public static final RegistryObject<Block> BLACK_LANTERN
			= REGISTAR.register("black_lantern",
			() -> new BlackLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN)
					.lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 5)
					.strength(3.5F, 10F)
					.randomTicks()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "black_lantern")))
			));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_STAIR
			= REGISTAR.register("cracked_abyssalite_bricks_stair",
			() -> new StairBlock(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS)
							.strength(4,7)
							.setId(ResourceKey.create(Registries.BLOCK,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_stair")))
			));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_SLAB
			= REGISTAR.register("cracked_abyssalite_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_slab")))
			));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_WALL
			= REGISTAR.register("cracked_abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL)
					.strength(4,7)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_wall")))
			));

	public static final RegistryObject<Block> CONTAGION_INCARNATION_SKULL
			= REGISTAR.register("contagion_incarnation_skull",
			() -> new ContagionIncarnationSkullBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SKELETON_SKULL)
					.strength(4.0F, 7.0F).noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation_skull")))
			));

	public static final RegistryObject<Block> REINFORCED_GLASS
			= REGISTAR.register("reinforced_glass",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
					.strength(3.0F, 6.0F).noOcclusion()
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "reinforced_glass")))
			));

	public static final RegistryObject<Block> WIND_WHISPERER
			= REGISTAR.register("wind_whisperer",
			() -> new WindWhispererBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					.strength(2.0F, 3.0F)
					.noOcclusion()
					.randomTicks()
					.lightLevel((state) -> 10)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "wind_whisperer")))
			));

	public static final RegistryObject<Block> SOUL_SPAWNER
			= REGISTAR.register("soul_spawner",
			() -> new SoulSpawner(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER)
					.strength(7.0F)
					.noOcclusion()
					.randomTicks()
					.lightLevel((state) -> 2)
					.setId(ResourceKey.create(Registries.BLOCK,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "soul_spawner")))
			));
}