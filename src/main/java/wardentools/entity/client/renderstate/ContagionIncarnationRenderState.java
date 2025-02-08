package wardentools.entity.client.renderstate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;

public class ContagionIncarnationRenderState extends LivingEntityRenderState {
    public boolean isSonicStrikeStarted;
    public int tickSpawn;
    public int contagionIncarnationDeathTime;
    public Level level;
    public final AnimationState dyingAnimationState = new AnimationState();
    public final AnimationState ambient = new AnimationState();
    public final AnimationState idleAmbient = new AnimationState();
    public final AnimationState sprint = new AnimationState();
    public final AnimationState spawnAnimation = new AnimationState();
    public final AnimationState rightSwing = new AnimationState();
    public final AnimationState leftSwing = new AnimationState();
    public final AnimationState sonicStrike = new AnimationState();

    public float section6XRot;
    public float section6YRot;
    public float section7XRot;
    public float section7YRot;
    public float section8XRot;
    public float section8YRot;
    public float section9XRot;
    public float section9YRot;
    public float section10XRot;
    public float section10YRot;
    public float section11XRot;
    public float section11YRot;
    public float section12XRot;
    public float section12YRot;
    public float section13XRot;
    public float section13YRot;
    public float section14XRot;
    public float section14YRot;
    public float section15XRot;
    public float section15YRot;
    public float section16XRot;
    public float section16YRot;
    public float section17XRot;
    public float section17YRot;
    public float section18XRot;
    public float section18YRot;
    public float sectionEndXRot;
    public float sectionEndYRot;

    public ContagionIncarnationRenderState() {

    }
}
