package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.LyingFigure2Entity;
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

public class LyingFigure2Model extends HumanoidModel<LyingFigure2Entity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "lying_figure2"), "main");

    public LyingFigure2Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -2.0F).rotation(90.0F, 0.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(18, 21).addBox(-3.5F, -2.3007F, -2.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(40, 24).addBox(-2.5F, 1.6993F, -2.9537F, 5.0F, 1.0F, 3.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(0, 0).addBox(-3.5F, -4.5007F, -4.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F), 2.5F, 0.0F, 0.0F), PartPose.offset(0.0F, -1.0F, -1.0F));
    
        PartDefinition corpo = bone2.addOrReplaceChild("Corpo", CubeListBuilder.create()
            .texOffs(0, 13).addBox(-3.5F, 2.7031F, -3.1282F, 7.0F, 8.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 3.0F, -1.0F));
    
        PartDefinition bracos = bone2.addOrReplaceChild("Bracos", CubeListBuilder.create()
            .texOffs(12, 40).addBox(-5.5F, 2.7069F, -3.30268F, 2.0F, 8.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(22, 0).addBox(-3.5F, 2.7069F, -3.30268F, 7.0F, 4.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(3.5F, 2.7069F, -3.30268F, 2.0F, 8.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 9.0F, -2.0F));
    
        PartDefinition cabeca = bone2.addOrReplaceChild("Cabeca", CubeListBuilder.create()
            .texOffs(0, 25).addBox(-2.5F, 10.7031F, -3.1282F, 5.0F, 6.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 11.0F, -2.0F));
    
        PartDefinition peito = bone2.addOrReplaceChild("Peito", CubeListBuilder.create()
            .texOffs(44, 28).addBox(-3.0F, 5.47113F, -3.26619F, 3.0F, 4.0F, 2.0F, CubeDeformation.NONE, -35.36165F, 10.97831F, 8.56081F)
            .texOffs(36, 41).addBox(0.0F, 5.47113F, -3.26619F, 3.0F, 4.0F, 2.0F, CubeDeformation.NONE, -35.36165F, -10.97831F, -8.56081F)
            .texOffs(0, 2).addBox(-2.0F, 5.2F, -2.26619F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE, -35.36165F, 10.97831F, 8.56081F)
            .texOffs(0, 0).addBox(1.0F, 5.2F, -2.26619F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE, -35.36165F, -10.97831F, -8.56081F), 
            PartPose.offset(0.0F, 8.0F, -2.0F));
    
        PartDefinition rightLeg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(0, 35).addBox(-3.0F, -20.30101F, 2.04628F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, 60.0F, 0.0F, 0.0F)
            .texOffs(18, 29).addBox(-3.0F, -7.30101F, -2.95372F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE, -5.0F, 0.0F, -5.0F)
            .texOffs(22, 41).addBox(-2.5F, -2.30101F, -1.95372F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE, -5.0F, -20.0F, -5.0F)
            .texOffs(44, 45).addBox(-3.0F, -20.30101F, 0.04628F, 3.0F, 3.0F, 2.0F, CubeDeformation.NONE, 60.0F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(-1.9F, -0.30071F, -0.95372F, -160.0F * (float)(Math.PI / 180.0F), 82.5F * (float)(Math.PI / 180.0F), -87.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(44, 34).addBox(0.0F, -20.30101F, 0.04628F, 3.0F, 3.0F, 2.0F, CubeDeformation.NONE, 60.0F, 0.0F, 0.0F)
            .texOffs(26, 9).addBox(0.0F, -7.30101F, -2.95372F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE, -5.0F, 0.0F, 5.0F)
            .texOffs(37, 17).addBox(-1.5F, -2.30101F, -1.95372F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE, -5.0F, 20.0F, 5.0F)
            .texOffs(32, 29).addBox(0.0F, -20.30101F, 2.04628F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, 60.0F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(2.1F, -0.30071F, -0.95372F, -160.0F * (float)(Math.PI / 180.0F), -82.5F * (float)(Math.PI / 180.0F), 87.5F * (float)(Math.PI / 180.0F)));
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}