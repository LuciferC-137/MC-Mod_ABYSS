package wardentools.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import wardentools.ModMain;

public class ModFluidTypes {
    public static final ResourceLocation LIQUID_CORRUPTION_STILL_RL
            = new ResourceLocation(ModMain.MOD_ID, "block/liquid_corruption_still");
    public static final ResourceLocation LIQUID_CORRUPTION_FLOWING_RL
            = new ResourceLocation(ModMain.MOD_ID, "block/liquid_corruption_flow");
    public static final ResourceLocation LIQUID_CORRUPTION_OVERLAY_RL
            = new ResourceLocation(ModMain.MOD_ID, "misc/in_liquid_corruption_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ModMain.MOD_ID);

    public static final RegistryObject<FluidType> LIQUID_CORRUPTION = register("liquid_corruption",
            FluidType.Properties.create()
                    .canSwim(false)
                    .pathType(BlockPathTypes.LAVA)
                    .adjacentPathType(BlockPathTypes.LAVA)
                    .lightLevel(5)
                    .density(3000)
                    .viscosity(10)
                    .supportsBoating(true)
                    .canConvertToSource(false)
                    .canDrown(false)
                    .canPushEntity(true)
                    .motionScale(0.01f)
                    .sound(SoundAction.get("splash"), SoundEvents.HONEY_DRINK)
                    .sound(SoundAction.get("swim"), SoundEvents.HONEY_BLOCK_SLIDE)
                    .sound(SoundAction.get("wade"), SoundEvents.HONEY_DRINK));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name,
                () -> new BaseFluidType(LIQUID_CORRUPTION_STILL_RL, LIQUID_CORRUPTION_FLOWING_RL, LIQUID_CORRUPTION_OVERLAY_RL,
                0xA1085b61, new Vector3f(18f / 255f, 52f / 255f, 56f / 255f), properties));
    }
}
