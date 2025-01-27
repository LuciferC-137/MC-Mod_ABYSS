package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class NoctilureRenderState extends LivingEntityRenderState {
    public float speed;
    public boolean isFreeFalling;
    public boolean isFlightSprinting;
    public final AnimationState standing = new AnimationState();
    public final AnimationState walking = new AnimationState();
    public final AnimationState flying = new AnimationState();
    public final AnimationState landing = new AnimationState();
    public final AnimationState idleFlying = new AnimationState();
    public final AnimationState idleFlyToFly = new AnimationState();

    public NoctilureRenderState() {

    }
}
