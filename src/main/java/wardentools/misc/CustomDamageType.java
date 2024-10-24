package wardentools.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import wardentools.ModMain;


public class CustomDamageType {

    public static ResourceKey<DamageType> CORRUPTED_KEY
        = ResourceKey.create(Registries.DAMAGE_TYPE,
            new ResourceLocation(ModMain.MOD_ID, "corrupted"));
}
