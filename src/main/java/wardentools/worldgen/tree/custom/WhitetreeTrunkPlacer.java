package wardentools.worldgen.tree.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.base.Function;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
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
	public static final MapCodec<WhitetreeTrunkPlacer> CODEC = RecordCodecBuilder
			 .mapCodec(whitetreeTrunkPlacerInstance ->
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
				
		int treeHeight = pFreeTreeHeight;
		
		boolean doubleTrunk = false; //Deciding if the tree will have 2x2 tunk
		if (treeHeight > 9) {
			doubleTrunk = true;
		}
		
		for (int i=0; i<treeHeight; i++) {
			listTrunk.add(pPos.above(i));
			if (doubleTrunk) {
				listTrunk.add(pPos.offset(1, i, 0));
				listTrunk.add(pPos.offset(0, i, 1));
				listTrunk.add(pPos.offset(1, i, 1));
			}
			if (i >= Math.round(treeHeight/3) && (i-Math.round(treeHeight/3))%3 == 0) {
				int lengthBranches = (int)Math.round((treeHeight - i)/3) ;
				List<List<BlockPos>> listBranches = this.branches(pPos.above(i), lengthBranches, doubleTrunk);
				listTrunkNS.addAll(listBranches.get(0));
				listTrunkWE.addAll(listBranches.get(1));
			}
		}
		
		foliagePositions.add(new FoliageAttachment(pPos, 0, doubleTrunk));
		
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
	
	private List<List<BlockPos>> branches(BlockPos startPos, int length, boolean doubleTrunk) {
	    List<BlockPos> listTrunkNS = new ArrayList<>();
	    List<BlockPos> listTrunkWE = new ArrayList<>();
	    int offsetDouble = doubleTrunk ? 1 : 0;
	    for (int i = 1; i <= length; i++) {
	    	listTrunkNS.add(startPos.offset(-i, 0, 0));
	        listTrunkNS.add(startPos.offset(i + offsetDouble, 0, 0));
	        listTrunkWE.add(startPos.offset(0, 0, i + offsetDouble));
	        listTrunkWE.add(startPos.offset(0, 0, -i));
	        if (doubleTrunk) {
		    	listTrunkNS.add(startPos.offset(-i, 0, 1));
		    	listTrunkNS.add(startPos.offset(i + 1, 0, 1));
		    	listTrunkWE.add(startPos.offset(1, 0, -i));
		    	listTrunkWE.add(startPos.offset(1, 0, i + 1));
		    }
	    }
	    List<List<BlockPos>> result = new ArrayList<>();
	    result.add(listTrunkNS);
	    result.add(listTrunkWE);
	    return result;
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
