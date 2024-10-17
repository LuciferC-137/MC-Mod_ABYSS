package wardentools.entity.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;


public class NoctilureFlyingMoveControl extends MoveControl {
    private final NoctilureEntity noctilure;

    public NoctilureFlyingMoveControl(NoctilureEntity mob) {
        super(mob);
        this.noctilure = mob;
    }

    public void tick() {
        if (this.hasWanted()) {
            this.operation = MoveControl.Operation.WAIT;
            double dx = this.getWantedX() - this.noctilure.getX();
            double dy = this.getWantedY() - this.noctilure.getY();
            double dz = this.getWantedZ() - this.noctilure.getZ();
            this.noctilure.setDeltaMovement(new Vec3(dx, dy, dz)
                    .normalize().scale(NoctilureEntity.FLYING_SPEED));
            float newYRot = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90.0F;
            this.noctilure.yBodyRot = this.noctilure.getYRot();
            this.noctilure.setYRot(this.rotlerp(this.noctilure.getYRot(), newYRot, 90.0F));
        }
    }
}
