package com.raeyncreations.silenthill.client.animation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a transition between two animation states in the state machine.
 * <p>
 * A transition defines how and when the animation controller should move from
 * one state to another. Each transition has:
 * <ul>
 *   <li>A target state to transition to</li>
 *   <li>A condition that determines when the transition should occur</li>
 *   <li>A blend duration for smooth interpolation between states</li>
 *   <li>A priority for resolving conflicts when multiple transitions are valid</li>
 * </ul>
 * </p>
 * 
 * <h3>Priority System:</h3>
 * <p>
 * When multiple transitions from a state have valid conditions, the transition
 * with the highest priority is chosen. This allows you to create hierarchical
 * state machines where specific conditions override general ones.
 * </p>
 * 
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * // High priority transition to death animation
 * AnimationTransition toDeath = new AnimationTransition(
 *     "death",
 *     (entity, dt) -> entity instanceof LivingEntity living && !living.isAlive(),
 *     0.1f,  // Quick transition
 *     100    // Highest priority
 * );
 * 
 * // Medium priority transition to attack
 * AnimationTransition toAttack = new AnimationTransition(
 *     "attack",
 *     (entity, dt) -> entity instanceof Mob mob && mob.isAggressive(),
 *     0.2f,
 *     50
 * );
 * 
 * // Low priority transition to walk
 * AnimationTransition toWalk = new AnimationTransition(
 *     "walk",
 *     (entity, dt) -> entity.getDeltaMovement().horizontalDistance() > 0.01,
 *     0.3f,
 *     10
 * );
 * }</pre>
 * 
 * @author RaEyn Creations
 * @version 1.0
 * @since 3.0.0
 */
public class AnimationTransition {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimationTransition.class);
    
    private final String targetState;
    private final TransitionCondition condition;
    private final float blendDuration;
    private final int priority;
    
    /**
     * Creates a new animation transition with default priority (0).
     * 
     * @param targetState The name of the state to transition to
     * @param condition The condition that must be met for this transition
     * @param blendDuration Duration in seconds for blending between states
     * @throws IllegalArgumentException if targetState is null/empty or blendDuration is negative
     * @throws NullPointerException if condition is null
     */
    public AnimationTransition(String targetState, TransitionCondition condition, float blendDuration) {
        this(targetState, condition, blendDuration, 0);
    }
    
    /**
     * Creates a new animation transition with specified priority.
     * 
     * @param targetState The name of the state to transition to
     * @param condition The condition that must be met for this transition
     * @param blendDuration Duration in seconds for blending between states (must be >= 0)
     * @param priority Priority for resolving conflicts (higher = higher priority)
     * @throws IllegalArgumentException if targetState is null/empty or blendDuration is negative
     * @throws NullPointerException if condition is null
     */
    public AnimationTransition(String targetState, TransitionCondition condition, 
                              float blendDuration, int priority) {
        if (targetState == null || targetState.trim().isEmpty()) {
            throw new IllegalArgumentException("Target state cannot be null or empty");
        }
        if (condition == null) {
            throw new NullPointerException("Transition condition cannot be null");
        }
        if (blendDuration < 0) {
            throw new IllegalArgumentException("Blend duration cannot be negative");
        }
        
        this.targetState = targetState;
        this.condition = condition;
        this.blendDuration = blendDuration;
        this.priority = priority;
    }
    
    /**
     * Tests whether this transition should occur.
     * <p>
     * This method delegates to the transition condition to determine if
     * the transition is valid for the current entity state.
     * </p>
     * 
     * @param entity The entity being animated
     * @param deltaTime Time elapsed since last update in seconds
     * @return {@code true} if the transition condition is met, {@code false} otherwise
     */
    public boolean shouldTransition(Object entity, float deltaTime) {
        try {
            return condition.test(entity, deltaTime);
        } catch (Exception e) {
            // Log error but don't crash - return false to prevent transition
            LOGGER.error("Error evaluating transition condition to state '{}': {}", 
                        targetState, e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Gets the name of the target state for this transition.
     * 
     * @return The target state name
     */
    public String getTargetState() {
        return targetState;
    }
    
    /**
     * Gets the blend duration for this transition.
     * <p>
     * The blend duration determines how long the transition takes to complete,
     * creating a smooth interpolation between the source and target animations.
     * A duration of 0 means an instant transition with no blending.
     * </p>
     * 
     * @return Blend duration in seconds
     */
    public float getBlendDuration() {
        return blendDuration;
    }
    
    /**
     * Gets the priority of this transition.
     * <p>
     * When multiple transitions from the same state have valid conditions,
     * the one with the highest priority is selected. If priorities are equal,
     * the first valid transition found is used.
     * </p>
     * 
     * @return The transition priority (higher values = higher priority)
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * Gets the transition condition.
     * <p>
     * This is provided for inspection purposes. The condition should
     * normally be evaluated via {@link #shouldTransition(Object, float)}.
     * </p>
     * 
     * @return The transition condition
     */
    public TransitionCondition getCondition() {
        return condition;
    }
    
    @Override
    public String toString() {
        return String.format("AnimationTransition{target='%s', priority=%d, blendDuration=%.2fs}",
                           targetState, priority, blendDuration);
    }
    
    /**
     * Builder class for creating AnimationTransition instances with a fluent API.
     * 
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * AnimationTransition transition = AnimationTransition.builder()
     *     .targetState("attack")
     *     .condition((entity, dt) -> entity.isAggressive())
     *     .blendDuration(0.2f)
     *     .priority(50)
     *     .build();
     * }</pre>
     */
    public static class Builder {
        private String targetState;
        private TransitionCondition condition;
        private float blendDuration = 0.2f;
        private int priority = 0;
        
        /**
         * Sets the target state for the transition.
         * 
         * @param targetState The state to transition to
         * @return This builder for chaining
         */
        public Builder targetState(String targetState) {
            this.targetState = targetState;
            return this;
        }
        
        /**
         * Sets the transition condition.
         * 
         * @param condition The condition that triggers this transition
         * @return This builder for chaining
         */
        public Builder condition(TransitionCondition condition) {
            this.condition = condition;
            return this;
        }
        
        /**
         * Sets the blend duration.
         * 
         * @param blendDuration Duration in seconds for blending
         * @return This builder for chaining
         */
        public Builder blendDuration(float blendDuration) {
            this.blendDuration = blendDuration;
            return this;
        }
        
        /**
         * Sets the transition priority.
         * 
         * @param priority Priority value (higher = higher priority)
         * @return This builder for chaining
         */
        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }
        
        /**
         * Builds the AnimationTransition instance.
         * 
         * @return A new AnimationTransition
         * @throws IllegalStateException if required fields are not set
         */
        public AnimationTransition build() {
            if (targetState == null) {
                throw new IllegalStateException("Target state must be set");
            }
            if (condition == null) {
                throw new IllegalStateException("Condition must be set");
            }
            return new AnimationTransition(targetState, condition, blendDuration, priority);
        }
    }
    
    /**
     * Creates a new builder for constructing AnimationTransition instances.
     * 
     * @return A new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
