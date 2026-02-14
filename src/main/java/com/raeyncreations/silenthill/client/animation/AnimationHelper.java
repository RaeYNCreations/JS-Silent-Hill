package com.raeyncreations.silenthill.client.animation;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/**
 * Helper class to apply Bedrock animations to Minecraft model parts.
 * Supports both Euler angle and quaternion-based rotation interpolation.
 */
public class AnimationHelper {
    
    /**
     * Apply animation transforms to a model part using quaternion-based rotation.
     * This provides smoother interpolation and avoids gimbal lock.
     * @param part The ModelPart to animate
     * @param boneName The bone name from Bedrock animation
     * @param state The current animation state
     * @param animation The Bedrock animation to apply
     * @param ageInTicks Current age/time for the entity
     */
    public static void applyAnimationWithQuaternion(ModelPart part, String boneName, AnimationState state, 
                                                   BedrockAnimation animation, float ageInTicks) {
        if (animation == null || part == null) {
            return;
        }
        
        AnimationChannel channel = animation.getChannelForBone(boneName);
        if (channel == null) {
            return;
        }
        
        // Get animation time (in seconds)
        float animTime = state.getElapsedTime() / 20.0F; // Convert ticks to seconds
        float normalizedTime = animation.normalizeTime(animTime);
        
        // Get interpolated transforms using quaternion slerp for rotation
        Quaternion rotation = channel.getQuaternionAt(normalizedTime);
        float[] position = channel.getPositionAtTime(normalizedTime);
        
        // Convert quaternion to Euler angles and apply rotation
        float[] euler = rotation.toEuler();
        float blendWeight = state.getBlendWeight();
        part.xRot = Mth.lerp(blendWeight, part.xRot, euler[0]);
        part.yRot = Mth.lerp(blendWeight, part.yRot, euler[1]);
        part.zRot = Mth.lerp(blendWeight, part.zRot, euler[2]);
        
        // Apply position offset
        if (position != null) {
            part.x = Mth.lerp(blendWeight, part.x, position[0]);
            part.y = Mth.lerp(blendWeight, part.y, position[1]);
            part.z = Mth.lerp(blendWeight, part.z, position[2]);
        }
        
        // Note: Scale is not directly supported in ModelPart, would need custom rendering
    }
    
    /**
     * Apply animation transforms to a model part based on current animation state.
     * Uses traditional linear interpolation for backwards compatibility.
     * @param part The ModelPart to animate
     * @param boneName The bone name from Bedrock animation
     * @param state The current animation state
     * @param animation The Bedrock animation to apply
     * @param ageInTicks Current age/time for the entity
     */
    public static void applyAnimation(ModelPart part, String boneName, AnimationState state, 
                                     BedrockAnimation animation, float ageInTicks) {
        if (animation == null || part == null) {
            return;
        }
        
        AnimationChannel channel = animation.getChannelForBone(boneName);
        if (channel == null) {
            return;
        }
        
        // Get animation time (in seconds)
        float animTime = state.getElapsedTime() / 20.0F; // Convert ticks to seconds
        float normalizedTime = animation.normalizeTime(animTime);
        
        // Get interpolated transforms
        float[] rotation = channel.getRotationAtTime(normalizedTime);
        float[] position = channel.getPositionAtTime(normalizedTime);
        float[] scale = channel.getScaleAtTime(normalizedTime);
        
        // Apply rotation (convert degrees to radians)
        if (rotation != null) {
            float blendWeight = state.getBlendWeight();
            part.xRot = Mth.lerp(blendWeight, part.xRot, rotation[0] * Mth.DEG_TO_RAD);
            part.yRot = Mth.lerp(blendWeight, part.yRot, rotation[1] * Mth.DEG_TO_RAD);
            part.zRot = Mth.lerp(blendWeight, part.zRot, rotation[2] * Mth.DEG_TO_RAD);
        }
        
        // Apply position offset
        if (position != null) {
            float blendWeight = state.getBlendWeight();
            part.x = Mth.lerp(blendWeight, part.x, position[0]);
            part.y = Mth.lerp(blendWeight, part.y, position[1]);
            part.z = Mth.lerp(blendWeight, part.z, position[2]);
        }
        
        // Note: Scale is not directly supported in ModelPart, would need custom rendering
    }
    
    /**
     * Reset model part to default pose
     */
    public static void resetPose(ModelPart part) {
        part.xRot = 0.0F;
        part.yRot = 0.0F;
        part.zRot = 0.0F;
        part.x = 0.0F;
        part.y = 0.0F;
        part.z = 0.0F;
    }

    /**
     * Apply animation with scale support using ScaleableModelPart.
     * @param scaleable The ScaleableModelPart wrapper
     * @param boneName The bone name from Bedrock animation
     * @param state The current animation state
     * @param animation The Bedrock animation to apply
     * @param ageInTicks Current age/time for the entity
     */
    public static void applyAnimationWithScale(com.raeyncreations.silenthill.client.renderer.ScaleableModelPart scaleable, 
                                              String boneName, AnimationState state, 
                                              BedrockAnimation animation, float ageInTicks) {
        if (animation == null || scaleable == null) {
            return;
        }
        
        AnimationChannel channel = animation.getChannelForBone(boneName);
        if (channel == null) {
            return;
        }
        
        ModelPart part = scaleable.getModelPart();
        float animTime = state.getElapsedTime() / 20.0F;
        float normalizedTime = animation.normalizeTime(animTime);
        
        // Get interpolated transforms
        float[] rotation = channel.getRotationAtTime(normalizedTime);
        float[] position = channel.getPositionAtTime(normalizedTime);
        float[] scale = channel.getScaleAtTime(normalizedTime);
        
        // Apply rotation
        if (rotation != null) {
            float blendWeight = state.getBlendWeight();
            part.xRot = Mth.lerp(blendWeight, part.xRot, rotation[0] * Mth.DEG_TO_RAD);
            part.yRot = Mth.lerp(blendWeight, part.yRot, rotation[1] * Mth.DEG_TO_RAD);
            part.zRot = Mth.lerp(blendWeight, part.zRot, rotation[2] * Mth.DEG_TO_RAD);
        }
        
        // Apply position
        if (position != null) {
            float blendWeight = state.getBlendWeight();
            part.x = Mth.lerp(blendWeight, part.x, position[0]);
            part.y = Mth.lerp(blendWeight, part.y, position[1]);
            part.z = Mth.lerp(blendWeight, part.z, position[2]);
        }
        
        // Apply scale (with blend)
        if (scale != null) {
            float blendWeight = state.getBlendWeight();
            float currentScaleX = scaleable.getScaleX();
            float currentScaleY = scaleable.getScaleY();
            float currentScaleZ = scaleable.getScaleZ();
            
            scaleable.setScale(
                Mth.lerp(blendWeight, currentScaleX, scale[0]),
                Mth.lerp(blendWeight, currentScaleY, scale[1]),
                Mth.lerp(blendWeight, currentScaleZ, scale[2])
            );
        }
    }
    
    /**
     * Reset scaleable model part to default pose and scale
     */
    public static void resetPose(com.raeyncreations.silenthill.client.renderer.ScaleableModelPart scaleable) {
        resetPose(scaleable.getModelPart());
        scaleable.resetScale();
    }
}
