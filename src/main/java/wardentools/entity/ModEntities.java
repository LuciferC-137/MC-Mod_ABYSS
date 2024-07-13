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
import wardentools.entity.custom.PaleWandererEntity;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModMain.MOD_ID);
	
	public static final RegistryObject<EntityType<DeepLurkerEntity>> DEEPLURKER =
			ENTITY_TYPES.register("deeplurker",
					()->EntityType.Builder.of(DeepLurkerEntity::new, MobCategory.CREATURE)
					.sized(0.5f, 0.5f)
					.build(new ResourceLocation(ModMain.MOD_ID, "deeplurker").toString())
					);

	public static final RegistryObject<EntityType<PaleWandererEntity>> PALE_WANDERER =
			ENTITY_TYPES.register("pale_wanderer",
					()->EntityType.Builder.of(PaleWandererEntity::new, MobCategory.CREATURE)
					.sized(0.9f, 0.4f)
					.build(new ResourceLocation(ModMain.MOD_ID, "pale_wanderer").toString())
					);
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
