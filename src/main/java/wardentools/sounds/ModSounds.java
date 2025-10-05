package wardentools.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;


public class ModSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModMain.MOD_ID);
	
	public static final RegistryObject<SoundEvent> CONTAGION_INCARNATION_SCREAM =
			registerSoundEvents("contagion_incarnation_scream");
	public static final RegistryObject<SoundEvent> CONTAGION_INCARNATION_CRAWL =
			registerSoundEvents("contagion_incarnation_crawl");
	public static final RegistryObject<SoundEvent> CONTAGION_INCARNATION_AMBIENT =
			registerSoundEvents("contagion_incarnation_ambient");
	public static final RegistryObject<SoundEvent> CONTAGION_INCARNATION_DEATH =
			registerSoundEvents("contagion_incarnation_death");
	public static final RegistryObject<SoundEvent> NOCTILURE_AMBIENT =
			registerSoundEvents("noctilure_ambient");
	public static final RegistryObject<SoundEvent> NOCTILURE_DEATH =
			registerSoundEvents("noctilure_death");
	public static final RegistryObject<SoundEvent> SHADOW_AMBIENT =
			registerSoundEvents("shadow_ambient");
	public static final RegistryObject<SoundEvent> SHADOW_DEATH =
			registerSoundEvents("shadow_death");
	public static final RegistryObject<SoundEvent> PARASYTE_DEATH =
			registerSoundEvents("parasyte_death");
	public static final RegistryObject<SoundEvent> PARASYTE_HURT =
			registerSoundEvents("parasyte_hurt");
	public static final RegistryObject<SoundEvent> PARASYTE_STEP =
			registerSoundEvents("parasyte_step");
	public static final RegistryObject<SoundEvent> DEEP_LURKER_HURT =
			registerSoundEvents("deeplurker_hurt");
	public static final RegistryObject<SoundEvent> DEEP_LURKER_DEATH =
			registerSoundEvents("deeplurker_death");
	public static final RegistryObject<SoundEvent> PALE_WANDERER_DEATH =
			registerSoundEvents("pale_wanderer_death");
	public static final RegistryObject<SoundEvent> PALE_WANDERER_HURT =
			registerSoundEvents("pale_wanderer_hurt");
	public static final RegistryObject<SoundEvent> PROTECTOR_AMBIENT =
			registerSoundEvents("protector_ambient");
	public static final RegistryObject<SoundEvent> PROTECTOR_DEATH =
			registerSoundEvents("protector_death");
	public static final RegistryObject<SoundEvent> ANCIENT_LABORATORY_GATE_CLOSING =
			registerSoundEvents("ancient_laboratory_gate_closing");
	public static final RegistryObject<SoundEvent> CONTAGION_INCARNATION_EMERGE =
			registerSoundEvents("contagion_incarnation_emerge");
	public static final RegistryObject<SoundEvent> SONIC_STRIKE =
			registerSoundEvents("sonic_strike");
	public static final RegistryObject<SoundEvent> SHOCKWAVE_THUNDER =
			registerSoundEvents("shockwave_thunder");
	public static final RegistryObject<SoundEvent> WHISTLE =
			registerSoundEvents("whistle");
	public static final RegistryObject<SoundEvent> WIND_WHISPERS =
			registerSoundEvents("wind_whispers");
	public static final RegistryObject<SoundEvent> VINYL_START =
			registerSoundEvents("vinyl_start");
	public static final RegistryObject<SoundEvent> VINYL_END =
			registerSoundEvents("vinyl_end");
	public static final RegistryObject<SoundEvent> VINYL_SCRATCH =
			registerSoundEvents("vinyl_scratch");
	public static final RegistryObject<SoundEvent> HEART_BEAT =
			registerSoundEvents("heart_beat");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_AMBIENT =
			registerSoundEvents("crystal_golem_ambient");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_TURNING_ON =
			registerSoundEvents("crystal_golem_turning_on");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_TURNING_OFF =
			registerSoundEvents("crystal_golem_turning_off");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_HURT =
			registerSoundEvents("crystal_golem_hurt");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_DEATH =
			registerSoundEvents("crystal_golem_death");
	public static final RegistryObject<SoundEvent> CRYSTAL_GOLEM_STEP =
			registerSoundEvents("crystal_golem_step");
	public static final RegistryObject<SoundEvent> LASER_CHARGE =
			registerSoundEvents("laser_charge");
	public static final RegistryObject<SoundEvent> LASER_SHOOT =
			registerSoundEvents("laser_shoot");

	//Musics
	public static final RegistryObject<SoundEvent> DEEP_FOREST =
			registerSoundEvents("deep_forest_music");
	public static final RegistryObject<SoundEvent> WHITE_FOREST =
			registerSoundEvents("white_forest_music");
	public static final RegistryObject<SoundEvent> INCARNATION_THEME =
			registerSoundEvents("incarnation_theme");
	public static final RegistryObject<SoundEvent> ABYSS_THEME =
			registerSoundEvents("abyss_theme");
	public static final RegistryObject<SoundEvent> REFLECTION =
			registerSoundEvents("reflection_of_the_past_music");

	// Music discs (lazy references)
	public static Holder.Reference<SoundEvent> getDeepForestMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(ResourceKey
				.create(Registries.SOUND_EVENT, ModSounds.DEEP_FOREST.getId()));
	}
	public static Holder.Reference<SoundEvent> getWhiteForestMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.WHITE_FOREST.getId()));
	}
	public static Holder.Reference<SoundEvent> getIncarnationThemeMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.INCARNATION_THEME.getId()));
	}
	public static Holder.Reference<SoundEvent> getAbyssThemeMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.ABYSS_THEME.getId()));
	}
	public static Holder.Reference<SoundEvent> getReflectionMusicDisc() {
		return BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(
				ResourceKey.create(Registries.SOUND_EVENT, ModSounds.REFLECTION.getId()));
	}

	public static RegistryObject<SoundEvent> registerSoundEvents(String name){
		return SOUND_EVENTS.register(name,
				() -> SoundEvent.createVariableRangeEvent(
						ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name)));
	}
	
	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
