package wardentools.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

public class BlockEntityRegistry {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModMain.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<RadianceCatalystBlockEntity>> RADIANCE_CATALYST_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("radiance_catalyst_block_entity",
					() -> BlockEntityType.Builder.of(RadianceCatalystBlockEntity::new,
							BlockRegistry.RADIANCE_CATALYST.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<ProtectorInvokerBlockEntity>> PROTECTOR_INVOKER_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("protector_invoker_block_entity",
					() -> BlockEntityType.Builder.of(ProtectorInvokerBlockEntity::new,
							BlockRegistry.PROTECTOR_INVOKER.get()).build(null));

}
