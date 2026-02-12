package com.raeyncreations.silenthill.client.animation;

/**
 * Tracks the animation state for an entity instance.
 * Manages current animation, playback time, and blend transitions.
 */
public class AnimationState {
    private String currentAnimation;
    private float elapsedTime;
    private boolean playing;
    private float blendWeight;
    private float blendSpeed;
    
    // Transition state
    private String previousAnimation;
    private float previousElapsedTime;
    private float transitionTime;
    private float transitionDuration;
    
    public AnimationState() {
        this.currentAnimation = null;
        this.elapsedTime = 0;
        this.playing = false;
        this.blendWeight = 1.0f;
        this.blendSpeed = 1.0f;
        this.transitionDuration = 0.2f; // Default 200ms transition
    }
    
    /**
     * Start playing an animation.
     * @param animationName The name of the animation to play
     * @param restart If true, restart from beginning even if already playing
     */
    public void playAnimation(String animationName, boolean restart) {
        if (restart || !animationName.equals(currentAnimation)) {
            this.previousAnimation = this.currentAnimation;
            this.previousElapsedTime = this.elapsedTime;
            this.currentAnimation = animationName;
            this.elapsedTime = 0;
            this.transitionTime = 0;
        }
        this.playing = true;
    }
    
    /**
     * Stop the current animation.
     */
    public void stopAnimation() {
        this.playing = false;
    }
    
    /**
     * Update the animation state.
     * @param deltaTime Time elapsed since last update in seconds
     */
    public void update(float deltaTime) {
        if (!playing) {
            return;
        }
        
        // Update elapsed time
        elapsedTime += deltaTime * blendSpeed;
        
        // Update transition
        if (previousAnimation != null) {
            transitionTime += deltaTime;
            if (transitionTime >= transitionDuration) {
                // Transition complete
                previousAnimation = null;
                blendWeight = 1.0f;
            } else {
                // Calculate blend weight (0 = previous, 1 = current)
                blendWeight = transitionTime / transitionDuration;
            }
        } else {
            blendWeight = 1.0f;
        }
    }
    
    /**
     * Get the current animation name.
     * @return The animation name or null if none
     */
    public String getCurrentAnimation() {
        return currentAnimation;
    }
    
    /**
     * Get the elapsed time in the current animation.
     * @return Elapsed time in seconds
     */
    public float getElapsedTime() {
        return elapsedTime;
    }
    
    /**
     * Set the elapsed time (useful for syncing animations).
     * @param time The time to set in seconds
     */
    public void setElapsedTime(float time) {
        this.elapsedTime = time;
    }
    
    /**
     * Check if an animation is currently playing.
     * @return True if playing
     */
    public boolean isPlaying() {
        return playing;
    }
    
    /**
     * Get the current blend weight for transitions.
     * @return Weight from 0 (previous animation) to 1 (current animation)
     */
    public float getBlendWeight() {
        return blendWeight;
    }
    
    /**
     * Set the blend weight manually.
     * @param weight The weight to set (0-1)
     */
    public void setBlendWeight(float weight) {
        this.blendWeight = Math.max(0, Math.min(1, weight));
    }
    
    /**
     * Get the playback speed multiplier.
     * @return Speed multiplier (1.0 = normal speed)
     */
    public float getBlendSpeed() {
        return blendSpeed;
    }
    
    /**
     * Set the playback speed multiplier.
     * @param speed Speed multiplier (1.0 = normal, 2.0 = double speed, etc.)
     */
    public void setBlendSpeed(float speed) {
        this.blendSpeed = Math.max(0, speed);
    }
    
    /**
     * Get the previous animation name (during transition).
     * @return Previous animation name or null
     */
    public String getPreviousAnimation() {
        return previousAnimation;
    }
    
    /**
     * Get the elapsed time of the previous animation (during transition).
     * @return Previous elapsed time in seconds
     */
    public float getPreviousElapsedTime() {
        return previousElapsedTime;
    }
    
    /**
     * Check if currently transitioning between animations.
     * @return True if in transition
     */
    public boolean isTransitioning() {
        return previousAnimation != null;
    }
    
    /**
     * Set the duration for animation transitions.
     * @param duration Duration in seconds
     */
    public void setTransitionDuration(float duration) {
        this.transitionDuration = Math.max(0, duration);
    }
    
    /**
     * Get the transition duration.
     * @return Duration in seconds
     */
    public float getTransitionDuration() {
        return transitionDuration;
    }
    
    /**
     * Reset the animation state to defaults.
     */
    public void reset() {
        this.currentAnimation = null;
        this.previousAnimation = null;
        this.elapsedTime = 0;
        this.previousElapsedTime = 0;
        this.playing = false;
        this.blendWeight = 1.0f;
        this.transitionTime = 0;
    }
}
