package wardentools.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow @Final private ModelManager modelManager;
    @Unique private static final ModelResourceLocation ABYSS_DIVER_MODEL
            = ModelResourceLocation.inventory(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "abyss_diver_3d"));
    @Unique private static final ModelResourceLocation RADIANT_STAFF_MODEL
            = ModelResourceLocation.inventory(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "radiant_staff_3d"));
    @Unique private static final ModelResourceLocation ABYSSAL_SCYTHE_MODEL
            = ModelResourceLocation.inventory(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "abyssal_scythe_3d"));
    @Unique private static final ModelResourceLocation RADIANT_SPEAR_MODEL
            = ModelResourceLocation.inventory(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "radiant_spear_3d"));
    @Shadow private void renderItemModelRaw(ItemStack stack,
                                            ItemDisplayContext context,
                                            boolean b, PoseStack poseStack,
                                            MultiBufferSource buffer,
                                            int i, int i1, BakedModel model,
                                            boolean b1, float v) {};


    @Inject(method = "renderSimpleItemModel", at = @At(value = "HEAD"), cancellable = true)
    public void renderOverride(ItemStack stack, ItemDisplayContext context,
                               boolean b, PoseStack poseStack,
                               MultiBufferSource buffer, int i, int j,
                               BakedModel model, boolean v,
                               CallbackInfo ci) {
        if (context == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ||
                context == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND ||
                context == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ||
                context == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND) {
            if (stack.getItem() == ItemRegistry.ABYSS_DIVER.get()) {
                model = this.modelManager.getModel(ABYSS_DIVER_MODEL);
                this.renderItemModelRaw(stack, context, b, poseStack, buffer, i,
                        j, model, v, -0.5F);
                ci.cancel();
            } else if (stack.getItem() == ItemRegistry.RADIANT_STAFF.get()) {
                model = this.modelManager.getModel(RADIANT_STAFF_MODEL);
                this.renderItemModelRaw(stack, context, b, poseStack, buffer, i,
                        j, model, v, -0.5F);
                ci.cancel();
            } else if (stack.getItem() == ItemRegistry.ABYSSAL_SCYTHE.get()) {
                model = this.modelManager.getModel(ABYSSAL_SCYTHE_MODEL);
                this.renderItemModelRaw(stack, context, b, poseStack, buffer, i,
                        j, model, v, -0.5F);
                ci.cancel();
            } else if (stack.getItem() == ItemRegistry.RADIANT_SPEAR.get()) {
                model = this.modelManager.getModel(RADIANT_SPEAR_MODEL);
                this.renderItemModelRaw(stack, context, b, poseStack, buffer, i,
                        j, model, v, -0.5F);
                ci.cancel();
            }
        }
    }
}
