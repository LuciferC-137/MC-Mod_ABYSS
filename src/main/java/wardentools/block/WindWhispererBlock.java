package wardentools.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;
import wardentools.misc.WindWhisper;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionImplosion;
import wardentools.worldgen.dimension.ModDimensions;

public class WindWhispererBlock extends Block {
    private final WindWhisper windWhisper;

    public WindWhispererBlock(Properties properties) {
        super(properties);
        this.windWhisper = new WindWhisper();
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.2, 0.0, 0.2,
                0.8, 1.0, 0.8);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        level.players().forEach(player -> {
            if (isInAbyss(player)) {
                Minecraft minecraft = Minecraft.getInstance();
                LanguageManager languageManager = minecraft.getLanguageManager();
                String currentLanguage = languageManager.getSelected();
                if ("fr_fr".equals(currentLanguage)) {
                    sendMessage(player, "<Vent> " + this.windWhisper.getWhisperFr());
                }
                else {
                    sendMessage(player, "<Wind> " + this.windWhisper.getWhisperEn());
                }
            }
        });
        PacketHandler.sendToAllClient(new ParticleContagionImplosion(blockPos.getCenter()));
    }

    private static void sendMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }

    private static boolean isInAbyss(Player player) {
        if (player==null) return false;
        return player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY;
    }
}
