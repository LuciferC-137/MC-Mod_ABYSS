package wardentools.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class WardenLaserAttack {
	
	public WardenLaserAttack() {
		
	}
	
    public void tick(ServerLevel level, Player player, long time, Vec3 startPosition, Vec3 direction, int laserLength) {

        // Apply damage and effects to target entities
        for (Entity entity : level.getEntities(player,
        		player.getBoundingBox().expandTowards(direction.scale(laserLength)).inflate(3.0D))) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity != player) {
                    livingEntity.hurt(player.damageSources().sonicBoom(player), 38.0F);

                    double knockbackResistance = 1.0D - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                    double knockbackHorizontal = 2.5D * knockbackResistance;
                    double knockbackVertical = 0.5D * knockbackResistance;
                    livingEntity.push(direction.x * knockbackHorizontal,
                    		direction.y * knockbackVertical, direction.z * knockbackHorizontal);
                    
                }
            }
        }
    }
}
