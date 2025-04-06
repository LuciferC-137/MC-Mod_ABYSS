package wardentools.playerdata;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class KnownWhispersDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<KnownWhispers> WHISPERS_CAPABILITY
            = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<KnownWhispers> instance = LazyOptional.of(this::getOrCreateKnownWhispers);
    private KnownWhispers knownWhispers = null;

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        return cap == WHISPERS_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    public KnownWhispers getOrCreateKnownWhispers() {
        if (this.knownWhispers == null) {
            this.knownWhispers = new KnownWhispers();
        }
        return this.knownWhispers;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        getOrCreateKnownWhispers().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        getOrCreateKnownWhispers().loadNBTData(tag);
    }
}
