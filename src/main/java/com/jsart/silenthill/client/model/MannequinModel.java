package com.jsart.silenthill.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MannequinModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath("silenthill", "mannequin"), "main");
    
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public MannequinModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-3.0F, 0.0F, -1.5F, 6.0F, 10.0F, 3.0F), PartPose.offset(0.0F, 2.0F, 0.0F));
        
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, 2.0F, 0.0F));
        
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(24, 16)
                .addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F), PartPose.offset(-3.5F, 3.0F, 0.0F));
        
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(24, 16).mirror()
                .addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F), PartPose.offset(3.5F, 3.0F, 0.0F));
        
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(0, 29)
                .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(-1.5F, 12.0F, 0.0F));
        
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(0, 29).mirror()
                .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F), PartPose.offset(1.5F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightLeg.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = (float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightArm.xRot = (float) Math.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount;
        this.leftArm.xRot = (float) Math.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, buffer, packedLight, packedOverlay, color);
        head.render(poseStack, buffer, packedLight, packedOverlay, color);
        rightArm.render(poseStack, buffer, packedLight, packedOverlay, color);
        leftArm.render(poseStack, buffer, packedLight, packedOverlay, color);
        rightLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
        leftLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
