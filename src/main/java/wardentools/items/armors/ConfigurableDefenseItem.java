package wardentools.items.armors;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class ConfigurableDefenseItem extends ArmorItem {

    private Supplier<ItemAttributeModifiers> dynamicModifiers;

    public ConfigurableDefenseItem(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        this.updateDefense((material.value()).getDefense(type));
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.dynamicModifiers.get();
    }

    @Override
    public int getDefense() {
        return ModMaterials.getAbyssArmorProtection(this.type);
    }

    public void updateDefense(int defense) {
        this.dynamicModifiers = Suppliers.memoize(() -> {
            float toughness = (material.value()).toughness();
            ItemAttributeModifiers.Builder itemattributemodifiers$builder
                    = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentslotgroup
                    = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation resourcelocation
                    = ResourceLocation.withDefaultNamespace("armor." + type.getName());
            itemattributemodifiers$builder.add(Attributes.ARMOR,
                    new AttributeModifier(resourcelocation, defense,
                            AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
            itemattributemodifiers$builder.add(Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(resourcelocation, toughness,
                            AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
            float f1 = (material.value()).knockbackResistance();
            if (f1 > 0.0F) {
                itemattributemodifiers$builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourcelocation, f1, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup);
            }
            return itemattributemodifiers$builder.build();
        });
    }
}
