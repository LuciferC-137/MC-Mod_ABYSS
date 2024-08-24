package wardentools.worldgen;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.lang.reflect.Field;

public class CustomChunkGenerator extends NoiseBasedChunkGenerator {
    public CustomChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource, settings);
        try {
            Field globalFluidPickerField =
                    NoiseBasedChunkGenerator.class.getDeclaredField("globalFluidPicker");
            globalFluidPickerField.setAccessible(true);
            globalFluidPickerField.set(this,
                    Suppliers.memoize(() -> createCustomFluidPicker(settings.value())));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Aquifer.FluidPicker createCustomFluidPicker(NoiseGeneratorSettings settings) {
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(-54, Blocks.WATER.defaultBlockState());
        int i = settings.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, settings.defaultFluid());
        return (p_224274_, p_224275_, p_224276_) -> {
            return p_224275_ < Math.min(-54, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
        };
    }
}

