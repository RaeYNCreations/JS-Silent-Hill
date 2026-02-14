package com.raeyncreations.silenthill.client.animation;

/**
 * Functional interface for defining animation transition conditions.
 * <p>
 * This interface allows you to define custom logic for when an animation
 * should transition from one state to another. Conditions can check entity
 * properties, movement state, health, or any other game logic.
 * </p>
 * 
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * // Transition to walk animation when entity is moving
 * TransitionCondition isMoving = (entity, deltaTime) -> {
 *     if (entity instanceof LivingEntity living) {
 *         return living.getDeltaMovement().horizontalDistance() > 0.01;
 *     }
 *     return false;
 * };
 * 
 * // Transition to attack when attacking
 * TransitionCondition isAttacking = (entity, deltaTime) -> {
 *     if (entity instanceof Mob mob) {
 *         return mob.isAggressive() && mob.getTarget() != null;
 *     }
 *     return false;
 * };
 * }</pre>
 * 
 * @author RaEyn Creations
 * @version 1.0
 * @since 3.0.0
 */
@FunctionalInterface
public interface TransitionCondition {
    
    /**
     * Tests whether the transition condition is met.
     * <p>
     * This method is called every tick to evaluate if a state transition
     * should occur. It should be lightweight and avoid expensive operations.
     * </p>
     * 
     * @param entity The entity being animated (typically a LivingEntity or Mob)
     * @param deltaTime Time elapsed since last update in seconds (typically 0.05s per tick)
     * @return {@code true} if the transition condition is satisfied, {@code false} otherwise
     * @throws ClassCastException if the entity is not of the expected type (should be caught in implementation)
     */
    boolean test(Object entity, float deltaTime);
    
    /**
     * Returns a condition that always evaluates to true.
     * Useful for unconditional transitions or testing.
     * 
     * @return A condition that always returns true
     */
    static TransitionCondition always() {
        return (entity, deltaTime) -> true;
    }
    
    /**
     * Returns a condition that always evaluates to false.
     * Useful as a placeholder or for disabled transitions.
     * 
     * @return A condition that always returns false
     */
    static TransitionCondition never() {
        return (entity, deltaTime) -> false;
    }
    
    /**
     * Returns a condition that inverts this condition's result.
     * 
     * @return A negated version of this condition
     */
    default TransitionCondition negate() {
        return (entity, deltaTime) -> !this.test(entity, deltaTime);
    }
    
    /**
     * Returns a composed condition that represents a logical AND
     * of this condition and another.
     * 
     * @param other The other condition to AND with
     * @return A condition that returns true only if both conditions are true
     * @throws NullPointerException if other is null
     */
    default TransitionCondition and(TransitionCondition other) {
        if (other == null) {
            throw new NullPointerException("Other condition cannot be null");
        }
        return (entity, deltaTime) -> 
            this.test(entity, deltaTime) && other.test(entity, deltaTime);
    }
    
    /**
     * Returns a composed condition that represents a logical OR
     * of this condition and another.
     * 
     * @param other The other condition to OR with
     * @return A condition that returns true if either condition is true
     * @throws NullPointerException if other is null
     */
    default TransitionCondition or(TransitionCondition other) {
        if (other == null) {
            throw new NullPointerException("Other condition cannot be null");
        }
        return (entity, deltaTime) -> 
            this.test(entity, deltaTime) || other.test(entity, deltaTime);
    }
}
