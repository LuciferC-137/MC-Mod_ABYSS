package wardentools.effects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.RandomSource;

public class WindWhisper {
	
	private static final List<String> whispers_en = new ArrayList<String>();
	private static final List<String> whispers_fr = new ArrayList<String>();
	
	public WindWhisper() {
		whispers_en.add("Are you lost ?");
		whispers_en.add("Do you here this ?");
		whispers_en.add("After all, you might have gotten too deep...");
		whispers_en.add("It is so quiet...");
		whispers_en.add("Even the wind here listen to you.");
		whispers_en.add("If you listen very carefully, you might be able to hear the whispers of the Void.");
		whispers_en.add("It is hidding in the space between two silences...");
		whispers_en.add("Have you fallen here by chance ?");
		whispers_en.add("Where are you from ? Ave you ever seen the light ?");
		whispers_en.add("There is an End to everything. But you have fallen here right before reaching it...");
		whispers_en.add("Are you dreaming ?");
		whispers_en.add("Would you really listen to the wind ?");
		whispers_en.add("How long have you been sneaking in the dark ?");
		whispers_en.add("Death is only another form of silence.");
		whispers_en.add("You really became a good listener.");
		whispers_en.add("How about jupping in the Void ?");
		whispers_en.add("There is no limits.");
		whispers_en.add("You are free from the light here. Come and rest among the shadows.");
		whispers_en.add("There used to be people here. Only I can remember.");
		whispers_en.add("Do you know how the story ends ? I have seen an End once...");
		whispers_en.add("What are you doing here ?");
		whispers_en.add("Nobody came for so long...");
		whispers_en.add("This darkness devour everything...");
		whispers_en.add("This world used to be noisy and peacefull. Now, it is silent and even more peacefull.");

		whispers_fr.add("Tu es perdu ?");
		whispers_fr.add("Tu entends ça ?");
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
	}
	
	public WindWhisper(List<String> addWhispers) {
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
