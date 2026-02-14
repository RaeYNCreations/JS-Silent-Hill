package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.Victism16Entity;
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

public class Victism16Model extends HumanoidModel<Victism16Entity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "victism16"), "main");

    public Victism16Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 50.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(24, 12).addBox(-3.5F, 12.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE), 
            PartPose.offset(0.0F, 0.0F, 1.0F));
    
        PartDefinition leftLeg2 = bone2.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(24, 56).addBox(0.0F, -0.30101F, -2.95372F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(0, 49).addBox(0.0F, 6.69899F, -0.95372F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(46, 48).addBox(0.0F, -0.30101F, -0.95372F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(2.1F, 13.69929F, 1.04628F, -29.9055F * (float)(Math.PI / 180.0F), -2.49762F * (float)(Math.PI / 180.0F), -4.33287F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightLeg2 = bone2.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(47, 18).addBox(-3.0F, -0.30101F, -0.95372F, 3.0F, 7.0F, 3.0F, CubeDeformation.NONE)
            .texOffs(47, 7).addBox(-3.0F, 6.69899F, -0.95372F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(35, 54).addBox(-3.0F, -0.30101F, -2.95372F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(-1.9F, 13.69929F, 1.04628F, -29.9055F * (float)(Math.PI / 180.0F), 2.49762F * (float)(Math.PI / 180.0F), 4.33287F * (float)(Math.PI / 180.0F)));
    
        PartDefinition body = bone2.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(27, 0).addBox(-3.5F, 18.69929F, -0.95372F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
            .texOffs(26, 51).addBox(-1.5F, 23.69929F, 0.04628F, 3.0F, 2.0F, 2.0F, CubeDeformation.NONE)
            .texOffs(0, 41).addBox(-2.5F, 15.69929F, -0.95372F, 5.0F, 3.0F, 4.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(0.0F, 14.69929F, 2.04628F, 10.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-3.0F, 24.19929F, -2.95372F, 6.0F, 8.0F, 7.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 26).addBox(-3.0F, 16.19929F, -2.95372F, 6.0F, 9.0F, 5.0F, new CubeDeformation(-0.4F))
            .texOffs(24, 21).addBox(-3.0F, 18.19929F, 0.04628F, 6.0F, 9.0F, 5.0F, new CubeDeformation(-0.4F)), 
            PartPose.offsetAndRotation(0.0F, 24.69929F, 1.04628F, -47.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition leftear = head.addOrReplaceChild("leftear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(5.0F, 28.69929F, 1.04628F, 0.0F, 0.0F, -30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition rightear = head.addOrReplaceChild("rightear", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(-5.0F, 28.69929F, 1.04628F, 0.0F, 0.0F, 30.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create(), 
            PartPose.offset(0.0F, 22.69929F, 1.04628F));
    
        PartDefinition rightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create()
            .texOffs(36, 36).addBox(-5.5F, 12.69929F, -0.95372F, 3.0F, 11.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(50, 0).addBox(-5.5F, 11.19929F, -0.95372F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 16).addBox(-3.36092F, 12.32126F, -0.95372F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(-4.0F, 22.69929F, 0.04628F, -55.0F * (float)(Math.PI / 180.0F), 0.0F, 15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leftArm = body.addOrReplaceChild("LeftArm", CubeListBuilder.create()
            .texOffs(23, 36).addBox(2.5F, 12.69929F, -0.95372F, 3.0F, 11.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(49, 38).addBox(2.5F, 11.19929F, -0.95372F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 3).addBox(2.36092F, 12.32126F, -0.95372F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(4.0F, 22.69929F, 0.04628F, -55.0F * (float)(Math.PI / 180.0F), 0.0F, -15.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition booba = body.addOrReplaceChild("Booba", CubeListBuilder.create(), 
            PartPose.offsetAndRotation(0.0F, 21.69929F, 0.04628F, -17.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition booba2 = booba.addOrReplaceChild("Booba2", CubeListBuilder.create()
            .texOffs(13, 49).addBox(-3.5F, 19.80299F, -2.0F, 3.0F, 5.0F, 3.07107F, CubeDeformation.NONE)
            .texOffs(0, 0).addBox(-0.5F, 19.80299F, -1.3F, 1.0F, 0.0F, 2.07107F, CubeDeformation.NONE)
            .texOffs(47, 29).addBox(0.5F, 19.80299F, -2.0F, 3.0F, 5.0F, 3.07107F, CubeDeformation.NONE), 
            PartPose.offsetAndRotation(0.0F, 21.69929F, 3.04628F, -2.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition saia = bone2.addOrReplaceChild("Saia", CubeListBuilder.create()
            .texOffs(0, 16).addBox(-4.5F, 11.19929F, -1.45372F, 9.0F, 4.0F, 5.0F, new CubeDeformation(-0.2F)), 
            PartPose.offsetAndRotation(0.0F, 14.69929F, 2.04628F, -17.5F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Victism16Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}