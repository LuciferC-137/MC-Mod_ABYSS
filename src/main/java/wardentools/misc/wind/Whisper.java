package wardentools.misc.wind;

import net.minecraft.network.chat.Component;
import wardentools.ModMain;

public record Whisper(WhisperTags.Tag tag, int id, int globalId) {

    public Component whisper() {
        return Component.translatable("message." + ModMain.MOD_ID + ".whisper."
                + this.tag.getTranslatableTag() + "." + id);
    }
}
