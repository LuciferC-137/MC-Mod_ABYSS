package wardentools.events.gameevents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.block.LiquidCorruptionBlock;
import wardentools.effect.ModEffects;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ApplyCorruptionInLiquidCorruption {

    @SubscribeEvent
    public static void onLivingUpdate(LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide) {
            BlockPos entityPos = entity.blockPosition();
            BlockState blockState = entity.level().getBlockState(entityPos);
            if (blockState.is(BlockRegistry.LIQUID_CORRUPTION_BLOCK.get())
                    && entity.level().getGameTime()%20==0) {
                entity.addEffect(new MobEffectInstance(ModEffects.CORRUPTED.get(),
                        400, 1, false, false));
                Holder<DamageType> magicDamageTypeHolder
                        = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DamageTypes.MAGIC);
                entity.hurt(new DamageSource(magicDamageTypeHolder,
                        null, entity, null), 3f);
            }
        }
    }
}
