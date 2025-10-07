package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.options.GlyphParticleRotatingOptions;

@OnlyIn(Dist.CLIENT)
public class GlyphParticleRotating extends TextureSheetParticle {
    private static final int AV_SPRITE_DURATION = 20;

    private final Vec3 center;
    private final Vec3 axis; // Axe de rotation
    private final boolean emissive;
    private final SpriteSet sprites;

    private final double angularVelocity;

    private double totalAngle = 0;
    private final double radius;
    private final Vec3 initialDir;

    protected GlyphParticleRotating(ClientLevel level, double x, double y, double z,
                                    SpriteSet spriteSet, double xd, double yd, double zd,
                                    Vec3 center, Vec3 axis, int lifetime, int color,
                                    boolean emissive, double angularVelocity) {
        super(level, x, y, z, xd, yd, zd);
        this.sprites = spriteSet;
        this.friction = 1F;
        this.hasPhysics = false;
        this.quadSize = 0.0525F;
        this.lifetime = lifetime;
        this.center = center;
        this.axis = axis.normalize();
        this.emissive = emissive;

        this.angularVelocity = angularVelocity;
        Vec3 offset = new Vec3(this.x, this.y, this.z).subtract(center);
        this.radius = offset.length();
        this.initialDir = offset.normalize();

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
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.xd = this.yd = this.zd = 0;

        this.age++;
        if (this.age >= this.lifetime) {
            this.remove();
            return;
        }

        totalAngle += angularVelocity;
        Vec3 rotated = rotateAroundAxis(initialDir.scale(radius), axis, (float) totalAngle);

        Vec3 worldPos = center.add(rotated);
        this.setPos(worldPos.x, worldPos.y, worldPos.z);

        if (this.random.nextInt(AV_SPRITE_DURATION) == 0) {
            this.setRandomSprite();
        }

        this.fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1 / (float) lifetime) * age + 1);
    }

    private void setRandomSprite() {
        this.pickSprite(this.sprites);
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    private static Vec3 rotateAroundAxis(Vec3 pos, Vec3 axis, float angle) {
        axis = axis.normalize();
        double half = angle / 2.0;
        double a = Mth.cos((float) half);
        double s = Mth.sin((float) half);
        Vec3 b = axis.scale(s);

        // p' = pos + 2 * b × (a * pos + b × pos)
        return pos.add(b.cross(pos.scale(a)).add(b.cross(b.cross(pos))).scale(2));
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<GlyphParticleRotatingOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(GlyphParticleRotatingOptions opts, @NotNull ClientLevel level,
                                       double x, double y, double z, double dx, double dy, double dz) {
            GlyphParticleRotating p = new GlyphParticleRotating(
                    level, x, y, z, this.sprites,
                    dx, dy, dz,
                    opts.center(), opts.axis(),
                    opts.lifetime(), opts.color(),
                    opts.emissive(), opts.angularVelocity());
            p.pickSprite(this.sprites);
            return p;
        }
    }
}
