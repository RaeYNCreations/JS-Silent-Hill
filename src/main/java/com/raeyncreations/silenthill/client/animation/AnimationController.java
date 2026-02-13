package com.raeyncreations.silenthill.client.animation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Main animation state machine controller that manages animation states and transitions.
 * <p>
 * The AnimationController is the core of the state-based animation system. It:
 * <ul>
 *   <li>Manages multiple named animation states</li>
 *   <li>Automatically evaluates and executes transitions</li>
 *   <li>Handles smooth blending between animation states</li>
 *   <li>Provides thread-safe state updates</li>
 *   <li>Tracks current state and playback time</li>
 * </ul>
 * </p>
 * 
 * <h3>Basic Setup:</h3>
 * <pre>{@code
 * // Create controller
 * AnimationController controller = new AnimationController("mob_controller");
 * 
 * // Load animations
 * BedrockAnimation idleAnim = BedrockAnimationParser.parseAnimation(...);
 * BedrockAnimation walkAnim = BedrockAnimationParser.parseAnimation(...);
 * BedrockAnimation attackAnim = BedrockAnimationParser.parseAnimation(...);
 * 
 * // Create states
 * AnimationStateNode idle = new AnimationStateNode("idle", idleAnim);
 * AnimationStateNode walk = new AnimationStateNode("walk", walkAnim);
 * AnimationStateNode attack = new AnimationStateNode("attack", attackAnim);
 * 
 * // Add transitions
 * idle.addTransition(new AnimationTransition("walk", 
 *     (entity, dt) -> entity.getDeltaMovement().horizontalDistance() > 0.01, 0.2f));
 * walk.addTransition(new AnimationTransition("idle",
 *     (entity, dt) -> entity.getDeltaMovement().horizontalDistance() <= 0.01, 0.2f));
 * walk.addTransition(new AnimationTransition("attack",
 *     (entity, dt) -> entity.isAggressive(), 0.1f, 100)); // High priority
 * attack.addTransition(new AnimationTransition("idle",
 *     (entity, dt) -> !entity.isAggressive(), 0.3f));
 * 
 * // Add states to controller
 * controller.addState(idle);
 * controller.addState(walk);
 * controller.addState(attack);
 * 
 * // Set initial state
 * controller.transitionTo("idle", 0.0f);
 * 
 * // Update each tick (in render or entity update)
 * controller.update(entity, deltaTime);
 * 
 * // Get current animation data
 * BedrockAnimation currentAnim = controller.getCurrentAnimation();
 * float animTime = controller.getCurrentTime();
 * }</pre>
 * 
 * <h3>Thread Safety:</h3>
 * <p>
 * This controller is thread-safe and can be safely accessed from multiple threads.
 * State modifications use write locks, while reads use read locks, allowing
 * concurrent reads but exclusive writes.
 * </p>
 * 
 * @author RaEyn Creations
 * @version 1.0
 * @since 3.0.0
 */
