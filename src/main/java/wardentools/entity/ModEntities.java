package wardentools.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.entity.custom.*;
import wardentools.weather.lightning.AbyssLightningEntity;

import java.util.function.Supplier;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ModMain.MOD_ID);
	
	public static final Supplier<EntityType<DeepLurkerEntity>> DEEPLURKER =
			ENTITY_TYPES.register("deeplurker",
					()->EntityType.Builder.of(DeepLurkerEntity::new, MobCategory.CREATURE)
					.sized(0.5f, 0.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deeplurker").toString())
					);

	public static final Supplier<EntityType<PaleWandererEntity>> PALE_WANDERER =
			ENTITY_TYPES.register("pale_wanderer",
					()->EntityType.Builder.of(PaleWandererEntity::new, MobCategory.CREATURE)
					.sized(0.9f, 0.4f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_wanderer").toString())
					);
	
	public static final Supplier<EntityType<ProtectorEntity>> PROTECTOR =
			ENTITY_TYPES.register("protector",
					()->EntityType.Builder.of(ProtectorEntity::new, MobCategory.CREATURE)
					.sized(1.5f, 2.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector").toString()));
	
	public static final Supplier<EntityType<ContagionIncarnationEntity>> CONTAGION_INCARNATION =
			ENTITY_TYPES.register("contagion_incarnation",
					()->EntityType.Builder.of(ContagionIncarnationEntity::new, MobCategory.MONSTER)
					.sized(3f, 2f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation").toString())
					);
	public static final Supplier<EntityType<ModBoatEntity>> MOD_BOAT =
			ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
					.sized(1.375f, 0.5625f).build("mod_boat"));
	public static final Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
			ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
					.sized(1.375f, 0.5625f).build("mod_chest_boat"));
	public static final Supplier<EntityType<TemperEntity>> TEMPER =
			ENTITY_TYPES.register("temper",
					()->EntityType.Builder.of(TemperEntity::new, MobCategory.CREATURE)
					.sized(0.5f, 0.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "temper").toString()));
	public static final Supplier<EntityType<ParasyteEntity>> PARASYTE =
			ENTITY_TYPES.register("parasyte",
					() -> EntityType.Builder.of(ParasyteEntity::new, MobCategory.MONSTER)
					.sized(0.4f, 0.3f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "parasyte").toString()));

	public static final Supplier<EntityType<NoctilureEntity>> NOCTILURE =
			ENTITY_TYPES.register("noctilure",
					() -> EntityType.Builder.of(NoctilureEntity::new, MobCategory.CREATURE)
							.sized(2f, 1f)
							.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "noctilure").toString()));

	public static final Supplier<EntityType<ShadowEntity>> SHADOW =
			ENTITY_TYPES.register("shadow",
					() -> EntityType.Builder.of(ShadowEntity::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "shadow").toString()));

	public static final Supplier<EntityType<ContagionIncarnationCorpseEntity>> CONTAGION_INCARNATION_CORPSE =
			ENTITY_TYPES.register("contagion_incarnation_corpse",
					()->EntityType.Builder.of(ContagionIncarnationCorpseEntity::new, MobCategory.MISC)
					.sized(4f, 1.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation_corpse").toString()));

	public static final Supplier<EntityType<AbyssLightningEntity>> ABYSS_LIGHTNING =
			ENTITY_TYPES.register("abyss_lightning",
					()->EntityType.Builder.of(AbyssLightningEntity::new, MobCategory.MISC)
					.sized(0.5f, 0.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss_lightning").toString()));

	public static final Supplier<EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM =
			ENTITY_TYPES.register("crystal_golem",
					()->EntityType.Builder.of(CrystalGolemEntity::new, MobCategory.CREATURE)
					.sized(0.6F, 1.625F)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "crystal_golem").toString()));

	public static final Supplier<EntityType<CrystalLaserEntity>> CRYSTAL_LASER =
			ENTITY_TYPES.register("crystal_laser",
					()->EntityType.Builder.of(CrystalLaserEntity::new, MobCategory.MISC)
					.sized(0.5f, 0.5f)
					.build(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "crystal_laser").toString()));
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
