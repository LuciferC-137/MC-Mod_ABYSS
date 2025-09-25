package wardentools.events;


import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.*;
import wardentools.network.PacketHandler;
import wardentools.particle.ParticleRegistry;
import wardentools.particle.custom.*;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
	
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(PacketHandler::register);
		event.enqueueWork(()->{
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.WHITE_TORCHFLOWER.getId(),
					BlockRegistry.POTTED_WHITE_TORCHFLOWER);
		});
		ModCriteriaTriggers.init();
    }
    
    @SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ModEntities.DEEPLURKER.get(), DeepLurkerEntity.createAttribute().build());
		event.put(ModEntities.PALE_WANDERER.get(), PaleWandererEntity.createAttribute().build());
		event.put(ModEntities.PROTECTOR.get(), ProtectorEntity.createAttribute().build());
		event.put(ModEntities.CONTAGION_INCARNATION.get(), ContagionIncarnationEntity.createAttribute().build());
		event.put(ModEntities.TEMPER.get(), TemperEntity.createAttribute().build());
		event.put(ModEntities.PARASYTE.get(), ParasyteEntity.createAttribute().build());
		event.put(ModEntities.NOCTILURE.get(), NoctilureEntity.createAttribute().build());
		event.put(ModEntities.SHADOW.get(), ShadowEntity.createAttribute().build());
		event.put(ModEntities.CONTAGION_INCARNATION_CORPSE.get(),
				ContagionIncarnationCorpseEntity.createAttribute().build());
	}
    
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
    	event.register(ModEntities.DEEPLURKER.get(),
				SpawnPlacementTypes.ON_GROUND,
    			Heightmap.Types.MOTION_BLOCKING,
    			DeepLurkerEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.PALE_WANDERER.get(),
    			SpawnPlacementTypes.ON_GROUND,
    			Heightmap.Types.WORLD_SURFACE,
    			PaleWandererEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.PROTECTOR.get(),
    			SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
    			ProtectorEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.CONTAGION_INCARNATION.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
    			ContagionIncarnationEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
		event.register(ModEntities.TEMPER.get(),
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Heightmap.Types.MOTION_BLOCKING,
				TemperEntity::canSpawn,
				SpawnPlacementRegisterEvent.Operation.OR);
		event.register(ModEntities.PARASYTE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
				ParasyteEntity::canSpawn,
				SpawnPlacementRegisterEvent.Operation.OR);
		event.register(ModEntities.NOCTILURE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.WORLD_SURFACE,
				NoctilureEntity::canSpawn,
				SpawnPlacementRegisterEvent.Operation.OR);
		event.register(ModEntities.SHADOW.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING,
				ShadowEntity::canSpawn,
				SpawnPlacementRegisterEvent.Operation.OR);
    }

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.ABYSS_AMBIENT.get(),
				AbyssAmbient.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.CORRUPTION.get(),
				Corruption.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.ABYSS_PORTAL.get(),
				AbyssPortal.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.RADIANCE.get(),
				Radiance.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.BLACK_CORRUPTION.get(),
				BlackCorruption.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleRegistry.SHINE_PARTICLE.get(),
				ShineParticle.Provider::new);
	}
}