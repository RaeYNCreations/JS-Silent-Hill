package com.raeyncreations.silenthill.client.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents a single state node in the animation state machine.
 * <p>
 * Each state node contains:
 * <ul>
 *   <li>A unique name identifying the state</li>
 *   <li>A reference to the Bedrock animation to play</li>
 *   <li>A list of transitions to other states</li>
 *   <li>Optional playback speed multiplier</li>
 *   <li>Optional loop override</li>
 * </ul>
 * </p>
 * 
 * <h3>State Lifecycle:</h3>
 * <ol>
 *   <li><b>Entry</b> - State becomes active, animation starts playing</li>
 *   <li><b>Update</b> - State checks transitions each tick</li>
 *   <li><b>Transition</b> - Valid transition found, blend to new state begins</li>
 *   <li><b>Exit</b> - State becomes inactive</li>
 * </ol>
 * 
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * BedrockAnimation walkAnim = ...; // Load from file
 * 
 * AnimationStateNode walkState = new AnimationStateNode("walk", walkAnim);
 * walkState.setSpeed(1.0f);
 * 
 * // Add transition to idle when stopped
 * walkState.addTransition(new AnimationTransition(
 *     "idle",
 *     (entity, dt) -> entity.getDeltaMovement().horizontalDistance() < 0.01,
 *     0.2f,
 *     10
 * ));
 * 
 * // Add transition to run when sprinting
 * walkState.addTransition(new AnimationTransition(
 *     "run",
 *     (entity, dt) -> entity.isSprinting(),
 *     0.15f,
 *     20
 * ));
 * }</pre>
 * 
 * @author RaEyn Creations
 * @version 1.0
 * @since 3.0.0
 */
public class AnimationStateNode {
    
    private final String name;
    private final BedrockAnimation animation;
    private final List<AnimationTransition> transitions;
    private float speed;
    private Boolean loopOverride;
    
