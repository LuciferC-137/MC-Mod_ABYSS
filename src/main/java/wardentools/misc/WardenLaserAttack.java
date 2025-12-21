package wardentools.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.AbyssConfig;

import java.util.List;

public class WardenLaserAttack {

    private static final float RADIUS = 2.0F;
	
	public WardenLaserAttack() {
		
	}
	
    public void tick(ServerLevel level, Player player, Vec3 startPosition, Vec3 direction, int laserLength) {

        Vec3 end = startPosition.add(direction.normalize().scale(laserLength));
        AABB aabb = new AABB(startPosition, end).inflate(RADIUS);
        for (Entity entity : level.getEntities(player, aabb)) {
            if (entity instanceof LivingEntity livingEntity) {
                if (!livingEntity.is(player)) {
                    if (livingEntity instanceof TamableAnimal tamableAnimal) {
                        if (tamableAnimal.isTame() && tamableAnimal.getOwner() != null && tamableAnimal.getOwner().is(player)){
                            continue;
                        }
                    }
                    List<Vec3> testPoints = getPointsFromAABB(livingEntity);

                    boolean hit = false;
                    for (Vec3 p : testPoints) {
                        if (distanceSqPointToSegment(p, startPosition, end) <= RADIUS) {
                            hit = true;
                            break;
                        }
                    }
                    if (hit) {
                        livingEntity.hurt(player.damageSources().sonicBoom(player),
                                (float)AbyssConfig.COMMON.WARDEN_HEART_LASER.get());
                        double knockbackResistance = 1.0D - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                        double knockbackHorizontal = 2.5D * knockbackResistance;
                        double knockbackVertical = 0.5D * knockbackResistance;
                        livingEntity.push(direction.x * knockbackHorizontal,
                                direction.y * knockbackVertical,
                                direction.z * knockbackHorizontal);
                    }
                }
            }
        }
    }

    private static @NotNull List<Vec3> getPointsFromAABB(LivingEntity living) {
        AABB box = living.getBoundingBox();
        return List.of(
                box.getCenter(),
                new Vec3(box.minX, box.minY, box.minZ),
                new Vec3(box.minX, box.minY, box.maxZ),
                new Vec3(box.minX, box.maxY, box.minZ),
                new Vec3(box.minX, box.maxY, box.maxZ),
                new Vec3(box.maxX, box.minY, box.minZ),
                new Vec3(box.maxX, box.minY, box.maxZ),
                new Vec3(box.maxX, box.maxY, box.minZ),
                new Vec3(box.maxX, box.maxY, box.maxZ)
        );
    }

    private static double distanceSqPointToSegment(Vec3 p, Vec3 a, Vec3 b) {
        Vec3 ab = b.subtract(a);
        Vec3 ap = p.subtract(a);
        double abLenSq = ab.lengthSqr();
        if (abLenSq == 0) return ap.lengthSqr();
        double t = ap.dot(ab) / abLenSq;
        t = Math.max(0.0, Math.min(1.0, t));
        Vec3 closest = a.add(ab.scale(t));
        return p.subtract(closest).lengthSqr();
    }
}
