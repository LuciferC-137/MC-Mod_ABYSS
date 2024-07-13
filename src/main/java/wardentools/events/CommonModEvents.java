package wardentools.events;


import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.entity.custom.PaleWandererEntity;
import wardentools.network.PacketHandler;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
	
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {PacketHandler.register();});
    }
    
    @SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ModEntities.DEEPLURKER.get(), DeepLurkerEntity.createAttribute().build());
		event.put(ModEntities.PALE_WANDERER.get(), PaleWandererEntity.createAttribute().build());
	}
    
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
    	event.register(ModEntities.DEEPLURKER.get(),
    			SpawnPlacements.Type.ON_GROUND,
    			Heightmap.Types.WORLD_SURFACE,
    			DeepLurkerEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    	event.register(ModEntities.PALE_WANDERER.get(),
    			SpawnPlacements.Type.ON_GROUND,
    			Heightmap.Types.WORLD_SURFACE,
    			PaleWandererEntity::canSpawn,
    			SpawnPlacementRegisterEvent.Operation.OR);
    }
}