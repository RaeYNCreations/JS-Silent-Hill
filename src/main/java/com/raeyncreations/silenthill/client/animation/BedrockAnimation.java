package com.raeyncreations.silenthill.client.animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a complete Bedrock animation with all bone channels.
 * Contains metadata like animation length and loop behavior.
 */
public class BedrockAnimation {
    private final String name;
    private final float animationLength;
    private final boolean loop;
    private final Map<String, AnimationChannel> boneChannels;
    
    public BedrockAnimation(String name, float animationLength, boolean loop) {
        this.name = name;
        this.animationLength = animationLength;
        this.loop = loop;
        this.boneChannels = new HashMap<>();
    }
    
    public void addBoneChannel(AnimationChannel channel) {
        boneChannels.put(channel.getBoneName(), channel);
    }
    
    public String getName() {
        return name;
    }
    
    public float getAnimationLength() {
        return animationLength;
    }
    
    public boolean isLoop() {
        return loop;
    }
    
    public Map<String, AnimationChannel> getBoneChannels() {
        return boneChannels;
    }
    
    /**
     * Get the animation channel for a specific bone.
     * @param boneName The name of the bone
     * @return The animation channel or null if not found
     */
    public AnimationChannel getChannelForBone(String boneName) {
        return boneChannels.get(boneName);
    }
    
    /**
     * Get the rotation for a bone at a specific time.
     * @param boneName The bone name
     * @param time The animation time in seconds
     * @return Rotation [x, y, z] in degrees, or default [0, 0, 0] if bone not found
     */
    public float[] getBoneRotation(String boneName, float time) {
        AnimationChannel channel = boneChannels.get(boneName);
        if (channel != null) {
            return channel.getRotationAtTime(time);
        }
        return new float[]{0, 0, 0};
    }
    
    /**
     * Get the position for a bone at a specific time.
     * @param boneName The bone name
     * @param time The animation time in seconds
     * @return Position [x, y, z], or default [0, 0, 0] if bone not found
     */
    public float[] getBonePosition(String boneName, float time) {
        AnimationChannel channel = boneChannels.get(boneName);
        if (channel != null) {
            return channel.getPositionAtTime(time);
        }
        return new float[]{0, 0, 0};
    }
    
    /**
     * Get the scale for a bone at a specific time.
     * @param boneName The bone name
     * @param time The animation time in seconds
     * @return Scale [x, y, z], or default [1, 1, 1] if bone not found
     */
    public float[] getBoneScale(String boneName, float time) {
        AnimationChannel channel = boneChannels.get(boneName);
        if (channel != null) {
            return channel.getScaleAtTime(time);
        }
        return new float[]{1, 1, 1};
    }
    
    /**
     * Normalize the time value based on loop behavior.
     * @param time The raw animation time
     * @return The normalized time within animation bounds
     */
    public float normalizeTime(float time) {
        if (animationLength <= 0) {
            return 0;
        }
        
        if (loop) {
            // Loop the animation
            return time % animationLength;
        } else {
            // Clamp to animation length
            return Math.min(time, animationLength);
        }
    }
}
