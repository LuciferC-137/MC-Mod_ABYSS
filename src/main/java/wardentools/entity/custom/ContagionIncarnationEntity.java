package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import wardentools.sounds.ModSounds;

public class ContagionIncarnationEntity extends Monster {

	public ContagionIncarnationEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1000D)
	            .add(Attributes.MOVEMENT_SPEED, 0.2D)
	            .add(Attributes.JUMP_STRENGTH, 3.0D)
	            .add(Attributes.ATTACK_DAMAGE, 10.0D);
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			
		}
		super.tick();
	}
	
	public static boolean canSpawn(EntityType<ContagionIncarnationEntity> entityType, ServerLevelAccessor level,
            MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    	return true;
    }
	
	@Override
	public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
    	return true;
    }
	
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.CONTAGION_INCARNATION_SCREAM.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ModSounds.CONTAGION_INCARNATION_CRAWL.get(), 1.0F, 1.0F);
    }

}
