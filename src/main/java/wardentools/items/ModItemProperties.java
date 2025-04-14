package wardentools.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.ModMain;

@OnlyIn(Dist.CLIENT)
public class ModItemProperties {
    public static void addCustomProperties() {
        ItemProperties.register(ItemRegistry.WHISTLE.get(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blowing"),
                (stack, level, entity, seed) -> stack.is(ItemRegistry.WHISTLE.get())
                        && ((WhistleItem)stack.getItem()).isUsing() ? 1.0F : 0.0F);
    }
}
