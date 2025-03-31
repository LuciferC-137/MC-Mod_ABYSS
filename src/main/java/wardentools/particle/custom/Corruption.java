package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class Corruption extends TextureSheetParticle {
    protected Corruption(ClientLevel level, double x, double y, double z,
                         SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);
        this.friction = 0.8F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.35F;
        this.lifetime = 50;
        this.setSpriteFromAge(spriteSet);
        this.rCol = 1f - level.random.nextFloat() * 0.4f;
        this.gCol = 1f - level.random.nextFloat() * 0.1f;
        this.bCol = 1f - level.random.nextFloat() * 0.1f;
    }

    @Override
    public void tick() {
        super.tick();
        this.fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new Corruption(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
