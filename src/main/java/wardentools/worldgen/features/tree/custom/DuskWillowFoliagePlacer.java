package wardentools.worldgen.features.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import wardentools.worldgen.features.tree.ModFoliagePlacers;

public class DuskWillowFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<DuskWillowFoliagePlacer> CODEC
            = RecordCodecBuilder.mapCodec(placerInstance
            -> foliagePlacerParts(placerInstance)
            .and(Codec.intRange(0, 16).fieldOf("height").forGetter(fp -> fp.height))
            .apply(placerInstance, DuskWillowFoliagePlacer::new));

    private final int height;

    public DuskWillowFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<DuskWillowFoliagePlacer> type() {
        return ModFoliagePlacers.DUSK_WILLOW_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader levelSimulatedReader,
                                 @NotNull FoliageSetter foliageSetter,
                                 @NotNull RandomSource randomSource,
                                 @NotNull TreeConfiguration treeConfiguration,
                                 int freeTreeHeight, @NotNull FoliageAttachment foliageAttachment,
                                 int foliageHeight, int foliageRadius, int offset) {

    }

    @Override
    public int foliageHeight(@NotNull RandomSource randomSource, int i,
                             @NotNull TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource randomSource,
                                         int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}
