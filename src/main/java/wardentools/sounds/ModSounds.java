package wardentools.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ModSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ModMain.MOD_ID);
	
	public static final Supplier<SoundEvent> CONTAGION_INCARNATION_SCREAM =
			registerSoundEvents("contagion_incarnation_scream");
	public static final Supplier<SoundEvent> CONTAGION_INCARNATION_CRAWL =
			registerSoundEvents("contagion_incarnation_crawl");
	public static final Supplier<SoundEvent> CONTAGION_INCARNATION_AMBIENT =
			registerSoundEvents("contagion_incarnation_ambient");
	public static final Supplier<SoundEvent> CONTAGION_INCARNATION_DEATH =
			registerSoundEvents("contagion_incarnation_death");
	public static final Supplier<SoundEvent> NOCTILURE_AMBIENT =
			registerSoundEvents("noctilure_ambient");
	public static final Supplier<SoundEvent> NOCTILURE_DEATH =
			registerSoundEvents("noctilure_death");
	public static final Supplier<SoundEvent> SHADOW_AMBIENT =
			registerSoundEvents("shadow_ambient");
	public static final Supplier<SoundEvent> SHADOW_DEATH =
			registerSoundEvents("shadow_death");
	public static final Supplier<SoundEvent> PARASYTE_DEATH =
			registerSoundEvents("parasyte_death");
	public static final Supplier<SoundEvent> PARASYTE_HURT =
			registerSoundEvents("parasyte_hurt");
	public static final Supplier<SoundEvent> PARASYTE_STEP =
			registerSoundEvents("parasyte_step");
	public static final Supplier<SoundEvent> DEEP_LURKER_HURT =
			registerSoundEvents("deeplurker_hurt");
	public static final Supplier<SoundEvent> DEEP_LURKER_DEATH =
			registerSoundEvents("deeplurker_death");
	public static final Supplier<SoundEvent> PALE_WANDERER_DEATH =
			registerSoundEvents("pale_wanderer_death");
	public static final Supplier<SoundEvent> PALE_WANDERER_HURT =
			registerSoundEvents("pale_wanderer_hurt");
	public static final Supplier<SoundEvent> PROTECTOR_AMBIENT =
			registerSoundEvents("protector_ambient");
	public static final Supplier<SoundEvent> PROTECTOR_DEATH =
			registerSoundEvents("protector_death");
	public static final Supplier<SoundEvent> ANCIENT_LABORATORY_GATE_CLOSING =
			registerSoundEvents("ancient_laboratory_gate_closing");
	public static final Supplier<SoundEvent> CONTAGION_INCARNATION_EMERGE =
			registerSoundEvents("contagion_incarnation_emerge");
	public static final Supplier<SoundEvent> SONIC_STRIKE =
			registerSoundEvents("sonic_strike");
	public static final Supplier<SoundEvent> SHOCKWAVE_THUNDER =
			registerSoundEvents("shockwave_thunder");
	public static final Supplier<SoundEvent> WHISTLE =
			registerSoundEvents("whistle");
	public static final Supplier<SoundEvent> WIND_WHISPERS =
			registerSoundEvents("wind_whispers");

	//Musics
	public static final Supplier<SoundEvent> DEEP_FOREST =
			registerSoundEvents("deep_forest_music");
	public static final Supplier<SoundEvent> WHITE_FOREST =
			registerSoundEvents("white_forest_music");
	public static final Supplier<SoundEvent> INCARNATION_THEME =
			registerSoundEvents("incarnation_theme");
	public static final Supplier<SoundEvent> ABYSS_THEME =
			registerSoundEvents("abyss_theme");

	// Music discs (lazy references)
	public static Holder.Reference<SoundEvent> getDeepForestMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(ResourceKey
				.create(Registries.SOUND_EVENT, ModSounds.DEEP_FOREST.get().getLocation()));
	}
	public static Holder.Reference<SoundEvent> getWhiteForestMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.WHITE_FOREST.get().getLocation()));
	}
	public static Holder.Reference<SoundEvent> getIncarnationThemeMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.INCARNATION_THEME.get().getLocation()));
	}
	public static Holder.Reference<SoundEvent> getAbyssThemeMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.ABYSS_THEME.get().getLocation()));
	}

	private static ResourceKey<JukeboxSong> createSong(String name) {
		return ResourceKey.create(Registries.JUKEBOX_SONG,
				ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
	}

	private static Supplier<SoundEvent> registerSoundEvents(String name) {
		ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name);
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
	}
	
	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
