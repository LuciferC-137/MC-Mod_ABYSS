package wardentools.entity.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class NoctilureFlyingMoveControl extends MoveControl {
    private float speed;
    private final NoctilureEntity noctilure;

    public NoctilureFlyingMoveControl(NoctilureEntity mob, float speed) {
        super(mob);
        this.noctilure = mob;
        this.speed = speed;
    }

    public void tick() {
        if (this.noctilure.horizontalCollision) {
            this.noctilure.setYRot(this.noctilure.getYRot() + 180.0F);
            this.speed = 0.1F;
        }

        double d0 = this.noctilure.moveTargetPoint.x - this.noctilure.getX();
        double d1 = this.noctilure.moveTargetPoint.y - this.noctilure.getY();
        double d2 = this.noctilure.moveTargetPoint.z - this.noctilure.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        if (Math.abs(d3) > (double)1.0E-5F) {
            double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
            d0 *= d4;
            d2 *= d4;
            d3 = Math.sqrt(d0 * d0 + d2 * d2);
            double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
            float f = this.noctilure.getYRot();
            float f1 = (float) Mth.atan2(d2, d0);
            float f2 = Mth.wrapDegrees(this.noctilure.getYRot() + 90.0F);
            float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
            this.noctilure.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
            this.noctilure.yBodyRot = this.noctilure.getYRot();
            if (Mth.degreesDifferenceAbs(f, this.noctilure.getYRot()) < 3.0F) {
                this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
            } else {
                this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
            }

            float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
            this.noctilure.setXRot(f4);
            float f5 = this.noctilure.getYRot() + 90.0F;
            double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
            double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
            double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
            Vec3 vec3 = this.noctilure.getDeltaMovement();
            this.noctilure.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
        }

    }
}
