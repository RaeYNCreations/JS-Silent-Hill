package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.DoubleHeadEntity;
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

public class DoubleHeadModel extends HumanoidModel<DoubleHeadEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "double_head"), "main");

    public DoubleHeadModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-2.5F, 5.6993F, -5.9537F, 5.0F, 5.0F, 11.0F, CubeDeformation.NONE)
            .texOffs(32, 0).addBox(-2.5F, 6.0046F, -7.9604F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F)), PartPose.offset(0.0F, 8.0F, 5.0F));
    
        PartDefinition head = bone2.addOrReplaceChild("Head", CubeListBuilder.create()
            .texOffs(24, 25).addBox(-2.0F, 6.7031F, -10.1282F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(8, 42).addBox(-2.0F, 6.7031F, -12.1282F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(16, 44).addBox(-1.0F, 9.0F, -12.5F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 10.0F, -7.0F));
    
        PartDefinition head2 = head.addOrReplaceChild("Head2", CubeListBuilder.create()
            .texOffs(0, 16).addBox(0.0F, 6.7031F, -10.1282F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(0, 42).addBox(0.0F, 6.7031F, -12.1282F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(16, 42).addBox(0.0F, 9.0F, -12.5F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 10.0F, -7.0F));
    
        PartDefinition RightLeg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(12, 25).addBox(-2.5F, 3.699F, 2.0463F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(24, 34).addBox(-2.5F, -0.301F, 4.0463F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.9F, 8.6993F, 4.0463F, -7.5F, 0.0F, 2.5F));
    
        PartDefinition RightLeg3 = bone.addOrReplaceChild("RightLeg3", CubeListBuilder.create()
            .texOffs(8, 34).addBox(-3.5F, -0.301F, -3.9537F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F))
            .texOffs(24, 16).addBox(-3.5F, 4.699F, -5.9537F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.9F, 9.6993F, -3.9537F, -7.5F, 0.0F, 2.5F));
    
        PartDefinition LeftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(0, 25).addBox(0.5F, 3.699F, 2.0463F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(16, 34).addBox(0.5F, -0.301F, 4.0463F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.1F, 8.6993F, 4.0463F, -7.5F, 0.0F, -2.5F));
    
        PartDefinition LeftLeg3 = bone.addOrReplaceChild("LeftLeg3", CubeListBuilder.create()
            .texOffs(12, 16).addBox(1.5F, 4.699F, -5.9537F, 2.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(0, 34).addBox(1.5F, -0.301F, -3.9537F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.1F, 9.6993F, -3.9537F, -7.5F, 0.0F, -2.5F));
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(DoubleHeadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}