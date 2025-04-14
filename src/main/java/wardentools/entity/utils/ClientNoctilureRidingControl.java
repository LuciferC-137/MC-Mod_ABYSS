package wardentools.entity.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.entity.custom.NoctilureEntity;

@OnlyIn(Dist.CLIENT)
public class ClientNoctilureRidingControl {

    public static void handlePlayerControl(NoctilureEntity noctilure) {
        if (noctilure.getWantsToLand()) {noctilure.setWantsToLand(false);}
        if (noctilure.getWantsToTakeOff()) {noctilure.setWantsToTakeOff(false);}
        if (Minecraft.getInstance().options.keyDown.isDown()){
            if (noctilure.getIsFlying() && noctilure.getHeightAboveGround() <= 1){
                noctilure.land();
            }
        }
        if (noctilure.getIsFlying()) {
            if (Minecraft.getInstance().options.keySprint.isDown()) {
                if (Minecraft.getInstance().options.keyUp.isDown()) noctilure.lowerEnergy(10);
                if (noctilure.energyWasZero) noctilure.setFlightSprinting(noctilure.getSprintEnergy() > 10);
                else noctilure.setFlightSprinting(noctilure.getSprintEnergy() > 0);
            } else {
                noctilure.setFlightSprinting(false);
            }
            if (noctilure.getSprintEnergy() < NoctilureEntity.MAX_SPRINT_ENERGY) {
                if (!Minecraft.getInstance().options.keyUp.isDown()) {
                    noctilure.increaseEnergy(2);
                } else if (!Minecraft.getInstance().options.keySprint.isDown()) {
                    noctilure.increaseEnergy(1);
                }
            }
        } else if (noctilure.getSprintEnergy() < NoctilureEntity.MAX_SPRINT_ENERGY) {
            noctilure.increaseEnergy(20);
        }
    }
    
    public static void noctilureMovement(NoctilureEntity noctilure, Player player) {
        if (noctilure.getIsFlying() && Minecraft.getInstance().options.keyUp.isDown()){
            noctilure.flyingTravel(player.getViewVector(1f).scale(
                    noctilure.getFlightSprinting() ? 1.1f : 0.5f).scale(noctilure.isInWater() ? 0.1f : 1f));
        }
    }
}
