package wardentools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
	public static final FoodProperties DEEP_FRUIT_PROPERTIES =
			new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 800), 1f).build();

}
