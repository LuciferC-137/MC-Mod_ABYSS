package wardentools.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
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

    public WhistleItem(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player,
                                                           @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        play(level, player);
        player.getCooldowns().addCooldown(this, USE_DURATION);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!level.isClientSide) callForTamedNoctilure(level, player);
        return InteractionResultHolder.consume(itemstack);
    }

    private void callForTamedNoctilure(Level level, Player player) {
        AABB aabb = player.getBoundingBox().inflate(callRange);
        level.getEntitiesOfClass(NoctilureEntity.class, aabb).forEach(noctilure -> {
            if (noctilure.isTamed() && noctilure.getOwner() == player) {
                noctilure.call();
            }
        });
    }

    public int getUseDuration(@NotNull ItemStack stack) {
        return USE_DURATION;
    }

    private static void play(Level level, Player player) {
        level.playSound(player, player, SOUND, SoundSource.RECORDS, 0.8f, 1.0F);
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }
}
