package wardentools.block;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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
					));
	
	
	public static final RegistryObject<Block> DEEP_CRISTAL = REGISTAR.register("deepcristal",
			()->new CristalBlock(7.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.strength(30.0F, 900.0F)
					));
	
	public static final RegistryObject<Block> PALE_SHARD = REGISTAR.register("pale_shard",
			() -> new CristalBlock(10.0F, 5.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 11)
					.strength(20.0F, 700.0F)));
	
	
	public static final RegistryObject<Block> DARKTREE_LOG = REGISTAR.register("darktree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE))
		    );
	
	public static final RegistryObject<Block> DARKTREE_WOOD = REGISTAR.register("darktree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final RegistryObject<Block> STRIPPED_DARKTREE_LOG = REGISTAR.register("stripped_darktree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final RegistryObject<Block> STRIPPED_DARKTREE_WOOD = REGISTAR.register("stripped_darktree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final RegistryObject<Block> DARKTREE_PLANKS = REGISTAR.register("darktree_planks", 
		    () -> new Block(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_PLANKS)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)) {
		    	
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
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
	
	public static final RegistryObject<Block> DARKTREE_SLAB = REGISTAR.register("darktree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	
	public static final RegistryObject<Block> DARKTREE_BUTTON = REGISTAR.register("darktree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
            		));
	
	public static final RegistryObject<Block> DARKTREE_PRESSURE_PLATE = REGISTAR.register("darktree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
	
	public static final RegistryObject<Block> DARKTREE_FENCE = REGISTAR.register("darktree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	
	public static final RegistryObject<Block> DARKTREE_FENCE_GATE = REGISTAR.register("darktree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	
	public static final RegistryObject<Block> DARKTREE_DOOR = REGISTAR.register("darktree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            		.noOcclusion()));
	
	public static final RegistryObject<Block> DARKTREE_TRAPDOOR = REGISTAR.register("darktree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            		.noOcclusion()));
	
 
	public static final RegistryObject<Block> DARKTREE_LEAVES = REGISTAR.register("darktree_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
					.lightLevel((state) -> LIGHT_DARK_TREE + 2)) {
				
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
					.lightLevel((state) -> 11)
			));
	
	
	public static final RegistryObject<Block> DARKGRASS_BLOCK = REGISTAR.register("darkgrass_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.9F)
					.sound(SoundType.GRASS)
					));
	
	public static final RegistryObject<Block> DARKDIRT = REGISTAR.register("darkdirt",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.8F)));
	
	public static final RegistryObject<Block> WHITETREE_LOG = REGISTAR.register("whitetree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE))
		    );
	
	public static final RegistryObject<Block> WHITETREE_WOOD = REGISTAR.register("whitetree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> STRIPPED_WHITETREE_LOG = REGISTAR.register("stripped_whitetree_log", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> STRIPPED_WHITETREE_WOOD = REGISTAR.register("stripped_whitetree_wood", 
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> WHITETREE_LEAVES = REGISTAR.register("whitetree_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
					.lightLevel((state) -> LIGHT_WHITE_TREE + 2)) {
				
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
			));
	
	public static final RegistryObject<Block> WHITETREE_PLANKS = REGISTAR.register("whitetree_planks", 
		    () -> new Block(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_PLANKS)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)) {
		    	
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
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
	
	public static final RegistryObject<Block> WHITETREE_SLAB = REGISTAR.register("whitetree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
	
	public static final RegistryObject<Block> WHITETREE_BUTTON = REGISTAR.register("whitetree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
            		));
	
	public static final RegistryObject<Block> WHITETREE_PRESSURE_PLATE = REGISTAR.register("whitetree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));
	
	public static final RegistryObject<Block> WHITETREE_FENCE = REGISTAR.register("whitetree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
	
	public static final RegistryObject<Block> WHITETREE_FENCE_GATE = REGISTAR.register("whitetree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
	
	public static final RegistryObject<Block> WHITETREE_DOOR = REGISTAR.register("whitetree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            		.noOcclusion()));
	
	public static final RegistryObject<Block> WHITETREE_TRAPDOOR = REGISTAR.register("whitetree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            		.noOcclusion()));
	
	public static final RegistryObject<Block> TALL_WHITE_GRASS = REGISTAR.register("tall_white_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));
	
	public static final RegistryObject<Block> WHITE_GRASS = REGISTAR.register("white_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));
	
	public static final RegistryObject<Block> WHITE_TORCHFLOWER = REGISTAR.register("white_torchflower",
			() -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_TREE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_WHITE_TORCHFLOWER = REGISTAR.register("potted_white_torchflower",
			() -> new FlowerPotBlock(BlockRegistry.WHITE_TORCHFLOWER.get(),
			BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)
			.noOcclusion()
			.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	
	public static final RegistryObject<RadianceCatalystBlock> RADIANCE_CATALYST =
			REGISTAR.register("radiance_catalyst",
			() -> new RadianceCatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					.strength(5.0f, 15.0f)
					.noOcclusion()
					.lightLevel((state) -> 10)));
	
	public static final RegistryObject<Block> DEEPFLOWER = REGISTAR.register("deepflower",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final RegistryObject<Block> BLUE_BUSH = REGISTAR.register("blue_bush",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final RegistryObject<Block> TALL_DARK_GRASS = REGISTAR.register("tall_dark_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final RegistryObject<Block> DARK_GRASS = REGISTAR.register("dark_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final RegistryObject<Block> PROTECTOR_INVOKER = REGISTAR.register("protector_invoker",
			 () -> new ProtectorInvokerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					 .strength(5.0f, 15.0f)
					 .noOcclusion()
					 .lightLevel((state) -> 10)));
	
	public static final RegistryObject<Block> ABYSSALITE = REGISTAR.register("abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)
					));
	
	public static final RegistryObject<Block> CHISELED_ABYSSALITE = REGISTAR.register("chiseled_abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS = REGISTAR.register("abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS = REGISTAR.register("cracked_abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_STAIRS = REGISTAR.register("abyssalite_bricks_stair",
            () -> new StairBlock(Blocks.BLACKSTONE_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).strength(4,7)));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_SLAB = REGISTAR.register("abyssalite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).strength(4,7)));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_WALL = REGISTAR.register("abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).strength(4,7)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_COAL_ORE = REGISTAR.register("abyssalite_coal_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 3),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_LAPIS_ORE = REGISTAR.register("abyssalite_lapis_ore",
			() -> new DropExperienceBlock(UniformInt.of(3,6),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DIAMOND_ORE = REGISTAR.register("abyssalite_diamond_ore",
			() -> new DropExperienceBlock(UniformInt.of(4,8),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DEEP_ORE = REGISTAR.register("abyssalite_deep_ore",
			() -> new DropExperienceBlock(UniformInt.of(7,10),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(6,9)));

	public static final RegistryObject<LiquidCorruptionBlock> LIQUID_CORRUPTION_BLOCK
			= REGISTAR.register("liquid_corruption_block",
				() -> new LiquidCorruptionBlock(FluidRegistry.SOURCE_LIQUID_CORRUPTION,
						BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).speedFactor(0.5f)));
}