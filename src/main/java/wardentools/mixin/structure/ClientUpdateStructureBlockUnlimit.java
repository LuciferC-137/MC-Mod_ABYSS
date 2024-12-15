package wardentools.mixin.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.ModMain;

@Mixin(value = ServerboundSetStructureBlockPacket.class, priority = 999)
public class ClientUpdateStructureBlockUnlimit {

    @Shadow(remap = false)
    @Final
    @Mutable
    private BlockPos offset;

    @Shadow(remap = false)
    @Final
    @Mutable
    private Vec3i size;

    @Inject(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("RETURN"), require = 0, remap = false)
    public void readInts(FriendlyByteBuf buf, CallbackInfo ci) {
        final int newStructureSize = ModMain.NEW_STRUCTURE_SIZE;
        this.offset = new BlockPos(
                Mth.clamp(buf.readInt(), -newStructureSize, newStructureSize),
                Mth.clamp(buf.readInt(), -newStructureSize, newStructureSize),
                Mth.clamp(buf.readInt(), -newStructureSize, newStructureSize)
        );

        this.size = new BlockPos(
                Mth.clamp(buf.readInt(), 0, newStructureSize),
                Mth.clamp(buf.readInt(), 0, newStructureSize),
                Mth.clamp(buf.readInt(), 0, newStructureSize)
        );
    }

    @Inject(method = "write", at = @At("RETURN"), require = 0, remap = false)
    public void writeInts(FriendlyByteBuf buf, CallbackInfo ci) {
        buf.writeInt(offset.getX());
        buf.writeInt(offset.getY());
        buf.writeInt(offset.getZ());
        buf.writeInt(size.getX());
        buf.writeInt(size.getY());
        buf.writeInt(size.getZ());
    }
}
