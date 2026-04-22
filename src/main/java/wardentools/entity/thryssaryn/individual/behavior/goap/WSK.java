package wardentools.entity.thryssaryn.individual.behavior.goap;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIType;

import java.util.Set;

/**
 * World State Keys
 */
public final class WSK<T> {
    public static final WSK<Float> HEALTH = new WSK<>("HEALTH", Float.class);
    public static final WSK<Integer> HUNGER = new WSK<>("HUNGER", Integer.class);
    public static final WSK<Boolean> IS_IN_VILLAGE = new WSK<>("IS_IN_VILLAGE", Boolean.class);
    public static final WSK<TagKey<?>> BIOME_IN = new WSK<>("BIOME_IN", erased(TagKey.class));

    public static final WSK<LivingEntity> TARGET_ENTITY = new WSK<>("TARGET_ENTITY", LivingEntity.class);
    public static final WSK<EntityType<? extends Mob>> TARGET_ENTITY_TYPE =
            new WSK<>("TARGET_ENTITY_TYPE", erased(EntityType.class));
    public static final WSK<Item> TARGET_ITEM_TYPE = new WSK<>("TARGET_ITEM_TYPE", Item.class);
    public static final WSK<BlockPos> TARGET_POS = new WSK<>("TARGET_POS", BlockPos.class);

    public static final WSK<Boolean> HAS_TARGET = new WSK<>("HAS_TARGET", Boolean.class);
    public static final WSK<Boolean> IS_AT_TARGET = new WSK<>("IS_AT_TARGET", Boolean.class);
    public static final WSK<Boolean> IS_MOVING = new WSK<>("IS_MOVING", Boolean.class);
    public static final WSK<Boolean> IS_STUCK = new WSK<>("IS_STUCK", Boolean.class);
    public static final WSK<Boolean> IS_PATH_REACHABLE = new WSK<>("IS_PATH_REACHABLE", Boolean.class);
    public static final WSK<Float> DISTANCE_TO_TARGET = new WSK<>("DISTANCE_TO_TARGET", Float.class);

    public static final WSK<POIInstance> POI_AT = new WSK<>("POI_AT", POIInstance.class);
    public static final WSK<Set<POIType>> KNOWN_POI_TYPES = new WSK<>("KNOWN_POI_TYPES", erased(Set.class));

    private final String name;
    private final Class<T> type;

    private WSK(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> erased(Class<?> rawType) {
        return (Class<T>) rawType;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
