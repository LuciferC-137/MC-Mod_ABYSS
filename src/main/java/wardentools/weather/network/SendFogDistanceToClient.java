package wardentools.weather.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.weather.AbyssWeatherEvent;

public class SendFogDistanceToClient {
    private final boolean isStorming;

    public SendFogDistanceToClient(boolean isStorming) {
        this.isStorming = isStorming;
    }

    public SendFogDistanceToClient(FriendlyByteBuf buffer) {
        this.isStorming = buffer.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isStorming);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            AbyssWeatherEvent.CLIENT_WEATHER.setIsStorming(isStorming);
        });
        context.setPacketHandled(true);
    }
}
