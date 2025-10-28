package wardentools.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModParticleUtils {

    public static void addClientParticle(Level level, ParticleOptions options,
                                         Vec3 origin, Vec3 target, float speed) {
        if (level.isClientSide()) {
            Vec3 motion = target.subtract(origin).scale(speed);
            level.addParticle(options, origin.x, origin.y, origin.z, motion.x, motion.y, motion.z);
        }
    }

    public static void addClientParticle(Level level, ParticleOptions options,
                                         Vec3 origin, Vec3 direction) {
        addClientParticle(level, options, origin, origin.add(direction), (float)direction.length());
    }

    public static void addStaticClientParticle(Level level, ParticleOptions options,
                                           Vec3 position) {
        addClientParticle(level, options, position, position, 0.0F);
    }

    public static void particleCircle(Level level, ParticleOptions options,
                                      Vec3 center, float radius, int count, Vec3 normal) {
        particleCircle(level, options, center, radius, count, normal, Vec3.ZERO);
    }

    public static void particleCircle(Level level, ParticleOptions options, Vec3 center,
                                      float radius, int count, Vec3 normal, Vec3 speed) {
        Vec3 n = normal.normalize();
        Vec3 ref = Math.abs(n.x) < 0.99 ? new Vec3(1, 0, 0) :
                new Vec3(0, 1, 0);
        Vec3 u = n.cross(ref).normalize();
        Vec3 v = n.cross(u).normalize();
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            Vec3 pos = center.add(u.scale(x * radius)).add(v.scale(y * radius));
            level.addParticle(options, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z);
        }
    }
}
