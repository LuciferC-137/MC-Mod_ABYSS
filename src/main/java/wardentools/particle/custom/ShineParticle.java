package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.options.ShineParticleOptions;

@OnlyIn(Dist.CLIENT)
public class ShineParticle extends TextureSheetParticle {
    private final Vec3 goal;
    private final boolean emissive;
    private final boolean hasGoal;

    protected ShineParticle(ClientLevel level, double x, double y, double z,
                            SpriteSet spriteSet, double xd, double yd, double zd,
                            Vec3 goal, int color, boolean emissive, boolean hasGoal) {
        super(level, x, y, z, xd, yd, zd);
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.friction = 0.95F;
        this.quadSize *= 0.35F;
        this.lifetime = 40;
        this.goal = goal;
        this.emissive = emissive;
        this.hasGoal = hasGoal;
        this.setSpriteFromAge(spriteSet);
        this.setColor(((color >> 16) & 0xFF) / 255F,
                ((color >> 8) & 0xFF) / 255F,
                (color & 0xFF) / 255F);
    }

    @Override
    protected int getLightColor(float f) {
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
    public static class Provider implements ParticleProvider<ShineParticleOptions> {
        private final SpriteSet sprites;
        public Provider(SpriteSet sprites) { this.sprites = sprites; }

        @Override
        public Particle createParticle(ShineParticleOptions opts, @NotNull ClientLevel level,
                                       double x, double y, double z, double dx, double dy, double dz) {
            ShineParticle p = new ShineParticle(level, x, y, z, this.sprites, dx, dy, dz,
                    opts.goal(), opts.color(), opts.emissive(), opts.hasGoal());
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
