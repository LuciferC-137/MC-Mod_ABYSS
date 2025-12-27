package wardentools.entity.utils;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.AnimationState;
import org.slf4j.Logger;

import java.util.Arrays;

public class AnimationSequence {
    private static final Logger LOGGER = LogUtils.getLogger();
    public int currentIndex = 0;
    public int currentTick = 0;
    private final int[] durations;
    private float[] speedMultipliers;
    private final AnimationState[] states;
    private boolean isValid = true;
    private boolean isRunning = false;
    private boolean isLooping = false;

    public AnimationSequence(int[] durations, AnimationState ... states) {
        if (durations.length != states.length) {
            LOGGER.warn("AnimationSequence created with mismatched lengths");
            this.isValid = false;
        }

        this.durations = durations.clone();
        this.states = states.clone();
        this.speedMultipliers = new float[states.length];
        Arrays.fill(this.speedMultipliers, 1.0f);
    }

    public static AnimationSequence loop(int[] durations, AnimationState ... states) {
        AnimationSequence sequence = new AnimationSequence(durations, states);
        sequence.makeLooping();
        return sequence;
    }

    public void setSpeeds(float speedMultiplier) {
        setSpeeds(new float[] { speedMultiplier });
    }

    public void setSpeeds(float... speedMultipliers) {
        this.speedMultipliers = speedMultipliers.clone();
    }

    private void makeLooping() {
        this.isLooping = true;
    }

    public void tick(int tickCount) {
        if (!isValid || !isRunning || states.length == 0) return;
        currentTick++;
        if (currentTick >= durations[currentIndex]) {
            if (!isLooping && currentIndex >= states.length - 1) {
                this.stop();
                return;
            }
            currentIndex = (currentIndex + 1) % states.length;
            currentTick = 0;
        }
        for (int i = 0; i < states.length; i++) {
            AnimationState state = states[i];
            if (state != null) {
                state.animateWhen(currentIndex==i, tickCount);
            }
        }
    }

    public void start() {
        if (!isValid) return;
        if (states.length == 0) return;
        currentIndex = 0;
        currentTick = 0;
        isRunning = true;
    }

    public void stop() {
        if (states.length == 0) return;
        if (states[currentIndex] != null) states[currentIndex].stop();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
