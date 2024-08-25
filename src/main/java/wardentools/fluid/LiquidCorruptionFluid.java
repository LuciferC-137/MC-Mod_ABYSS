package wardentools.fluid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class LiquidCorruptionFluid extends ForgeFlowingFluid {

    public LiquidCorruptionFluid(Properties prop){
        super(prop);
    }

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
    public int getAmount(FluidState state) {
        return state.getValue(LEVEL);
    }

    public Item getBucket() {
        return Items.AIR;
    }

    public static class Source extends LiquidCorruptionFluid
    {
        public Source(Properties properties) {
            super(properties);
        }
        public int getAmount(FluidState state) {
            return 8;
        }
        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends LiquidCorruptionFluid
    {
        public Flowing(Properties properties) {
            super(properties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }
        public boolean isSource(FluidState state) {
            return false;
        }
    }
}