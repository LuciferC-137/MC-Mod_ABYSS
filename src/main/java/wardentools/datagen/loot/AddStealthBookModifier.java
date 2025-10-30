package wardentools.datagen.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import wardentools.items.enchantment.EnchantmentRegistry;

public class AddStealthBookModifier extends LootModifier implements IGlobalLootModifier {
    public static final MapCodec<AddStealthBookModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst).apply(inst, AddStealthBookModifier::new)
    );

    public AddStealthBookModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // 8% chance to add a Stealth enchanted book
        if (context.getRandom().nextFloat() < 0.5f) {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

            Holder.Reference<Enchantment> stealthEnchantment = context.getLevel()
                    .registryAccess()
                    .registryOrThrow(Registries.ENCHANTMENT)
                    .getHolderOrThrow(EnchantmentRegistry.STEALTH);

            enchantedBook.enchant(stealthEnchantment, 1);

            generatedLoot.add(enchantedBook);
        }

        return generatedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}