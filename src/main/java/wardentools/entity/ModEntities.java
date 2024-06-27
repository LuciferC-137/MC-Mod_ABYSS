package wardentools.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.DeepLurkerEntity;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModMain.MOD_ID);
	
	public static final RegistryObject<EntityType<DeepLurkerEntity>> DEEPLURKER =
			ENTITY_TYPES.register("deeplurker",
					()->EntityType.Builder.of(DeepLurkerEntity::new, MobCategory.CREATURE)
					.sized(0.6f, 0.6f)
					.build(new ResourceLocation(ModMain.MOD_ID, "deeplurker").toString())
					);
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
