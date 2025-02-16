package wardentools.weather.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.weather.AbyssWeatherEvent;

public class RequestFogDistanceUpdateFromServer {

    public RequestFogDistanceUpdateFromServer() {
    }

    public RequestFogDistanceUpdateFromServer(FriendlyByteBuf buffer) {
    }

    public void encode(FriendlyByteBuf buffer) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            AbyssWeatherEvent.WEATHER_MANAGER.sendServerFogDistanceToClient(context.getSender());
        });
        context.setPacketHandled(true);
    }
}
