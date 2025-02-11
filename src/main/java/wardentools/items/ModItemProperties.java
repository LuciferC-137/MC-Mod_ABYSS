package wardentools.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public class ModItemProperties {
    public static void addCustomProperties() {
        ItemProperties.register(ItemRegistry.WHISTLE.get(),
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blowing"),
                (stack, level, entity, seed) -> {
            return stack.is(ItemRegistry.WHISTLE.get())
                    && ((WhistleItem)stack.getItem())
                    .doUseAnimation(stack, level, entity, seed) ? 1.0F : 0.0F;});
    }
}
