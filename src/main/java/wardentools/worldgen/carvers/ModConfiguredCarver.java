package wardentools.worldgen.carvers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import wardentools.ModMain;

public class ModConfiguredCarver {
	public static final ResourceKey<ConfiguredWorldCarver<?>> ABYSS_CAVE = createKey("abyss_cave");
	
	private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
	      return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(ModMain.MOD_ID, name));
	}
	
	public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> ctx) {
		ctx.register(ABYSS_CAVE, WorldCarver.CAVE.configured(getCaveCarver(ctx)));
 
	}
	
	
	public static CaveCarverConfiguration getCaveCarver(BootstapContext<ConfiguredWorldCarver<?>> ctx) {
    	HolderGetter<Block> holdergetter = ctx.lookup(Registries.BLOCK);
    	return new CaveCarverConfiguration(
    			0.2F, //cave probability
    			UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(30)), // min & max of height
    			UniformFloat.of(1F, 5F), // tunnel size
    			VerticalAnchor.aboveBottom(8), //lava level
    			CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()),
    			holdergetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
    			UniformFloat.of(2F, 3.5F), //horizontal radius multiplier
    			UniformFloat.of(5F, 7F), //vertical radius multiplier
    			UniformFloat.of(-1.0F, -0.5F)); //floor level
    }

}
