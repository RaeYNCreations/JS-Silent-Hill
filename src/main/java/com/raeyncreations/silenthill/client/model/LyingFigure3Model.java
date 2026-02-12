package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.LyingFigure3;
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

public class LyingFigure3Model extends HumanoidModel<LyingFigure3> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "lying_figure3"), "main");

    public LyingFigure3Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(18, 21).addBox(-3.5F, 13.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(23, 24).addBox(-0.5F, 14.6993F, -2.9537F, 0.0F, 1.0F, 2.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(40, 24).addBox(-2.5F, 17.6993F, -0.9537F, 5.0F, 1.0F, 3.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 15.0F, 1.0F));
    
        PartDefinition corpo = bone2.addOrReplaceChild("Corpo", CubeListBuilder.create()
            .texOffs(0, 13).addBox(-3.5F, 18.7031F, -1.1282F, 7.0F, 8.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 19.0F, 1.0F));
    
        PartDefinition bracos = bone2.addOrReplaceChild("Bracos", CubeListBuilder.create()
            .texOffs(12, 40).addBox(-5.5F, 18.7069F, -1.30268F, 2.0F, 8.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(22, 0).addBox(-3.5F, 18.7069F, -1.30268F, 7.0F, 4.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(3.5F, 18.7069F, -1.30268F, 2.0F, 8.0F, 3.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(5.5F, 18.7069F, -1.30268F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(-5.5F, 26.7069F, -1.30268F, 1.0F, 2.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(-6.5F, 24.7069F, 0.69732F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(-6.5F, 21.7069F, -0.30268F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(2.5F, 17.7069F, -0.30268F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(5.5F, 20.7069F, -0.30268F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F)
            .texOffs(40, 4).addBox(5.5F, 21.7069F, 1.69732F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, -27.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 25.0F, 0.0F));
    
        PartDefinition cabeca = bone2.addOrReplaceChild("Cabeca", CubeListBuilder.create()
            .texOffs(0, 25).addBox(-2.5F, 26.7031F, -1.1282F, 5.0F, 6.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(2.5F, 31.7031F, 0.8718F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(2.5F, 28.7031F, 2.8718F, 0.0F, 1.0F, 1.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(0.5F, 30.7031F, 2.8718F, 0.0F, 1.0F, 1.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(1.5F, 28.7031F, -0.1282F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(2.5F, 29.7031F, 2.8718F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(1.5F, 32.7031F, 0.8718F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(0.5F, 32.7031F, -1.1282F, 1.0F, 1.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(6, 22).addBox(-0.5F, 32.7031F, 2.8718F, 1.0F, 2.0F, 0.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 27.0F, 0.0F));
    
        PartDefinition peito = bone2.addOrReplaceChild("Peito", CubeListBuilder.create()
            .texOffs(44, 28).addBox(-3.0F, 21.47113F, -1.26619F, 3.0F, 4.0F, 2.0F, CubeDeformation.NONE, -35.36165F, 10.97831F, 8.56081F)
            .texOffs(36, 41).addBox(0.0F, 21.47113F, -1.26619F, 3.0F, 4.0F, 2.0F, CubeDeformation.NONE, -35.36165F, -10.97831F, -8.56081F)
            .texOffs(0, 2).addBox(-2.0F, 21.2F, -0.26619F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE, -35.36165F, 10.97831F, 8.56081F)
            .texOffs(0, 0).addBox(1.0F, 21.2F, -0.26619F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE, -35.36165F, -10.97831F, -8.56081F), 
            PartPose.offset(0.0F, 24.0F, 0.0F));
    
        PartDefinition rightLeg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(0, 35).addBox(-3.0F, -0.30101F, 1.04628F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(3, 40).addBox(-5.0F, 6.69899F, 1.04628F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(18, 29).addBox(-3.0F, 8.69899F, -0.95372F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE, -5.0F, 0.0F, -5.0F)
            .texOffs(22, 41).addBox(-2.5F, 13.69899F, 0.04628F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE, -5.0F, -20.0F, -5.0F)
            .texOffs(44, 45).addBox(-3.0F, -0.30101F, -0.95372F, 3.0F, 3.0F, 2.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(-1.9F, 15.69929F, 1.04628F, -7.5F * (float)(Math.PI / 180.0F), 0.0F, 2.5F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftLeg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(44, 34).addBox(0.0F, -0.30101F, -0.95372F, 3.0F, 3.0F, 2.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(26, 9).addBox(0.0F, 8.69899F, -0.95372F, 3.0F, 7.0F, 4.0F, CubeDeformation.NONE, -5.0F, 0.0F, 5.0F)
            .texOffs(37, 17).addBox(-1.5F, 13.69899F, 0.04628F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE, -5.0F, 20.0F, 5.0F)
            .texOffs(32, 29).addBox(0.0F, -0.30101F, 1.04628F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(27, 34).addBox(3.0F, 1.69899F, 1.04628F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(27, 34).addBox(-1.0F, 1.69899F, 1.04628F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(27, 34).addBox(3.0F, 5.69899F, 2.04628F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(27, 34).addBox(-1.0F, 5.69899F, 2.04628F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, 15.0F, 0.0F, 0.0F)
            .texOffs(27, 34).addBox(3.0F, 12.04093F, 0.85321F, 2.0F, 1.0F, 0.0F, CubeDeformation.NONE, -7.5F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(2.1F, 15.69929F, 1.04628F, -7.5F * (float)(Math.PI / 180.0F), 0.0F, -2.5F * (float)(Math.PI / 180.0F)));
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}