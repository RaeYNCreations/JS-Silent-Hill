package com.raeyncreations.silenthill.client.animation;

/**
 * Represents a single keyframe in a Bedrock animation.
 * Contains rotation, position, and scale values for a specific time point.
 */
public class AnimationKeyframe {
    private final float time;
    private final float[] rotation;  // [x, y, z] in degrees
    private final float[] position;  // [x, y, z] offset
    private final float[] scale;     // [x, y, z] scale multipliers
    
    public AnimationKeyframe(float time, float[] rotation, float[] position, float[] scale) {
        this.time = time;
        this.rotation = rotation != null ? rotation : new float[]{0, 0, 0};
        this.position = position != null ? position : new float[]{0, 0, 0};
        this.scale = scale != null ? scale : new float[]{1, 1, 1};
    }
    
    public float getTime() {
        return time;
    }
    
    public float[] getRotation() {
        return rotation;
    }
    
    public float[] getPosition() {
        return position;
    }
    
    public float[] getScale() {
        return scale;
    }
    
    /**
     * Linearly interpolate between this keyframe and the next one
     */
    public static float[] lerp(float[] a, float[] b, float t) {
        return new float[]{
            a[0] + (b[0] - a[0]) * t,
            a[1] + (b[1] - a[1]) * t,
            a[2] + (b[2] - a[2]) * t
        };
    }
}
