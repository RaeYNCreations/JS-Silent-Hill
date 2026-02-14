package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.MannequinEntity;
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

public class MannequinModel extends HumanoidModel<MannequinEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "mannequin"), "main");

    public MannequinModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(0, 13).addBox(-3.5F, 11.6993F, -0.9537F, 7.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(32, 20).addBox(-2.5F, 16.6993F, -0.9537F, 5.0F, 2.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(0, 0).addBox(-3.5F, 9.4993F, -2.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F)), PartPose.offset(0.0F, 13.0F, 1.0F));
    
        PartDefinition RightLeg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(14, 29).addBox(-3.0F, -0.301F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(0, 22).addBox(-3.0F, 6.699F, -0.9537F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(26, 29).addBox(-2.5F, 11.699F, 0.0463F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(30, 7).addBox(-3.0F, -0.301F, -0.9537F, 3.0F, 1.0F, 2.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.9F, 13.6993F, 1.0463F, -7.5F, 0.0F, 2.5F));
    
        PartDefinition LeftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(18, 13).addBox(0.0F, -0.301F, -0.9537F, 3.0F, 1.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(18, 18).addBox(0.0F, 6.699F, -0.9537F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(22, 0).addBox(-1.5F, 11.699F, 0.0463F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(28, 10).addBox(0.0F, -0.301F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(2.1F, 13.6993F, 1.0463F, -7.5F, 0.0F, -2.5F));
    
        PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, -180.0F));
    
        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create()
            .texOffs(0, 13).addBox(-3.5F, 12.6993F, -0.9537F, 7.0F, 5.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(32, 20).addBox(-2.5F, 17.6993F, -0.9537F, 5.0F, 2.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(0, 0).addBox(-3.5F, 10.4993F, -2.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F)), PartPose.offset(0.0F, 14.0F, 1.0F));
    
        PartDefinition RightLeg3 = bone3.addOrReplaceChild("RightLeg3", CubeListBuilder.create()
            .texOffs(14, 29).addBox(-3.0F, -9.301F, 4.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(0, 22).addBox(-3.0F, 7.699F, -0.9537F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(26, 29).addBox(-2.5F, 12.699F, 0.0463F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.9F, 14.6993F, 1.0463F, -7.5F, 0.0F, 2.5F));
    
        PartDefinition LeftLeg3 = bone3.addOrReplaceChild("LeftLeg3", CubeListBuilder.create()
            .texOffs(18, 18).addBox(0.0F, 7.699F, -0.9537F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(22, 0).addBox(-1.5F, 12.699F, 0.0463F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(28, 10).addBox(0.0F, -9.301F, 4.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(2.1F, 14.6993F, 1.0463F, -7.5F, 0.0F, -2.5F));
    
        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void setupAnim(MannequinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}