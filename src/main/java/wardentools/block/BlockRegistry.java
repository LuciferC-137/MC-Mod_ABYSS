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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.fluid.FluidRegistry;
import wardentools.worldgen.features.ModConfiguredFeatures;

public class BlockRegistry {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModMain.MOD_ID);
	private static final int LIGHT_DARK_TREE = 6;
	private static final int LIGHT_WHITE_TREE = 10;
	private static final int LIGHT_WHITE_VEGETATION = 8;
	private static final int LIGHT_DARK_VEGETATION = 5;
	
	public static final DeferredBlock<Block> DEEPBLOCK = BLOCKS.register("deepblock",
			()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					));
	
	
	public static final DeferredBlock<Block> DEEP_CRISTAL = BLOCKS.register("deepcristal",
			()->new CristalBlock(7.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.strength(30.0F, 900.0F)
					));
	
	public static final DeferredBlock<Block> RADIANCE_CRISTAL = BLOCKS.register("radiance_cristal",
			() -> new RadianceCristalBlock(10.0F, 5.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 11)
					.strength(20.0F, 700.0F)));
	
	
	public static final DeferredBlock<Block> DARKTREE_LOG = BLOCKS.register("darktree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE))
		    );
	
	public static final DeferredBlock<Block> DARKTREE_WOOD = BLOCKS.register("darktree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> STRIPPED_DARKTREE_LOG = BLOCKS.register("stripped_darktree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> STRIPPED_DARKTREE_WOOD = BLOCKS.register("stripped_darktree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_PLANKS = BLOCKS.register("darktree_planks",
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
	
	public static final DeferredBlock<Block> DARKTREE_STAIR = BLOCKS.register("darktree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
							.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_SLAB = BLOCKS.register("darktree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
					.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_BUTTON = BLOCKS.register("darktree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
							.lightLevel((state) -> LIGHT_DARK_TREE)
            		));
	
	public static final DeferredBlock<Block> DARKTREE_PRESSURE_PLATE = BLOCKS.register("darktree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
							.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_FENCE = BLOCKS.register("darktree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
					.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_FENCE_GATE = BLOCKS.register("darktree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
					.lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final DeferredBlock<Block> DARKTREE_DOOR = BLOCKS.register("darktree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
					.lightLevel((state) -> LIGHT_DARK_TREE)
            		.noOcclusion()));
	
	public static final DeferredBlock<Block> DARKTREE_TRAPDOOR = BLOCKS.register("darktree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
					.lightLevel((state) -> LIGHT_DARK_TREE)
            		.noOcclusion()));
	
 
	public static final DeferredBlock<Block> DARKTREE_LEAVES = BLOCKS.register("darktree_leaves",
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
	
	public static final DeferredBlock<Block> DARKTREE_SAPLING = BLOCKS.register("darktree_sapling",
			()-> new SaplingBlock(new TreeGrower("darktree",
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY),
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY),
					Optional.of(ModConfiguredFeatures.DARKTREE_KEY)),
					BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
					.lightLevel((state) -> LIGHT_DARK_TREE)
			));
	
	
	public static final DeferredBlock<Block> DARKGRASS_BLOCK = BLOCKS.register("darkgrass_block",
			() -> new DarkGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.9F)
					.sound(SoundType.GRASS).randomTicks()
					));
	
	public static final DeferredBlock<Block> DARKDIRT = BLOCKS.register("darkdirt",
			() -> new DarkDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.8F).randomTicks()));
	
	public static final DeferredBlock<Block> WHITETREE_LOG = BLOCKS.register("whitetree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE))
		    );
	
	public static final DeferredBlock<Block> WHITETREE_WOOD = BLOCKS.register("whitetree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> STRIPPED_WHITETREE_LOG = BLOCKS.register("stripped_whitetree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> STRIPPED_WHITETREE_WOOD = BLOCKS.register("stripped_whitetree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
		    		.strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_LEAVES = BLOCKS.register("whitetree_leaves",
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
	
	public static final DeferredBlock<Block> WHITETREE_SAPLING = BLOCKS.register("whitetree_sapling",
			()-> new SaplingBlock(new TreeGrower("whitetree",
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY),
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY),
					Optional.of(ModConfiguredFeatures.WHITETREE_KEY)),
					BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
					.lightLevel((state) -> 12)
			));
	
	public static final DeferredBlock<Block> WHITETREE_PLANKS = BLOCKS.register("whitetree_planks",
		    () -> new Block(BlockBehaviour.Properties
		    		.ofFullCopy(Blocks.OAK_PLANKS)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)) {
		    	
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
	
	public static final DeferredBlock<Block> WHITETREE_STAIR = BLOCKS.register("whitetree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
							.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_SLAB = BLOCKS.register("whitetree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
					.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_BUTTON = BLOCKS.register("whitetree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10,
            		BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
							.lightLevel((state) -> LIGHT_WHITE_TREE)
            		));
	
	public static final DeferredBlock<Block> WHITETREE_PRESSURE_PLATE = BLOCKS.register("whitetree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
							.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_FENCE = BLOCKS.register("whitetree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
					.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_FENCE_GATE = BLOCKS.register("whitetree_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
					.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final DeferredBlock<Block> WHITETREE_DOOR = BLOCKS.register("whitetree_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion()));
	
	public static final DeferredBlock<Block> WHITETREE_TRAPDOOR = BLOCKS.register("whitetree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties
					.ofFullCopy(Blocks.OAK_TRAPDOOR)
					.lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion()));
	
	public static final DeferredBlock<Block> TALL_WHITE_GRASS = BLOCKS.register("tall_white_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));
	
	public static final DeferredBlock<Block> WHITE_GRASS = BLOCKS.register("white_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));
	
	public static final DeferredBlock<Block> WHITE_TORCHFLOWER = BLOCKS.register("white_torchflower",
			() -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_TREE)));
	@SuppressWarnings("deprecation")
	public static final DeferredBlock<Block> POTTED_WHITE_TORCHFLOWER = BLOCKS.register("potted_white_torchflower",
			() -> new FlowerPotBlock(BlockRegistry.WHITE_TORCHFLOWER.get(),
			BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)
			.noOcclusion()
			.lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	
	public static final DeferredBlock<RadianceCatalystBlock> RADIANCE_CATALYST =
			BLOCKS.register("radiance_catalyst",
			() -> new RadianceCatalystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					.strength(5.0f, 15.0f)
					.noOcclusion()
					.lightLevel((state) -> 10)));
	
	public static final DeferredBlock<Block> DEEPFLOWER = BLOCKS.register("deepflower",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final DeferredBlock<Block> BLUE_BUSH = BLOCKS.register("blue_bush",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final DeferredBlock<Block> TALL_DARK_GRASS = BLOCKS.register("tall_dark_grass",
			() -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final DeferredBlock<Block> DARK_GRASS = BLOCKS.register("dark_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
					.noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));
	
	public static final DeferredBlock<Block> PROTECTOR_INVOKER = BLOCKS.register("protector_invoker",
			 () -> new ProtectorInvokerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					 .strength(5.0f, 15.0f)
					 .noOcclusion()
					 .lightLevel((state) -> 10)));
	
	public static final DeferredBlock<Block> ABYSSALITE = BLOCKS.register("abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)
					));
	
	public static final DeferredBlock<Block> CHISELED_ABYSSALITE = BLOCKS.register("chiseled_abyssalite",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final DeferredBlock<Block> ABYSSALITE_BRICKS = BLOCKS.register("abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final DeferredBlock<Block> CRACKED_ABYSSALITE_BRICKS = BLOCKS.register("cracked_abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(4,7)));
	
	public static final DeferredBlock<Block> ABYSSALITE_BRICKS_STAIRS = BLOCKS.register("abyssalite_bricks_stair",
            () -> new StairBlock(Blocks.BLACKSTONE_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).strength(4,7)));
	
	public static final DeferredBlock<Block> ABYSSALITE_BRICKS_SLAB = BLOCKS.register("abyssalite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).strength(4,7)));
	
	public static final DeferredBlock<Block> ABYSSALITE_BRICKS_WALL = BLOCKS.register("abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).strength(4,7)));

	public static final DeferredBlock<DropExperienceBlock> ABYSSALITE_COAL_ORE = BLOCKS.register("abyssalite_coal_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 3),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final DeferredBlock<DropExperienceBlock> ABYSSALITE_LAPIS_ORE = BLOCKS.register("abyssalite_lapis_ore",
			() -> new DropExperienceBlock(UniformInt.of(3,6),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final DeferredBlock<DropExperienceBlock> ABYSSALITE_DIAMOND_ORE = BLOCKS.register("abyssalite_diamond_ore",
			() -> new DropExperienceBlock(UniformInt.of(4,8),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(5,8)));

	public static final DeferredBlock<DropExperienceBlock> ABYSSALITE_DEEP_ORE = BLOCKS.register("abyssalite_deep_ore",
			() -> new DropExperienceBlock(UniformInt.of(7,10),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(6,9)));

	public static final DeferredBlock<LiquidCorruptionBlock> LIQUID_CORRUPTION_BLOCK
			= BLOCKS.register("liquid_corruption_block",
				() -> new LiquidCorruptionBlock(FluidRegistry.SOURCE_LIQUID_CORRUPTION,
						BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).speedFactor(0.5f)));

	public static final DeferredBlock<Block> PALE_CRISTAL
			= BLOCKS.register("pale_cristal",
			() -> new CristalBlock(11.0F, 4.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(3, 6)
							.lightLevel((state) -> 8)));

	public static final DeferredBlock<Block> PALE_CRISTAL_BLOCK
			= BLOCKS.register("pale_cristal_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
						.strength(4,7)
						.requiresCorrectToolForDrops()
						.lightLevel((state) -> 8)));

	public static final DeferredBlock<Block> CITRINE
			= BLOCKS.register("citrine",
				() -> new CristalBlock(9.0F, 4.0F,
						BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
						.requiresCorrectToolForDrops()
						.strength(3, 6)
								.lightLevel((state) -> 8)));

	public static final DeferredBlock<Block> CITRINE_BLOCK
			= BLOCKS.register("citrine_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 8)));

	public static final DeferredBlock<Block> ECHO_CRISTAL
			= BLOCKS.register("echo_cristal",
			() -> new CristalBlock(10.0F, 5.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(3, 6)
							.lightLevel((state) -> 3)));

	public static final DeferredBlock<Block> ECHO_BLOCK
			= BLOCKS.register("echo_block",
				() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 3)));

	public static final DeferredBlock<Block> RUBY
			= BLOCKS.register("ruby",
			() -> new CristalBlock(7.0F, 3.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(4, 7)
							.lightLevel((state) -> 6)));

	public static final DeferredBlock<Block> RUBY_BLOCK
			= BLOCKS.register("ruby_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)));

	public static final DeferredBlock<Block> MALACHITE
			= BLOCKS.register("malachite",
			() -> new CristalBlock(7.0F, 3.0F,
					BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
							.requiresCorrectToolForDrops()
							.strength(4, 7)
							.lightLevel((state) -> 6)));

	public static final DeferredBlock<Block> MALACHITE_BLOCK
			= BLOCKS.register("malachite_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)));

	public static final DeferredBlock<AbyssPortalBlock> ABYSS_PORTAL_BLOCK
			= BLOCKS.register("abyss_portal",
			() -> new AbyssPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_PORTAL)
					.noCollission()
					.forceSolidOn()
					.lightLevel((state) -> 10)));

	public static final DeferredBlock<Block> SOLID_CORRUPTION
			= BLOCKS.register("solid_corruption",
			() -> new SolidCorruptionBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					.lightLevel((state) -> 3)
					.randomTicks()));

	public static final DeferredBlock<Block> DYSFUNCTIONNING_CATALYST
			= BLOCKS.register("dysfunctionning_catalyst",
			() -> new DysfunctionningCatalystBlock(BlockBehaviour.Properties
					.ofFullCopy(BlockRegistry.RADIANCE_CATALYST.get())
					.strength(-1f)
					.lightLevel((state) -> 3)));

	public static final DeferredBlock<Block> BLACK_LANTERN
			= BLOCKS.register("black_lantern",
			() -> new BlackLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN)
					.lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 5)
					.strength(3.5F, 10F)
					.randomTicks()));

	public static final DeferredBlock<Block> CRACKED_ABYSSALITE_BRICKS_STAIR
			= BLOCKS.register("cracked_abyssalite_bricks_stair",
			() -> new StairBlock(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).strength(4,7)));

	public static final DeferredBlock<Block> CRACKED_ABYSSALITE_BRICKS_SLAB
			= BLOCKS.register("cracked_abyssalite_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB)
					.strength(4,7)));

	public static final DeferredBlock<Block> CRACKED_ABYSSALITE_BRICKS_WALL
			= BLOCKS.register("cracked_abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL)
					.strength(4,7)));

	public static final DeferredBlock<Block> CONTAGION_INCARNATION_SKULL
			= BLOCKS.register("contagion_incarnation_skull",
			() -> new ContagionIncarnationSkullBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SKELETON_SKULL)
					.strength(4.0F, 7.0F).noOcclusion()));

	public static final DeferredBlock<Block> REINFORCED_GLASS
			= BLOCKS.register("reinforced_glass",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
					.strength(3.0F, 6.0F).noOcclusion()));

	public static final DeferredBlock<Block> WIND_WHISPERER
			= BLOCKS.register("wind_whisperer",
			() -> new WindWhispererBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON)
					.strength(2.0F, 3.0F)
					.noOcclusion()
					.randomTicks()
					.lightLevel((state) -> 10)));

	public static final DeferredBlock<Block> SOUL_SPAWNER
			= BLOCKS.register("soul_spawner",
			() -> new SoulSpawner(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER)
					.strength(3.0F)
					.noOcclusion()
					.randomTicks()
					.lightLevel((state) -> 2)));
}