package wardentools.mixin;

import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(BookViewScreen.WritableBookAccess.class)
public abstract class WritableBookAccessMixin {

    @Inject(method = "getPageRaw", at = @At("RETURN"), cancellable = true)
    private void getPageRawMixin(int index, CallbackInfoReturnable<FormattedText> cir) {
        FormattedText page = cir.getReturnValue();
        if (page == null) return;

        String raw = page.getString().trim();

        if (raw.startsWith("book.wardentools.")) {
            cir.setReturnValue(Component.translatable(raw));
        }
    }
}
