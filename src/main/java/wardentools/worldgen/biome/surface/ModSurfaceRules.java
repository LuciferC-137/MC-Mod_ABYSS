package wardentools.worldgen.biome.surface;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import wardentools.block.BlockRegistry;
import wardentools.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DARKDIRT = makeStateRule(BlockRegistry.DARKDIRT.get());
    private static final SurfaceRules.RuleSource DARKGRASS = makeStateRule(BlockRegistry.DARKGRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource GRAVESTONE = makeStateRule(BlockRegistry.ABYSSALITE.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Default grass surface in the overworld
       SurfaceRules.RuleSource defaultOverWorld = SurfaceRules.sequence(
    		   SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
        		SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK)), DIRT);
       
       //Default bedrock floor
       SurfaceRules.RuleSource bedrock_floor = SurfaceRules.ifTrue(SurfaceRules.verticalGradient(
				"minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
				BEDROCK);
       
       // Default darkgrass surface in the abyss
       SurfaceRules.RuleSource darkgrassSurface = SurfaceRules.ifTrue(
    		SurfaceRules.abovePreliminarySurface(),
            SurfaceRules.sequence(
    		SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
    		   	SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DARKGRASS)),
    		SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DARKDIRT),
    		   			SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DARKDIRT)
    		)
       );
       
       // Default Gravestone underground
       SurfaceRules.RuleSource gravestoneUnderground =
	       SurfaceRules.sequence(
	    		   SurfaceRules.ifTrue(
	    				   SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.aboveBottom(56), 0)), GRAVESTONE),
	    		   SurfaceRules.ifTrue(
		    		   SurfaceRules.verticalGradient("wardentools:gravestone",
		    				   VerticalAnchor.aboveBottom(56), VerticalAnchor.aboveBottom(64)),
		    		   GRAVESTONE));

        //Default abyss generation
        SurfaceRules.RuleSource abyssSurface = SurfaceRules.sequence(
        		bedrock_floor,
        		darkgrassSurface,
        		gravestoneUnderground,
        		DEEPSLATE
        		);

        //Make abyss generation in each abyss biome
        SurfaceRules.RuleSource abyssGeneration = SurfaceRules.sequence(
        		SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DEEP_FOREST), abyssSurface),
        		SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WASTE_LAND), abyssSurface),
        		SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WHITE_FOREST), abyssSurface)
        		//Add here other abyss biomes
        		);

        return SurfaceRules.sequence(
                abyssGeneration,
                defaultOverWorld
                );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}