package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.Crystal;

public class PaleShine extends TextureSheetParticle {
    protected PaleShine(ClientLevel level, double x, double y, double z,
                        SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);
        this.friction = 0.4F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.35F;
        this.lifetime = 50;
        this.setSpriteFromAge(spriteSet);
        this.setColor(Crystal.PALE.getRed(), Crystal.PALE.getGreen(), Crystal.PALE.getBlue());
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
            PaleShine shine = new PaleShine(level, x, y, z, this.sprites, dx, dy, dz);
            shine.pickSprite(this.sprites);
            return shine;
        }
    }
}
