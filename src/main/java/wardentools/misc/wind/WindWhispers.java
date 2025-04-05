package wardentools.misc.wind;

import net.minecraft.client.Minecraft;
import wardentools.playerdata.KnownWhispersDataProvider;
import wardentools.misc.wind.WhisperTags.Tag;

import javax.annotation.Nullable;
import java.util.*;

public class WindWhispers {
	private final Map<Integer, Whisper> whispers = new HashMap<>();
    public final WhisperTags whisperTags = new WhisperTags();
	private int lastId = 0;


	public WindWhispers() {
        // ------------------------- SMALL TALK ---------------------------
		addWhisper("Are you lost ?", "Tu es perdu ?", Tag.SMALL_TALK);
		addWhisper("Do you hear this ?", "Est-ce que tu entends ça ?", Tag.SMALL_TALK);
        addWhisper("Even the wind here listens to you.", "Ici, même le vent t'écoute.", Tag.SMALL_TALK);
        addWhisper("If you listen very carefully, you might be able to hear the whispers of the Void.", "Si tu écoutes attentivement, tu pourrais bien entendre les murmures du Néant.", Tag.SMALL_TALK);
        addWhisper("Have you fallen here by chance ?", "Tu es tombé ici par hasard ?", Tag.SMALL_TALK);
        addWhisper("Where are you from ? Have you ever seen the light ?", "D'où est-ce que tu viens ? Tu as déjà vu la lumière ?", Tag.SMALL_TALK);
        addWhisper("There is an End to everything. But you have fallen here right before reaching it...", "Il y a une Fin à tout. Mais tu es tombé ici juste avant de l'atteindre...", Tag.SMALL_TALK);
        addWhisper("Are you dreaming ?", "Est-ce que tu es en train de rêver ?", Tag.SMALL_TALK);
        addWhisper("Would you really listen to the wind ?", "Tu écouterais vraiment ce que dis le vent ?", Tag.SMALL_TALK);
        addWhisper("How long have you been sneaking in the dark ?", "Depuis combien de temps ères-tu dans le noir ?", Tag.SMALL_TALK);
        addWhisper("Death is only another form of silence.", "La mort est seulement une autre forme de silence.", Tag.SMALL_TALK);
        addWhisper("You really became a good listener.", "Tu es vraiment devenu attentif.", Tag.SMALL_TALK);
        addWhisper("You are free from the light here. Come and rest among the shadows.", "Tu es libéré de la lumière ici. Viens, et repose-toi parmi les ombres.", Tag.SMALL_TALK);
        addWhisper("There used to be people here. Only I can remember.", "Il y avait des gens autrefois ici. Je suis le seul à pouvoir m'en souvenir.", Tag.SMALL_TALK);
        addWhisper("After all, you might have gotten too deep...", "Après tout, tu t'es peut-être enfoncé trop profondément...", Tag.SMALL_TALK);
        addWhisper("Do you know how the story ends ? I have seen an End once...", "Est-ce que tu sais comment l'histoire se termine ? J'ai vu une Fin, une fois...", Tag.SMALL_TALK);
        addWhisper("What are you doing here ?", "Qu'est-ce que tu fais ici ?", Tag.SMALL_TALK);
        addWhisper("Nobody came for so long...", "Personne n'est venu depuis si longtemps...", Tag.SMALL_TALK);
        addWhisper("So it is true that you can hear us...", "Il est donc vrai que tu peux nous entendre dorénavant...", Tag.SMALL_TALK);
        addWhisper("The sun hasn't shed a light on the wind for so long down here...", "Le moindre rayon de soleil n'a pas éclairé le vent depuis si longtemps ici-bas...", Tag.SMALL_TALK);

        // ------------------------- GENERIC ---------------------------

        addWhisper("It is hiding in the space between two silences...", "Il se cache dans l'espace entre deux silences...", Tag.GENERIC);
        addWhisper("What about jumping in the Void ?", "Pourquoi pas se jeter dans le Néant ?", Tag.GENERIC);
        addWhisper("There is no limits.", "Il n'y a aucune limites.", Tag.GENERIC);
		addWhisper("It is so quiet...", "Tout est tellement silencieux...", Tag.GENERIC);
		addWhisper("This darkness devours everything...", "Ces ténèbres dévorent tout...", Tag.GENERIC);
        addWhisper("Silence ! He is listening !", "Silence ! Il nous écoute !", Tag.GENERIC);
        addWhisper("No hope. No light. Only infinite nothingness, watched by aimless wardens.", "Pas d'espoir. Pas de lumière. Seulement l'infini Néant, surveillé par des gardiens sans but.", Tag.GENERIC);
        addWhisper("I fly in the sky, I dive into caves, I hurl on the Void, and yet, you are more free than I will ever be.", "Je vole dans le ciel, je plonge dans les cavernes, je rugis dans le Néant et pourtant, pourtant tu es plus libre que je ne le serai jamais.", Tag.GENERIC);

        // --------------------------- LORE ----------------------------

		addWhisper("This world used to be noisy and peacefull. Now, it is silent and even more peacefull.", "Ce monde était bruyant et paisible autrefois. Maintenant, il est silencieux et encore plus paisible.", Tag.LORE);
		addWhisper("The darkness of this world has gotten into yours, hasn't it ?", "Les ténèbres de ce monde ont gagné le tiens, n'est-ce pas ?", Tag.LORE);
		addWhisper("Do you think he will be able to find what used to make the beauty of this world ?", "Tu pense qu'il sera capable de découvrir ce qui faisait la beauté de ce monde ?", Tag.LORE);
		addWhisper("Who we are ? Some kind of ghosts I guess... But you are no different. You have seen it, don't you ? The Great Void ?", "Qui sommes nous ? Des sortes de fantômes, j'imagine... Mais nous ne sommes pas différents. Tu l'as déjà vu, n'est-ce pas ? Le Grand Néant ?", Tag.LORE);
        addWhisper("Were we alive some day ? I don't remember...", "Etions-nous en vie à un moment ? Je ne me souviens plus...", Tag.LORE);
        addWhisper("I couldn't imagine that the corruption would also reach the sky itself...", "Je ne pouvais pas imaginer que la corruption gagnerait le ciel lui-même...", Tag.LORE);

        // ------------------------- DEEP LORE --------------------------

		addWhisper("Before it came, what we most fear was our best ally...", "Avant qu'il ne vienne, ce que nous craignons aujourd'hui le plus était notre plus grand allié...", Tag.DEEP_LORE);
		addWhisper("It turned our protectors against us... They became blind and angry. They couldn't listen to the music anymore. All they wanted to hear was screams.", "Il a retourné nos protecteurs contre nous... Ils sont devenus aveugle et furieux. Ils ne pouvaient plus entendre la musique. Tout ce qu'ils voulaient entendre, c'était des hurlements.", Tag.DEEP_LORE);
        addWhisper("They began to worship the corrupted crystals. They began to worship the Void.", "Ils commencèrent à adorer les cristaux corrompus. Ils commencèrent à adorer le Néant.", Tag.DEEP_LORE);
        addWhisper("Once the corruption took over, they created a Beast from it. An Incarnation of destruction.", "Une fois que la corruption prit le dessus, ils forgèrent une Bête avec. Une Incarnation de la destruction.", Tag.DEEP_LORE);
        addWhisper("Do you think he could perform the reactivation ritual ?", "Tu pense qu'il pourrait réaliser le rituel de réactivation ?", Tag.DEEP_LORE);
		addWhisper("There was all sort of crystals, each with its own energy. But we tried to harvest the power of the forbidden one.", "Il y avait toute sorte de cristaux, chacun avec sa propre énergie. Mais nous avons tenté de récolter le pouvoir de celui qui était interdit.", Tag.DEEP_LORE);

        // ----------------------- ANCIENT CITADEL ------------------------

		addWhisper("Soon after the incident, the contagion begun to rise from the depths.", "Après l'incident, la contagion commença à s'élever depuis les profondeurs.", Tag.ANCIENT_CITADEL);
		addWhisper("They thought they could control it. They thought they could be safe in their underground Citadel.", "Ils pensaient pouvoir la contrôler. Ils pensaient être en sécurité dans leur Citadelle souterraine.", Tag.ANCIENT_CITADEL);
		addWhisper("Crystals were growing everywhere. They were beautiful, but they were also dangerous.", "Les cristaux poussaient partout. Ils étaient beaux, mais ils étaient aussi dangereux.", Tag.ANCIENT_CITADEL);
		addWhisper("Entire cities would run thanks to the energy of the crystals.", "Des villes entières fonctionnaient grâce à l'énergie des cristaux.", Tag.ANCIENT_CITADEL);

        // ----------------------- DEEP FOREST --------------------------

        addWhisper("Some fell to the corruption, some fought it. Others adapted.", "Certains tombèrent dans la corruption, certains luttèrent. D'autres s'adaptèrent.", Tag.DEEPFOREST);
        addWhisper("I don't remember those tree from back then...", "Je ne me souviens pas de ces arbres à l'époque...", Tag.DEEPFOREST);
        addWhisper("Those plants look strange... But also, somehow alive... Is it reasuring ?", "Ces plantes ont l'air étrange... Mais aussi, vivantes d'une certaine manière... Est-ce que c'est rassurant ?", Tag.DEEPFOREST);
        addWhisper("The grass might look ill, but it's safe to walk on it.", "L'herbe a peut-être l'air malade, mais on peut marcher dessus sans soucis.", Tag.DEEPFOREST);
        addWhisper("Avoid the sculk. It is everywhere. The most abject form of life", "Evite le sculk. Il est partout. La forme de vie la plus abjecte.", Tag.DEEPFOREST);
        addWhisper("Are you sure you're gonna eat this fruit ?", "Tu sûr, tu vas manger ce fruit ?", Tag.DEEPFOREST);
        addWhisper("To some extent, the sculk is the perfect form of life.", "D'une certaine manière, le sculk est une forme de vie parfaite", Tag.DEEPFOREST);
        addWhisper("The sculk does not need water, food, or sun. It just feeds on death and void.", "Le sculk n'a pas besoin d'eau, de nourriture ou de soleil. Il se nourrit juste de mort et de néant.", Tag.DEEPFOREST);
        addWhisper("Those little lurker are cute, but their big eyes... I'm glad they can't see me.", "Ces petits lurkers sont mignons, mais leurs grands yeux... Je suis content qu'ils ne puissent pas me voir.", Tag.DEEPFOREST);
        addWhisper("You should try riding one those birds. You could experience a bit what it is to be me.", "Tu devrais essayer de monter sur ces oiseaux. Tu pourrais bien ressentir ce que ça fait d'être moi.", Tag.DEEPFOREST);

        // ----------------------- WHITE FOREST --------------------------

        addWhisper("The trees are so white... It is so quiet...", "Les arbres sont si blancs... C'est si tranquille...", Tag.WHITE_FOREST);
        addWhisper("It wasn't like this either before... Those trees are new", "Ce n'était pas non plus comme ça avant... Ces arbres sont nouveaux", Tag.WHITE_FOREST);
        addWhisper("They grew as if they were fighting the corruption. Some sort of... immune system ?", "Ils ont poussé comme s'ils combattaient la corruption. Une sorte de... système immunitaire ?", Tag.WHITE_FOREST);
        addWhisper("Creatures here are peaceful.", "Les créatures ici sont paisibles.", Tag.WHITE_FOREST);

        // ----------------------- CRYSTAL CAVES --------------------------
        addWhisper("Those crystals are beautiful.", "Ces cristaux sont magnifiques.", Tag.CRYSTAL_CAVES);
        addWhisper("Some are so sharp, even I could cut myself !", "Ils sont si aiguisés, même moi je pourrais me couper!", Tag.CRYSTAL_CAVES);
        addWhisper("They were called neutral crystal.", "On les appelait les cristaux neutres", Tag.CRYSTAL_CAVES);
        addWhisper("They were used to create the first machines.", "Ils étaient utilisés pour créer les premières machines.", Tag.CRYSTAL_CAVES);
        addWhisper("Everyone one of those crystals had a unique power.", "Chacun de ces cristaux avait un pouvoir unique.", Tag.CRYSTAL_CAVES);
        addWhisper("I guess their power could still be harvested...", "Je suppose que leur pouvoir pourrait encore être récolté...", Tag.CRYSTAL_CAVES);
        addWhisper("Some of those crystals weren't neutral at all.", "Certains de ces cristaux n'étaient pas du tout neutres.", Tag.CRYSTAL_CAVES);
        addWhisper("Some gems could almost speak.", "Certaines gemmes pouvaient presque parler.", Tag.CRYSTAL_CAVES);
        addWhisper("If you listen carefully, you could here the black ones whispering promises of eternal life.", "Si tu écoutes attentivement, tu pourrais entendre les noires chuchoter des promesses de vie éternelle.", Tag.CRYSTAL_CAVES);
        addWhisper("The white ones also promised something, but they were more about an eternal bliss.", "Les blanches promettaient aussi quelque chose, mais elles parlaient plutôt d'une béatitude.", Tag.CRYSTAL_CAVES);
        addWhisper("If you want my opinion, none of the power crystals whispered anything good.", "Si tu veux mon avis, aucun des cristaux de pouvoir ne chuchotait quoi que ce soit de bon.", Tag.CRYSTAL_CAVES);
        addWhisper("The ancient always wanted more.", "Les anciens en voulaient toujours plus.", Tag.CRYSTAL_CAVES);


	}

