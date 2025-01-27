package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class DeeplurkerRenderState extends LivingEntityRenderState {
    public final AnimationState calmAnimationState = new AnimationState();
    public final AnimationState scaredAnimationState = new AnimationState();

    public DeeplurkerRenderState() {

    }
}
