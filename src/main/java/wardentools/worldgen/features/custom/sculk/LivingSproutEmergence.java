package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilTree;

import java.util.ArrayList;
import java.util.List;

import static wardentools.worldgen.features.custom.sculk.TendrilTreeUtils.*;

public class LivingSproutEmergence extends Feature<LivingSproutEmergenceConfiguration> {

    public LivingSproutEmergence(Codec<LivingSproutEmergenceConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<LivingSproutEmergenceConfiguration> context) {
        LivingSproutEmergenceConfiguration config = context.config();
        WorldGenLevel level = context.level();
        RandomSource random = level.getRandom();
        BlockPos tip = context.origin();
        int length = random.nextInt(config.minLength(), config.maxLength());
        List<Direction> directions = new ArrayList<>();

        TendrilTree tree = new TendrilTree(context.origin());

        this.setBlock(level,
                config.upward() ? context.origin().below() : context.origin().above(),
                Blocks.SCULK.defaultBlockState());

        for (int i=0; i<length; i++){
            directions.add(config.upward() ? Direction.UP : Direction.DOWN);
            tip = tip.relative(directions.getLast());
            if (random.nextFloat() < config.shiftProb()) {
                directions.add(getRandomHorizontalDirectionExcept(directions.getLast()));
                tip = tip.relative(directions.getLast());
            }
        }

        TendrilTreeUtils.addPosToTree(tree, context.origin(), directions,
                0.0F, level.getRandom());
        TendrilTreeUtils.placeBlocksWithoutUpdate(level, tree, context.origin());
        TendrilTreeUtils.configureBlockEntities(level, tree, context.origin());

        tree.updateAllNodes(level);

        if (tree.hasNode(tip)) {
            level.setBlock(config.upward() ? tip.above(): tip.below(),
                    BlockRegistry.LIVING_SPROUT.get().defaultBlockState(),
                    Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
        }

        return true;
    }
}
