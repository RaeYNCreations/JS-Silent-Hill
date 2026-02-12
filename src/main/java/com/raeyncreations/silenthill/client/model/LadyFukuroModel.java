package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.entity.LadyFukuro;
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

public class LadyFukuroModel extends HumanoidModel<LadyFukuro> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "lady_fukuro"), "main");

    public LadyFukuroModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(23, 34).addBox(-3.5F, 14.6993F, -0.9537F, 7.0F, 3.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(0.0F, 15.0F, 1.0F, -17.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition LeftLeg2 = bone2.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(65, 15).addBox(0.2F, 8.699F, -0.9537F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.2F), -5.0F, 0.0F, 5.0F), 
            PartPose.offsetAndRotation(2.1F, 15.6993F, 1.0463F, -48.09908F * (float)(Math.PI / 180.0F), -4.09151F * (float)(Math.PI / 180.0F), -34.56404F * (float)(Math.PI / 180.0F)));
    
        PartDefinition LeftLeg3 = LeftLeg2.addOrReplaceChild("LeftLeg3", CubeListBuilder.create()
            .texOffs(66, 65).addBox(1.2F, 1.699F, 0.0463F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(0, 64).addBox(1.2F, 1.699F, 2.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(3.1F, 8.6993F, 2.0463F, 130.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition RightLeg2 = bone2.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(26, 64).addBox(-3.2F, 8.699F, -0.9537F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.2F), -5.0F, 0.0F, -5.0F), 
            PartPose.offsetAndRotation(-1.9F, 15.6993F, 1.0463F, -48.09908F * (float)(Math.PI / 180.0F), 4.09151F * (float)(Math.PI / 180.0F), 34.56404F * (float)(Math.PI / 180.0F)));
    
        PartDefinition RightLeg3 = RightLeg2.addOrReplaceChild("RightLeg3", CubeListBuilder.create()
            .texOffs(13, 64).addBox(-4.2F, 1.699F, 2.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(67, 0).addBox(-4.2F, 1.699F, 0.0463F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(-2.9F, 8.6993F, 2.0463F, 130.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition body = bone2.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(0, 30).addBox(-3.5F, 20.6993F, -1.9537F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
            .texOffs(68, 32).addBox(-1.5F, 25.6993F, -0.9537F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(40, 50).addBox(-3.0F, 17.6993F, -1.5F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), 
            PartPose.offsetAndRotation(0.0F, 19.6993F, 1.0463F, 20.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-3.0F, 26.1993F, -3.9537F, 6.0F, 7.0F, 7.0F, new CubeDeformation(-0.4F))
            .texOffs(41, 37).addBox(-3.1422F, 25.6364F, -3.3558F, 3.0F, 7.0F, 5.0F, new CubeDeformation(-0.4F), -5.91444F, 8.69628F, 11.24532F)
            .texOffs(0, 40).addBox(0.1422F, 25.6364F, -3.3558F, 3.0F, 7.0F, 5.0F, new CubeDeformation(-0.4F), -5.91444F, -8.69628F, -11.24532F)
            .texOffs(53, 65).addBox(-2.0F, 27.1993F, -3.9537F, 5.0F, 6.0F, 1.0F, CubeDeformation.NONE, -39.32121F, -86.18816F, 12.8304F), 
            PartPose.offsetAndRotation(0.0F, 26.6993F, 0.0463F, 12.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition leftear = head.addOrReplaceChild("leftear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(5.0F, 30.6993F, 0.0463F, 0.0F, 0.0F, -30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightear = head.addOrReplaceChild("rightear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(-5.0F, 30.6993F, 0.0463F, 0.0F, 0.0F, 30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 24.6993F, 0.0463F));
    
        PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create()
            .texOffs(27, 50).addBox(-5.7588F, 15.6652F, -1.9537F, 3.0F, 10.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(66, 50).addBox(-5.7588F, 14.1652F, -1.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F), 0.0F, 0.0F, -7.5F)
            .texOffs(0, 3).addBox(-3.6197F, 15.2872F, -1.9537F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F), 17.5F, 0.0F, -25.0F), 
            PartPose.offsetAndRotation(-4.0F, 24.6993F, -0.9537F, 31.23252F * (float)(Math.PI / 180.0F), 16.66577F * (float)(Math.PI / 180.0F), -10.31123F * (float)(Math.PI / 180.0F)));
    
        PartDefinition LeftArm = body.addOrReplaceChild("LeftArm", CubeListBuilder.create()
            .texOffs(14, 50).addBox(2.7588F, 15.6652F, -1.9537F, 3.0F, 10.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(65, 8).addBox(2.7588F, 14.1652F, -1.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F), 0.0F, 0.0F, 7.5F)
            .texOffs(0, 0).addBox(2.6197F, 15.2872F, -1.9537F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F), 17.5F, 0.0F, 25.0F), 
            PartPose.offsetAndRotation(4.0F, 24.6993F, -0.9537F, 31.23252F * (float)(Math.PI / 180.0F), -16.66577F * (float)(Math.PI / 180.0F), 10.31123F * (float)(Math.PI / 180.0F)));
    
        PartDefinition Booba = body.addOrReplaceChild("Booba", CubeListBuilder.create()
            .texOffs(17, 42).addBox(-3.5F, 22.1946F, -3.3854F, 7.0F, 4.0F, 3.0711F, new CubeDeformation(-0.02F), -44.65648F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 22.6993F, -0.9537F));
    
        PartDefinition Booba2 = Booba.addOrReplaceChild("Booba2", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(0.0F, 22.6993F, 2.0463F, -2.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition Saia = bone2.addOrReplaceChild("Saia", CubeListBuilder.create()
            .texOffs(22, 10).addBox(-4.5F, 12.1993F, -3.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F), -17.5F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(0.0F, 13.6993F, 1.0463F, -47.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition Monster = bone.addOrReplaceChild("Monster", CubeListBuilder.create()
            .texOffs(21, 22).addBox(-3.5F, 8.6993F, -0.9537F, 7.0F, 5.0F, 6.0F, CubeDeformation.NONE, 7.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 1.0F, 1.0F));
    
        PartDefinition LeftLeg4 = Monster.addOrReplaceChild("LeftLeg4", CubeListBuilder.create()
            .texOffs(58, 36).addBox(2.2F, 3.699F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, 5.0F), 
            PartPose.offsetAndRotation(4.1F, 10.6993F, 3.0463F, -38.12355F * (float)(Math.PI / 180.0F), -24.72953F * (float)(Math.PI / 180.0F), 2.11598F * (float)(Math.PI / 180.0F)));
    
        PartDefinition LeftLeg5 = LeftLeg4.addOrReplaceChild("LeftLeg5", CubeListBuilder.create()
            .texOffs(64, 26).addBox(2.2F, -4.301F, -0.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F), -30.0F, 0.0F, 0.0F)
            .texOffs(40, 57).addBox(2.2F, -3.301F, 1.0463F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F)), 
            PartPose.offsetAndRotation(4.1F, 3.6993F, 1.0463F, 70.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition RightLeg4 = Monster.addOrReplaceChild("RightLeg4", CubeListBuilder.create()
            .texOffs(56, 54).addBox(-5.2F, 3.699F, 1.0463F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, -5.0F), 
            PartPose.offsetAndRotation(-3.9F, 10.6993F, 3.0463F, -38.12355F * (float)(Math.PI / 180.0F), 24.72953F * (float)(Math.PI / 180.0F), -2.11598F * (float)(Math.PI / 180.0F)));
    
        PartDefinition RightLeg5 = RightLeg4.addOrReplaceChild("RightLeg5", CubeListBuilder.create()
            .texOffs(0, 53).addBox(-5.2F, -3.301F, 1.0463F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(56, 47).addBox(-5.2F, -4.301F, -0.9537F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F), -30.0F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(-3.9F, 3.6993F, 1.0463F, 70.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition body2 = Monster.addOrReplaceChild("body2", CubeListBuilder.create()
            .texOffs(27, 0).addBox(-3.5F, 12.6993F, -4.5F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
            .texOffs(51, 11).addBox(-2.5F, 17.6993F, -4.0F, 5.0F, 3.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(48, 29).addBox(-3.0F, 10.0F, -4.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.2F), -15.0F, 0.0F, 0.0F), 
            PartPose.offsetAndRotation(0.0F, 11.6993F, -1.9537F, 122.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition head2 = body2.addOrReplaceChild("head2", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 18.6993F, -2.9537F));
    
        PartDefinition leftear2 = head2.addOrReplaceChild("leftear2", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(5.0F, 22.6993F, -2.9537F, 0.0F, 0.0F, -30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightear2 = head2.addOrReplaceChild("rightear2", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(-5.0F, 22.6993F, -2.9537F, 0.0F, 0.0F, 30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition hat2 = head2.addOrReplaceChild("hat2", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 16.6993F, -2.9537F));
    
        PartDefinition RightArm2 = body2.addOrReplaceChild("RightArm2", CubeListBuilder.create()
            .texOffs(50, 0).addBox(-5.7588F, 10.6652F, -4.9537F, 5.0F, 7.0F, 3.0F, new CubeDeformation(-0.3F)), 
            PartPose.offsetAndRotation(-4.0F, 16.6993F, -3.9537F, 20.0F * (float)(Math.PI / 180.0F), 0.0F, 15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition LeftArm2 = body2.addOrReplaceChild("LeftArm2", CubeListBuilder.create()
            .texOffs(48, 18).addBox(0.7588F, 10.6652F, -4.9537F, 5.0F, 7.0F, 3.0F, new CubeDeformation(-0.3F)), 
            PartPose.offsetAndRotation(4.0F, 16.6993F, -3.9537F, 20.0F * (float)(Math.PI / 180.0F), 0.0F, -15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition Booba3 = body2.addOrReplaceChild("Booba3", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 14.6993F, -3.9537F));
    
        PartDefinition Booba4 = Booba3.addOrReplaceChild("Booba4", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(0.0F, 14.6993F, -0.9537F, -2.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition Saia2 = Monster.addOrReplaceChild("Saia2", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 9.6993F, 6.0463F));
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}