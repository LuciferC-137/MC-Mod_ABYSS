package wardentools.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.*;
import wardentools.items.ItemRegistry;
import wardentools.weather.lightning.AbyssLightningEntity;

public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModMain.MOD_ID);
	
	public static final RegistryObject<EntityType<DeepLurkerEntity>> DEEPLURKER =
			ENTITY_TYPES.register("deeplurker",
					()->EntityType.Builder.of(DeepLurkerEntity::new, MobCategory.CREATURE)
					.sized(0.5f, 0.5f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deeplurker")))
					);

	public static final RegistryObject<EntityType<PaleWandererEntity>> PALE_WANDERER =
			ENTITY_TYPES.register("pale_wanderer",
					()->EntityType.Builder.of(PaleWandererEntity::new, MobCategory.CREATURE)
					.sized(0.9f, 0.4f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_wanderer")))
					);
	
	public static final RegistryObject<EntityType<ProtectorEntity>> PROTECTOR =
			ENTITY_TYPES.register("protector",
					()->EntityType.Builder.of(ProtectorEntity::new, MobCategory.CREATURE)
					.sized(1.5f, 2.5f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector")))
					);
	
	public static final RegistryObject<EntityType<ContagionIncarnationEntity>> CONTAGION_INCARNATION =
			ENTITY_TYPES.register("contagion_incarnation",
					()->EntityType.Builder.of(ContagionIncarnationEntity::new, MobCategory.MONSTER)
					.sized(3f, 2f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation")))
					);
	public static final RegistryObject<EntityType<DarktreeBoat>> DARKTREE_BOAT =
			ENTITY_TYPES.register("darktree_boat", () -> EntityType.Builder.<DarktreeBoat>of(
							(type, level) -> new DarktreeBoat(type, level, ItemRegistry.DARKTREE_BOAT)
							, MobCategory.MISC)
					.sized(1.375f, 0.5625f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_boat")))
			);

	public static final RegistryObject<EntityType<WhitetreeBoat>> WHITETREE_BOAT =
			ENTITY_TYPES.register("whitetree_boat", () -> EntityType.Builder.<WhitetreeBoat>of(
							(type, level) -> new WhitetreeBoat(type, level, ItemRegistry.WHITETREE_BOAT)
							, MobCategory.MISC)
					.sized(1.375f, 0.5625f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_boat")))
			);

	public static final RegistryObject<EntityType<DarktreeChestBoat>> WHITETREE_CHEST_BOAT =
			ENTITY_TYPES.register("whitetree_chest_boat",
					() -> EntityType.Builder.<DarktreeChestBoat>of(
							(type, level) -> new DarktreeChestBoat(type, level, ItemRegistry.WHITETREE_CHEST_BOAT), MobCategory.MISC)
					.sized(1.375f, 0.5625f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_chest_boat")))
					);

	public static final RegistryObject<EntityType<DarktreeChestBoat>> DARKTREE_CHEST_BOAT =
			ENTITY_TYPES.register("darktree_chest_boat",
					() -> EntityType.Builder.<DarktreeChestBoat>of(
									(type, level) -> new DarktreeChestBoat(type, level, ItemRegistry.DARKTREE_CHEST_BOAT), MobCategory.MISC)
							.sized(1.375f, 0.5625f)
							.build(ResourceKey.create(Registries.ENTITY_TYPE,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_chest_boat")))
			);

	public static final RegistryObject<EntityType<TemperEntity>> TEMPER =
			ENTITY_TYPES.register("temper",
					()->EntityType.Builder.of(TemperEntity::new, MobCategory.MISC)
					.sized(0.5f, 0.5f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "temper")))
					);

	public static final RegistryObject<EntityType<ParasyteEntity>> PARASYTE =
			ENTITY_TYPES.register("parasyte",
					() -> EntityType.Builder.of(ParasyteEntity::new, MobCategory.MONSTER)
					.sized(0.4f, 0.3f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "parasyte")))
			);

	public static final RegistryObject<EntityType<NoctilureEntity>> NOCTILURE =
			ENTITY_TYPES.register("noctilure",
					() -> EntityType.Builder.of(NoctilureEntity::new, MobCategory.CREATURE)
					.sized(2f, 1f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "noctilure")))
			);

	public static final RegistryObject<EntityType<ShadowEntity>> SHADOW =
			ENTITY_TYPES.register("shadow",
					() -> EntityType.Builder.of(ShadowEntity::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "shadow")))
			);

	public static final RegistryObject<EntityType<ContagionIncarnationCorpseEntity>> CONTAGION_INCARNATION_CORPSE =
			ENTITY_TYPES.register("contagion_incarnation_corpse",
					()->EntityType.Builder.of(ContagionIncarnationCorpseEntity::new, MobCategory.MISC)
					.sized(4f, 1.5f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation_corpse")))
			);

	public static final RegistryObject<EntityType<AbyssLightningEntity>> ABYSS_LIGHTNING =
			ENTITY_TYPES.register("abyss_lightning",
					()->EntityType.Builder.of(AbyssLightningEntity::new, MobCategory.MISC)
					.sized(0.5f, 0.5f)
					.build(ResourceKey.create(Registries.ENTITY_TYPE,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss_lightning")))
			);
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

}
