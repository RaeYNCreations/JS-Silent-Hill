package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.WormHead;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class WormHeadModel extends HumanoidModel<WormHead> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "worm_head"), "main");

    public WormHeadModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-2.5F, 5.6993F, -5.9537F, 5.0F, 5.0F, 11.0F, CubeDeformation.NONE, -2.5F, 0.0F, 0.0F)
            .texOffs(32, 0).addBox(-2.5F, 6.0046F, -7.9604F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F), -15.0F, 0.0F, 0.0F), PartPose.offset(0.0F, 8.0F, 5.0F));
    
        PartDefinition head = bone2.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(24, 25).addBox(-2.0F, 6.7031F, -10.1282F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(8, 42).addBox(-2.0F, 6.7031F, -12.1282F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(0, 42).addBox(0.0F, 6.7031F, -12.1282F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(0, 16).addBox(0.0F, 6.7031F, -10.1282F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 10.0F, -7.0F));
    
        PartDefinition rightLeg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(12, 25).addBox(-2.5F, 3.69899F, 2.04628F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 0.0F, 0.0F, -5.0F)
            .texOffs(24, 34).addBox(-2.5F, -0.30101F, 4.04628F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.1F), 6.0F, 0.0F, -5.0F), 
            PartPose.offset(-0.9F, 8.69929F, 4.04628F).xRot(-7.5F * (float)(Math.PI / 180.0F)).zRot(2.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightLeg3 = bone.addOrReplaceChild("RightLeg3", CubeListBuilder.create()
            .texOffs(8, 34).addBox(-3.5F, -0.30101F, -3.95372F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F), 6.0F, 0.0F, -5.0F)
            .texOffs(24, 16).addBox(-3.5F, 4.69899F, -5.95372F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 0.0F, 0.0F, -5.0F), 
            PartPose.offset(-1.9F, 9.69929F, -3.95372F).xRot(-7.5F * (float)(Math.PI / 180.0F)).zRot(2.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(0, 25).addBox(0.5F, 3.69899F, 2.04628F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 0.0F, 0.0F, 5.0F)
            .texOffs(16, 34).addBox(0.5F, -0.30101F, 4.04628F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.1F), 6.0F, 0.0F, 5.0F), 
            PartPose.offset(2.1F, 8.69929F, 4.04628F).xRot(-7.5F * (float)(Math.PI / 180.0F)).zRot(-2.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftLeg3 = bone.addOrReplaceChild("LeftLeg3", CubeListBuilder.create()
            .texOffs(12, 16).addBox(1.5F, 4.69899F, -5.95372F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE, 0.0F, 0.0F, 5.0F)
            .texOffs(0, 34).addBox(1.5F, -0.30101F, -3.95372F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F), 6.0F, 0.0F, 5.0F), 
            PartPose.offset(2.1F, 9.69929F, -3.95372F).xRot(-7.5F * (float)(Math.PI / 180.0F)).zRot(-2.5F * (float)(Math.PI / 180.0F)));
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}