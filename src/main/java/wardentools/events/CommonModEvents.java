package wardentools.events;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import wardentools.gui.title.CustomMainMenuScreen;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.*;
import wardentools.network.PacketHandler;
import wardentools.particle.ParticleRegistry;
import wardentools.particle.custom.AbyssAmbient;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
	
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {PacketHandler.register();});
		ModCriteriaTriggers.init();
    }

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		RenderSystem.recordRenderCall(() -> {
			Minecraft.getInstance().setScreen(new CustomMainMenuScreen(Minecraft.getInstance()));
		});
	}
    
    @SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ModEntities.DEEPLURKER.get(), DeepLurkerEntity.createAttribute().build());
		event.put(ModEntities.PALE_WANDERER.get(), PaleWandererEntity.createAttribute().build());
		event.put(ModEntities.PROTECTOR.get(), ProtectorEntity.createAttribute().build());
		event.put(ModEntities.CONTAGION_INCARNATION.get(), ContagionIncarnationEntity.createAttribute().build());
		event.put(ModEntities.TEMPER.get(), TemperEntity.createAttribute().build());
	}
    
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
    	event.register(ModEntities.DEEPLURKER.get(),
    			SpawnPlacements.Type.ON_GROUND,
    			Heightmap.Types.MOTION_BLOCKING,
    			DeepLurkerEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.PALE_WANDERER.get(),
    			SpawnPlacements.Type.ON_GROUND,
    			Heightmap.Types.WORLD_SURFACE,
    			PaleWandererEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.PROTECTOR.get(),
    			SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
    			ProtectorEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.CONTAGION_INCARNATION.get(),
    			SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
    			ContagionIncarnationEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
		event.register(ModEntities.TEMPER.get(),
				SpawnPlacements.Type.NO_RESTRICTIONS,
				Heightmap.Types.MOTION_BLOCKING,
				TemperEntity::canSpawn,
				SpawnPlacementRegisterEvent.Operation.OR);
    }

	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.ABYSS_AMBIENT.get(),
				AbyssAmbient.Provider::new);
	}
}