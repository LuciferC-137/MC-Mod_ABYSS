package wardentools.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.DarktreeChestBoat;
import wardentools.entity.custom.WhitetreeBoat;

import java.util.List;

public class BoatItem extends Item {
    private final Type boat;

    public BoatItem(Type boat, Item.Properties pProperties) {
        super(pProperties);
        this.boat = boat;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level pLevel, Player pPlayer,
                                          @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        HitResult hitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else {
            Vec3 vec3 = pPlayer.getViewVector(1.0F);
            List<Entity> list = pLevel.getEntities(pPlayer,
                    pPlayer.getBoundingBox().expandTowards(vec3.scale(5.0D))
                            .inflate(1.0D));
            if (!list.isEmpty()) {
                Vec3 vec31 = pPlayer.getEyePosition();

                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResult.PASS;
                    }
                }
            }
            if (hitresult.getType() == HitResult.Type.BLOCK) {
                return this.choseAndPlaceBoat(pLevel, hitresult, itemstack, pPlayer);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    private InteractionResult choseAndPlaceBoat(Level level, HitResult hitResult, ItemStack stack, Player player) {
        if (this.boat == Type.DARKTREE_BOAT) {
            DarktreeChestBoat boat = new DarktreeChestBoat(level,
                    hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z,
                    () -> this);
            return placeBoat(level, hitResult, stack, player, boat);
        } else if (this.boat == Type.WHITETREE_BOAT) {
            WhitetreeBoat boat = new WhitetreeBoat(level,
                    hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z,
                    () -> this);
            return placeBoat(level, hitResult, stack, player, boat);
        } else if (this.boat == Type.DARKTREE_CHEST_BOAT) {
            DarktreeChestBoat boat = new DarktreeChestBoat(level,
                    hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z,
                    () -> this);
            return placeBoat(level, hitResult, stack, player, boat);
        } else if (this.boat == Type.WHITETREE_CHEST_BOAT) {
            DarktreeChestBoat boat = new DarktreeChestBoat(level,
                    hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z,
                    () -> this);
            return placeBoat(level, hitResult, stack, player, boat);
        }
        return InteractionResult.FAIL;
    }

    private InteractionResult placeBoat(Level level, HitResult hitResult, ItemStack stack, Player player, Entity boat) {
        boat.setYRot(player.getYRot());
        if (!level.noCollision(boat, boat.getBoundingBox())) {
            return InteractionResult.FAIL;
        } else {
            if (!level.isClientSide) {
                level.addFreshEntity(boat);
                level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResult.SUCCESS;
        }
    }

    public enum Type {
        DARKTREE_BOAT,
        WHITETREE_BOAT,
        DARKTREE_CHEST_BOAT,
        WHITETREE_CHEST_BOAT;

        public String getName() {
            if (this == DARKTREE_BOAT || this == DARKTREE_CHEST_BOAT) {
                return "darktree";
            } else {
                return "whitetree";
            }
        }
    }
}