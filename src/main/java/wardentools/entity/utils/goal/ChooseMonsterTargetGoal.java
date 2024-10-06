package wardentools.entity.utils.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;

public class ChooseMonsterTargetGoal extends NearestAttackableTargetGoal<Monster> {
    public ChooseMonsterTargetGoal(Mob mob, boolean mustSee) {
        super(mob, Monster.class, mustSee);
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }
}
