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

    public void tick(){
        if (this.noctilure.horizontalCollision) {
            this.noctilure.setYRot(this.noctilure.getYRot() + 180.0F);
        }
        if (this.noctilure.moveTargetPoint != null) {
            double dx = this.noctilure.moveTargetPoint.x - this.noctilure.getX();
            double dy = this.noctilure.moveTargetPoint.y - this.noctilure.getY();
            double dz = this.noctilure.moveTargetPoint.z - this.noctilure.getZ();
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance < this.noctilure.getBoundingBox().getSize()) {
                this.noctilure.moveTargetPoint = null;
            } else {
                this.noctilure.setDeltaMovement(new Vec3(
                        dx / distance * NoctilureEntity.FLYING_SPEED,
                        dy / distance * NoctilureEntity.FLYING_SPEED,
                        dz / distance * NoctilureEntity.FLYING_SPEED
                ));
                this.noctilure.setYRot((float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90.0F);
                this.noctilure.yBodyRot = this.noctilure.getYRot();
            }
        }
    }
}
