package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;

public class ProtectorRenderState extends LivingEntityRenderState {
    public boolean isSpawning;
    public boolean isDispawning;
    public boolean isSprinting;
    public final AnimationState earTickle = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState spawning = new AnimationState();
    public final AnimationState dispawning = new AnimationState();

    public ProtectorRenderState() {

    }
}
