package wardentools.items.armors;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ConfigurableDefenseItem extends ArmorItem {

    private int defense;

    private Multimap<Attribute, AttributeModifier> dynamicModifiers;

    private final UUID modifierId;

    public ConfigurableDefenseItem(ArmorMaterial material, Type type, Item.Properties properties) {
        super(material, type, properties);

        this.modifierId = UUID.nameUUIDFromBytes(("wardentools:armor." + type.getName())
                .getBytes(StandardCharsets.UTF_8));

        this.updateDefense(ModMaterials.getAbyssArmorProtection(type));
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier>
    getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == this.type.getSlot()) {
            return this.dynamicModifiers;
        }

        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    public void updateDefense(int defense) {
        this.defense = defense;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.put(Attributes.ARMOR,
                new AttributeModifier(this.modifierId, "Armor modifier",
                        defense, AttributeModifier.Operation.ADDITION));

        builder.put(Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(this.modifierId, "Armor toughness",
                        this.material.getToughness(), AttributeModifier.Operation.ADDITION)
        );

        float knockbackResistance = this.material.getKnockbackResistance();

        if (knockbackResistance > 0.0F) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(this.modifierId, "Armor knockback resistance",
                            knockbackResistance, AttributeModifier.Operation.ADDITION)
            );
        }

        this.dynamicModifiers = builder.build();
    }
}

