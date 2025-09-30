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
}
