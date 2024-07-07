package wardentools.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import wardentools.worldgen.tree.ModFoliagePlacers;

public class WhitetreeFoliagePlacer extends FoliagePlacer{
	public static final Codec<WhitetreeFoliagePlacer> CODEC = RecordCodecBuilder.create(whitetreeFoliagePlacerInstance
            -> foliagePlacerParts(whitetreeFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(whitetreeFoliagePlacerInstance, WhitetreeFoliagePlacer::new));
    private final int height;
    
    public WhitetreeFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.WHITETREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter,
    		RandomSource pRandom, TreeConfiguration pConfig,
            int pMaxFreeTreeHeight, FoliageAttachment pAttachment,
            int pFoliageHeight, int pFoliageRadius, int pOffset) {
    	boolean doubleTrunk = pAttachment.doubleTrunk();
    	System.out.println("pMaxFreeTreeHeight :" + pMaxFreeTreeHeight
    			+ " pFoliageHeight :" + pFoliageHeight + " pFoliageRadius :"
    			+ pFoliageRadius + " pOffset :" + pOffset + " this.height :" + this.height);
    	   	
    	int radius_wide = (int)Math.round((pMaxFreeTreeHeight - this.height) / 2) + 1;

	    placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
	    			pMaxFreeTreeHeight, pAttachment, radius_wide, pOffset);
	    placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
    			pMaxFreeTreeHeight, pAttachment, (int)Math.round(radius_wide/2) + 1, pOffset + 1);
	    placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
    			pMaxFreeTreeHeight, pAttachment, (int)Math.round(radius_wide/3) + 1, pOffset + 2);
    }
    
    private void placeFoliageDisk(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter,
			RandomSource pRandom, TreeConfiguration pConfig,
				int pMaxFreeTreeHeight, FoliageAttachment pAttachment,
					 int pFoliageRadius, int pOffset) {
	
		int radiusSquared = pFoliageRadius * pFoliageRadius;
		for (int x = -pFoliageRadius; x <= pFoliageRadius; x++) {
			for (int z = -pFoliageRadius; z <= pFoliageRadius; z++) {
				if (x * x + z * z <= radiusSquared) {
					BlockPos pos = pAttachment.pos().offset(x, pOffset, z);
					WhitetreeFoliagePlacer.tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
				}
			}
     }
}
    

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
