package com.raeyncreations.silenthill.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

/**
 * Wrapper for ModelPart that supports scale transformations.
 * Since ModelPart doesn't natively support scale, this class applies
 * scale transforms during rendering using PoseStack.
 */
public class ScaleableModelPart {
    private final ModelPart modelPart;
    private float scaleX = 1.0F;
    private float scaleY = 1.0F;
    private float scaleZ = 1.0F;
    
    public ScaleableModelPart(ModelPart modelPart) {
        this.modelPart = modelPart;
    }
    
    /**
     * Set uniform scale for all axes.
     */
    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
        this.scaleZ = scale;
    }
    
    /**
     * Set non-uniform scale for each axis.
     */
    public void setScale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }
    
    /**
     * Set scale from array (Bedrock format: [x, y, z]).
     */
    public void setScale(float[] scale) {
        if (scale != null && scale.length == 3) {
            this.scaleX = scale[0];
            this.scaleY = scale[1];
            this.scaleZ = scale[2];
        }
    }
    
    public float getScaleX() {
        return scaleX;
    }
    
    public float getScaleY() {
        return scaleY;
    }
    
    public float getScaleZ() {
        return scaleZ;
    }
    
    /**
     * Get the wrapped ModelPart for direct manipulation.
     */
    public ModelPart getModelPart() {
        return modelPart;
    }
    
    /**
     * Render the model part with scale applied.
     * This should be called instead of modelPart.render() when scale is needed.
     */
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, 
                      int packedLight, int packedOverlay) {
        // Apply scale transform
        if (scaleX != 1.0F || scaleY != 1.0F || scaleZ != 1.0F) {
            poseStack.pushPose();
            poseStack.scale(scaleX, scaleY, scaleZ);
            modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            poseStack.popPose();
        } else {
            // No scale, render normally
            modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }
    
    /**
     * Reset scale to default (1.0, 1.0, 1.0).
     */
    public void resetScale() {
        this.scaleX = 1.0F;
        this.scaleY = 1.0F;
        this.scaleZ = 1.0F;
    }
}
