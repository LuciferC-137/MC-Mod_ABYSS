package wardentools.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.*;

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
	
	public static final RegistryObject<EntityType<ProtectorEntity>> PROTECTOR =
			ENTITY_TYPES.register("protector",
					()->EntityType.Builder.of(ProtectorEntity::new, MobCategory.CREATURE)
					.sized(1.5f, 2.5f)
					.build(new ResourceLocation(ModMain.MOD_ID, "protector").toString()));
	
	public static final RegistryObject<EntityType<ContagionIncarnationEntity>> CONTAGION_INCARNATION =
			ENTITY_TYPES.register("contagion_incarnation",
					()->EntityType.Builder.of(ContagionIncarnationEntity::new, MobCategory.MONSTER)
					.sized(4f, 4f)
					.build(new ResourceLocation(ModMain.MOD_ID, "contagion_incarnation").toString())
					);
	public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
			ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
					.sized(1.375f, 0.5625f).build("mod_boat"));
	public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
			ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
					.sized(1.375f, 0.5625f).build("mod_chest_boat"));
	public static final RegistryObject<EntityType<TemperEntity>> TEMPER =
			ENTITY_TYPES.register("temper",
					()->EntityType.Builder.of(TemperEntity::new, MobCategory.MISC)
					.sized(0.5f, 0.5f)
					.build(new ResourceLocation(ModMain.MOD_ID, "temper").toString()));
	public static final RegistryObject<EntityType<ParasyteEntity>> PARASYTE =
			ENTITY_TYPES.register("parasyte",
					() -> EntityType.Builder.of(ParasyteEntity::new, MobCategory.MONSTER)
					.sized(0.4f, 0.3f)
					.build(new ResourceLocation(ModMain.MOD_ID, "parasyte").toString()));

	public static final RegistryObject<EntityType<NoctilureEntity>> NOCTILURE =
			ENTITY_TYPES.register("noctilure",
					() -> EntityType.Builder.of(NoctilureEntity::new, MobCategory.CREATURE)
							.sized(2f, 1f)
							.build(new ResourceLocation(ModMain.MOD_ID, "noctilure").toString()));
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
