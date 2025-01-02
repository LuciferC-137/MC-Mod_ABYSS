package wardentools.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wardentools.items.enchantment.EnchantmentRegistry;

import java.util.Optional;

@Mixin(Entity.class)
public class EntityWalkingVibrationMixin {
    @Shadow public float fallDistance;
    @Shadow public Optional<BlockPos> mainSupportingBlockPos;
    @Shadow private Vec3 position;

    @Inject(method="vibrationAndSoundEffectsFromBlock", at=@At("HEAD"), cancellable = true)
    private void onVibrationAndSound(BlockPos pos, BlockState state, boolean doSound, boolean doVibration,
                                     Vec3 movement, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof Player player) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (boots.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                cir.cancel();
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method="checkFallDamage", at=@At("HEAD"), cancellable = true)
    protected void checkFallDamage(double v, boolean b, BlockState state, BlockPos pos, CallbackInfo ci) {
        Level level = ((Entity)(Object)this).level();
        if (b) {
            if (this.fallDistance > 0.0F) {
                state.getBlock().fallOn(level, state, pos, (Entity)(Object)this, this.fallDistance);
                boolean cancel = false;
                if ((Object)this instanceof Player player) {
                    ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
                    if (leggings.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                        cancel = true;
                    }
                }
                if (!cancel) {
                    level.gameEvent(GameEvent.HIT_GROUND, this.position, GameEvent.Context.of((Entity)(Object)this,
                            this.mainSupportingBlockPos.map(level::getBlockState).orElse(state)));
                }
            }
            this.fallDistance = 0F;
        } else if (v < 0.0D) {
            this.fallDistance -= (float)v;
        }
        ci.cancel();
    }

    @Inject(method="dampensVibrations", at=@At("HEAD"), cancellable = true)
    private void onDampensVibrations(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof Player player) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (boots.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0 &&
                    leggings.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0 &&
                    chestplate.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0 &&
                    helmet.getEnchantmentLevel(EnchantmentRegistry.STEALTH.get()) > 0) {
                cir.cancel();
                cir.setReturnValue(true);
            }
        }
    }
}
