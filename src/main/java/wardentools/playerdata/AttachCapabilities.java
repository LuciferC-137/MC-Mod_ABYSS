package wardentools.playerdata;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.playerdata.serializables.CompletedTasks;
import wardentools.playerdata.serializables.KnownWindWhispers;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttachCapabilities {

    private static final ResourceLocation COMPLETED_TASKS_ID =
            new ResourceLocation(ModMain.MOD_ID, "task_list");

    private static final ResourceLocation KNOWN_WIND_WHISPERS_ID =
            new ResourceLocation(ModMain.MOD_ID, "known_wind_whispers");

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof Player)) {
            return;
        }

        CompletedTasks tasks = new CompletedTasks();
        LazyOptional<CompletedTasks> tasksOptional = LazyOptional.of(() -> tasks);

        event.addCapability(COMPLETED_TASKS_ID,
                new ICapabilitySerializable<CompoundTag>() {

                    @Override
                    public <T> @NotNull LazyOptional<T> getCapability(
                            @NotNull Capability<T> capability,
                            @Nullable Direction side) {
                        return capability == ModCapabilities.COMPLETED_TASKS
                                ? tasksOptional.cast() : LazyOptional.empty();
                    }

                    @Override
                    public CompoundTag serializeNBT() {
                        return tasks.serializeNBT();
                    }

                    @Override
                    public void deserializeNBT(CompoundTag tag) {
                        tasks.deserializeNBT(tag);
                    }
                });

        event.addListener(tasksOptional::invalidate);

        KnownWindWhispers whispers = new KnownWindWhispers();
        LazyOptional<KnownWindWhispers> whispersOptional =
                LazyOptional.of(() -> whispers);

        event.addCapability(KNOWN_WIND_WHISPERS_ID,
                new ICapabilitySerializable<CompoundTag>() {

                    @Override
                    public <T> @NotNull LazyOptional<T> getCapability(
                            @NotNull Capability<T> capability,
                            @Nullable Direction side) {
                        return capability == ModCapabilities.KNOWN_WIND_WHISPERS
                                ? whispersOptional.cast()
                                : LazyOptional.empty();
                    }

                    @Override
                    public CompoundTag serializeNBT() {
                        return whispers.serializeNBT();
                    }

                    @Override
                    public void deserializeNBT(CompoundTag tag) {
                        whispers.deserializeNBT(tag);
                    }
                });

        event.addListener(whispersOptional::invalidate);
    }
}
