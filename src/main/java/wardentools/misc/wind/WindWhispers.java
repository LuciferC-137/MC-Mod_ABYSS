package wardentools.misc.wind;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.biome.Biome;
import wardentools.network.PacketHandler;
import wardentools.playerdata.whispers.KnownWhispersDataProvider;
import wardentools.playerdata.whispers.WhisperDataSyncServerPacket;

import javax.annotation.Nullable;
import java.util.*;

public class WindWhispers {
	private final Map<Integer, Whisper> whispers = new HashMap<>();
    public final WhisperTags whisperTags = new WhisperTags();


	public WindWhispers() {
        int globalId = 0;
        for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
            for (int i = 1; i <= tag.getNumberOfWhispers(); i++) {
                Whisper whisper = new Whisper(tag, i, globalId);
                globalId++;
                whispers.put(globalId, whisper);
                whisperTags.addTag(tag, whisper);
            }
        }
        whisperTags.initUnContextualWhispers();
	}

    public @Nullable Whisper getNewRandomContextualWhisper(WhisperTags.Tag tag) {
        if (Minecraft.getInstance().player == null) return null;
        List<Whisper> availableWhispers = new ArrayList<>();
        Minecraft.getInstance().player.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
            for (Whisper whisper : whisperTags.getContextualWhispers(tag)) {
                if (!data.knowsWhisper(whisper.globalId())) {
                    availableWhispers.add(whisper);
                }
            }
        });
        if (availableWhispers.isEmpty()) return null;
        Random rand = new Random();
        Whisper whisper = availableWhispers.get(rand.nextInt(availableWhispers.size()));
        addWhisperIdToPlayer(whisper);
        return whisper;
    }

	public Whisper getRandomWhisper() {
		List<Whisper> whisperList = whispers.values().stream().toList();
		Random rand = new Random();
		Whisper whisper = whisperList.get(rand.nextInt(whisperList.size()));
		addWhisperIdToPlayer(whisper);
		return whisper;
	}

    public static void addWhisperIdToPlayer(Whisper whisper) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.getCapability(
                    KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                if (!data.knowsWhisper(whisper.globalId())) {
                    data.addKnownWhisper(whisper.globalId());
                }
            });
            PacketHandler.sendToServer(new WhisperDataSyncServerPacket(whisper.globalId()));
        }
    }

    public Component getContextualWhisper(Holder<Biome> biomeHolder) {
        Whisper whisper = getNewRandomContextualWhisper(ContextualWind.getTagForBiome(biomeHolder));
        return whisper == null ? getRandomWhisper().whisper() : whisper.whisper();
    }

    public static String getLockedString() {
        return Component.translatable("message.wardentools.locked").getString();
    }
}
