package wardentools.entity.thryssaryn.individual.behavior.goap;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

/**
 * World State Keys
 */
public enum WSK {
    HEALTH(Integer.class),
    HUNGER(Integer.class),
    IS_IN_VILLAGE(Boolean.class),
    BIOME_IN(TagKey.class),

    TARGET_ENTITY(LivingEntity.class),
    TARGET_ENTITY_TYPE(EntityType.class),
    TARGET_ITEM_TYPE(Item.class),
    TARGET_POS(BlockPos.class),

    HAS_TARGET(Boolean.class),
    IS_AT_TARGET(Boolean.class),
    IS_MOVING(Boolean.class),
    IS_STUCK(Boolean.class),
    IS_PATH_REACHABLE(Boolean.class),
    DISTANCE_TO_TARGET(Float.class),
    ;

    private final Class<?> type;

    WSK(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}
