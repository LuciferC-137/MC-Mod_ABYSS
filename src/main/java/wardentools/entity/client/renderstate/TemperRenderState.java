package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class TemperRenderState extends LivingEntityRenderState {
    public final AnimationState attackAnimationState = new AnimationState();
    public boolean onGround;

    public TemperRenderState() {

    }
}