    /**
     * Creates a new animation state node.
     * 
     * @param name Unique identifier for this state (e.g., "idle", "walk", "attack")
     * @param animation The Bedrock animation to play in this state
     * @throws IllegalArgumentException if name is null/empty
     * @throws NullPointerException if animation is null
     */
    public AnimationStateNode(String name, BedrockAnimation animation) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("State name cannot be null or empty");
        }
        if (animation == null) {
            throw new NullPointerException("Animation cannot be null");
        }
        
        this.name = name;
        this.animation = animation;
        this.transitions = new CopyOnWriteArrayList<>(); // Thread-safe for concurrent updates
        this.speed = 1.0f;
        this.loopOverride = null; // null means use animation's default loop setting
    }
    
    /**
     * Adds a transition from this state to another state.
     * <p>
     * Transitions are evaluated in the order they were added, with priority
     * as the tiebreaker when multiple transitions are valid.
     * </p>
     * 
     * @param transition The transition to add
     * @throws NullPointerException if transition is null
     * @throws IllegalArgumentException if transition points to this same state
     */
    public void addTransition(AnimationTransition transition) {
        if (transition == null) {
            throw new NullPointerException("Transition cannot be null");
        }
        if (transition.getTargetState().equals(this.name)) {
            throw new IllegalArgumentException(
                "Self-transitions are not allowed. State '" + name + 
                "' cannot transition to itself.");
        }
        
        transitions.add(transition);
    }
    
    /**
     * Removes a transition from this state.
     * 
     * @param transition The transition to remove
     * @return {@code true} if the transition was removed, {@code false} if not found
     */
    public boolean removeTransition(AnimationTransition transition) {
        return transitions.remove(transition);
    }
    
    /**
     * Removes all transitions to a specific target state.
     * 
     * @param targetStateName The name of the target state
     * @return The number of transitions removed
     */
    public int removeTransitionsTo(String targetStateName) {
        int removed = 0;
        List<AnimationTransition> toRemove = new ArrayList<>();
        
        for (AnimationTransition transition : transitions) {
            if (transition.getTargetState().equals(targetStateName)) {
                toRemove.add(transition);
            }
        }
        
        for (AnimationTransition transition : toRemove) {
            if (transitions.remove(transition)) {
                removed++;
            }
        }
        
        return removed;
    }
    
    /**
     * Checks all transitions and returns the first valid one.
     * <p>
     * Transitions are evaluated based on their priority. If multiple transitions
     * have the same priority, they are checked in the order they were added.
     * </p>
     * 
     * @param entity The entity being animated
     * @param deltaTime Time elapsed since last update in seconds
     * @return The highest priority valid transition, or {@code null} if none are valid
     */
    public AnimationTransition checkTransitions(Object entity, float deltaTime) {
        if (transitions.isEmpty()) {
            return null;
        }
        
        AnimationTransition bestTransition = null;
        int highestPriority = Integer.MIN_VALUE;
        
        // Find the highest priority valid transition
        for (AnimationTransition transition : transitions) {
            if (transition.shouldTransition(entity, deltaTime)) {
                int priority = transition.getPriority();
                if (priority > highestPriority) {
                    highestPriority = priority;
                    bestTransition = transition;
                }
            }
        }
        
        return bestTransition;
    }
    
    /**
     * Gets the name of this state.
     * 
     * @return The state name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the Bedrock animation associated with this state.
     * 
     * @return The animation
     */
    public BedrockAnimation getAnimation() {
        return animation;
    }
    
    /**
     * Gets an unmodifiable list of all transitions from this state.
     * 
     * @return List of transitions (read-only)
     */
    public List<AnimationTransition> getTransitions() {
        return Collections.unmodifiableList(new ArrayList<>(transitions));
    }
    
    /**
     * Gets the number of transitions from this state.
     * 
     * @return The transition count
     */
    public int getTransitionCount() {
        return transitions.size();
    }
    
    /**
     * Gets the playback speed multiplier for this state.
     * 
     * @return Speed multiplier (1.0 = normal speed, 2.0 = double speed, etc.)
     */
    public float getSpeed() {
        return speed;
    }
    
    /**
     * Sets the playback speed multiplier for this state.
     * <p>
     * This allows you to speed up or slow down animations per-state.
     * For example, a "slow walk" state could use speed 0.5, while
     * a "fast run" state could use speed 1.5.
     * </p>
     * 
     * @param speed Speed multiplier (must be > 0)
     * @throws IllegalArgumentException if speed is <= 0
     */
    public void setSpeed(float speed) {
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be greater than 0");
        }
        this.speed = speed;
    }
    
    /**
     * Gets whether this state overrides the animation's loop setting.
     * 
     * @return The loop override, or {@code null} if using animation's default
     */
    public Boolean getLoopOverride() {
        return loopOverride;
    }
    
    /**
     * Sets whether to override the animation's loop setting.
     * <p>
     * By default ({@code null}), the state uses the animation's loop setting.
     * Setting this to {@code true} or {@code false} overrides that behavior.
     * This is useful when the same animation needs different loop behavior
     * in different states.
     * </p>
     * 
     * @param loop {@code true} to force looping, {@code false} to force non-looping,
     *             {@code null} to use the animation's default
     */
    public void setLoopOverride(Boolean loop) {
        this.loopOverride = loop;
    }
    
    /**
     * Determines if the animation should loop in this state.
     * 
     * @return {@code true} if looping (either override or animation default)
     */
    public boolean shouldLoop() {
        return loopOverride != null ? loopOverride : animation.isLoop();
    }
    
    /**
     * Checks if this state has any transitions.
     * 
     * @return {@code true} if at least one transition exists
     */
    public boolean hasTransitions() {
        return !transitions.isEmpty();
    }
    
    /**
     * Checks if this state has a transition to the specified target state.
     * 
     * @param targetStateName The target state name to check
     * @return {@code true} if a transition to that state exists
     */
    public boolean hasTransitionTo(String targetStateName) {
        for (AnimationTransition transition : transitions) {
            if (transition.getTargetState().equals(targetStateName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clears all transitions from this state.
     */
    public void clearTransitions() {
        transitions.clear();
    }
    
    @Override
    public String toString() {
        return String.format("AnimationStateNode{name='%s', animation='%s', transitions=%d, speed=%.2f}",
                           name, animation.getName(), transitions.size(), speed);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AnimationStateNode)) return false;
        AnimationStateNode other = (AnimationStateNode) obj;
        return name.equals(other.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
