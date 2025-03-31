package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class Radiance extends SimpleAnimatedParticle {
    Radiance(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0125F);
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.7F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setFadeColor(15916745);
        this.setSpriteFromAge(sprites);
    }

    public void move(double xd, double yd, double zd) {
        this.setBoundingBox(this.getBoundingBox().move(xd, yd, zd));
        this.setLocationFromBoundingbox();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level,
                                       double x, double y, double z,
                                       double xd, double yd, double zd) {
            return new Radiance(level, x, y, z, xd, yd, zd, this.sprites);
        }
    }
}
