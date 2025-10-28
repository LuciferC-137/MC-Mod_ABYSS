package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import wardentools.entity.custom.CrystalGolemEntity;

public class CrystalGolemAttackGoal extends MeleeAttackGoal {
    private static final int CHANCE_TO_SHOOT_LASER = 250;

    private final CrystalGolemEntity golem;

    public CrystalGolemAttackGoal(CrystalGolemEntity golem, double speedModifier) {
        super(golem, speedModifier, true);
        this.golem = golem;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && this.golem.hasGrief() && this.golem.isActive();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse()
                && this.golem.getState() == CrystalGolemEntity.GolemState.TRAVELING;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void tick() {
        if (this.golem.getRandom().nextInt(CHANCE_TO_SHOOT_LASER) == 0) {
            this.golem.setState(CrystalGolemEntity.GolemState.CHARGING_LASER);
        }
        super.tick();
    }
}
