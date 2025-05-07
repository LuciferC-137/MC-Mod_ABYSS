package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.WardenLaserParticleAndSoundPacket;

import javax.annotation.Nullable;


public class SonicBlaster extends DirectionalBlock {
    public static final MapCodec<SonicBlaster> CODEC = simpleCodec(SonicBlaster::new);
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    private static final int TRIGGER_DELAY = 4;
    private static final float LASER_LENGTH = 6f;
    private static final float PUSH_STRENGTH = 2.8f;

    @Override
    protected @NotNull MapCodec<SonicBlaster> codec() {return CODEC;}

    public SonicBlaster(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.FALSE));
    }

    @Nullable
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        BlockState blockStatePlacement = super.getStateForPlacement(placeContext);
        if (blockStatePlacement != null) {
            return blockStatePlacement.setValue(FACING, placeContext.getNearestLookingDirection().getOpposite());
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING).add(TRIGGERED);
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        sonicBoom(level, pos, state.getValue(FACING));
    }

    protected void neighborChanged(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                   @NotNull Block block, @NotNull BlockPos posNeighbor, boolean b) {
        boolean redstoneSignal = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        if (redstoneSignal && !state.getValue(TRIGGERED)) {
            level.scheduleTick(pos, this, TRIGGER_DELAY);
            state.setValue(TRIGGERED, true);
        } else if (!redstoneSignal && state.getValue(TRIGGERED)) {
            state.setValue(TRIGGERED, false);
        }
    }

    private void sonicBoom(ServerLevel level, BlockPos pos, Direction facing) {
        Vec3 origin = Vec3.atCenterOf(pos);
        Vec3 direction = Vec3.directionFromRotation(facing.toYRot(), facing == Direction.UP ?
                -90F : (facing == Direction.DOWN ? 90F : 0F));
        Vec3 target = origin.add(direction.scale(LASER_LENGTH));

        AABB aabb = new AABB(origin, target).inflate(3.0D);
        PacketHandler.sendToAllClient(new WardenLaserParticleAndSoundPacket(
                origin, direction, (int)LASER_LENGTH));

        for (Entity entity : level.getEntities(null, aabb)) {
            if (entity instanceof LivingEntity livingEntity) {
                double knockbackResistance = 1.0D - livingEntity
                        .getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                double knockback = (double)PUSH_STRENGTH * knockbackResistance;

                livingEntity.push(
                        direction.x * knockback,
                        direction.y * knockback,
                        direction.z * knockback
                );
                if (livingEntity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(
                            serverPlayer.getId(),
                            serverPlayer.getDeltaMovement()
                    ));
                }
            }
        }
    }


}
