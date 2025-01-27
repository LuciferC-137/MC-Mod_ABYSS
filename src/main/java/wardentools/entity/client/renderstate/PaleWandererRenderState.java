package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class PaleWandererRenderState extends LivingEntityRenderState {
    public final AnimationState calmAnimationState = new AnimationState();

    public PaleWandererRenderState() {

    }
}
