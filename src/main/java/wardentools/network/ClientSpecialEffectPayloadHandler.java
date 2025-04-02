package wardentools.network;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.joml.Vector3f;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.misc.wind.WhisperManager;
import wardentools.network.PayloadsRecords.ParticlesSounds.*;
import wardentools.particle.ParticleRegistry;
import wardentools.sounds.ModMusics;
import wardentools.sounds.ModSounds;

public class ClientSpecialEffectPayloadHandler {
    private static final BlockParticleOption FENCE_PARTICLE
            = new BlockParticleOption(ParticleTypes.BLOCK,
            BlockRegistry.DARKTREE_FENCE.get().defaultBlockState());

    public static void ancientLaboratoryGateSound(AncientLaboratoryGateSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Vector3f source = msg.pos();
            Level level = ctx.player().level();
            BlockPos pos = new BlockPos((int)source.x(), (int)source.y(), (int)source.z());
            level.playLocalSound(pos, ModSounds.ANCIENT_LABORATORY_GATE_CLOSING.get(), SoundSource.BLOCKS,
                    2.5f, 1.0f, false);
        }, ctx);
    }

    public static void incarnationEmergeSound(IncarnationEmergeSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            SoundEvent soundEvent = ModSounds.CONTAGION_INCARNATION_EMERGE.get();
            Level level = ctx.player().level();
            level.playLocalSound(msg.pos().x, msg.pos().y, msg.pos().z,
                    soundEvent, SoundSource.HOSTILE, 1.0f, 1.0f, false);
        }, ctx);
    }

    public static void incarnationScreamSound(IncarnationScreamSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Vector3f source = msg.pos();
            Level level = ctx.player().level();
            BlockPos pos = new BlockPos((int)source.x(), (int)source.y(), (int)source.z());
            level.playLocalSound(pos, ModSounds.CONTAGION_INCARNATION_SCREAM.get(), SoundSource.HOSTILE,
                    2f, 1.0f, false);
        }, ctx);
    }

    public static void incarnationSonicStrikeSound(IncarnationSonicStrikeSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Vector3f source = msg.pos();
            Level level = ctx.player().level();
            BlockPos pos = new BlockPos((int)source.x(), (int)source.y(), (int)source.z());
            level.playLocalSound(pos, ModSounds.SONIC_STRIKE.get(), SoundSource.HOSTILE,
                    4f, 1.0f, false);
        }, ctx);
    }

    public static void contagionParticleExplosion(ContagionParticleExplosion msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            particleExplosion(level, msg.pos(), msg.radius(),
                    msg.speed(), msg.particleNumber(), ParticleRegistry.CORRUPTION.get(), msg.implosion());
        }, ctx);
    }

    public static void particleDarktreeFenceDestroy(ParticleDarktreeFenceDestroy msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            for (int i = 0; i < 8; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * 0.05;
                double offsetY = (level.random.nextDouble() - 0.5) * 0.05;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.05;
                level.addParticle(FENCE_PARTICLE, msg.pos().x, msg.pos().y, msg.pos().z,
                        offsetX, offsetY, offsetZ);
            }
        }, ctx);
    }

    public static void radianceCatalystChargedParticleSound(RadianceCatalystChargedParticleSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            int number = level.random.nextInt(3) + 1;
            for (int i = 0; i < number; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
                double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
                level.addParticle(RadianceCatalystBlockEntity.PARTICLE, true,
                        msg.pos().x, msg.pos().y, msg.pos().z, offsetX, offsetY, offsetZ);
            }
            level.playLocalSound(msg.pos().x, msg.pos().y, msg.pos().z,
                    SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 0.5F, 1.0F, false);
        }, ctx);
    }

    public static void radianceCatalystChargingParticleSound(RadianceCatalystChargingParticleSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            double speed = 1d / 15d;
            double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
            double offsetY = (level.random.nextDouble() - 0.5) * 2.0;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;
            level.addParticle(RadianceCatalystBlockEntity.PARTICLE, true,
                    msg.pos().x + offsetX, msg.pos().y + offsetY, msg.pos().z + offsetZ,
                    -offsetX * speed, -offsetY * speed, -offsetZ * speed);
            if (level.random.nextInt(20)==1) {
                level.playLocalSound(msg.pos().x, msg.pos().y, msg.pos().z, SoundEvents.FURNACE_FIRE_CRACKLE,
                        SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
        }, ctx);
    }

    public static void radianceCatalystPurifyingParticleSound(RadianceCatalystPurifyingParticleSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            int number = level.random.nextInt(3) + 1;
            for (int i = 0; i < number; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
                double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
                level.addParticle(ParticleRegistry.CORRUPTION.get(), true,
                        msg.pos().x, msg.pos().y, msg.pos().z, offsetX, offsetY, offsetZ);
            }
            level.playLocalSound(msg.pos().x, msg.pos().y, msg.pos().z,
                    SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 0.5f, 1.0F, false);
        }, ctx);
    }

    public static void radianceParticleExplosion(RadianceParticleExplosion msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            particleExplosion(level, msg.pos(), msg.radius(),
                    msg.speed(), msg.particleNumber(), ParticleRegistry.RADIANCE.get(), msg.implosion());
        }, ctx);
    }

    public static void wardenDeathParticle(WardenDeathParticle msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            particleExplosion(level, msg.pos(), 0.1f, 0.6f, 100,
                    ParticleTypes.SQUID_INK, false);
            for (int i=0; i<100; i++) {
                double offsetX = (level.random.nextDouble() - 0.5D);
                double offsetZ = (level.random.nextDouble() - 0.5D);
                level.addParticle(ParticleRegistry.CORRUPTION.get(), false,
                        msg.pos().x, msg.pos().y, msg.pos().z, offsetX, 0, offsetZ);
            }
        }, ctx);
    }

    public static void themeIncarnationStart(ThemeIncarnationStart msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Minecraft.getInstance().getMusicManager().stopPlaying();
            Minecraft.getInstance().getMusicManager().startPlaying(ModMusics.INCARNATION_THEME);
        }, ctx);
    }

    public static void protectorHeartSynchronize(ProtectorHeartSynchronize msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            BlockPos pos = new BlockPos((int)msg.pos().x, (int)msg.pos().y, (int)msg.pos().z);
            if (level.getBlockEntity(pos) instanceof ProtectorInvokerBlockEntity invoker) {
                invoker.saveHealth(msg.health());
            }
        }, ctx);
    }

    public static void wardenLaserParticleSound(WardenLaserParticleSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            Level level = ctx.player().level();
            Vector3f startPosition = msg.startPos();
            Vector3f direction = msg.direction();
            for (int i = 1; i < Mth.floor(direction.length()) + msg.laserLength(); ++i) {
                Vector3f particlePosition = startPosition.add(direction.mul((float)i));
                level.addParticle(ParticleTypes.SONIC_BOOM, false,
                        particlePosition.x, particlePosition.y, particlePosition.z, 0.0D, 0.0D, 0.0D);
            }
            level.playLocalSound(new BlockPos((int)startPosition.x, (int)startPosition.y, (int)startPosition.z),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS,
                    1.0F, 1.0F, false);
        }, ctx);
    }

    public static void windWhispererMessageSound(WindWhispererMessageSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof LocalPlayer player) {
                WhisperManager.sendRandomWhisperToPlayer(player);
            }
        }, ctx);
    }

    public static void windWhisperSound(WindWhisperSound msg, final IPayloadContext ctx) {
        handleDataOnNetwork(() -> {
            if (ctx.player() instanceof LocalPlayer player) {
                player.playSound(ModSounds.WIND_WHISPERS.get(), 5f,
                        (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);
            }
        }, ctx);
    }

    private static void particleExplosion(Level level, Vector3f pos, float radius,
                                          float speed, int particleNumber, SimpleParticleType particle,
                                          boolean implosion) {
        for (int i = 0; i < particleNumber; i++) {
            double theta = level.random.nextFloat() * Math.PI * 2;
            double phi = Math.acos(1 - 2 * level.random.nextFloat());

            float offsetX = (float) (radius * Math.sin(phi) * Math.cos(theta));
            float offsetY = (float) (radius * Math.cos(phi));
            float offsetZ = (float) (radius * Math.sin(phi) * Math.sin(theta));

            float norm = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
            if (norm == 0) continue;
            float adjustedSpeed = speed / norm * ((level.random.nextFloat() + 4F) / 5F);
            if (implosion) adjustedSpeed = -adjustedSpeed;

            level.addParticle(particle,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    offsetX * adjustedSpeed, offsetY * adjustedSpeed, offsetZ * adjustedSpeed);
        }
    }




    private static void handleDataOnNetwork(Runnable run, final IPayloadContext ctx) {
        ctx.enqueueWork(run)
                .exceptionally(e -> {
                    LogUtils.getLogger().error("Dive Into the Abyss networking failed{}", e.getMessage());
                    ctx.disconnect(Component.literal("Dive Into the Abyss networking failed"));
                    return null;
                });
    }
}
