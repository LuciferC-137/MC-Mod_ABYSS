package wardentools.playerdata;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import wardentools.ModMain;
import wardentools.playerdata.serializables.CompletedTasks;
import wardentools.playerdata.serializables.KnownWindWhispers;

import java.util.function.Supplier;

public class ModDataAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ModMain.MOD_ID);

    public static final Supplier<AttachmentType<CompletedTasks>> COMPLETED_TASKS =
            ATTACHMENTS.register("task_list", () -> AttachmentType.serializable(CompletedTasks::new).build());

    public static final Supplier<AttachmentType<KnownWindWhispers>> KNOWN_WIND_WHISPERS =
            ATTACHMENTS.register("known_wind_whispers", () -> AttachmentType.serializable(KnownWindWhispers::new).build());
}
