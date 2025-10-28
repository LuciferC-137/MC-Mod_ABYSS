package wardentools.events;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.items.PotionRegistry;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ForgeEvents {

    @SubscribeEvent
    public static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.WANDERER_PAW.get(),
                PotionRegistry.PURIFIED_POTION.getHolder().get());

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.LURKER_EYE.get(),
                Potions.LONG_NIGHT_VISION);

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.NOCTILURE_FEATHER.get(),
                Potions.SLOW_FALLING);
    }
}
