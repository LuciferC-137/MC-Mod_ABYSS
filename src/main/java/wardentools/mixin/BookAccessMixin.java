package wardentools.mixin;

import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(BookViewScreen.BookAccess.class)
public interface BookAccessMixin {

    @Inject(method = "fromItem", at = @At("RETURN"), cancellable = true)
    private static void fromItemMixin(ItemStack stack, CallbackInfoReturnable<BookViewScreen.BookAccess> cir) {
        BookViewScreen.BookAccess original = cir.getReturnValue();
        if (original == null) return;

        cir.setReturnValue(new BookViewScreen.BookAccess() {
            @Override
            public int getPageCount() {
                return original.getPageCount();
            }

            @Override
            public FormattedText getPageRaw(int index) {
                FormattedText page = original.getPageRaw(index);
                String raw = page.getString().trim();

                if (raw.startsWith("book.wardentools.")) {
                    return Component.translatable(raw);
                }

                return page;
            }
        });
    }
}
