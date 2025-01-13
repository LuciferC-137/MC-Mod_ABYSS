package wardentools.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.TemperEntity;

import java.util.List;

public class RadianceBringerEffect extends MobEffect {
    private static final int RADIUS_FOR_SPAWN = 3;
    private static final int MAX_NUMBER_OF_TEMPER = 3;

    protected RadianceBringerEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.tickCount%(200 / (amplifier + 1))==0)) return false;
        if (!(entity instanceof Player)) return false;
        if (countMyTemper(entity.level(), (Player)entity) >= MAX_NUMBER_OF_TEMPER) return false;
        TemperEntity temper = ModEntities.TEMPER.get().create(entity.level());
        if (temper==null) return false;
        BlockPos spawnPos = findSpawnPosition(entity.level(), entity.getOnPos());
        if (spawnPos == null) return false;
        temper.moveTo(spawnPos.getX() + 0.5f, spawnPos.getY() + 0.5f,
                spawnPos.getZ() + 0.5f);
        temper.setPlayerInvoker((Player)entity);
        entity.level().addFreshEntity(temper);
        return super.applyEffectTick(entity, amplifier);
    }

    private BlockPos findSpawnPosition(Level level, BlockPos origin) {
        for (int r = 1; r <= RADIUS_FOR_SPAWN; r++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (Math.abs(dx) == r || Math.abs(dz) == r) {
                        BlockPos pos = origin.offset(dx, 0, dz);
                        if (level.getBlockState(pos).isAir()) {
                            return pos;
                        }
                    }
                }
            }
        }
        return null;
    }

    private int countMyTemper(Level level, Player player) {
        if (player == null) return 0;
        List<TemperEntity> entities = level.getEntitiesOfClass(TemperEntity.class,
                new AABB(player.getX() - 30, player.getY() - 30, player.getZ() - 30,
                        player.getX() + 30, player.getY() + 30, player.getZ() + 30));
        int cnt = 0;
        for (TemperEntity temper : entities) {
            if (temper.getPlayerInvoker() != null && temper.getPlayerInvoker().is(player)) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
