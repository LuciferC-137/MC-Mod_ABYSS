package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.wind.WhisperManager;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionImplosion;
import wardentools.playerdata.KnownWhispersDataProvider;

public class WindWhispererBlock extends Block {

    public WindWhispererBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.2, 0.0, 0.2,
                0.8, 1.0, 0.8);
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (WhisperManager.INSTANCE.sendRandomWhisperToAllPlayers(level)) {
            PacketHandler.sendToAllClient(new ParticleContagionImplosion(blockPos.getCenter()));
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            player.getCapability(KnownWhispersDataProvider.PLAYER_DATA).ifPresent(data -> {
                for (int i : data.getAll().stream().toList()) {
                    player.sendSystemMessage(Component.literal("Known: " + i));
                }
            });
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}
