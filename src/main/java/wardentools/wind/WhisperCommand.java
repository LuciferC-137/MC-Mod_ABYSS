package wardentools.wind;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.playerdata.ModDataAttachments;
import wardentools.playerdata.serializables.KnownWindWhispers;

import java.util.HashSet;
import java.util.Set;

public class WhisperCommand {

    public WhisperCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> grant = Commands.literal("grant");
        LiteralArgumentBuilder<CommandSourceStack> revoke = Commands.literal("revoke");
        for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
            grant = grant.then(Commands.literal(tag.getKey())
                    .then(Commands.argument("id", IntegerArgumentType
                                    .integer(1, tag.getNumberOfWhispers() + 1))
                            .executes(context
                                    -> grant(context.getSource(), tag, IntegerArgumentType.getInteger(context, "id"))))
                    .then(Commands.literal("all")
                            .executes(context -> grantAllForTag(context.getSource(), tag)))
            );
            revoke = revoke.then(Commands.literal(tag.getKey())
                    .then(Commands.argument("id", IntegerArgumentType
                                    .integer(1, tag.getNumberOfWhispers() + 1))
                            .executes(context
                                    -> revoke(context.getSource(), tag, IntegerArgumentType.getInteger(context, "id"))))
                    .then(Commands.literal("all")
                            .executes(context -> revokeAllForTag(context.getSource(), tag)))
            );
        }
        grant = grant.then(Commands.literal("all")
                .executes(context -> grantAll(context.getSource())));
        revoke = revoke.then(Commands.literal("all")
                .executes(context -> revokeAll(context.getSource())));
        dispatcher.register(Commands.literal("whisper")
                .then(grant)
                .then(revoke));
    }

    public static Set<String> getAll() {
        Set<String> ids = new HashSet<>();
        for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
            for (int i = 1; i <= tag.getNumberOfWhispers(); i++) {
                ids.add(WhisperTags.getGlobalId(tag, i));
            }
        }
        return ids;
    }

    public static Set<String> getAllIdsForTag(WhisperTags.Tag tag) {
        Set<String> ids = new HashSet<>();
        for (int i = 1; i <= tag.getNumberOfWhispers(); i++) {
            ids.add(WhisperTags.getGlobalId(tag, i));
        }
        return ids;
    }

    public static int grantAll(CommandSourceStack source) {
        return send(source.getPlayer(), getAll(), false);
    }

    public static int revokeAll(CommandSourceStack source) {
        return send(source.getPlayer(), getAll(), true);
    }

    public static int grantAllForTag(CommandSourceStack source, WhisperTags.Tag tag) {
        return send(source.getPlayer(), getAllIdsForTag(tag), false);
    }

    public static int revokeAllForTag(CommandSourceStack source, WhisperTags.Tag tag) {
        return send(source.getPlayer(), getAllIdsForTag(tag), true);
    }

    public static int grant(CommandSourceStack source, WhisperTags.Tag tag, int id) {
        return send(source.getPlayer(), Set.of(WhisperTags.getGlobalId(tag, id)), false);
    }

    public static int revoke(CommandSourceStack source, WhisperTags.Tag tag, int id) {
        return send(source.getPlayer(), Set.of(WhisperTags.getGlobalId(tag, id)), true);
    }

    private static int send(@Nullable ServerPlayer player, Set<String> whispers, boolean revoke) {
        if (player == null) return 1;
        PacketDistributor.sendToPlayer(player,
                new SyncKnownWhisperToClient(whispers, revoke));
        updateDataOnServer(player, whispers, revoke);
        return 0;
    }

    private static void updateDataOnServer(@NotNull ServerPlayer player, Set<String> whispers, boolean revoke) {
        KnownWindWhispers data =player.getData(ModDataAttachments.KNOWN_WIND_WHISPERS);
        if (revoke) {
            for (String id : whispers) {
                data.removeKnownWhisper(id);
            }
        } else {
            for (String id : whispers) {
                data.addKnownWhisper(id);
            }
        }
        player.setData(ModDataAttachments.KNOWN_WIND_WHISPERS, data);
    }
}
