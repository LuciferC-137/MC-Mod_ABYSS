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

    public ContagionIncarnationRenderState() {

    }
}
