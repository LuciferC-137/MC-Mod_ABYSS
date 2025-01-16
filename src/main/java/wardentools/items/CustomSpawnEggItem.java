package wardentools.items;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.ModMain;

public class CustomSpawnEggItem extends SpawnEggItem {
    private static final List<CustomSpawnEggItem> MOD_EGGS = new ArrayList<>();
    private static final Map<EntityType<? extends Mob>, CustomSpawnEggItem> TYPE_MAP = new IdentityHashMap<>();
    private final Supplier<? extends EntityType<? extends Mob>> typeSupplier;
    private static final DispenseItemBehavior DEFAULT_DISPENSE_BEHAVIOR = (source, stack) -> {
        Direction face = (Direction)source.state().getValue(DispenserBlock.FACING);
        EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack);

        try {
            type.spawn(source.level(), stack, (Player)null, source.pos().relative(face), MobSpawnType.DISPENSER, face != Direction.UP, false);
        } catch (Exception var5) {
            Exception exception = var5;
            DispenseItemBehavior.LOGGER.error("Error while dispensing spawn egg from dispenser at {}", source.pos(), exception);
            return ItemStack.EMPTY;
        }

        stack.shrink(1);
        source.level().gameEvent(GameEvent.ENTITY_PLACE, source.pos(), Context.of(source.state()));
        return stack;
    };

    public CustomSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Item.Properties props) {
        super((EntityType)null, backgroundColor, highlightColor, props);
        this.typeSupplier = type;
        MOD_EGGS.add(this);
    }

    protected @Nullable DispenseItemBehavior createDispenseBehavior() {
        return DEFAULT_DISPENSE_BEHAVIOR;
    }

    public static @Nullable SpawnEggItem fromEntityType(@Nullable EntityType<?> type) {
        SpawnEggItem ret = (SpawnEggItem)TYPE_MAP.get(type);
        return ret != null ? ret : SpawnEggItem.byId(type);
    }

    protected @NotNull EntityType<?> getDefaultType() {
        return (EntityType)this.typeSupplier.get();
    }

    // Removed due to error in registering colors. Eggs would appear transparent.
    // Individuals textures have been added to bypass this.
    /*
    @EventBusSubscriber(
            value = {Dist.CLIENT},
            modid = ModMain.MOD_ID,
            bus = Bus.MOD
    )
    private static class ColorRegisterHandler {
        private ColorRegisterHandler() {
        }

        @SubscribeEvent(
                priority = EventPriority.HIGHEST
        )
        public static void registerSpawnEggColors(RegisterColorHandlersEvent.Item event) {
            CustomSpawnEggItem.MOD_EGGS.forEach((egg) -> {
                event.register((stack, layer) -> {
                    return egg.getColor(layer);
                }, new ItemLike[]{egg});
            });
        }
    }*/

    @EventBusSubscriber(
            modid = "forge",
            bus = Bus.MOD
    )
    private static class CommonHandler {
        private CommonHandler() {
        }

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            CustomSpawnEggItem.MOD_EGGS.forEach((egg) -> {
                DispenseItemBehavior dispenseBehavior = egg.createDispenseBehavior();
                if (dispenseBehavior != null) {
                    DispenserBlock.registerBehavior(egg, dispenseBehavior);
                }
                CustomSpawnEggItem.TYPE_MAP.put((EntityType)egg.typeSupplier.get(), egg);
            });
        }
    }
}

