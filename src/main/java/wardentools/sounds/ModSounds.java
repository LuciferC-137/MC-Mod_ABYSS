package wardentools.sounds;

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
	public static final RegistryObject<SoundEvent> DEEP_FOREST =
			registerSoundEvents("deep_forest");
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


	
	public static RegistryObject<SoundEvent> registerSoundEvents(String name){
		return SOUND_EVENTS.register(name,
				() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModMain.MOD_ID, name)));
	}
	
	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