public class AnimationController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimationController.class);
    
    private final String name;
    private final Map<String, AnimationStateNode> states;
    private final ReadWriteLock lock;
    
    // Current state tracking
    private AnimationStateNode currentState;
    private float currentTime;
    private boolean isPlaying;
    
    // Transition tracking
    private AnimationStateNode previousState;
    private float previousTime;
    private float blendTime;
    private float blendDuration;
    
    /**
     * Creates a new animation controller with the specified name.
     * 
     * @param name Unique identifier for this controller (for debugging/logging)
     * @throws IllegalArgumentException if name is null or empty
     */
    public AnimationController(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Controller name cannot be null or empty");
        }
        
        this.name = name;
        this.states = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.currentTime = 0.0f;
        this.isPlaying = false;
        this.blendTime = 0.0f;
        this.blendDuration = 0.0f;
    }
    
    /**
     * Adds a state to this controller.
     * <p>
     * If a state with the same name already exists, it will be replaced.
     * </p>
     * 
     * @param state The state to add
     * @throws NullPointerException if state is null
     */
    public void addState(AnimationStateNode state) {
        if (state == null) {
            throw new NullPointerException("State cannot be null");
        }
        
        lock.writeLock().lock();
        try {
            states.put(state.getName(), state);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Removes a state from this controller.
     * <p>
     * If the removed state is currently active, the controller will stop playing.
     * </p>
     * 
     * @param stateName The name of the state to remove
     * @return {@code true} if the state was removed, {@code false} if not found
     */
    public boolean removeState(String stateName) {
        lock.writeLock().lock();
        try {
            AnimationStateNode removed = states.remove(stateName);
            if (removed != null) {
                // If we removed the current state, stop playing
                if (currentState == removed) {
                    currentState = null;
                    isPlaying = false;
                }
                if (previousState == removed) {
                    previousState = null;
                }
                return true;
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Gets a state by name.
     * 
     * @param stateName The name of the state
     * @return The state, or {@code null} if not found
     */
    public AnimationStateNode getState(String stateName) {
        lock.readLock().lock();
        try {
            return states.get(stateName);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Checks if this controller has a state with the given name.
     * 
     * @param stateName The state name to check
     * @return {@code true} if the state exists
     */
    public boolean hasState(String stateName) {
        lock.readLock().lock();
        try {
            return states.containsKey(stateName);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the names of all states in this controller.
     * 
     * @return Set of state names
     */
    public Set<String> getStateNames() {
        lock.readLock().lock();
        try {
            return Set.copyOf(states.keySet());
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Manually transitions to a specific state.
     * <p>
     * This overrides any automatic transitions and immediately begins
     * transitioning to the specified state.
     * </p>
     * 
     * @param stateName The name of the state to transition to
     * @param blendDuration Duration in seconds for the transition blend
     * @return {@code true} if transition started, {@code false} if state not found
     */
    public boolean transitionTo(String stateName, float blendDuration) {
        lock.writeLock().lock();
        try {
            AnimationStateNode targetState = states.get(stateName);
            if (targetState == null) {
                LOGGER.warn("AnimationController '{}': Cannot transition to unknown state '{}'", 
                           name, stateName);
                return false;
            }
            
            // Don't transition if already in this state and not transitioning
            if (currentState == targetState && previousState == null) {
                return true;
            }
            
            // Set up transition
            this.previousState = this.currentState;
            this.previousTime = this.currentTime;
            this.currentState = targetState;
            this.currentTime = 0.0f;
            this.blendTime = 0.0f;
            this.blendDuration = Math.max(0, blendDuration);
            this.isPlaying = true;
            
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Updates the animation controller for one frame.
     * <p>
     * This method:
     * <ol>
     *   <li>Advances animation playback time</li>
     *   <li>Updates blend transitions</li>
     *   <li>Checks for and executes state transitions</li>
     *   <li>Handles animation looping</li>
     * </ol>
     * </p>
     * 
     * <p><b>Thread Safety:</b> This method is thread-safe and can be called from
     * any thread, though it's typically called from the game tick or render thread.</p>
     * 
     * @param entity The entity being animated (used for transition conditions)
     * @param deltaTime Time elapsed since last update in seconds
     */
    public void update(Object entity, float deltaTime) {
        lock.writeLock().lock();
        try {
            if (!isPlaying || currentState == null) {
                return;
            }
            
            // Update animation time
            float speed = currentState.getSpeed();
            currentTime += deltaTime * speed;
            
            // Update blend transition
            if (previousState != null) {
                blendTime += deltaTime;
                if (blendTime >= blendDuration) {
                    // Transition complete
                    previousState = null;
                    previousTime = 0.0f;
                    blendTime = 0.0f;
                }
            }
            
            // Handle animation looping
            BedrockAnimation animation = currentState.getAnimation();
            float animLength = animation.getAnimationLength();
            boolean shouldLoop = currentState.shouldLoop();
            
            if (currentTime >= animLength) {
                if (shouldLoop) {
                    currentTime = currentTime % animLength;
                } else {
                    currentTime = animLength;
                    // Could auto-transition to next state here if desired
                }
            }
            
            // Check for state transitions (only if not currently transitioning)
            if (previousState == null) {
                AnimationTransition validTransition = currentState.checkTransitions(entity, deltaTime);
                if (validTransition != null) {
                    String targetStateName = validTransition.getTargetState();
                    float transitionBlend = validTransition.getBlendDuration();
                    transitionTo(targetStateName, transitionBlend);
                }
            }
            
        } catch (Exception e) {
            LOGGER.error("Error updating AnimationController '{}': {}", name, e.getMessage(), e);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Gets the current state name.
     * 
     * @return The current state name, or {@code null} if no state is active
     */
    public String getCurrentStateName() {
        lock.readLock().lock();
        try {
            return currentState != null ? currentState.getName() : null;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the current state node.
     * 
     * @return The current state, or {@code null} if no state is active
     */
    public AnimationStateNode getCurrentState() {
        lock.readLock().lock();
        try {
            return currentState;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the current animation from the active state.
     * 
     * @return The current animation, or {@code null} if no state is active
     */
    public BedrockAnimation getCurrentAnimation() {
        lock.readLock().lock();
        try {
            return currentState != null ? currentState.getAnimation() : null;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the current playback time in the active animation.
     * 
     * @return Current time in seconds, or 0 if no state is active
     */
    public float getCurrentTime() {
        lock.readLock().lock();
        try {
            return currentTime;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Sets the current playback time.
     * <p>
     * This is useful for syncing animations or seeking to a specific point.
     * </p>
     * 
     * @param time The time to set in seconds
     */
    public void setCurrentTime(float time) {
        lock.writeLock().lock();
        try {
            this.currentTime = Math.max(0, time);
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Checks if the controller is currently playing.
     * 
     * @return {@code true} if playing
     */
    public boolean isPlaying() {
        lock.readLock().lock();
        try {
            return isPlaying;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Starts or resumes playback.
     */
    public void play() {
        lock.writeLock().lock();
        try {
            this.isPlaying = true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Pauses playback without changing the current state.
     */
    public void pause() {
        lock.writeLock().lock();
        try {
            this.isPlaying = false;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Stops playback and resets to no active state.
     */
    public void stop() {
        lock.writeLock().lock();
        try {
            this.isPlaying = false;
            this.currentState = null;
            this.previousState = null;
            this.currentTime = 0.0f;
            this.previousTime = 0.0f;
            this.blendTime = 0.0f;
            this.blendDuration = 0.0f;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Checks if currently transitioning between states.
     * 
     * @return {@code true} if in the middle of a blend transition
     */
    public boolean isTransitioning() {
        lock.readLock().lock();
        try {
            return previousState != null;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the current blend weight during transitions.
     * 
     * @return Blend weight from 0 (previous state) to 1 (current state),
     *         or 1.0 if not transitioning
     */
    public float getBlendWeight() {
        lock.readLock().lock();
        try {
            if (previousState == null || blendDuration <= 0) {
                return 1.0f;
            }
            return Math.min(1.0f, blendTime / blendDuration);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the previous state during a transition.
     * 
     * @return The previous state, or {@code null} if not transitioning
     */
    public AnimationStateNode getPreviousState() {
        lock.readLock().lock();
        try {
            return previousState;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the playback time in the previous animation during a transition.
     * 
     * @return Previous animation time, or 0 if not transitioning
     */
    public float getPreviousTime() {
        lock.readLock().lock();
        try {
            return previousTime;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the name of this controller.
     * 
     * @return The controller name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the number of states in this controller.
     * 
     * @return State count
     */
    public int getStateCount() {
        lock.readLock().lock();
        try {
            return states.size();
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Clears all states from this controller and stops playback.
     */
    public void clearAllStates() {
        lock.writeLock().lock();
        try {
            states.clear();
            stop();
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            String currentStateName = currentState != null ? currentState.getName() : "none";
            return String.format("AnimationController{name='%s', currentState='%s', " +
                               "time=%.2fs, states=%d, playing=%b, transitioning=%b}",
                               name, currentStateName, currentTime, states.size(), 
                               isPlaying, previousState != null);
        } finally {
            lock.readLock().unlock();
        }
    }
}
