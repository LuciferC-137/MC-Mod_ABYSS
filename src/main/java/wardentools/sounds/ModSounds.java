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
	
	
	
	
	
	public static RegistryObject<SoundEvent> registerSoundEvents(String name){
		return SOUND_EVENTS.register(name,
				() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModMain.MOD_ID, name)));
	}
	
	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
