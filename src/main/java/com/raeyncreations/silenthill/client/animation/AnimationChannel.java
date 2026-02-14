package com.raeyncreations.silenthill.client.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents animation data for a single bone with keyframe interpolation.
 * Handles linear interpolation between keyframes for smooth animation.
 */
public class AnimationChannel {
    private final String boneName;
    private final List<AnimationKeyframe> keyframes;
    
    public AnimationChannel(String boneName) {
        this.boneName = boneName;
        this.keyframes = new ArrayList<>();
    }
    
    public void addKeyframe(AnimationKeyframe keyframe) {
        keyframes.add(keyframe);
        // Keep keyframes sorted by time for efficient lookup
        Collections.sort(keyframes, (a, b) -> Float.compare(a.getTime(), b.getTime()));
    }
    
    public String getBoneName() {
        return boneName;
    }
    
    public List<AnimationKeyframe> getKeyframes() {
        return keyframes;
    }
    
    /**
     * Get the interpolated rotation at a specific time point using quaternion slerp.
     * This provides smoother rotation interpolation than linear interpolation.
     * @param time The animation time in seconds
     * @return Interpolated quaternion rotation
     */
    public Quaternion getQuaternionAt(float time) {
        if (keyframes.isEmpty()) {
            return Quaternion.identity();
        }
        
        // If before first keyframe, return first keyframe rotation
        if (time <= keyframes.get(0).getTime()) {
            float[] rot = keyframes.get(0).getRotation();
            return Quaternion.fromEulerDegrees(rot[0], rot[1], rot[2]);
        }
        
        // If after last keyframe, return last keyframe rotation
        if (time >= keyframes.get(keyframes.size() - 1).getTime()) {
            float[] rot = keyframes.get(keyframes.size() - 1).getRotation();
            return Quaternion.fromEulerDegrees(rot[0], rot[1], rot[2]);
        }
        
        // Find the two keyframes to interpolate between
        for (int i = 0; i < keyframes.size() - 1; i++) {
            AnimationKeyframe current = keyframes.get(i);
            AnimationKeyframe next = keyframes.get(i + 1);
            
            if (time >= current.getTime() && time <= next.getTime()) {
                // Calculate interpolation factor
                float duration = next.getTime() - current.getTime();
                float progress = (time - current.getTime()) / duration;
                
                // Convert Euler angles to quaternions
                float[] currentRot = current.getRotation();
                float[] nextRot = next.getRotation();
                
                Quaternion q1 = Quaternion.fromEulerDegrees(currentRot[0], currentRot[1], currentRot[2]);
                Quaternion q2 = Quaternion.fromEulerDegrees(nextRot[0], nextRot[1], nextRot[2]);
                
                // Perform slerp interpolation
                return Quaternion.slerp(q1, q2, progress);
            }
        }
        
        // Fallback
        return Quaternion.identity();
    }
    
    /**
     * Get the interpolated rotation at a specific time point.
     * @param time The animation time in seconds
     * @return Interpolated rotation [x, y, z] in degrees
     */
    public float[] getRotationAtTime(float time) {
        return getValueAtTime(time, ValueType.ROTATION);
    }
    
    /**
     * Get the interpolated position at a specific time point.
     * @param time The animation time in seconds
     * @return Interpolated position [x, y, z]
     */
    public float[] getPositionAtTime(float time) {
        return getValueAtTime(time, ValueType.POSITION);
    }
    
    /**
     * Get the interpolated scale at a specific time point.
     * @param time The animation time in seconds
     * @return Interpolated scale [x, y, z]
     */
    public float[] getScaleAtTime(float time) {
        return getValueAtTime(time, ValueType.SCALE);
    }
    
    private float[] getValueAtTime(float time, ValueType type) {
        if (keyframes.isEmpty()) {
            return type == ValueType.SCALE ? new float[]{1, 1, 1} : new float[]{0, 0, 0};
        }
        
        // If before first keyframe, return first keyframe value
        if (time <= keyframes.get(0).getTime()) {
            return getValue(keyframes.get(0), type);
        }
        
        // If after last keyframe, return last keyframe value
        if (time >= keyframes.get(keyframes.size() - 1).getTime()) {
            return getValue(keyframes.get(keyframes.size() - 1), type);
        }
        
        // Find the two keyframes to interpolate between
        for (int i = 0; i < keyframes.size() - 1; i++) {
            AnimationKeyframe current = keyframes.get(i);
            AnimationKeyframe next = keyframes.get(i + 1);
            
            if (time >= current.getTime() && time <= next.getTime()) {
                // Calculate interpolation factor
                float duration = next.getTime() - current.getTime();
                float progress = (time - current.getTime()) / duration;
                
                // Lerp between the two keyframes
                float[] currentValue = getValue(current, type);
                float[] nextValue = getValue(next, type);
                return AnimationKeyframe.lerp(currentValue, nextValue, progress);
            }
        }
        
        // Fallback (should not reach here)
        return type == ValueType.SCALE ? new float[]{1, 1, 1} : new float[]{0, 0, 0};
    }
    
    private float[] getValue(AnimationKeyframe keyframe, ValueType type) {
        switch (type) {
            case ROTATION:
                return keyframe.getRotation().clone();
            case POSITION:
                return keyframe.getPosition().clone();
            case SCALE:
                return keyframe.getScale().clone();
            default:
                return new float[]{0, 0, 0};
        }
    }
    
    private enum ValueType {
        ROTATION, POSITION, SCALE
    }
}