	private void addWhisper(String whisperEn, String whisperFr, WhisperTags.Tag... tags) {
		lastId++;
        Whisper whisper = new Whisper(whisperEn, whisperFr, lastId);
		this.whispers.put(whisper.getId(), whisper);
        for (WhisperTags.Tag tag : tags) {
            this.whisperTags.addTag(tag, whisper);
        }
	}

    public @Nullable Whisper getNewRandomWhisper() {
        if (Minecraft.getInstance().player == null) return null;
        List<Whisper> availableWhispers = new ArrayList<>();
        Minecraft.getInstance().player.getCapability(KnownWhispersDataProvider.PLAYER_DATA).ifPresent(data -> {
            for (Whisper whisper : whispers.values()) {
                if (!data.knowsWhisper(whisper.getId())) {
                    availableWhispers.add(whisper);
                }
            }
        });
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
                    KnownWhispersDataProvider.PLAYER_DATA).ifPresent(data -> {
                if (!data.knowsWhisper(whisper.getId())) {
                    data.addKnownWhisper(whisper.getId());
                }
            });
        }
    }

	public String getWhisperEn() {
        Whisper whisper = getNewRandomWhisper();
		return whisper == null ? getRandomWhisper().getWhisper() : whisper.getWhisper();
	}

	public String getWhisperFr() {
        Whisper whisper = getNewRandomWhisper();
        return whisper == null ? getRandomWhisper().getWhisper() : whisper.getWhisper_fr();
	}
}
