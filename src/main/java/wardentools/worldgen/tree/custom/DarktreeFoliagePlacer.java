package wardentools.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.worldgen.tree.ModFoliagePlacers;

public class DarktreeFoliagePlacer extends FoliagePlacer {
	private static final int CHANCE_OF_DEPTH_VINES = 10;
	private static final int CHANCE_OF_LIT_VINE = 7;
	private static final float AVERAGE_FRACTION_OF_AVAILABLE_HEIGHT = 0.5f;

	public static final MapCodec<DarktreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(darktreeFoliagePlacerInstance
            -> foliagePlacerParts(darktreeFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(darktreeFoliagePlacerInstance, DarktreeFoliagePlacer::new));
    private final int height;

    public DarktreeFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.DARKTREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader pLevel, @NotNull FoliageSetter pBlockSetter,
								 RandomSource pRandom, @NotNull TreeConfiguration pConfig,
								 int pMaxFreeTreeHeight, @NotNull FoliageAttachment pAttachment,
								 int pFoliageHeight, int pFoliageRadius, int pOffset) {
    	
    	int variation = pRandom.nextInt(-2, 3);
    	int layers = pFoliageHeight;
    	int radius = (pFoliageRadius) + variation;
    	if (radius<=3) {layers=2;}
    	for (int i=0; i<layers; i++) {
    		radius = (int)(pFoliageRadius/(i/1.3+1)) + variation;
    		if (radius>6) {radius = 6;}
    		if (radius<0) {radius = 0;}
    		placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
    				pAttachment, radius, pOffset+i, i==0);
    		
    	}
    }
    
    private void placeFoliageDisk(LevelSimulatedReader level, FoliageSetter setter,
								  RandomSource random, TreeConfiguration config,
								  FoliageAttachment attachment,
								  int foliageRadius, int offset, boolean addDepthVines) {
    	
			int radiusSquared = foliageRadius * foliageRadius;
			for (int x = -foliageRadius; x <= foliageRadius; x++) {
				for (int z = -foliageRadius; z <= foliageRadius; z++) {
					if (x * x + z * z <= radiusSquared) {
						BlockPos pos = attachment.pos().offset(x, offset, z);
						DarktreeFoliagePlacer.tryPlaceLeaf(level, setter, random, config, pos);
						if (addDepthVines && random.nextInt(CHANCE_OF_DEPTH_VINES) == 0) {
							BlockPos vinePos = pos.below();
							DarktreeFoliagePlacer.tryPlaceVines(random, vinePos, level, setter);
						}
					}
				}
			}
    }

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource pRandom, int pLocalX, int pLocalY,
										 int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }

	private static void tryPlaceVines(RandomSource random, BlockPos pos,
									  LevelSimulatedReader reader, FoliageSetter setter) {
		int available = availableHeight(reader, pos);
		if (available == 0) return;
		int length = random.nextInt((int)(available * AVERAGE_FRACTION_OF_AVAILABLE_HEIGHT * 0.5f),
				(int)(available * AVERAGE_FRACTION_OF_AVAILABLE_HEIGHT * 1.5f + 1));
		if (length == 0) return;
		for (int i=0; i<length; i++) {
			BlockPos vinePos = pos.below(i);
			if (reader.isStateAtPosition(vinePos, BlockBehaviour.BlockStateBase::isAir)) {
				Block blockToPlace = (i < length - 1) ?
						BlockRegistry.DEPTH_VINES_PLANT.get() :
						BlockRegistry.DEPTH_VINES.get();
				BlockState stateToPlace = blockToPlace.defaultBlockState();
				if (random.nextInt(CHANCE_OF_LIT_VINE) == 0) {
					stateToPlace = stateToPlace.setValue(BlockStateProperties.BERRIES, true);
				}
				setter.set(vinePos, stateToPlace);
			} else {
				break;
			}
		}
	}

	private static int availableHeight(LevelSimulatedReader reader, BlockPos pos) {
		for (int i = 0; i < 10; i++) {
			if (!reader.isStateAtPosition(pos.below(i), BlockBehaviour.BlockStateBase::isAir)) return i;
		}
		return 10;
	}

}
