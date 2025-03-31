package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.gui.menu.DysfunctionningCatalystMenu;
import wardentools.items.ItemRegistry;
import wardentools.network.ModPackets;
import wardentools.network.ParticulesSoundsEffects.AncientLaboratoryGateSound;
import wardentools.network.ParticulesSoundsEffects.ContagionIncarnationEmergeSound;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionExplosion;
import wardentools.network.ParticulesSoundsEffects.ParticleDarktreeFenceDestroyed;
import wardentools.particle.ParticleRegistry;

import java.util.List;

public class DysfunctionningCatalystBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
    private static final float particleSpawnRadius = 2f;
    private final ItemStackHandler inventory = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            DysfunctionningCatalystBlockEntity.this.setChanged();
        }
    };
    private static final Component TITLE =
            Component.translatable("container." + ModMain.MOD_ID + ".dysfunctionning_catalyst");
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> DysfunctionningCatalystBlockEntity.this.citrine;
                case 1 -> DysfunctionningCatalystBlockEntity.this.amethyst;
                case 2 -> DysfunctionningCatalystBlockEntity.this.pale_shard;
                case 3 -> DysfunctionningCatalystBlockEntity.this.ruby;
                case 4 -> DysfunctionningCatalystBlockEntity.this.malachite;
                case 5 -> DysfunctionningCatalystBlockEntity.this.echo_shard;
                case 6 -> DysfunctionningCatalystBlockEntity.this.total_charge;
                case 7 -> DysfunctionningCatalystBlockEntity.this.eye_progression;
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
        }
        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> DysfunctionningCatalystBlockEntity.this.citrine = value;
                case 1 -> DysfunctionningCatalystBlockEntity.this.amethyst = value;
                case 2 -> DysfunctionningCatalystBlockEntity.this.pale_shard = value;
                case 3 -> DysfunctionningCatalystBlockEntity.this.ruby = value;
                case 4 -> DysfunctionningCatalystBlockEntity.this.malachite = value;
                case 5 -> DysfunctionningCatalystBlockEntity.this.echo_shard = value;
                case 6 -> DysfunctionningCatalystBlockEntity.this.total_charge = value;
                case 7 -> DysfunctionningCatalystBlockEntity.this.eye_progression = value;
                default -> throw new IllegalStateException("Unexpected value: " + index);
            }
        }
        @Override
        public int getCount() {
            return 8;
        }
    };
    public static final int MAX_PROGRESSION = 28;
    public static final int MAX_TOTAL = 72;
    public static final int MAX_EYE = 40;
    private static final int UPDATE_INTERVAL = 5;
    private int citrine = 0;
    private int amethyst = 0;
    private int pale_shard = 0;
    private int ruby = 0;
    private int malachite = 0;
    private int echo_shard = 0;
    private int  total_charge = 0;
    private int eye_progression = 0;
    private int next_check = 0;

    private static final int FENCE_INTERVAL = 35;
    private static final int MAX_FENCE = 5;
    private int schedule_fence = 0;
    private int fence_level = 0;

    private static final int SUMMON_DELAY = 200;
    private int schedule_summon = 0;

    private boolean isContagionDefeated = false;


    protected DysfunctionningCatalystBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DysfunctionningCatalystBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        var wardentoolsData = tag.getCompound(ModMain.MOD_ID);
        if (wardentoolsData.isEmpty()) return;
        if (wardentoolsData.contains("Inventory", Tag.TAG_COMPOUND)) {
            this.inventory.deserializeNBT(provider, wardentoolsData.getCompound("Inventory"));
        }
        this.citrine = wardentoolsData.getInt("Citrine");
        this.amethyst = wardentoolsData.getInt("Amethyst");
        this.pale_shard = wardentoolsData.getInt("PaleShard");
        this.ruby = wardentoolsData.getInt("Ruby");
        this.malachite = wardentoolsData.getInt("Malachite");
        this.echo_shard = wardentoolsData.getInt("EchoShard");
        this.total_charge = wardentoolsData.getInt("TotalCharge");
        this.isContagionDefeated = wardentoolsData.getBoolean("IsContagionDefeated");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        var wardentoolsData = new CompoundTag();
        wardentoolsData.put("Inventory", this.inventory.serializeNBT(provider));
        wardentoolsData.putInt("Citrine", this.citrine);
        wardentoolsData.putInt("Amethyst", this.amethyst);
        wardentoolsData.putInt("PaleShard", this.pale_shard);
        wardentoolsData.putInt("Ruby", this.ruby);
        wardentoolsData.putInt("Malachite", this.malachite);
        wardentoolsData.putInt("EchoShard", this.echo_shard);
        wardentoolsData.putInt("TotalCharge", this.total_charge);
        wardentoolsData.putBoolean("IsContagionDefeated", this.isContagionDefeated);
        tag.put(ModMain.MOD_ID, wardentoolsData);
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            sendUpdate();
            if (this.next_check <= 0 && this.total_charge != MAX_TOTAL) {
                this.next_check = UPDATE_INTERVAL;
                if (this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())
                        && this.citrine < MAX_PROGRESSION) {
                    this.citrine +=1;
                    this.sendUpdate();
                } else if (this.citrine > 0
                        && !this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())) {
                    this.citrine -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)
                        && this.amethyst < MAX_PROGRESSION) {
                    this.amethyst +=1;
                    this.sendUpdate();
                } else if (this.amethyst > 0
                        && !this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)) {
                    this.amethyst -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())
                        && this.pale_shard < MAX_PROGRESSION) {
                    this.pale_shard +=1;
                    this.sendUpdate();
                } else if (this.pale_shard > 0
                        && !this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())) {
                    this.pale_shard -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())
                        && this.ruby < MAX_PROGRESSION) {
                    this.ruby +=1;
                    this.sendUpdate();
                } else if (this.ruby > 0
                        && !this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())) {
                    this.ruby -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())
                        && this.malachite < MAX_PROGRESSION) {
                    this.malachite +=1;
                    this.sendUpdate();
                } else if (this.malachite > 0
                        && !this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())) {
                    this.malachite -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)
                        && this.echo_shard < MAX_PROGRESSION) {
                    this.echo_shard +=1;
                    this.sendUpdate();
                } else if (this.echo_shard > 0
                        && !this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)) {
                    this.echo_shard -= 1;
                    this.sendUpdate();
                }
            } else {
                this.next_check--;
            }
            if (this.crystalsFullyCharged() && this.total_charge < MAX_TOTAL) {
                this.total_charge++;
                if (this.readyToSummon() && this.isInCorrectSpot()) {
                    this.doSummoning();
                }
            } else if (this.total_charge > 0 && !this.crystalsFullyCharged() && this.total_charge < MAX_TOTAL) {
                this.total_charge--;
            }
            if (total_charge == MAX_TOTAL && this.eye_progression < MAX_EYE) {
                this.eye_progression ++;
            } else if (eye_progression > 0 && total_charge < MAX_TOTAL) {
                this.eye_progression --; // this should normally never happen.
            }

            if (this.isInCorrectSpot()) {
                // Fence placement Logic
                if (this.schedule_fence > 0 && this.fence_level < MAX_FENCE) {
                    this.schedule_fence--;
                    if (this.schedule_fence == 0) {
                        this.placeFence();
                        this.fence_level++;
                        if (this.fence_level < MAX_FENCE) this.schedule_fence = FENCE_INTERVAL;
                    }
                }
                // Summoning Logic
                if (this.schedule_summon > 0) {
                    this.schedule_summon--;
                    if (this.schedule_summon == 0) {
                        this.summonContagionIncarnation();
                    }
                }
            }
        }
    }

    private void summonContagionIncarnation() {
        if (this.level == null) return;
        this.placeSolidCorruptionBlock();
        ContagionIncarnationEntity contagionIncarnation
                = new ContagionIncarnationEntity(ModEntities.CONTAGION_INCARNATION.get(), this.level);
        contagionIncarnation.setPos(this.worldPosition.below(12)
                .getCenter().add(0, 0.5, 0));
        contagionIncarnation.setHealth(contagionIncarnation.getMaxHealth());
        contagionIncarnation.initiateSpawnAnimation();
        contagionIncarnation.setCatalystPos(this.worldPosition);
        this.level.addFreshEntity(contagionIncarnation);
        ModPackets.sendToAllClient(new ContagionIncarnationEmergeSound());
    }

    public void clientTick() {
        this.handleParticleEffects();
    }

    private void placeFence() {
        if (this.level != null) {
            List<BlockPos> fencePos = this.getFencePosByLevel(this.fence_level);
            if (fencePos != null) {
                for (BlockPos pos : this.getFenceSoundPos()) {
                    this.sendGateClosingSoundEffectToClient(pos.getCenter());
                }
                for (BlockPos pos : fencePos) {
                    this.level.setBlockAndUpdate(pos, BlockRegistry.DARKTREE_FENCE.get().defaultBlockState());
                }
            }
        }
    }

    private void removeFences() {
        if (this.level != null) {
            for (int level = 0; level <= MAX_FENCE; level++) {
                List<BlockPos> fencePos = this.getFencePosByLevel(level);
                if (fencePos != null) {
                    for (BlockPos pos : fencePos) {
                        if (this.level.getBlockState(pos).getBlock() == BlockRegistry.DARKTREE_FENCE.get()) {
                            this.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                            ModPackets.sendToAllClient(new ParticleDarktreeFenceDestroyed(pos.getCenter()));
                        }
                    }
                }
            }
        }
    }

    public void contagionDied() {
        this.isContagionDefeated = true;
        this.removeFences();
        this.placeAbyssPortalBlock();
    }

    public boolean isContagionDefeated() {
        return this.isContagionDefeated;
    }

    private void sendGateClosingSoundEffectToClient(Vec3 source) {
        ModPackets.sendToAllClient(new AncientLaboratoryGateSound(source));
    }

    private void handleParticleEffects() {
        if (this.isContagionDefeated) return;
        if (this.level == null) return;
        if (level.getGameTime()%5 == level.getRandom().nextInt(5)){
            Vec3 center = this.getBlockPos().getCenter();
            float x =  (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float y = (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float z = (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float norm = Mth.sqrt(x*x + y*y + z*z) / 0.2f;
            level.addParticle(ParticleRegistry.CORRUPTION.get(),
                    (float)center.x + x,
                    (float)center.y + y,
                    (float)center.z + z,
                    -x / norm,
                    -y / norm,
                    -z / norm);
        }
        if (this.isChargingCrystals()) {
            this.particleLevitation(10, 0.2f, 5f,
                    -13f, 5f);
        }
        if (this.isChargingTotal()) {
            this.particleImplosion(10, 0.4f, 20f);
        }
        if (this.isFightActive()) {
            this.particleLevitation(100, 0.05f, 19f,
                    -13f, 5f);
        }
    }

    private void particleImplosion(int particleNumber, float speedMultiplier, float radius) {
        if (this.level == null || !this.level.isClientSide) return;
        Vec3 source = this.worldPosition.getCenter();
        for (int i = 0; i < particleNumber; i++) {
            double offsetX = (this.level.random.nextDouble() - 0.5) * radius;
            double offsetY = (this.level.random.nextDouble() - 0.5) * radius;
            double offsetZ = (this.level.random.nextDouble() - 0.5) * radius;
            double norm = Math.sqrt(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
            double speed = speedMultiplier / norm;
            this.level.addParticle(ParticleRegistry.CORRUPTION.get(),
                    source.x + offsetX,
                    source.y + offsetY,
                    source.z + offsetZ,
                    -offsetX * speed, -offsetY * speed, -offsetZ * speed);
        }
    }

    private void particleLevitation(int particleNumber, float speedMultiplier, float radius,
                                    float yMinParticleSpawn, float yMaxParticleSpawn) {
        if (this.level == null || !this.level.isClientSide) return;
        Vec3 source = this.worldPosition.getCenter();
        for (int i = 0; i < particleNumber; i++) {
            float x =  (this.level.random.nextFloat() * 2 - 1f) * radius;
            float y = (this.level.random.nextFloat()
                    * (yMaxParticleSpawn - yMinParticleSpawn) + yMinParticleSpawn);
            float z = (this.level.random.nextFloat() * 2 - 1f) * radius;
            this.level.addParticle(ParticleRegistry.CORRUPTION.get(),
                    source.x + x,
                    source.y + y,
                    source.z + z,
                    0,
                    speedMultiplier,
                    0);
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = super.getUpdateTag(provider);
        saveAdditional(nbt, provider);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory,
                                            @NotNull Player player) {
        return new DysfunctionningCatalystMenu(
                containerId, playerInventory, this, this.containerData);
    }

    private void sendUpdate() {
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public boolean crystalsFullyCharged() {
        return (this.citrine == MAX_PROGRESSION && this.amethyst == MAX_PROGRESSION
                && this.pale_shard == MAX_PROGRESSION && this.ruby == MAX_PROGRESSION
                && this.malachite == MAX_PROGRESSION && this.echo_shard == MAX_PROGRESSION);
    }

    public boolean isChargingCrystals() {
        return ( (this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())
                  && this.citrine < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)
                    && this.amethyst < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())
                    && this.pale_shard < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())
                    && this.ruby < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())
                    && this.malachite < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)
                    && this.echo_shard < MAX_PROGRESSION));
    }

    public boolean isChargingTotal() {
        return (this.total_charge < MAX_TOTAL && this.crystalsFullyCharged());
    }

    public boolean readyToSummon() {
        return (this.total_charge == MAX_TOTAL);
    }

    public boolean isFightActive() {
        return this.total_charge == MAX_TOTAL;
    }

    public boolean isInCorrectSpot() {
        if (this.level == null) return false;
        for (BlockPos pos : this.getFountainBelowPositions()) {
            if (!this.level.getBlockState(pos.below(2)).getBlock().equals(Blocks.BEDROCK)) {
                return false;
            }
        }
        return true;
    }

    public void doSummoning() {
        this.hugeParticleExplosion();
        this.schedule_fence = FENCE_INTERVAL;
        this.schedule_summon = SUMMON_DELAY;
        this.removeInventory();
    }

    private void removeInventory() {
        for (int i = 0; i < this.inventory.getSlots(); i++) {
            this.inventory.getStackInSlot(i).shrink(1);
        }
    }

    public void placeSolidCorruptionBlock() {
        if (this.level != null) {
            for (BlockPos pos : getFountainBelowPositions()) {
                this.level.setBlockAndUpdate(pos, BlockRegistry.SOLID_CORRUPTION.get().defaultBlockState());
            }
        }
    }

    public void placeAbyssPortalBlock() {
        if (this.level != null) {
            for (BlockPos pos : getFountainBelowPositions()) {
                this.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                this.level.setBlockAndUpdate(pos.below(), BlockRegistry.ABYSS_PORTAL_BLOCK.get().defaultBlockState());
                BlockEntity blockEntity = this.level.getBlockEntity(pos.below());
                if (blockEntity instanceof AbyssPortalBlockEntity abyssPortalBlockEntity) {
                    abyssPortalBlockEntity.setShouldShowWinScreen(true);
                }
            }
        }
    }

    public void hugeParticleExplosion() {
        ModPackets.sendToAllClient(new ParticleContagionExplosion(
                this.worldPosition.getCenter(), 1f, 2f, 800));
    }

    public List<BlockPos> getFenceSoundPos() {
        return List.of(
                this.worldPosition.offset(19, -9, 0),
                this.worldPosition.offset(0, -9, 19),
                this.worldPosition.offset(-19, -9, 0),
                this.worldPosition.offset(0, -9, -19)
        );
    }

    public @Nullable List<BlockPos> getFencePosByLevel(int level) {
        if (level == 0) {
            return List.of(
                this.worldPosition.offset(19, -8, 0),
                this.worldPosition.offset(19, -8, 1),
                this.worldPosition.offset(19, -8, -1),
                this.worldPosition.offset(0, -8, 19),
                this.worldPosition.offset(1, -8, 19),
                this.worldPosition.offset(-1, -8, 19),
                this.worldPosition.offset(-19, -8, 0),
                this.worldPosition.offset(-19, -8, 1),
                this.worldPosition.offset(-19, -8, -1),
                this.worldPosition.offset(0, -8, -19),
                this.worldPosition.offset(1, -8, -19),
                this.worldPosition.offset(-1, -8, -19)
            );
        } else if (level >= 1 && level <= MAX_FENCE) {
            return List.of(
                    this.worldPosition.offset(19, -8 - level, 0),
                    this.worldPosition.offset(19, -8 - level, 1),
                    this.worldPosition.offset(19, -8 - level, -1),
                    this.worldPosition.offset(0, -8 - level, 19),
                    this.worldPosition.offset(1, -8 - level, 19),
                    this.worldPosition.offset(-1, -8 - level, 19),
                    this.worldPosition.offset(-19, -8 - level, 0),
                    this.worldPosition.offset(-19, -8 - level, 1),
                    this.worldPosition.offset(-19, -8 - level, -1),
                    this.worldPosition.offset(0, -8 - level, -19),
                    this.worldPosition.offset(1, -8 - level, -19),
                    this.worldPosition.offset(-1, -8 - level, -19),
                    this.worldPosition.offset(19, -8 - level, 2),
                    this.worldPosition.offset(19, -8 - level, -2),
                    this.worldPosition.offset(2, -8 - level, 19),
                    this.worldPosition.offset(-2, -8 - level, 19),
                    this.worldPosition.offset(-19, -8 - level, 2),
                    this.worldPosition.offset(-19, -8 - level, -2),
                    this.worldPosition.offset(2, -8 - level, -19),
                    this.worldPosition.offset(-2, -8 - level, -19)
            );
        }
        return null;
    }

    public List<BlockPos> getFountainBelowPositions() {
        return List.of(
                this.worldPosition.offset(0, -12, 0),
                this.worldPosition.offset(1,-12,0),
                this.worldPosition.offset(0,-12,1),
                this.worldPosition.offset(1,-12,1),
                this.worldPosition.offset(2, -12, 0),
                this.worldPosition.offset(2,-12,1),
                this.worldPosition.offset(2,-12,2),
                this.worldPosition.offset(1, -12, 2),
                this.worldPosition.offset(0, -12, 2),
                this.worldPosition.offset(3, -12, 0),
                this.worldPosition.offset(3,-12,1),
                this.worldPosition.offset(3,-12,2),
                this.worldPosition.offset(3, -12, 3),
                this.worldPosition.offset(2,-12,3),
                this.worldPosition.offset(1,-12,3),
                this.worldPosition.offset(0, -12, 3),
                this.worldPosition.offset(4,-12,0),
                this.worldPosition.offset(4,-12,1),
                this.worldPosition.offset(4, -12, 2),
                this.worldPosition.offset(1,-12,0),
                this.worldPosition.offset(0,-12,1),
                this.worldPosition.offset(1,-12,1),
                this.worldPosition.offset(2, -12, 0),
                this.worldPosition.offset(2,-12,1),
                this.worldPosition.offset(2,-12,2),
                this.worldPosition.offset(1, -12, 2),
                this.worldPosition.offset(3, -12, 0),
                this.worldPosition.offset(3,-12,1),
                this.worldPosition.offset(3,-12,2),
                this.worldPosition.offset(3, -12, 3),
                this.worldPosition.offset(2,-12,3),
                this.worldPosition.offset(1,-12,3),
                this.worldPosition.offset(0, -12, 3),
                this.worldPosition.offset(4,-12,0),
                this.worldPosition.offset(4,-12,1),
                this.worldPosition.offset(4, -12, 2),
                this.worldPosition.offset(2, -12, 4),
                this.worldPosition.offset(1, -12, 4),
                this.worldPosition.offset(0, -12, 4),
                this.worldPosition.offset(-1,-12,0),
                this.worldPosition.offset(0,-12,-1),
                this.worldPosition.offset(-1,-12,-1),
                this.worldPosition.offset(-2, -12, 0),
                this.worldPosition.offset(-2,-12,-1),
                this.worldPosition.offset(-2,-12,-2),
                this.worldPosition.offset(-1, -12, -2),
                this.worldPosition.offset(0, -12, -2),
                this.worldPosition.offset(-3, -12, 0),
                this.worldPosition.offset(-3,-12,-1),
                this.worldPosition.offset(-3,-12,-2),
                this.worldPosition.offset(-3, -12, -3),
                this.worldPosition.offset(-2,-12,-3),
                this.worldPosition.offset(-1,-12,-3),
                this.worldPosition.offset(0, -12, -3),
                this.worldPosition.offset(-4,-12,0),
                this.worldPosition.offset(-4,-12,-1),
                this.worldPosition.offset(-4, -12, -2),
                this.worldPosition.offset(-2, -12, -4),
                this.worldPosition.offset(-1, -12, -4),
                this.worldPosition.offset(0, -12, -4),
                this.worldPosition.offset(-1,-12,1),
                this.worldPosition.offset(-2,-12,1),
                this.worldPosition.offset(-2,-12,2),
                this.worldPosition.offset(-1, -12, 2),
                this.worldPosition.offset(-3,-12,1),
                this.worldPosition.offset(-3,-12,2),
                this.worldPosition.offset(-3, -12, 3),
                this.worldPosition.offset(-2,-12,3),
                this.worldPosition.offset(-1,-12,3),
                this.worldPosition.offset(-4,-12,1),
                this.worldPosition.offset(-4, -12, 2),
                this.worldPosition.offset(-2, -12, 4),
                this.worldPosition.offset(-1, -12, 4),
                this.worldPosition.offset(1,-12,-1),
                this.worldPosition.offset(2,-12,-1),
                this.worldPosition.offset(2,-12,-2),
                this.worldPosition.offset(1, -12, -2),
                this.worldPosition.offset(3,-12,-1),
                this.worldPosition.offset(3,-12,-2),
                this.worldPosition.offset(3, -12, -3),
                this.worldPosition.offset(2,-12,-3),
                this.worldPosition.offset(1,-12,-3),
                this.worldPosition.offset(4,-12,-1),
                this.worldPosition.offset(4, -12, -2),
                this.worldPosition.offset(2, -12, -4),
                this.worldPosition.offset(1, -12, -4)
        );
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

}
