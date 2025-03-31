package wardentools.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.tree.ModFoliagePlacers;

public class WhitetreeFoliagePlacer extends FoliagePlacer{
	public static final MapCodec<WhitetreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(whitetreeFoliagePlacerInstance
            -> foliagePlacerParts(whitetreeFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(whitetreeFoliagePlacerInstance, WhitetreeFoliagePlacer::new));
    private final int height;
    
    public WhitetreeFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.WHITETREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader pLevel, @NotNull FoliageSetter pBlockSetter,
								 @NotNull RandomSource pRandom, @NotNull TreeConfiguration pConfig,
								 int pMaxFreeTreeHeight, FoliageAttachment pAttachment,
								 int pFoliageHeight, int pFoliageRadius, int pOffset) {
    	boolean doubleTrunk = pAttachment.doubleTrunk();
    	
    	double radius_base = pMaxFreeTreeHeight / 2 + 1;
    	double radius_base_height = radius_base;
    	int start = Math.round(pMaxFreeTreeHeight/3);
    	
    	placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
				pMaxFreeTreeHeight, pAttachment, (int)Math.round(radius_base_height / 2),
				pOffset + start - 1, doubleTrunk);   	

    	//Making the whole Foliage
    	for (int i=start; i<pMaxFreeTreeHeight + (doubleTrunk ? 0 : 2); i++) {
    		radius_base_height = radius_base * (pMaxFreeTreeHeight - i) / pMaxFreeTreeHeight + 1;
    		double radius = radius_base_height /(1 + (i - start) % 3) / 1.5 + 1;
    		placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
    				pMaxFreeTreeHeight, pAttachment, radius, pOffset + i, doubleTrunk);
    	}
    	//Making top tree
    	if (doubleTrunk) {
    		for (int x=-1; x<3; x++) {
    			for (int z=-1; z<3; z++) {
    				if (!(x==-1 && z==-1) && !(x==2 && z==2) && !(x==-1 && z==2) && !(x==2 && z==-1)) {
    					WhitetreeFoliagePlacer.tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig,
    		    				pAttachment.pos().offset(x, pMaxFreeTreeHeight, z));
    				}
    			}
    		}
    		for (int x=0; x<2; x++) {
    			for (int y=0; y<2; y++) {
    				WhitetreeFoliagePlacer.tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig,
		    				pAttachment.pos().offset(x, pMaxFreeTreeHeight + 1, y));
    			}
    		}
    		
    	}
    }
    
    private void placeFoliageDisk(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter,
			RandomSource pRandom, TreeConfiguration pConfig,
			int pMaxFreeTreeHeight, FoliageAttachment pAttachment,
			double pFoliageRadius, int pOffset, boolean doubleTrunk) {
	
		double radiusSquared = (pFoliageRadius + (doubleTrunk ? 1 : 0)) * (pFoliageRadius + (doubleTrunk ? 1 : 0));
		double centerXOffset = doubleTrunk ? -0.5 : 0;
		double centerZOffset = doubleTrunk ? -0.5 : 0;
		
		for (int x = -(int)Math.round(pFoliageRadius); x <= pFoliageRadius + 2; x++) {
			for (int z = -(int)Math.round(pFoliageRadius); z <= pFoliageRadius + 2; z++) {
				boolean cond = (x + centerXOffset) * (x + centerXOffset)
						+ (z + centerZOffset) * (z + centerZOffset) < radiusSquared;
				if (cond) {
					BlockPos pos = pAttachment.pos().offset(x, pOffset, z);
					WhitetreeFoliagePlacer.tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
				}
			}
		}
    }



    

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource pRandom, int pLocalX,
										 int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
