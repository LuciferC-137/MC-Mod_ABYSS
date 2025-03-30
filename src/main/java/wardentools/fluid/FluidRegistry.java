package wardentools.fluid;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import java.util.function.Supplier;

public class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, ModMain.MOD_ID);

    public static final Supplier<BaseFlowingFluid> SOURCE_LIQUID_CORRUPTION
            = FLUIDS.register("liquid_corruption",
                () -> new LiquidCorruptionFluid.Source(FluidRegistry.LIQUID_CORRUPTION_PROPERTIES));

    public static final Supplier<BaseFlowingFluid> FLOWING_LIQUID_CORRUPTION
            = FLUIDS.register("flowing_liquid_corruption",
                () -> new LiquidCorruptionFluid.Flowing(FluidRegistry.LIQUID_CORRUPTION_PROPERTIES));

    public static final BaseFlowingFluid.Properties LIQUID_CORRUPTION_PROPERTIES
                = new BaseFlowingFluid.Properties(
                    ModFluidTypes.LIQUID_CORRUPTION, SOURCE_LIQUID_CORRUPTION, FLOWING_LIQUID_CORRUPTION)
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .tickRate(30)
                    .block(BlockRegistry.LIQUID_CORRUPTION_BLOCK);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
