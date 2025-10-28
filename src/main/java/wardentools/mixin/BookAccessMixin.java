package wardentools.mixin;

import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(BookViewScreen.BookAccess.class)
public class BookAccessMixin {

    @Inject(method = "fromItem", at = @At("RETURN"), cancellable = true)
    private static void fromItemMixin(ItemStack stack, CallbackInfoReturnable<BookViewScreen.BookAccess> cir) {
        BookViewScreen.BookAccess access = cir.getReturnValue();
        if (access == null) return;

        List<Component> newPages = new ArrayList<>();
        boolean modified = false;

        for (Component page : access.pages()) {
            String raw = page.getString().trim();
            if (raw.startsWith("book.wardentools.")) {
                newPages.add(Component.translatable(raw));
                modified = true;
            } else {
                newPages.add(page);
            }
        }

        if (modified) {
            cir.setReturnValue(new BookViewScreen.BookAccess(newPages));
        }
    }
}

