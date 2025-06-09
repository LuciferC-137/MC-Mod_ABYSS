package wardentools.worldgen.tree.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;

public class DarktreeTrunkPlacer extends TrunkPlacer {
	 public static final MapCodec<DarktreeTrunkPlacer> CODEC = RecordCodecBuilder
			 .mapCodec(darktreeTrunkPlacerInstance ->
			 		trunkPlacerParts(darktreeTrunkPlacerInstance)
			 			.apply(darktreeTrunkPlacerInstance, DarktreeTrunkPlacer::new));


	public DarktreeTrunkPlacer(int baseHeight, int pHeightRandA, int pHeightRandB) {
		super(baseHeight, pHeightRandA, pHeightRandB);

	}

	@Override
	protected @NotNull TrunkPlacerType<?> type() {
		return ModTrunkPlacerTypes.DARKTREE_TRUNK_PLACER.get();
	}

	@Override
	public @NotNull List<FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader level,
													   @NotNull BiConsumer<BlockPos, BlockState> setter,
													   @NotNull RandomSource random,
													   int freeTreeHeight, BlockPos pos,
													   @NotNull TreeConfiguration config) {
		setDirtAt(level, setter, random, pos.below(), config);
		
		int heightFirstBranch = freeTreeHeight + random.nextInt(-2, 2);
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
			listTrunk.add(pos.above(i));
		}	
		
		
		BlockPos startPoint = pos.above(heightFirstBranch);
		List<Integer> actualBranchStart = new ArrayList<Integer>();
		
		//Creating the four branches
		branchCurvature = branchCurvatureMean*random.nextDouble()*2;
		branchLengthVariation = random.nextInt(-3,3);
		branchPosVariation = random.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int x = 0; x <= branchLength + branchLengthVariation; x++) {
            int y = (int) Math.round(branchCurvature * x * x) + branchPosVariation;
            if (y-listTrunk.getLast().getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(-x, y-1, 0));
            }
            listTrunk.add(startPoint.offset(-x, y, 0));
    	}
		listTrunk.add(listTrunk.getLast().offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.getLast(), -2, false));
		
		branchCurvature = branchCurvatureMean*random.nextDouble()*2;
		branchLengthVariation = random.nextInt(-3,3);
		branchPosVariation = random.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int z = 0; z <= branchLength + branchLengthVariation; z++) {
            int y = (int) Math.round(branchCurvature * z * z) + branchPosVariation;
            if (y-listTrunk.getLast().getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(0, y-1, z));
            }
            listTrunk.add(startPoint.offset(0, y, z));
		}
		listTrunk.add(listTrunk.getLast().offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.getLast(), -2, false));
		
		branchCurvature = branchCurvatureMean*random.nextDouble()*2;
		branchLengthVariation = random.nextInt(-3,3);
		branchPosVariation = random.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int x = 0; x <= branchLength + branchLengthVariation; x++) {
            int y = (int) Math.round(branchCurvature * x * x) + branchPosVariation;
            if (y-listTrunk.getLast().getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(x, y-1, 0));
            }
            listTrunk.add(startPoint.offset(x, y, 0));
        }
		listTrunk.add(listTrunk.getLast().offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.getLast(), -2, false));
		
		branchCurvature = branchCurvatureMean*random.nextDouble()*2;
		branchLengthVariation = random.nextInt(-3,3);
		branchPosVariation = random.nextInt(-4,0);
		actualBranchStart.add(branchPosVariation);
		for (int z = 0; z <= branchLength+ branchLengthVariation; z++) {
            int y = (int) Math.round(branchCurvature * z * z) + branchPosVariation;
            if (y-listTrunk.getLast().getY()+startPoint.getY()>1) {
            	listTrunk.add(startPoint.offset(0, y-1, -z));
            }
            listTrunk.add(startPoint.offset(0, y, -z));
		}
		listTrunk.add(listTrunk.getLast().offset(0, 1, 0));
		foliagePositions.add(new FoliageAttachment(listTrunk.getLast(), -2, false));

		//Complete the trunk
		for (int i=heightFirstBranch-4; i<heightFirstBranch + Collections.max(actualBranchStart);i++) {
			listTrunk.add(pos.above(i));
		}
		
		//Create the trunk
        for (BlockPos blockPos : listTrunk) {
            placeLog(level, setter, random, blockPos, config);

        }
		
		return foliagePositions;
	}

}
