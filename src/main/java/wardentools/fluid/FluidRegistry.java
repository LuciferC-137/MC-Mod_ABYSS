package wardentools.fluid;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import java.util.function.Supplier;

public class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, ModMain.MOD_ID);

    public static final Supplier<ForgeFlowingFluid> SOURCE_LIQUID_CORRUPTION
            = FLUIDS.register("liquid_corruption",
                () -> new LiquidCorruptionFluid.Source(FluidRegistry.LIQUID_CORRUPTION_PROPERTIES));

    public static final Supplier<ForgeFlowingFluid> FLOWING_LIQUID_CORRUPTION
            = FLUIDS.register("flowing_liquid_corruption",
                () -> new LiquidCorruptionFluid.Flowing(FluidRegistry.LIQUID_CORRUPTION_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_CORRUPTION_PROPERTIES
                = new ForgeFlowingFluid.Properties(
                    ModFluidTypes.LIQUID_CORRUPTION, SOURCE_LIQUID_CORRUPTION, FLOWING_LIQUID_CORRUPTION)
                    .slopeFindDistance(4)
                    .levelDecreasePerBlock(1)
                    .tickRate(30)
                    .block(BlockRegistry.LIQUID_CORRUPTION_BLOCK);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
