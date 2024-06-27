package wardentools.worldgen.tree.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;

public class DarktreeTrunkPlacer extends TrunkPlacer {
	 public static final Codec<DarktreeTrunkPlacer> CODEC = RecordCodecBuilder
			 .create(darktreeTrunkPlacerInstance ->
			 		trunkPlacerParts(darktreeTrunkPlacerInstance)
			 			.apply(darktreeTrunkPlacerInstance, DarktreeTrunkPlacer::new));


	public DarktreeTrunkPlacer(int baseHeight, int pHeightRandA, int pHeightRandB) {
		super(baseHeight, pHeightRandA, pHeightRandB);

	}

	@Override
	protected TrunkPlacerType<?> type() {
		return ModTrunkPlacerTypes.DARKTREE_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel,
			BiConsumer<BlockPos, BlockState> pBlockSetter,
			RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos,
			TreeConfiguration pConfig) {
		
		setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
		int heightFirstBranch = pFreeTreeHeight + pRandom.nextInt(-2, 2);
		List<BlockPos> listTrunk = new ArrayList<BlockPos>();
		List<FoliageAttachment> foliagePositions = new ArrayList<>();
		int branchLength = heightRandA;
		if (branchLength<3){branchLength=3;}
		
		int branchLengthVariation = 0;
		int branchPosVariation = 0;
		double branchCurvatureMean = 0.08; //Mean value
		double branchCurvature = 0.0;
		
		if (heightFirstBranch<5) {heightFirstBranch = 5;}
		//Initial Trunk
		for (int i=0; i<heightFirstBranch - 3;i++) {
			listTrunk.add(pPos.above(i));
		}	
		
		
		BlockPos startPoint = pPos.above(heightFirstBranch);
		List<Integer> actualBranchStart = new ArrayList<Integer>();
		
		//Creating the four branches
		branchCurvature = branchCurvatureMean*pRandom.nextDouble()*2;
		branchLengthVariation = pRandom.nextInt(-3,3);
		branchPosVariation = pRandom.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int x = 0; x <= branchLength + branchLengthVariation; x++) {
            int y = (int) Math.round(branchCurvature * x * x) + branchPosVariation;
            if (y-listTrunk.get(listTrunk.size()-1).getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(-x, y-1, 0));
            }
            listTrunk.add(startPoint.offset(-x, y, 0));
    	}
		listTrunk.add(listTrunk.get(listTrunk.size()-1).offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.get(listTrunk.size()-1), -2, false));
		
		branchCurvature = branchCurvatureMean*pRandom.nextDouble()*2;
		branchLengthVariation = pRandom.nextInt(-3,3);
		branchPosVariation = pRandom.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int z = 0; z <= branchLength + branchLengthVariation; z++) {
            int y = (int) Math.round(branchCurvature * z * z) + branchPosVariation;
            if (y-listTrunk.get(listTrunk.size()-1).getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(0, y-1, z));
            }
            listTrunk.add(startPoint.offset(0, y, z));
		}
		listTrunk.add(listTrunk.get(listTrunk.size()-1).offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.get(listTrunk.size()-1), -2, false));
		
		branchCurvature = branchCurvatureMean*pRandom.nextDouble()*2;
		branchLengthVariation = pRandom.nextInt(-3,3);
		branchPosVariation = pRandom.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int x = 0; x <= branchLength + branchLengthVariation; x++) {
            int y = (int) Math.round(branchCurvature * x * x) + branchPosVariation;
            if (y-listTrunk.get(listTrunk.size()-1).getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(x, y-1, 0));
            }
            listTrunk.add(startPoint.offset(x, y, 0));
        }
		listTrunk.add(listTrunk.get(listTrunk.size()-1).offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.get(listTrunk.size()-1), -2, false));
		
		branchCurvature = branchCurvatureMean*pRandom.nextDouble()*2;
		branchLengthVariation = pRandom.nextInt(-3,3);
		branchPosVariation = pRandom.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int z = 0; z <= branchLength+ branchLengthVariation; z++) {
            int y = (int) Math.round(branchCurvature * z * z) + branchPosVariation;
            if (y-listTrunk.get(listTrunk.size()-1).getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(0, y-1, -z));
            }
            listTrunk.add(startPoint.offset(0, y, -z));
		}
		listTrunk.add(listTrunk.get(listTrunk.size()-1).offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.get(listTrunk.size()-1), -2, false));
		
		
		//Complete the trunk
		for (int i=heightFirstBranch-4; i<heightFirstBranch + Collections.max(actualBranchStart);i++) {
			listTrunk.add(pPos.above(i));
		}
		
		//Create the trunk
		for (int i=0; i<listTrunk.size();i++) {
			placeLog(pLevel, pBlockSetter, pRandom, listTrunk.get(i), pConfig);
					
		}
		
		return foliagePositions;
	}
	
	
	@SuppressWarnings("unused")
	private List<BlockPos> getBranch(List<BlockPos> currentTree,
			BlockPos startPoint, int maxLength,
			RandomSource pRandom, int nbCurrentBranch, int direction, int lengthTree){
		
		int branchLength = (int)(pRandom.nextGaussian()/3+1)*(maxLength);
		if (branchLength<2) {
			return currentTree;
		}
		
		
		double branchCurvature = 0.4;
		
			if (direction==1) {
				for (int x = 0; x <= branchLength; x++) {
		            int y = (int) Math.round(branchCurvature * x * x);
		            currentTree.add(startPoint.offset(-x, y, 0));
	        	}
			}
			if (direction==2) {
				for (int z = 0; z <= branchLength; z++) {
		            int y = (int) Math.round(branchCurvature * z * z);
		            currentTree.add(startPoint.offset(0, y, z));
				}
			}
		
			if (direction==3) {
				for (int x = 0; x <= branchLength; x++) {
		            int y = (int) Math.round(branchCurvature * x * x);
		            currentTree.add(startPoint.offset(x, y, 0));
		        }
			}
			if (direction==4) {
				for (int z = 0; z <= branchLength; z++) {
		            int y = (int) Math.round(branchCurvature * z * z);
		            currentTree.add(startPoint.offset(0, y, -z));
				}
			}
		
		
		List<Integer> directionOut = new ArrayList<>();
		if (!(direction==3)) {directionOut.add(1);}
		if (!(direction==4)) {directionOut.add(2);}
		if (!(direction==1)) {directionOut.add(3);}
		if (!(direction==2)) {directionOut.add(4);}		
		
		if (nbCurrentBranch<=lengthTree){
			for (int i=0; i<=2; i++) {
				currentTree.addAll(getBranch(
						currentTree, currentTree.get(currentTree.size()-1),
						branchLength-1, pRandom, nbCurrentBranch+1, directionOut.get(i), lengthTree));
			}
			return currentTree;
		}
		else {
			return currentTree;
		}
	}

}
