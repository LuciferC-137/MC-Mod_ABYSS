package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class AbyssPortal extends TextureSheetParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected AbyssPortal(ClientLevel level, double x, double y, double z,
                          SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, x, y, z);
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
        this.quadSize = 0.1F * (this.random.nextFloat() * 0.2F + 0.5F);
        float f = this.random.nextFloat() * 0.3F + 0.7F;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;
        this.lifetime = (int)(Math.random() * 10.0D) + 40;
    }

    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double xd, double yd, double zd) {
        this.setBoundingBox(this.getBoundingBox().move(xd, yd, zd));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float v) {
        float f = ((float)this.age + v) / (float)this.lifetime;
        f = 1.0F - f;
        f *= f;
        f = 1.0F - f;
        return this.quadSize * f;
    }

    public int getLightColor(float v) {
        int i = super.getLightColor(v);
        float f = (float)this.age / (float)this.lifetime;
        f *= f;
        f *= f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.x = this.xStart + this.xd * (double)f2;
            this.y = this.yStart + this.yd * (double)f2 + (double)(1.0F - f);
            this.z = this.zStart + this.zd * (double)f2;
            this.setPos(this.x, this.y, this.z);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType,
                                       @NotNull ClientLevel level, double x, double y, double z,
                                       double xd, double yd, double zd) {
            AbyssPortal portalParticle = new AbyssPortal(level, x, y, z, this.sprite, xd, yd, zd);
            portalParticle.pickSprite(this.sprite);
            return portalParticle;
        }
    }
}
