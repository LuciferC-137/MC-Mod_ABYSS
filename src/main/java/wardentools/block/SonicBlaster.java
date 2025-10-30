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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.network.payloads.special_effects.WardenLaserParticleSound;
import wardentools.tags.ModTags;

import javax.annotation.Nullable;


public class SonicBlaster extends DirectionalBlock {
    public static final MapCodec<SonicBlaster> CODEC = simpleCodec(SonicBlaster::new);
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final int TRIGGER_DELAY = 4;
    private static final float LASER_LENGTH = 6f;
    private static final float LASER_RADIUS = 1.5f;
    private static final float PUSH_STRENGTH = 2.8f;

    @Override
    protected @NotNull MapCodec<SonicBlaster> codec() {return CODEC;}

    public SonicBlaster(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.FALSE).setValue(POWERED, Boolean.FALSE));
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
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                           @NotNull BlockState state1, boolean b) {
        super.onPlace(state, level, pos, state1, b);
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            if (level.getBlockState(neighborPos).is(ModTags.Blocks.CRISTAL_BLOCK)) {
                level.setBlock(pos, state.setValue(POWERED, true), Block.UPDATE_ALL);
                break;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING).add(TRIGGERED).add(POWERED);
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        sonicBoom(level, pos, state.getValue(FACING));
    }

    protected void neighborChanged(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                   @NotNull Block block, @NotNull BlockPos posNeighbor, boolean b) {
        if (level.getBlockState(posNeighbor).is(ModTags.Blocks.CRISTAL_BLOCK)) {
            level.setBlock(pos, state.setValue(POWERED, true), Block.UPDATE_ALL);
        } else {
            turnOffIfNoCrystalNeighbor(state, level, pos);
        }
        if (state.getValue(POWERED)) {
            boolean redstoneSignal = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
            if (redstoneSignal && !state.getValue(TRIGGERED)) {
                level.scheduleTick(pos, this, TRIGGER_DELAY);
                level.setBlock(pos, state.setValue(TRIGGERED, true), Block.UPDATE_ALL);
            } else if (!redstoneSignal && state.getValue(TRIGGERED)) {
                level.setBlock(pos, state.setValue(TRIGGERED, false), Block.UPDATE_ALL);
            }
        }
        super.neighborChanged(state, level, pos, block, posNeighbor, b);
    }

    private void turnOffIfNoCrystalNeighbor(@NotNull BlockState state, Level level, @NotNull BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            if (level.getBlockState(neighborPos).is(ModTags.Blocks.CRISTAL_BLOCK)) {
                return;
            }
        }
        level.setBlock(pos, state.setValue(POWERED, false)
                .setValue(TRIGGERED, false), Block.UPDATE_ALL);
    }

    private void sonicBoom(ServerLevel level, BlockPos pos, Direction facing) {
        Vec3 origin = Vec3.atCenterOf(pos);
        Vec3 direction = Vec3.atLowerCornerOf(facing.getNormal()).normalize();
        Vec3 target = origin.add(direction.scale(LASER_LENGTH));

        AABB aabb = sonicHitBox(facing, origin, target);

        PacketDistributor.sendToPlayersTrackingChunk(level,
                level.getChunkAt(pos).getPos(),
                new WardenLaserParticleSound(origin.toVector3f(), direction.toVector3f(), (int) LASER_LENGTH));

        for (Entity entity : level.getEntities(null, aabb)) {
            if (entity instanceof LivingEntity livingEntity) {
                double knockbackResistance = 1.0D - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                double knockback = (double) PUSH_STRENGTH * knockbackResistance;
                if (facing == Direction.DOWN || facing == Direction.UP) {
                    knockback *= 0.3; // Reduction of vertical knockback
                }

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

    private static @NotNull AABB sonicHitBox(Direction facing, Vec3 origin, Vec3 target) {
        AABB aabb;
        switch (facing) {
            case UP, DOWN -> {
                double minY = Math.min(origin.y, target.y);
                double maxY = Math.max(origin.y, target.y);
                aabb = new AABB(
                        origin.x - LASER_RADIUS, minY, origin.z - LASER_RADIUS,
                        origin.x + LASER_RADIUS, maxY, origin.z + LASER_RADIUS
                );
            }
            case NORTH, SOUTH -> {
                double minZ = Math.min(origin.z, target.z);
                double maxZ = Math.max(origin.z, target.z);
                aabb = new AABB(
                        origin.x - LASER_RADIUS, origin.y - LASER_RADIUS, minZ,
                        origin.x + LASER_RADIUS, origin.y + LASER_RADIUS, maxZ
                );
            }
            case EAST, WEST -> {
                double minX = Math.min(origin.x, target.x);
                double maxX = Math.max(origin.x, target.x);
                aabb = new AABB(
                        minX, origin.y - LASER_RADIUS, origin.z - LASER_RADIUS,
                        maxX, origin.y + LASER_RADIUS, origin.z + LASER_RADIUS
                );
            }
            default -> throw new IllegalStateException("Unexpected facing: " + facing);
        }
        return aabb;
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }


}
