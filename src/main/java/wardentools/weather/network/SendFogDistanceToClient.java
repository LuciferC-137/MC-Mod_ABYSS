package wardentools.weather.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.weather.AbyssWeatherEvent;

public class SendFogDistanceToClient {
    private final float serverFogDistance;

    public SendFogDistanceToClient(float fogDistance) {
        this.serverFogDistance = fogDistance;
    }

    public SendFogDistanceToClient(FriendlyByteBuf buffer) {
        this.serverFogDistance = buffer.readFloat();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(serverFogDistance);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            AbyssWeatherEvent.CLIENT_WEATHER.setTargetFogDistance(serverFogDistance);
        });
        context.setPacketHandled(true);
    }
}
