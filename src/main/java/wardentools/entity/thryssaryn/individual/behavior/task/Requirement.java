package wardentools.entity.thryssaryn.individual.behavior.task;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

/**
 * Requirements are static objects representing a simple requirement for a task.
 * They exist independently of the entities, therefore the entity must be passed
 * to evaluate the requirement.
 */
public interface Requirement {
    int MAX_DIFFICULTY = 1;
    int MIN_DIFFICULTY = 0;

    static float clampDifficulty(float difficulty) {
        return Math.min(Math.max(difficulty, MIN_DIFFICULTY), MAX_DIFFICULTY);
    }

    /**
     * Returns the difficulty of the requirement, from 0 to 1.
     * If a requirement is not feasible, returns 100.
     * @param entity the thryssaryn for which the requirement is evaluated
     * @return difficulty of the requirement
     */
    float getDifficulty(ThryssarynEntity entity);

    /**
     * Returns the estimated time, in tick, to meet this requirement.
     * @param entity the thryssaryn for which the requirement is evaluated
     * @return estimated time to meet the requirement
     */
    int getEstimatedTime(ThryssarynEntity entity);

    /**
     * Returns whether the requirement is feasible for a given entity.
     * @param entity the thryssaryn for which the requirement is evaluated
     * @return default: difficulty < 100
     */
    default boolean canRealize(ThryssarynEntity entity) {
        return this.getDifficulty(entity) < 100;
    }

    /**
     * Method to check if the requirement is met for a given entity.
     * @param entity the thryssaryn for which the requirement is evaluated
     * @return whether the requirement is met
     */
    boolean isMet(ThryssarynEntity entity);
}
