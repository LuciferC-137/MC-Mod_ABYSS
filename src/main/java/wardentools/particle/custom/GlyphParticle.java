package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.options.GlyphParticleOptions;

@OnlyIn(Dist.CLIENT)
public class GlyphParticle extends TextureSheetParticle {
    private static final int AV_SPRITE_DURATION = 20;

    private final Vec3 goal;
    private final boolean emissive;
    private final boolean hasGoal;
    private final SpriteSet sprites;

    protected GlyphParticle(ClientLevel level, double x, double y, double z,
                            SpriteSet spriteSet, double xd, double yd, double zd,
                            Vec3 goal, int lifetime, int color, boolean emissive, boolean hasGoal) {
        super(level, x, y, z, xd, yd, zd);
        this.sprites = spriteSet;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.friction = 1F;
        this.hasPhysics = false;
        this.quadSize = 0.0525F;
        this.lifetime = lifetime;
        this.goal = goal;
        this.emissive = emissive;
        this.hasGoal = hasGoal;
        this.setSpriteFromAge(this.sprites);
        this.setColor(((color >> 16) & 0xFF) / 255F,
                ((color >> 8) & 0xFF) / 255F,
                (color & 0xFF) / 255F);
    }

    @Override
    public int getLightColor(float f) {
        return emissive ? LightTexture.FULL_BRIGHT : super.getLightColor(f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasGoal) {
            double distanceSq = this.getPos().distanceToSqr(this.goal);
            if (distanceSq < 0.005) {
                this.remove();
                return;
            }
        }
        if (this.random.nextInt(AV_SPRITE_DURATION) == 0) {
            this.setRandomSprite();
        }
        this.fadeOut();
    }

    public void setRandomSprite() {
        this.pickSprite(this.sprites);
        this.setSpriteFromAge(this.sprites);
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<GlyphParticleOptions> {
        private final SpriteSet sprites;
        public Provider(SpriteSet sprites) { this.sprites = sprites; }

        @Override
        public Particle createParticle(GlyphParticleOptions opts, @NotNull ClientLevel level,
                                       double x, double y, double z, double dx, double dy, double dz) {
            GlyphParticle p = new GlyphParticle(level, x, y, z, this.sprites, dx, dy, dz,
                    opts.goal(), opts.lifetime(), opts.color(), opts.emissive(), opts.hasGoal());
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
