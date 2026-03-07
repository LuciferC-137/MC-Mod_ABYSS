package wardentools.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.client.rendering.AuroraShaderManager;
import wardentools.particle.options.PrismaticFireflyOptions;

@OnlyIn(Dist.CLIENT)
public class PrismaticFirefly extends TextureSheetParticle {
    private static final int AVERAGE_LIFETIME = 120;
    private static final int LIFETIME_VARIATION = 40;
    private static final float COLOR_VARIATION = 0.1f;
    private static final float SPEED_VARIATION = 0.02f;
    private static final int AVERAGE_TIME_BETWEEN_DIRECTION_CHANGE = 50;
    private static final int DIRECTION_CHANGE_VARIATION = 30;
    private final boolean colorFixed;
    private int timeUntilDirectionChange = 0;
    private final float colorVariation;

    protected PrismaticFirefly(ClientLevel level, double x, double y, double z,
                               boolean colorFixed, int color,
                               SpriteSet sprites) {
        super(level, x, y, z);
        this.friction = 0.999F;
        this.quadSize *= 0.1F + (this.random.nextFloat() - 0.5F) * 0.06F;
        this.lifetime = AVERAGE_LIFETIME + this.random.nextInt(2 * LIFETIME_VARIATION) - LIFETIME_VARIATION;
        this.setColor(((color >> 16) & 0xFF) / 255F,
                ((color >> 8) & 0xFF) / 255F,
                (color & 0xFF) / 255F);
        this.colorVariation = (this.random.nextFloat() * 2 - 1) * COLOR_VARIATION;
        this.colorFixed = colorFixed;
        this.changeDirection();
        this.setSpriteFromAge(sprites);
    }

    private void changeDirection() {
        Vector3f newDir = getRandomDirection();
        newDir.mul((this.level.random.nextFloat() / 2F + 0.5F) * SPEED_VARIATION);
        this.xd = newDir.x();
        this.yd = newDir.y();
        this.zd = newDir.z();
    }

    private Vector3f getRandomDirection() {
        float x = this.random.nextFloat() * 2 - 1;
        float y = this.random.nextFloat() * 2 - 1;
        float z = this.random.nextFloat() * 2 - 1;
        Vector3f dir = new Vector3f(x, y, z);
        dir.normalize();
        return dir;
    }

    private void updateColor(float[] baseColor) {
        float r = baseColor[0] + this.colorVariation;
        float g = baseColor[1] + this.colorVariation;
        float b = baseColor[2] + this.colorVariation;
        this.setColor(clampColor(r), clampColor(g), clampColor(b));
    }

    private float clampColor(float value) {
        return Math.max(0f, Math.min(1f, value));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.timeUntilDirectionChange <= 0) {
            this.timeUntilDirectionChange = AVERAGE_TIME_BETWEEN_DIRECTION_CHANGE
                    + this.random.nextInt(2 * DIRECTION_CHANGE_VARIATION) - DIRECTION_CHANGE_VARIATION;
            this.changeDirection();
        } else {
            this.timeUntilDirectionChange--;
        }
        if (!this.colorFixed && AuroraShaderManager.colorOverride(this.level.getBiome(this.myPos()))) {
            this.updateColor(AuroraShaderManager.getAuroraColor());
        }
    }

    private BlockPos myPos() {
        return new BlockPos((int)this.x, (int)this.y, (int)this.z);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<PrismaticFireflyOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(PrismaticFireflyOptions options, @NotNull ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new PrismaticFirefly(level, x, y, z,
                    options.colorFixed(), options.color(), this.sprites);
        }
    }

}
