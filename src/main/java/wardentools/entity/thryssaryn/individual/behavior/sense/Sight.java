package wardentools.entity.thryssaryn.individual.behavior.sense;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public class Sight extends Sense {
    private static final int MAX_ENTITY_CHECK = 10;

    public Sight(ThryssarynEntity entity) {
        super(entity);
    }

    public boolean hasLineOfSight(Vec3 target) {
        Vec3 origin = new Vec3(this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
        return !(target.distanceTo(origin) > (double) 128.0F)
                && this.entity.level().clip(clipContext(origin, target)).getType()
                == HitResult.Type.MISS;
    }

    public boolean hasLineOfSight(Entity entity) {
        if (entity.level() != this.entity.level()) {
            return false;
        } else {
            Vec3 target = new Vec3(entity.getX(), entity.getEyeY(), entity.getZ());
            return hasLineOfSight(target);
        }
    }

    private ClipContext clipContext(Vec3 origin, Vec3 target) {
        return  new ClipContext(origin, target, ClipContext.Block.COLLIDER,
                net.minecraft.world.level.ClipContext.Fluid.NONE, this.entity);
    }

    public @Nullable BlockPos canSee(Item item) {
        return canSee(e -> e instanceof ItemEntity && ((ItemEntity) e).getItem().is(item));
    }

    public @Nullable BlockPos canSee(EntityType<?> type) {
        return canSee(e -> e.getType() == type);
    }

    public @Nullable BlockPos canSee(Function<Entity, Boolean> filter) {
        List<Entity> entities = this.entity.level().getEntities(this.entity,
                this.entity.getBoundingBox().inflate(128), filter::apply);
        int cnt = 0;
        for (Entity entity : entities) {
            if (hasLineOfSight(entity)) {
                return entity.getOnPos();
            }
            cnt++;
            if (cnt > MAX_ENTITY_CHECK) {
                return null;
            }
        }
        return null;
    }
}
