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
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    
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
       SurfaceRules.RuleSource darkgrassSurface = SurfaceRules.sequence(
               SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                       SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                    		   SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.absolute(50), 0),
                    				   DARKGRASS))),
               SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DARKDIRT),
               SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DARKDIRT)
       );

       // Default abyss waste land
       SurfaceRules.RuleSource wasteLand = SurfaceRules.sequence(
    		   bedrock_floor,
    		   SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
    				   SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor",
    								   	VerticalAnchor.aboveBottom(10), VerticalAnchor.aboveBottom(0)),
    						   		    	AIR)
    						      ),
    		   DEEPSLATE
    		   );

        //Default abyss generation
        SurfaceRules.RuleSource abyssSurface = SurfaceRules.sequence(
        		bedrock_floor,
        		darkgrassSurface,
        		DEEPSLATE
        		);

        //Make abyss generation in each abyss biome
        SurfaceRules.RuleSource abyssGeneration = SurfaceRules.sequence(
        		SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DEEP_FOREST), abyssSurface),
        		SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WASTE_LAND), wasteLand)
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