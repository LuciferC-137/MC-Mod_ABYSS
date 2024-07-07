package wardentools.worldgen.tree.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.base.Function;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;

public class WhitetreeTrunkPlacer extends TrunkPlacer  {
	public static final Codec<WhitetreeTrunkPlacer> CODEC = RecordCodecBuilder
			 .create(whitetreeTrunkPlacerInstance ->
			 		trunkPlacerParts(whitetreeTrunkPlacerInstance)
			 			.apply(whitetreeTrunkPlacerInstance, WhitetreeTrunkPlacer::new));


	public WhitetreeTrunkPlacer(int baseHeight, int pHeightRandA, int pHeightRandB) {
		super(baseHeight, pHeightRandA, pHeightRandB);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return ModTrunkPlacerTypes.WHITETREE_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel,
			BiConsumer<BlockPos, BlockState> pBlockSetter,
			RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos,
			TreeConfiguration pConfig) {
		setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
		
		List<BlockPos> listTrunk = new ArrayList<BlockPos>();
		List<BlockPos> listTrunkNS = new ArrayList<BlockPos>();
		List<BlockPos> listTrunkWE = new ArrayList<BlockPos>();
		List<FoliageAttachment> foliagePositions = new ArrayList<>();
		
		int treeHeight = pFreeTreeHeight
				+ pRandom.nextInt(this.heightRandA + 1)
				+ pRandom.nextInt(this.heightRandB + 1);
		
		boolean doubleTrunk = false; //Deciding if the tree will have 2x2 tunk
		if (treeHeight > 8) {
			doubleTrunk = true;
		}
		
		for (int i=0; i<treeHeight; i++) {
			listTrunk.add(pPos.above(i));
			if (doubleTrunk) {
				listTrunk.add(pPos.offset(1, i, 0));
				listTrunk.add(pPos.offset(0, i, 1));
				listTrunk.add(pPos.offset(1, i, 1));
			}
			if (i%3 == 0 && i>Math.round(treeHeight)/4) {
				foliagePositions.add(new FoliageAttachment(pPos.above(i), 0, doubleTrunk));
				if (!doubleTrunk) { //Adding 1x1 branches on each side
					listTrunkNS.add(pPos.offset(1, i, 0));
					listTrunkNS.add(pPos.offset(-1, i, 0));
					listTrunkWE.add(pPos.offset(0, i, 1));
					listTrunkWE.add(pPos.offset(0, i, -1));
				} else { //Adding 2x1 branches on each side
					listTrunkNS.add(pPos.offset(-1, i, 0));
					listTrunkWE.add(pPos.offset(0, i, -1));
					listTrunkNS.add(pPos.offset(-1, i, 1));
					listTrunkWE.add(pPos.offset(1, i, -1));
					listTrunkNS.add(pPos.offset(2, i, 0));
					listTrunkNS.add(pPos.offset(2, i, 1));
					listTrunkWE.add(pPos.offset(0, i, 2));
					listTrunkWE.add(pPos.offset(1, i, 2));
				}
			}
			
		}
		foliagePositions.add(new FoliageAttachment(pPos.above(treeHeight - 1), 0, doubleTrunk));
		
		//Create the trunk
		for (int i=0; i<listTrunk.size();i++) {
			this.placeLog(pLevel, pBlockSetter, pRandom, listTrunk.get(i), pConfig);							
		}
		for (int i=0; i<listTrunkNS.size();i++) {
			this.placeLog(pLevel, pBlockSetter, pRandom, listTrunkNS.get(i), pConfig, Direction.EAST);							
		}
		for (int i=0; i<listTrunkWE.size();i++) {
			this.placeLog(pLevel, pBlockSetter, pRandom, listTrunkWE.get(i), pConfig, Direction.NORTH);							
		}
		return foliagePositions;
	}
	
	protected boolean placeLog(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
			RandomSource pRandom, BlockPos pos, TreeConfiguration pConfig, Direction direction) {
	    Function<BlockState, BlockState> orientLog = (logState) -> {
	        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
	            return logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
	        } else if (direction == Direction.EAST || direction == Direction.WEST) {
	            return logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
	        }
	        return logState;
	    };
	    
	    return this.placeLog(pLevel, pBlockSetter, pRandom, pos, pConfig, orientLog);
	}
	
	protected boolean placeLog(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
			RandomSource pRandom, BlockPos pos, TreeConfiguration pConfig, Function<BlockState, BlockState> orientLog) {
	    if (this.validTreePos(pLevel, pos)) {
	        pBlockSetter.accept(pos, orientLog.apply(pConfig.trunkProvider.getState(pRandom, pos)));
	        return true;
	    } else {
	        return false;
	    }
	}
	
	@Override
	protected boolean placeLog(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
			RandomSource pRandom, BlockPos pos, TreeConfiguration pConfig) {
	    return this.placeLog(pLevel, pBlockSetter, pRandom, pos, pConfig, Direction.UP);
	}

}
