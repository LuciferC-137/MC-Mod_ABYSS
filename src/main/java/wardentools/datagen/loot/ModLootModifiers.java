package wardentools.datagen.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModMain.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_STEALTH_BOOK =
            LOOT_MODIFIER_SERIALIZERS.register("add_stealth_book",
                    () -> AddStealthBookModifier.CODEC);

}
