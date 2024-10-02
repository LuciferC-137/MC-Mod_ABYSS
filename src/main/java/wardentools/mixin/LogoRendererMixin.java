package wardentools.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoRenderer.class)
public abstract class LogoRendererMixin {
    @Shadow @Final private boolean keepLogoThroughFade;
    @Unique private static final float ZOOM = 1.4f;
    @Unique private static final ResourceLocation MINECRAFT_LOGO
            = new ResourceLocation("textures/gui/title/minecraft.png");
    @Unique private static final ResourceLocation MINECRAFT_EDITION
            = new ResourceLocation("textures/gui/title/edition.png");
    @Unique private static final int LOGO_WIDTH = 256;
    @Unique private static final int LOGO_HEIGHT = 44;
    @Unique private static final int LOGO_TEXTURE_WIDTH = 256;
    @Unique private static final int LOGO_TEXTURE_HEIGHT = 64;
    @Unique private static final int EDITION_WIDTH = 128;
    @Unique private static final int EDITION_HEIGHT = 14;
    @Unique private static final int EDITION_TEXTURE_WIDTH = 128;
    @Unique private static final int EDITION_TEXTURE_HEIGHT = 16;

    @Inject(method = "renderLogo*", at = @At("HEAD"), cancellable = true)
    public void onRenderLogo(GuiGraphics graphics, int width, float fade, int height, CallbackInfo ci) {
        ci.cancel();
        renderLogoZoom(graphics, width, fade, height);
    }

    @Unique
    private void renderLogoZoom(GuiGraphics graphics, int width, float fade, int height) {
        graphics.setColor(1.0F, 1.0F, 1.0F, keepLogoThroughFade ? 1.0F : fade);
        int i = width / 2 - 128;
        graphics.blit(MINECRAFT_LOGO,
                i + (int)((float)LOGO_WIDTH * (1f - ZOOM) / 2f),
                height + (int)((float)LOGO_HEIGHT * ( 1f - ZOOM) / 2f),
                0.0F, 0.0F,
                (int)((float)LOGO_WIDTH * ZOOM), (int)((float)LOGO_HEIGHT * ZOOM),
                (int)((float)LOGO_TEXTURE_WIDTH * ZOOM), (int)((float)LOGO_TEXTURE_HEIGHT * ZOOM));
        int j = width / 2 - 64;
        int k = height + 44 - 7;
        graphics.blit(MINECRAFT_EDITION, j, k,
                0.0F, 0.0F,
                EDITION_WIDTH, EDITION_HEIGHT,
                EDITION_TEXTURE_WIDTH, EDITION_TEXTURE_HEIGHT);
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
