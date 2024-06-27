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

public class DarktreeFoliagePlacer extends FoliagePlacer {

	public static final Codec<DarktreeFoliagePlacer> CODEC = RecordCodecBuilder.create(darktreeFoliagePlacerInstance
            -> foliagePlacerParts(darktreeFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(darktreeFoliagePlacerInstance, DarktreeFoliagePlacer::new));
    private final int height;

    public DarktreeFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.DARKTREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter,
    		RandomSource pRandom, TreeConfiguration pConfig,
            int pMaxFreeTreeHeight, FoliageAttachment pAttachment,
            int pFoliageHeight, int pFoliageRadius, int pOffset) {
    	
    	int variation = pRandom.nextInt(-2, 3);
    	int layers = pFoliageHeight;
    	int radius = (pFoliageRadius) + variation;
    	if (radius<=3) {layers=2;}
    	for (int i=0;i<layers;i++) {
    		radius = (int)(pFoliageRadius/(i/1.3+1)) + variation;
    		if (radius>6) {radius = 6;}
    		if (radius<0) {radius = 0;}
    		placeFoliageDisk(pLevel, pBlockSetter, pRandom, pConfig,
    				pMaxFreeTreeHeight, pAttachment, radius, pOffset+i);
    		
    	}
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
						DarktreeFoliagePlacer.tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig, pos);
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
