package com.raeyncreations.silenthill.client.animation;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/**
 * Helper class to apply Bedrock animations to Minecraft model parts.
 */
public class AnimationHelper {
    
    /**
     * Apply animation transforms to a model part based on current animation state.
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
        
        AnimationChannel channel = animation.getChannel(boneName);
        if (channel == null) {
            return;
        }
        
        // Get animation time (in seconds)
        float animTime = state.getElapsedTime() / 20.0F; // Convert ticks to seconds
        float normalizedTime = animation.getNormalizedTime(animTime);
        
        // Get interpolated transforms
        float[] rotation = channel.getRotationAt(normalizedTime);
        float[] position = channel.getPositionAt(normalizedTime);
        float[] scale = channel.getScaleAt(normalizedTime);
        
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
}
