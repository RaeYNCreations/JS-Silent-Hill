package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.StraightjacketEntity;
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

public class StraightjacketModel extends HumanoidModel<StraightjacketEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "straightjacket"), "main");

    public StraightjacketModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition monster = bone.addOrReplaceChild("Monster", CubeListBuilder.create()
            .texOffs(21, 22).addBox(-3.5F, 8.6993F, -0.9537F, 7.0F, 5.0F, 6.0F, CubeDeformation.NONE, 7.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 1.0F, 1.0F));
    
        PartDefinition leftLeg4 = monster.addOrReplaceChild("LeftLeg4", CubeListBuilder.create()
            .texOffs(58, 36).addBox(2.2F, 3.6990F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, 5.0F), 
            PartPose.offset(4.1F, 10.6993F, 3.0463F).xRot(-38.12355F * (float)(Math.PI / 180.0F)).yRot(-24.72953F * (float)(Math.PI / 180.0F)).zRot(2.11598F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftLeg5 = leftLeg4.addOrReplaceChild("LeftLeg5", CubeListBuilder.create()
            .texOffs(40, 57).addBox(2.2F, -3.301F, 1.0463F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(64, 26).addBox(2.2F, -4.301F, -0.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F), -30.0F, 0.0F, 0.0F), 
            PartPose.offset(4.1F, 3.6993F, 1.0463F).xRot(70.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightLeg4 = monster.addOrReplaceChild("RightLeg4", CubeListBuilder.create()
            .texOffs(56, 54).addBox(-5.2F, 3.6990F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, -5.0F), 
            PartPose.offset(-3.9F, 10.6993F, 3.0463F).xRot(-38.12355F * (float)(Math.PI / 180.0F)).yRot(24.72953F * (float)(Math.PI / 180.0F)).zRot(-2.11598F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightLeg5 = rightLeg4.addOrReplaceChild("RightLeg5", CubeListBuilder.create()
            .texOffs(0, 53).addBox(-5.2F, -3.301F, 1.0463F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(56, 47).addBox(-5.2F, -4.301F, -0.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F), -30.0F, 0.0F, 0.0F), 
            PartPose.offset(-3.9F, 3.6993F, 1.0463F).xRot(70.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition body2 = monster.addOrReplaceChild("body2", CubeListBuilder.create()
            .texOffs(27, 0).addBox(-3.5F, 12.6993F, -4.5F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
            .texOffs(51, 11).addBox(-2.5F, 17.6993F, -4.0F, 5.0F, 3.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(48, 29).addBox(-3.0F, 10.0F, -4.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.2F), -15.0F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 11.6993F, -1.9537F).xRot(122.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition head2 = body2.addOrReplaceChild("head2", CubeListBuilder.create(), PartPose.offset(0.0F, 18.6993F, -2.9537F));
    
        PartDefinition leftear2 = head2.addOrReplaceChild("leftear2", CubeListBuilder.create(), 
            PartPose.offset(5.0F, 22.6993F, -2.9537F).zRot(-30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightear2 = head2.addOrReplaceChild("rightear2", CubeListBuilder.create(), 
            PartPose.offset(-5.0F, 22.6993F, -2.9537F).zRot(30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition hat2 = head2.addOrReplaceChild("hat2", CubeListBuilder.create(), PartPose.offset(0.0F, 16.6993F, -2.9537F));
    
        PartDefinition rightArm2 = body2.addOrReplaceChild("RightArm2", CubeListBuilder.create()
            .texOffs(50, 0).addBox(-5.7588F, 10.6652F, -4.9537F, 5.0F, 7.0F, 3.0F, new CubeDeformation(-0.3F)), 
            PartPose.offset(-4.0F, 16.6993F, -3.9537F).xRot(20.0F * (float)(Math.PI / 180.0F)).zRot(15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftArm2 = body2.addOrReplaceChild("LeftArm2", CubeListBuilder.create()
            .texOffs(48, 18).addBox(0.7588F, 10.6652F, -4.9537F, 5.0F, 7.0F, 3.0F, new CubeDeformation(-0.3F)), 
            PartPose.offset(4.0F, 16.6993F, -3.9537F).xRot(20.0F * (float)(Math.PI / 180.0F)).zRot(-15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition booba3 = body2.addOrReplaceChild("Booba3", CubeListBuilder.create(), PartPose.offset(0.0F, 14.6993F, -3.9537F));
    
        PartDefinition booba4 = booba3.addOrReplaceChild("Booba4", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 14.6993F, -0.9537F).xRot(-2.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition saia2 = monster.addOrReplaceChild("Saia2", CubeListBuilder.create(), PartPose.offset(0.0F, 9.6993F, 6.0463F));
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    @Override
    public void setupAnim(StraightjacketEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}