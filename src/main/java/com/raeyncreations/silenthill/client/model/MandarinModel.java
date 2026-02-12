package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.entity.MandarinEntity;
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

public class MandarinModel extends HumanoidModel<MandarinEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "mandarin"), "main");

    public MandarinModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(0, 43).addBox(-3.5F, 14.6993F, -0.9537F, 7.0F, 8.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(28, 0).addBox(-3.5F, 12.4993F, -2.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F), 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 3.0F, 1.0F));
    
        PartDefinition LeftLeg2 = bone2.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(54, 60).addBox(1.0F, 7.699F, -0.9537F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, -1.0F)
            .texOffs(42, 60).addBox(1.0349F, -0.3961F, -1.1242F, 3.0F, 9.0F, 3.0F, new CubeDeformation(-0.1F), 17.5F, 0.0F, -1.0F), 
            PartPose.offset(2.1F, 16.6993F, 1.0463F));
    
        PartDefinition RightLeg2 = bone2.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(58, 0).addBox(-4.0F, 7.699F, -0.9537F, 3.0F, 9.0F, 3.0F, CubeDeformation.NONE, -5.0F, 0.0F, 1.0F)
            .texOffs(56, 35).addBox(-4.0349F, -0.3961F, -1.1242F, 3.0F, 9.0F, 3.0F, new CubeDeformation(-0.1F), 20.0F, 0.0F, 1.0F), 
            PartPose.offset(-1.9F, 16.6993F, 1.0463F));
    
        PartDefinition body = bone2.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(22, 48).addBox(-3.5F, 22.6993F, -0.9537F, 7.0F, 7.0F, 4.0F, new CubeDeformation(0.001F))
            .texOffs(0, 55).addBox(-2.5F, 28.6993F, -0.9537F, 5.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), 
            PartPose.offset(0.0F, 23.6993F, 2.0463F));
    
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 29).addBox(-3.0F, 32.1993F, -4.9537F, 6.0F, 6.0F, 8.0F, new CubeDeformation(-0.4F), 20.0F, 0.0F, 0.0F)
            .texOffs(49, 25).addBox(-3.0F, 32.1993F, -7.9537F, 6.0F, 6.0F, 4.0F, CubeDeformation.NONE, 20.0F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 33.6993F, 2.0463F));
    
        PartDefinition leftear = head.addOrReplaceChild("leftear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(5.0F, 36.6993F, 1.0463F, 0.0F, 0.0F, -30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightear = head.addOrReplaceChild("rightear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(-5.0F, 36.6993F, 1.0463F, 0.0F, 0.0F, 30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 30.6993F, 1.0463F));
    
        PartDefinition Lingua = head.addOrReplaceChild("Lingua", CubeListBuilder.create()
            .texOffs(62, 72).addBox(-1.0F, 34.1993F, -5.9537F, 2.0F, 1.0F, 7.0F, CubeDeformation.NONE, 20.0F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 32.6993F, -6.9537F));
    
        PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create()
            .texOffs(30, 59).addBox(-5.5F, 18.6993F, -0.9537F, 3.0F, 11.0F, 3.0F, new CubeDeformation(-0.2F)), 
            PartPose.offsetAndRotation(-4.0F, 28.6993F, 0.0463F, 0.0F, 0.0F, 15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition Arm3 = RightArm.addOrReplaceChild("Arm3", CubeListBuilder.create()
            .texOffs(49, 13).addBox(-6.5F, 13.6993F, -1.9537F, 5.0F, 7.0F, 5.0F, CubeDeformation.NONE)
            .texOffs(21, 15).addBox(-7.5F, -1.3007F, -2.9537F, 7.0F, 15.0F, 7.0F, CubeDeformation.NONE), 
            PartPose.offset(-4.0F, 19.6993F, 0.0463F));
    
        PartDefinition LeftArm = body.addOrReplaceChild("LeftArm", CubeListBuilder.create()
            .texOffs(18, 59).addBox(2.5F, 18.6993F, -0.9537F, 3.0F, 11.0F, 3.0F, new CubeDeformation(-0.2F)), 
            PartPose.offsetAndRotation(4.0F, 28.6993F, 0.0463F, 0.0F, 0.0F, -15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition Arm4 = LeftArm.addOrReplaceChild("Arm4", CubeListBuilder.create()
            .texOffs(44, 48).addBox(1.5F, 13.6993F, -1.9537F, 5.0F, 7.0F, 5.0F, CubeDeformation.NONE)
            .texOffs(0, 0).addBox(0.5F, -1.3007F, -2.9537F, 7.0F, 15.0F, 7.0F, CubeDeformation.NONE), 
            PartPose.offset(4.0F, 19.6993F, 0.0463F));
    
        PartDefinition Booba = body.addOrReplaceChild("Booba", CubeListBuilder.create()
            .texOffs(0, 22).addBox(-3.5F, 26.1946F, -1.3854F, 7.0F, 3.0F, 2.0711F, CubeDeformation.NONE, -27.15648F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 26.6993F, 0.0463F));
    
        PartDefinition Saia = bone2.addOrReplaceChild("Saia", CubeListBuilder.create()
            .texOffs(28, 37).addBox(-4.5F, 14.1993F, -1.4537F, 9.0F, 6.0F, 5.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), 
            PartPose.offset(0.0F, 18.6993F, 2.0463F));
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}