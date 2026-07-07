package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.block.depthvines.DepthVines;
import wardentools.block.depthvines.DepthVinesBlock;
import wardentools.block.depthvines.DepthVinesPlantBlock;
import wardentools.block.sculktendril.SculkTendrilBlock;
import wardentools.fluid.FluidRegistry;
import wardentools.misc.Crystal;
import wardentools.worldgen.features.ModConfiguredFeatures;

public class BlockRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
	private static final int LIGHT_DARK_TREE = 6;
	private static final int LIGHT_WHITE_TREE = 10;
	private static final int LIGHT_WHITE_VEGETATION = 8;
	private static final int LIGHT_DARK_VEGETATION = 5;

    private static final MapColor DARKTREE_COLOR = MapColor.TERRACOTTA_BLUE;
    private static final MapColor DARKTREE_FOLIAGE_COLOR = MapColor.COLOR_CYAN;
    private static final MapColor DARK_VEGETATION_COLOR = MapColor.COLOR_CYAN;
    private static final MapColor WHITE_TREE_COLOR = MapColor.TERRACOTTA_CYAN;
    private static final MapColor DARKGRASS_COLOR = MapColor.TERRACOTTA_GREEN;


    public static final RegistryObject<Block> DEEPBLOCK = BLOCKS.register("deepblock",
			()->new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
					));


	public static final RegistryObject<Block> DEEP_CRISTAL = BLOCKS.register("deepcristal",
			()->new CrystalBlock(7.0F, 3.0F, 4, Crystal.ECHO,
                    BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(30.0F, 900.0F)
					));

	public static final RegistryObject<Block> RADIANCE_CRISTAL = BLOCKS.register("radiance_cristal",
			() -> new RadianceCrystalBlock(10.0F, 5.0F,
					BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.lightLevel((state) -> 11)
					.strength(20.0F, 700.0F)));


	public static final RegistryObject<Block> DARKTREE_LOG = BLOCKS.register("darktree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_LOG)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(4F)
                    .randomTicks()
		    		.lightLevel((state) -> LIGHT_DARK_TREE))
		    );

	public static final RegistryObject<Block> DARKTREE_WOOD = BLOCKS.register("darktree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_WOOD)
                    .mapColor(DARKTREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));

	public static final RegistryObject<Block> STRIPPED_DARKTREE_LOG = BLOCKS.register("stripped_darktree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(DARKTREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));

	public static final RegistryObject<Block> STRIPPED_DARKTREE_WOOD = BLOCKS.register("stripped_darktree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(DARKTREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_DARK_TREE)));

	public static final RegistryObject<Block> DARKTREE_PLANKS = BLOCKS.register("darktree_planks",
		    () -> new Block(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_PLANKS)
                    .mapColor(DARKTREE_COLOR)
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

	public static final RegistryObject<Block> DARKTREE_STAIR = BLOCKS.register("darktree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                            .mapColor(DARKTREE_COLOR)
                            .lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final RegistryObject<Block> DARKTREE_SLAB = BLOCKS.register("darktree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                            .mapColor(DARKTREE_COLOR)
                            .lightLevel((state) -> LIGHT_DARK_TREE)));


	public static final RegistryObject<Block> DARKTREE_BUTTON = BLOCKS.register("darktree_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
					.mapColor(DARKTREE_COLOR)
					.lightLevel((state) -> LIGHT_DARK_TREE),
					BlockSetType.OAK, 10, false
			));
	
	public static final RegistryObject<Block> DARKTREE_PRESSURE_PLATE = BLOCKS.register("darktree_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                            .mapColor(DARKTREE_COLOR)
                            .lightLevel((state) -> LIGHT_DARK_TREE), BlockSetType.OAK));
	
	public static final RegistryObject<Block> DARKTREE_FENCE = BLOCKS.register("darktree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .mapColor(DARKTREE_COLOR)
                    .lightLevel((state) -> LIGHT_DARK_TREE)));
	
	public static final RegistryObject<Block> DARKTREE_FENCE_GATE = BLOCKS.register("darktree_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
					.lightLevel((state) -> LIGHT_DARK_TREE), WoodType.OAK));
	
	public static final RegistryObject<Block> DARKTREE_DOOR = BLOCKS.register("darktree_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
					.lightLevel((state) -> LIGHT_DARK_TREE)
                    .mapColor(DARKTREE_COLOR)
                    .noOcclusion(), BlockSetType.OAK));
	
	public static final RegistryObject<Block> DARKTREE_TRAPDOOR = BLOCKS.register("darktree_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
                    .mapColor(DARKTREE_COLOR)
                    .lightLevel((state) -> LIGHT_DARK_TREE)
            		.noOcclusion(), BlockSetType.OAK));
	
 
	public static final RegistryObject<Block> DARKTREE_LEAVES = BLOCKS.register("darktree_leaves",
			() -> new DarktreeLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .mapColor(DARKTREE_FOLIAGE_COLOR)
                    .lightLevel((state) -> LIGHT_DARK_TREE + 2)
            ));

	public static final RegistryObject<Block> DARKTREE_SAPLING = BLOCKS.register("darktree_sapling",
			()-> new SaplingBlock(new DarkTreeGrower(),
					BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
                            .mapColor(DARKTREE_COLOR)
                            .lightLevel((state) -> LIGHT_DARK_TREE)
			));

	public static final RegistryObject<Block> DARKGRASS_BLOCK = BLOCKS.register("darkgrass_block",
			() -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)
                    .mapColor(DARKGRASS_COLOR)
                    .strength(0.9F)
					.sound(SoundType.GRASS).randomTicks()
					));

	public static final RegistryObject<Block> DARKDIRT = BLOCKS.register("darkdirt",
			() -> new DarkDirtBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(0.8F).randomTicks()));

	public static final RegistryObject<Block> WHITETREE_LOG = BLOCKS.register("whitetree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_LOG)
                    .mapColor(WHITE_TREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE))
		    );

	public static final RegistryObject<Block> WHITETREE_WOOD = BLOCKS.register("whitetree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_WOOD)
                    .mapColor(WHITE_TREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));

	public static final RegistryObject<Block> STRIPPED_WHITETREE_LOG = BLOCKS.register("stripped_whitetree_log",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.STRIPPED_OAK_LOG)
                    .mapColor(WHITE_TREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));

	public static final RegistryObject<Block> STRIPPED_WHITETREE_WOOD = BLOCKS.register("stripped_whitetree_wood",
		    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties
		    		.copy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(WHITE_TREE_COLOR)
                    .strength(4F)
		    		.lightLevel((state) -> LIGHT_WHITE_TREE)));

	public static final RegistryObject<Block> WHITETREE_LEAVES = BLOCKS.register("whitetree_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .mapColor(DyeColor.WHITE)
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

	public static final RegistryObject<Block> WHITETREE_SAPLING = BLOCKS.register("whitetree_sapling",
			()-> new SaplingBlock(new WhiteTreeGrower(),
					BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
                            .mapColor(WHITE_TREE_COLOR)
                            .lightLevel((state) -> 12)
			));

	public static final RegistryObject<Block> WHITETREE_PLANKS = BLOCKS.register("whitetree_planks",
		    () -> new Block(BlockBehaviour.Properties
		    		.copy(Blocks.OAK_PLANKS)
                    .mapColor(WHITE_TREE_COLOR)
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

	public static final RegistryObject<Block> WHITETREE_STAIR = BLOCKS.register("whitetree_stair",
            () -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                            .mapColor(WHITE_TREE_COLOR)
                            .lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> WHITETREE_SLAB = BLOCKS.register("whitetree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)
                    .mapColor(WHITE_TREE_COLOR)
                    .lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> WHITETREE_BUTTON = BLOCKS.register("whitetree_button",
            () -> new ButtonBlock(
            		BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                            .mapColor(WHITE_TREE_COLOR)
                            .lightLevel((state) -> LIGHT_WHITE_TREE),
					BlockSetType.OAK, 10, false));
	
	public static final RegistryObject<Block> WHITETREE_PRESSURE_PLATE = BLOCKS.register("whitetree_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
					BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                            .mapColor(WHITE_TREE_COLOR)
                            .lightLevel((state) -> LIGHT_WHITE_TREE), BlockSetType.OAK));
	
	public static final RegistryObject<Block> WHITETREE_FENCE = BLOCKS.register("whitetree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .mapColor(WHITE_TREE_COLOR)
                    .lightLevel((state) -> LIGHT_WHITE_TREE)));
	
	public static final RegistryObject<Block> WHITETREE_FENCE_GATE = BLOCKS.register("whitetree_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                    .mapColor(WHITE_TREE_COLOR)
                    .lightLevel((state) -> LIGHT_WHITE_TREE), WoodType.OAK));
	
	public static final RegistryObject<Block> WHITETREE_DOOR = BLOCKS.register("whitetree_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .mapColor(WHITE_TREE_COLOR)
                    .lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion(), BlockSetType.OAK));
	
	public static final RegistryObject<Block> WHITETREE_TRAPDOOR = BLOCKS.register("whitetree_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_TRAPDOOR)
                    .mapColor(WHITE_TREE_COLOR)
                    .lightLevel((state) -> LIGHT_WHITE_TREE)
            		.noOcclusion(), BlockSetType.OAK));
	
	public static final RegistryObject<Block> TALL_WHITE_GRASS = BLOCKS.register("tall_white_grass",
			() -> new AbyssDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DyeColor.WHITE)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));

	public static final RegistryObject<Block> WHITE_GRASS = BLOCKS.register("white_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DyeColor.WHITE)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_VEGETATION)));

	public static final RegistryObject<Block> WHITE_TORCHFLOWER = BLOCKS.register("white_torchflower",
			() -> new FlowerBlock(MobEffects.LUCK, 5, BlockBehaviour.Properties.copy(Blocks.TORCHFLOWER)
                    .mapColor(DyeColor.CYAN)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_WHITE_TREE)));

	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_WHITE_TORCHFLOWER = BLOCKS.register("potted_white_torchflower",
			() -> new FlowerPotBlock(BlockRegistry.WHITE_TORCHFLOWER.get(),
			BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)
			.noOcclusion()
			.lightLevel((state) -> LIGHT_WHITE_TREE)));


	public static final RegistryObject<RadianceCatalystBlock> RADIANCE_CATALYST =
			BLOCKS.register("radiance_catalyst",
			() -> new RadianceCatalystBlock(BlockBehaviour.Properties.copy(Blocks.BEACON)
					.strength(5.0f, 15.0f)
                    .mapColor(DyeColor.CYAN)
                    .noOcclusion()
					.noLootTable()
					.lightLevel((state) -> 10)));

	public static final RegistryObject<Block> DEEPFLOWER = BLOCKS.register("deepflower",
			() -> new AbyssDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DARK_VEGETATION_COLOR)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));

	public static final RegistryObject<Block> BLUE_BUSH = BLOCKS.register("blue_bush",
			() -> new BlueBush(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DARK_VEGETATION_COLOR)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));

	public static final RegistryObject<Block> TALL_DARK_GRASS = BLOCKS.register("tall_dark_grass",
			() -> new AbyssDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DARK_VEGETATION_COLOR)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));

	public static final RegistryObject<Block> DARK_GRASS = BLOCKS.register("dark_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
                    .mapColor(DARK_VEGETATION_COLOR)
                    .noOcclusion()
					.noCollission()
					.lightLevel((state) -> LIGHT_DARK_VEGETATION)));

	public static final RegistryObject<Block> PROTECTOR_INVOKER = BLOCKS.register("protector_invoker",
			 () -> new ProtectorInvokerBlock(BlockBehaviour.Properties.copy(Blocks.BEACON)
                     .mapColor(DyeColor.CYAN)
                     .strength(5.0f, 15.0f)
					 .noOcclusion()
					 .lightLevel((state) -> 10)));

	public static final RegistryObject<Block> ABYSSALITE = BLOCKS.register("abyssalite",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).strength(4,7)
					));

	public static final RegistryObject<Block> CHISELED_ABYSSALITE = BLOCKS.register("chiseled_abyssalite",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).strength(4,7)));

	public static final RegistryObject<Block> ABYSSALITE_BRICKS = BLOCKS.register("abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).strength(4,7)));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS = BLOCKS.register("cracked_abyssalite_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).strength(4,7)));

	public static final RegistryObject<Block> ABYSSALITE_BRICKS_STAIRS = BLOCKS.register("abyssalite_bricks_stair",
            () -> new StairBlock(Blocks.BLACKSTONE_STAIRS.defaultBlockState(),
            		BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_STAIRS).strength(4,7)));
	
	public static final RegistryObject<Block> ABYSSALITE_BRICKS_SLAB = BLOCKS.register("abyssalite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_SLAB).strength(4,7)));

	public static final RegistryObject<Block> ABYSSALITE_BRICKS_WALL = BLOCKS.register("abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_WALL).strength(4,7)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_COAL_ORE = BLOCKS.register("abyssalite_coal_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties
					.copy(Blocks.BLACKSTONE).strength(5,8),
					UniformInt.of(1, 3)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_LAPIS_ORE = BLOCKS.register("abyssalite_lapis_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties
					.copy(Blocks.BLACKSTONE).strength(5,8), UniformInt.of(3,6)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DIAMOND_ORE = BLOCKS.register("abyssalite_diamond_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties
					.copy(Blocks.BLACKSTONE).strength(5,8), UniformInt.of(4,8)));

	public static final RegistryObject<DropExperienceBlock> ABYSSALITE_DEEP_ORE = BLOCKS.register("abyssalite_deep_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties
					.copy(Blocks.BLACKSTONE).strength(6,9), UniformInt.of(7,10)));

    public static final RegistryObject<RedStoneOreBlock> ABYSSALITE_REDSTONE_ORE = BLOCKS.register("abyssalite_redstone_ore",
            () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE)
                    .strength(5, 8)));

	public static final RegistryObject<LiquidCorruptionBlock> LIQUID_CORRUPTION_BLOCK
			= BLOCKS.register("liquid_corruption_block",
				() -> new LiquidCorruptionBlock(FluidRegistry.SOURCE_LIQUID_CORRUPTION,
						BlockBehaviour.Properties
                                .copy(Blocks.LAVA)
                                .mapColor(MapColor.COLOR_BLACK)
                                .speedFactor(0.5f).noLootTable()));

	public static final RegistryObject<Block> PALE_CRISTAL
            = BLOCKS.register("pale_cristal",
            () -> new CrystalBlock(11.0F, 4.0F, 8, Crystal.PALE,
                    BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)
                            .requiresCorrectToolForDrops()
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(3, 6)
                            .lightLevel(CrystalBlock::getLightLevel)
            ));

	 public static final RegistryObject<Block> PALE_CRISTAL_BLOCK
            = BLOCKS.register("pale_cristal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                    .strength(4, 7)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> 8)
            ));

	public static final RegistryObject<Block> CITRINE
            = BLOCKS.register("citrine",
            () -> new CrystalBlock(9.0F, 4.0F, 8, Crystal.CITRINE,
                    BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)
                            .requiresCorrectToolForDrops()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .strength(3, 6)
                            .lightLevel(CrystalBlock::getLightLevel)
            ));

	public static final RegistryObject<Block> CITRINE_BLOCK
            = BLOCKS.register("citrine_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                    .strength(3, 6)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> 8)
            ));

	 public static final RegistryObject<Block> ECHO_CRISTAL
            = BLOCKS.register("echo_cristal",
            () -> new CrystalBlock(10.0F, 5.0F, 3, Crystal.ECHO,
                    BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)
                            .requiresCorrectToolForDrops()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(3, 6)
							.lightLevel(CrystalBlock::getLightLevel)
			));

	public static final RegistryObject<Block> ECHO_BLOCK
            = BLOCKS.register("echo_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                    .strength(3, 6)
                    .mapColor(MapColor.COLOR_BLACK)
                    .requiresCorrectToolForDrops()
                    .lightLevel((state) -> 3)
			));

	public static final RegistryObject<Block> RUBY
            = BLOCKS.register("ruby",
            () -> new CrystalBlock(7.0F, 3.0F, 6, Crystal.RUBY,
                    BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)
                            .requiresCorrectToolForDrops()
                            .mapColor(MapColor.COLOR_RED)
                            .strength(4, 7)
							.lightLevel(CrystalBlock::getLightLevel)
			));

	public static final RegistryObject<Block> RUBY_BLOCK
			= BLOCKS.register("ruby_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
                    .mapColor(MapColor.COLOR_RED)
                    .requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)));

	public static final RegistryObject<Block> MALACHITE
            = BLOCKS.register("malachite",
            () -> new CrystalBlock(7.0F, 3.0F, 6, Crystal.MALACHITE,
                    BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)
                            .requiresCorrectToolForDrops()
                            .mapColor(MapColor.COLOR_GREEN)
                            .strength(4, 7)
							.lightLevel(CrystalBlock::getLightLevel)
			));

	public static final RegistryObject<Block> MALACHITE_BLOCK
			= BLOCKS.register("malachite_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
					.strength(3, 6)
                    .mapColor(MapColor.COLOR_GREEN)
                    .requiresCorrectToolForDrops()
					.lightLevel((state) -> 6)));

	public static final RegistryObject<AbyssPortalBlock> ABYSS_PORTAL_BLOCK
			= BLOCKS.register("abyss_portal",
			() -> new AbyssPortalBlock(BlockBehaviour.Properties.copy(Blocks.END_PORTAL)
					.noCollission()
                    .mapColor(MapColor.COLOR_BLACK)
                    .forceSolidOn()
					.noLootTable()
					.lightLevel((state) -> 10)));

	public static final RegistryObject<Block> SOLID_CORRUPTION
			= BLOCKS.register("solid_corruption",
			() -> new SolidCorruptionBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .mapColor(MapColor.COLOR_BLACK)
                    .lightLevel((state) -> 3)
					.randomTicks()));

	public static final RegistryObject<Block> DYSFUNCTIONNING_CATALYST
			= BLOCKS.register("dysfunctionning_catalyst",
			() -> new DysfunctionningCatalystBlock(BlockBehaviour.Properties
					.copy(BlockRegistry.RADIANCE_CATALYST.get())
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(-1f)
					.noLootTable()
					.lightLevel((state) -> 3)));

	public static final RegistryObject<Block> BLACK_LANTERN
			= BLOCKS.register("black_lantern",
			() -> new BlackLanternBlock(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)
					.lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 5)
                    .mapColor(MapColor.TERRACOTTA_CYAN)
                    .strength(3.5F, 10F)
					.randomTicks()));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_STAIR
			= BLOCKS.register("cracked_abyssalite_bricks_stair",
			() -> new StairBlock(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_STAIRS).strength(4,7)));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_SLAB
			= BLOCKS.register("cracked_abyssalite_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_SLAB)
					.strength(4,7)));

	public static final RegistryObject<Block> CRACKED_ABYSSALITE_BRICKS_WALL
			= BLOCKS.register("cracked_abyssalite_bricks_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE_WALL)
					.strength(4,7)));

	public static final RegistryObject<Block> CONTAGION_INCARNATION_SKULL
			= BLOCKS.register("contagion_incarnation_skull",
			() -> new ContagionIncarnationSkullBlock(BlockBehaviour.Properties.copy(Blocks.SKELETON_SKULL)
					.strength(4.0F, 7.0F).noOcclusion()));

	public static final RegistryObject<Block> REINFORCED_GLASS
			= BLOCKS.register("reinforced_glass",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.GLASS)
					.strength(3.0F, 6.0F)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()));

	public static final RegistryObject<Block> WIND_WHISPERER
			= BLOCKS.register("wind_whisperer",
			() -> new WindWhispererBlock(BlockBehaviour.Properties.copy(Blocks.BEACON)
					.strength(2.0F, 3.0F)
					.noOcclusion()
					.randomTicks()
					.lightLevel((state) -> 10)));

	public static final RegistryObject<Block> SOUL_SPAWNER
			= BLOCKS.register("soul_spawner",
			() -> new SoulSpawner(BlockBehaviour.Properties.copy(Blocks.SPAWNER)
					.strength(3.0F)
					.noOcclusion()
					.randomTicks()
					.noLootTable()
					.lightLevel((state) -> 2)));

    public static final RegistryObject<Block> GRAMOPHONE
            = BLOCKS.register("gramophone",
            () -> new GramophoneBlock(BlockBehaviour.Properties.copy(Blocks.JUKEBOX)
                    .strength(3.0F, 6.0F)
                    .noOcclusion()));

    public static final RegistryObject<Block> SONIC_BLASTER
            = BLOCKS.register("sonic_blaster",
            () -> new SonicBlaster(BlockBehaviour.Properties.copy(Blocks.DISPENSER)
                    .strength(5.5f)
                    .lightLevel((state) -> 3)));

    public static final RegistryObject<Block> DEPTH_VINES
            = BLOCKS.register("depth_vines",
            () -> new DepthVinesBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .noCollission()
                    .lightLevel(DepthVines.emission(14))
                    .instabreak().sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> DEPTH_VINES_PLANT
            = BLOCKS.register("depth_vines_plant",
            () -> new DepthVinesPlantBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .lightLevel(DepthVines.emission(14))
                    .instabreak()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SCULK_TENDRIL_BLOCK
            = BLOCKS.register("sculk_tendril_block",
            () -> new SculkTendrilBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.copy(Blocks.SCULK)
                            .noOcclusion()
                            .dynamicShape().forceSolidOn()
                            .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> CORRUPTED_ABYSSALITE
            = BLOCKS.register("corrupted_abyssalite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)
                    .strength(4, 7)
            ));

    public static final RegistryObject<Block> LIVING_SPROUT
            = BLOCKS.register("living_sprout",
            () -> new LivingSproutBlock(BlockBehaviour.Properties.copy(Blocks.SCULK)
                    .noOcclusion()
                    .sound(SoundType.SCULK_SENSOR).lightLevel((state) -> 1)
                    .emissiveRendering((state, blockGetter, pos) -> LivingSproutBlock.getPhase(state)
                            == SculkSensorPhase.ACTIVE)
            ));

    public static final RegistryObject<Block> CRYSTAL_INFUSER
            = BLOCKS.register("crystal_infuser",
            () -> new CrystalInfuserBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)
                    .noOcclusion()
                    .noLootTable()
            ));

    public static final RegistryObject<Block> GOLEM_STONE
            = BLOCKS.register("golem_stone",
            () -> new GolemStoneBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)
                    .noLootTable()
            ));

    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    private static boolean always(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

	private static class DarkTreeGrower extends AbstractTreeGrower {
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean variant) {
			return ModConfiguredFeatures.DARKTREE_KEY;
		}
	}

	private static class WhiteTreeGrower extends AbstractTreeGrower {
		protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean variant) {
			return ModConfiguredFeatures.WHITETREE_KEY;
		}
	}

}