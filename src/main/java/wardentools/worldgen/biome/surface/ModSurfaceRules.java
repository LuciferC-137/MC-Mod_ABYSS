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
    private static final SurfaceRules.RuleSource ABYSSALITE = makeStateRule(BlockRegistry.ABYSSALITE.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource CORRUPTED_ABYSSALITE = makeStateRule(BlockRegistry.CORRUPTED_ABYSSALITE.get());

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Default grass surface
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

        // Default Abyssalite underground
        SurfaceRules.RuleSource abyssaliteUnderground =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.aboveBottom(56), 0)), ABYSSALITE),
                        SurfaceRules.ifTrue(
                                SurfaceRules.verticalGradient("wardentools:abyssalite",
                                        VerticalAnchor.aboveBottom(56), VerticalAnchor.aboveBottom(64)),
                                ABYSSALITE));

        //Default abyss generation
        SurfaceRules.RuleSource abyssSurface = SurfaceRules.sequence(
                bedrock_floor,
                darkgrassSurface,
                abyssaliteUnderground,
                DEEPSLATE
        );

        // Waste Land surface
        SurfaceRules.RuleSource wasteLandSurface = SurfaceRules.sequence(
                bedrock_floor,
                abyssaliteUnderground,
                DEEPSLATE
        );

        // Corrupted Abyssalite all surfaces
        SurfaceRules.RuleSource corruptedAbyssaliteCaves = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, CORRUPTED_ABYSSALITE),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CORRUPTED_ABYSSALITE)
        );

        // Blinding Depth surface
        SurfaceRules.RuleSource blindingDepthSurface = SurfaceRules.sequence(
                bedrock_floor,
                corruptedAbyssaliteCaves,
                abyssaliteUnderground,
                DEEPSLATE
        );

        //Make abyss generation in each abyss biome
        SurfaceRules.RuleSource abyssGeneration = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DEEP_FOREST), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WASTE_LAND), wasteLandSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.WHITE_FOREST), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CRYSTAL_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLINDING_DEPTH), blindingDepthSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.AMETHYST_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CITRINE_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MALACHITE_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.RUBY_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ECHO_CAVE), abyssSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.PALE_CAVE), abyssSurface)
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