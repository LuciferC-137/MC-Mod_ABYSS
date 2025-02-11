package wardentools.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.NoctilureEntity;
import wardentools.sounds.ModSounds;

public class WhistleItem extends Item {
    private static final int USE_DURATION = 100;
    private static final SoundEvent SOUND = ModSounds.WHISTLE.get();
    private static final float callRange = 100f;
    private SoundInstance lastSoundInstance;

    public WhistleItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player,
                                          @NotNull InteractionHand hand) {
        player.startUsingItem(hand);
        play(level, player);
        player.getCooldowns().addCooldown(player.getItemInHand(hand),
                USE_DURATION * 2);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!level.isClientSide) callForTamedNoctilure(level, player);
        return InteractionResult.CONSUME;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getSoundManager().stop(this.lastSoundInstance);
        super.onStopUsing(stack, entity, count);
    }

    public boolean isSoundActive() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getSoundManager().isActive(this.lastSoundInstance);
    }

    public boolean doUseAnimation(ItemStack stack, Level level, Entity entity, int seed){
        if (entity instanceof Player player) {
            return this.isSoundActive() || (player.isUsingItem()
                    && player.getItemInHand(InteractionHand.MAIN_HAND)
                    .is(stack.getItem()));
        }
        return this.isSoundActive();
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return USE_DURATION;
    }

    private void callForTamedNoctilure(Level level, Player player) {
        AABB aabb = player.getBoundingBox().inflate(callRange);
        level.getEntitiesOfClass(NoctilureEntity.class, aabb).forEach(noctilure -> {
            if (noctilure.isTamed() && noctilure.getOwner() == player) {
                noctilure.call();
            }
        });
    }


    private void play(Level level, Player player) {
        if (level.isClientSide) {
            this.lastSoundInstance
                    = new EntityBoundSoundInstance(SOUND, SoundSource.PLAYERS,
                    0.8f, 1.0F, player, 1L);
            Minecraft.getInstance().getSoundManager().play(lastSoundInstance);
        }
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }
}
