package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import wardentools.entity.utils.RenderToBufferFunction;
import wardentools.entity.utils.ScaleFunction;
import wardentools.entity.utils.SetUpAnimFunction;

public class ShadowRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState stasisAnimation = new AnimationState();
    public final AnimationState walkAnim = new AnimationState();
    public final AnimationState walkToIdleAnimation = new AnimationState();
    public boolean isStasis;
    public RenderToBufferFunction renderToBufferFunction;
    public SetUpAnimFunction<LivingEntityRenderState> setUpAnimFunction;
    public ScaleFunction<LivingEntityRenderState> scaleFunction;
    public LivingEntity mimicEntity;
    public LivingEntityRenderState mimicRenderState;

    public ShadowRenderState() {

    }
}
