package wardentools.misc.wind;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.payloads.datasync.SyncKnownWhisperToServer;
import wardentools.playerdata.ModDataAttachments;
import wardentools.playerdata.serializables.KnownWindWhispers;
import wardentools.sounds.ModSounds;

import javax.annotation.Nullable;
import java.util.*;

@OnlyIn(Dist.CLIENT)
public class WindWhispers {
	private final Map<Integer, Whisper> whispers = new HashMap<>();
    public final WhisperTags whisperTags = new WhisperTags();
    public static final WindWhispers INSTANCE = new WindWhispers();


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

    public static void sendRandomWhisperToPlayer(@NotNull Player player) {
        INSTANCE.sendRandomWhisperToPlayerLocal(player);
    }

    // This method must only be called externally by packets since this class should only work on server
    public void sendRandomWhisperToPlayerLocal(@NotNull Player player) {
        player.playSound(ModSounds.WIND_WHISPERS.get(), 5f,
                (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);
        Holder<Biome> biomeHolder = player.level().getBiome(player.blockPosition());
        Component whisper = this.getContextualWhisper(biomeHolder);
        Component windName = Component.translatable("message." + ModMain.MOD_ID + ".windname");
        sendMessage(player, windName.getString() + " " + whisper.getString());
    }

    private static void sendMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }

    public @Nullable Whisper getNewRandomContextualWhisper(WhisperTags.Tag tag) {
        if (Minecraft.getInstance().player == null) return null;
        List<Whisper> availableWhispers = new ArrayList<>();
        KnownWindWhispers data = Minecraft.getInstance().player.getData(ModDataAttachments.KNOWN_WIND_WHISPERS);
        for (Whisper whisper : whisperTags.getContextualWhispers(tag)) {
            if (!data.whisperKnown(whisper.globalId())) {
                availableWhispers.add(whisper);
            }
        }
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
            KnownWindWhispers data = Minecraft.getInstance().player.getData(ModDataAttachments.KNOWN_WIND_WHISPERS);
            if (!data.whisperKnown(whisper.globalId())) {
                data.addKnownWhisper(whisper.globalId());
                Minecraft.getInstance().player.setData(ModDataAttachments.KNOWN_WIND_WHISPERS, data);
                PacketDistributor.sendToServer(new SyncKnownWhisperToServer(whisper.globalId(), false));
            }
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
