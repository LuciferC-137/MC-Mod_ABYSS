package wardentools.misc.wind;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.RandomSource;

public class WindWhispers {
	
	private static final List<String> whispers_en = new ArrayList<String>();
	private static final List<String> whispers_fr = new ArrayList<String>();
	
	public WindWhispers() {
		whispers_en.add("Are you lost ?");
		whispers_en.add("Do you hear this ?");
		whispers_en.add("After all, you might have gotten too deep...");
		whispers_en.add("It is so quiet...");
		whispers_en.add("Even the wind here listens to you.");
		whispers_en.add("If you listen very carefully, you might be able to hear the whispers of the Void.");
		whispers_en.add("It is hidding in the space between two silences...");
		whispers_en.add("Have you fallen here by chance ?");
		whispers_en.add("Where are you from ? Have you ever seen the light ?");
		whispers_en.add("There is an End to everything. But you have fallen here right before reaching it...");
		whispers_en.add("Are you dreaming ?");
		whispers_en.add("Would you really listen to the wind ?");
		whispers_en.add("How long have you been sneaking in the dark ?");
		whispers_en.add("Death is only another form of silence.");
		whispers_en.add("You really became a good listener.");
		whispers_en.add("What about jumping in the Void ?");
		whispers_en.add("There is no limits.");
		whispers_en.add("You are free from the light here. Come and rest among the shadows.");
		whispers_en.add("There used to be people here. Only I can remember.");
		whispers_en.add("Do you know how the story ends ? I have seen an End once...");
		whispers_en.add("What are you doing here ?");
		whispers_en.add("Nobody came for so long...");
		whispers_en.add("This darkness devours everything...");
		whispers_en.add("This world used to be noisy and peacefull. Now, it is silent and even more peacefull.");
		whispers_en.add("The darkness of this world has gotten into yours, hasn't it ?");
		whispers_en.add("Do you think he will be able to find what used to make the beauty of this world ?");
		whispers_en.add("Silence ! He is listening !");
		whispers_en.add("So it is true that you can hear us...");
		whispers_en.add("Who we are ? Some kind of ghosts I guess... But you are no different. You have seen it, don't you ? The Great Void ?");
		whispers_en.add("Before it came, what we most fear was our best ally...");
		whispers_en.add("It turned our protectors against us... They became blind and angry. They couldn't listen to the music anymore. All they wanted to hear was screams.");
		whispers_en.add("Were we alive some day ? I don't remember...");
		whispers_en.add("The sun hasn't shed a light on the wind for so long down here...");
		whispers_en.add("I couldn't imagine that the corruption would also reach the sky itself...");
		whispers_en.add("No hope. No light. Only infinite nothingness, watched by aimless wardens.");
		whispers_en.add("I fly in the sky, I dive into caves, I hurl on the Void, and yet, you are more free than I will ever be.");
		whispers_en.add("Do you think he could perform the reactivation ritual ?");
		whispers_en.add("There was all sort of crystals, each with its own energy. But we tried to harvest the power of the forbidden one.");
		whispers_en.add("Soon after the incident, the contagion begun to rise from the depths.");
		whispers_en.add("They thought they could control it. They thought they could be safe in their underground Citadel.");
		whispers_en.add("Crystals were growing everywhere. They were beautiful, but they were also dangerous.");
		whispers_en.add("Entire cities would run thanks to the energy of the crystals.");
		whispers_en.add("They began to worship the corrupted crystals. They began to worship the Void.");
		whispers_en.add("Once the corruption took over, they created a Beast from it. An Incarnation of destruction.");

		whispers_fr.add("Tu es perdu ?");
		whispers_fr.add("Est-ce que tu entends ça ?");
		whispers_fr.add("Après tout, tu t'es peut-être enfoncé trop profondément...");
		whispers_fr.add("Tout est tellement silencieux...");
		whispers_fr.add("Ici, même le vent t'écoute.");
		whispers_fr.add("Si tu écoutes attentivement, tu pourrais bien entendre les murmures du Néant.");
		whispers_fr.add("Il se cache dans l'espace entre deux silences...");
		whispers_fr.add("Tu es tombé ici par hasard ?");
		whispers_fr.add("D'où est-ce que tu viens ? Tu as déjà vu la lumière ?");
		whispers_fr.add("Il y a une Fin à tout. Mais tu es tombé ici juste avant de l'atteindre...");
		whispers_fr.add("Est-ce que tu es en train de rêver ?");
		whispers_fr.add("Tu écouterais vraiment ce que dis le vent ?");
		whispers_fr.add("Depuis combien de temps ères-tu dans le noir ?");
		whispers_fr.add("La mort est seulement une autre forme de silence.");
		whispers_fr.add("Tu es vraiment devenu attentif.");
		whispers_fr.add("Pourquoi pas se jeter dans le Néant ?");
		whispers_fr.add("Il n'y a aucune limites.");
		whispers_fr.add("Tu es libéré de la lumière ici. Viens, et repose-toi parmi les ombres.");
		whispers_fr.add("Il y avait des gens autrefois ici. Je suis le seul à pouvoir m'en souvenir.");
		whispers_fr.add("Est-ce que tu sais comment l'histoire se termine ? J'ai vu une Fin, une fois...");
		whispers_fr.add("Qu'est-ce que tu fais ici ?");
		whispers_fr.add("Personne n'est venu depuis si longtemps...");
		whispers_fr.add("Ces ténèbres dévorent tout...");
		whispers_fr.add("Ce monde était bruyant et paisible autrefois. Maintenant, il est silencieux et encore plus paisible.");
		whispers_fr.add("Les ténèbres de ce monde ont gagné le tiens, n'est-ce pas ?");
		whispers_fr.add("Tu pense qu'il sera capable de découvrir ce qui faisait la beauté de ce monde ?");
		whispers_fr.add("Silence ! Il nous écoute !");
		whispers_fr.add("Il est donc vrai que tu peux nous entendre dorénavant...");
		whispers_fr.add("Qui sommes nous ? Des sortes de fantômes, j'imagine... Mais nous ne sommes pas différents. Tu l'as déjà vu, n'est-ce pas ? Le Grand Néant ?");
		whispers_fr.add("Avant qu'il ne vienne, ce que nous craignons aujourd'hui le plus était notre plus grand allié...");
		whispers_fr.add("Il a retourné nos protecteurs contre nous... Ils sont devenus aveugle et furieux. Ils ne pouvaient plus entendre la musique. Tout ce qu'ils voulaient entendre, c'était des hurlements.");
		whispers_fr.add("Etions-nous en vie à un moment ? Je ne me souviens plus...");
		whispers_fr.add("Le moindre rayon de soleil n'a pas éclairé le vent depuis si longtemps ici-bas...");
		whispers_fr.add("Je ne pouvais pas imaginer que la corruption gagnerait le ciel lui-même...");
		whispers_fr.add("Pas d'espoir. Pas de lumière. Seulement l'infini Néant, surveillé par des gardiens sans but.");
		whispers_fr.add("Je vole dans le ciel, je plonge dans les cavernes, je rugis dans le Néant et pourtant, pourtant tu es plus libre que je ne le serai jamais.");
		whispers_fr.add("Tu pense qu'il pourrait réaliser le rituel de réactivation ?");
		whispers_fr.add("Il y avait toute sorte de cristaux, chacun avec sa propre énergie. Mais nous avons tenté de récolter le pouvoir de celui qui était interdit.");
		whispers_fr.add("Après l'incident, la contagion commença à s'élever depuis les profondeurs.");
		whispers_fr.add("Ils pensaient pouvoir la contrôler. Ils pensaient être en sécurité dans leur Citadelle souterraine.");
		whispers_fr.add("Les cristaux poussaient partout. Ils étaient beaux, mais ils étaient aussi dangereux.");
		whispers_fr.add("Des villes entières fonctionnaient grâce à l'énergie des cristaux.");
		whispers_fr.add("Ils commencèrent à adorer les cristaux corrompus. Ils commencèrent à adorer le Néant.");
		whispers_fr.add("Une fois que la corruption prit le dessus, ils forgèrent une Bête avec. Une Incarnation de la destruction.");

	}
	
	public WindWhispers(List<String> addWhispers) {
		whispers_en.addAll(addWhispers);
		whispers_fr.addAll(addWhispers);
	}
	
	public String getWhisperEn() {
		RandomSource rand = RandomSource.create();
		return whispers_en.get(rand.nextInt(whispers_en.size()));
	}
	
	public String getWhisperFr() {
		RandomSource rand = RandomSource.create();
		return whispers_fr.get(rand.nextInt(whispers_fr.size()));
	}
}
