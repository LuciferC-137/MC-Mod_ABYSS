package wardentools.datagen.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModMain.MOD_ID);

    public static final Supplier<Codec<? extends IGlobalLootModifier>> ADD_STEALTH_BOOK =
            LOOT_MODIFIER_SERIALIZERS.register("add_stealth_book",
                    () -> AddStealthBookModifier.CODEC);

}
