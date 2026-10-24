package wardentools.datagen.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import wardentools.items.enchantment.EnchantmentRegistry;

public class AddStealthBookModifier extends LootModifier implements IGlobalLootModifier {
    public static final Codec<AddStealthBookModifier> CODEC =
            RecordCodecBuilder.create(inst ->
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
                    .getHolderOrThrow(EnchantmentRegistry.STEALTH.getKey());

            enchantedBook.enchant(stealthEnchantment.value(), 1);

            generatedLoot.add(enchantedBook);
        }

        return generatedLoot;
    }

    @Override
    public @NotNull Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}