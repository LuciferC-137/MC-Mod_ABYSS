package wardentools.blockentity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import java.util.function.Supplier;

public class BlockEntityRegistry {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModMain.MOD_ID);
	
	public static final Supplier<BlockEntityType<RadianceCatalystBlockEntity>> RADIANCE_CATALYST_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("radiance_catalyst_block_entity",
					() -> BlockEntityType.Builder.of(RadianceCatalystBlockEntity::new,
							BlockRegistry.RADIANCE_CATALYST.get()).build(null));
	
	public static final Supplier<BlockEntityType<ProtectorInvokerBlockEntity>> PROTECTOR_INVOKER_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("protector_invoker_block_entity",
					() -> BlockEntityType.Builder.of(ProtectorInvokerBlockEntity::new,
							BlockRegistry.PROTECTOR_INVOKER.get()).build(null));

	public static final Supplier<BlockEntityType<AbyssPortalBlockEntity>> ABYSS_PORTAL_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("abyss_portal_block_entity",
					() -> BlockEntityType.Builder.of(AbyssPortalBlockEntity::new,
							BlockRegistry.ABYSS_PORTAL_BLOCK.get()).build(null));

	public static final Supplier<BlockEntityType<DysfunctionningCatalystBlockEntity>>
			DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("dysfunctionning_catalyst_block_entity",
			() -> BlockEntityType.Builder.of(DysfunctionningCatalystBlockEntity::new,
					BlockRegistry.DYSFUNCTIONNING_CATALYST.get()).build(null));

	public static final Supplier<BlockEntityType<ContagionIncarnationSkullBlockEntity>>
			CONTAGION_INCARNATION_SKULL_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("contagion_incarnation_skull_block_entity",
			() -> BlockEntityType.Builder.of(ContagionIncarnationSkullBlockEntity::new,
					BlockRegistry.CONTAGION_INCARNATION_SKULL.get()).build(null));

	public static final Supplier<BlockEntityType<BlackLanternBlockEntity>>
			BLACK_LANTERN_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("black_lantern_block_entity",
			() -> BlockEntityType.Builder.of(BlackLanternBlockEntity::new,
					BlockRegistry.BLACK_LANTERN.get()).build(null));

	public static final Supplier<BlockEntityType<SoulSpawnerBlockEntity>> SOUL_SPAWNER_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("soul_spawner_block_entity",
					() -> BlockEntityType.Builder.of(SoulSpawnerBlockEntity::new,
							BlockRegistry.SOUL_SPAWNER.get()).build(null));

}
