package wardentools.registries;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import wardentools.block.AbyssPortalBlock;
import wardentools.block.DeepCristalBlock;
import wardentools.block.ModFlammableRotatedPillarBlock;
import wardentools.worldgen.ModConfiguredFeatures;

public class BlockRegistry {
	public static final DeferredRegister<Block> REGISTAR = DeferredRegister.create(ForgeRegistries.BLOCKS, ModMain.MOD_ID);
	public static final int LIGHT_DARK_TREE = 6;
	
	public static final RegistryObject<Block> DEEPBLOCK = REGISTAR.register("deepblock",
			()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					));
	
	
	public static final RegistryObject<Block> DEEP_CRISTAL = REGISTAR.register("deepcristal",
			()->new DeepCristalBlock(5.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD)
					.requiresCorrectToolForDrops()
					.strength(30.0F, 900.0F)
					));
	
	
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
					.lightLevel((state) -> 11)) {
				
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
	
	public static final RegistryObject<Block> ABYSS_PORTAL = REGISTAR.register("abyss_portal",
			() -> new AbyssPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
					.noLootTable()
					.noOcclusion()
					.noCollission()));
	
	public static final RegistryObject<Block> DARKGRASS_BLOCK = REGISTAR.register("darkgrass_block",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.9F)
					.sound(SoundType.GRASS)
					));
	
	public static final RegistryObject<Block> DARKDIRT = REGISTAR.register("darkdirt",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
					.strength(0.8F)));
	
	
	
}