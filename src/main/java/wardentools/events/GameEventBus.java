package wardentools.events;

import net.minecraft.core.Holder;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;
import wardentools.items.PotionRegistry;

@EventBusSubscriber(modid = ModMain.MOD_ID)

public class GameEventBus {

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.WANDERER_PAW.get(),
                Holder.direct(PotionRegistry.PURIFIED_POTION.get()));

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.LURKER_EYE.get(),
                Potions.LONG_NIGHT_VISION);

        builder.addMix(Potions.AWKWARD,
                ItemRegistry.NOCTILURE_FEATHER.get(),
                Potions.SLOW_FALLING);
    }
}
