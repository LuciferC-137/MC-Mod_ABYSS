package wardentools.entity.thryssaryn.individual.behavior;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

public interface SerializableCompound {

    default @NotNull CompoundTag toCompoundTag() {return new CompoundTag();}
}
