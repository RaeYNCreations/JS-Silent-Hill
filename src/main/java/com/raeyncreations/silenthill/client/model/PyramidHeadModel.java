package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.entity.PyramidHeadEntity;
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

public class PyramidHeadModel extends HumanoidModel<PyramidHeadEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "pyramid_head"), "main");

    public PyramidHeadModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(31, 120).addBox(-8.0F, 15.0F, -2.0F, 16.0F, 10.0F, 8.0F)
            .texOffs(0, 42).addBox(-8.0F, 5.0F, -2.0F, 16.0F, 10.0F, 8.0F, new CubeDeformation(0.5F))
            .texOffs(112, 0).addBox(-7.0F, 25.0F, -2.0F, 14.0F, 18.0F, 8.0F)
            .texOffs(123, 84).addBox(-8.0F, 37.0F, -9.0F, 16.0F, 4.0F, 5.0F, CubeDeformation.NONE, -37.5F, 0.0F, 0.0F)
            .texOffs(0, 19).addBox(-7.0F, 31.0F, -3.0F, 14.0F, 10.0F, 1.0F), 
            PartPose.offset(0.0F, 47.0F, 2.0F));
    
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-7.0F, 43.85163F, -19.67026F, 14.0F, 2.0F, 32.0F, new CubeDeformation(0.1F), -22.5F, 0.0F, 0.0F)
            .texOffs(49, 105).addBox(2.0F, 42.85163F, -12.67026F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 103).addBox(-5.0F, 42.85163F, -6.67026F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 98).addBox(-3.0F, 42.85163F, -14.67026F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 93).addBox(-6.0F, 42.85163F, 0.32974F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(92, 57).addBox(3.0F, 42.85163F, 4.32974F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(92, 50).addBox(-1.0F, 42.85163F, 2.32974F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(98, 75).addBox(1.0F, 42.85163F, -2.67026F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(96, 95).addBox(-4.0F, 50.50741F, -12.2094F, 11.0F, 2.0F, 25.0F, CubeDeformation.NONE, -22.99008F, -11.53486F, 4.8493F)
            .texOffs(49, 93).addBox(-7.0F, 50.50741F, -12.2094F, 11.0F, 2.0F, 25.0F, CubeDeformation.NONE, -22.99008F, 11.53486F, -4.8493F)
            .texOffs(91, 122).addBox(-6.0F, 52.12892F, -17.13955F, 2.0F, 2.0F, 29.0F, CubeDeformation.NONE, 12.15656F, 2.24944F, -17.35932F)
            .texOffs(58, 120).addBox(4.0F, 52.12892F, -17.13955F, 2.0F, 2.0F, 29.0F, CubeDeformation.NONE, 12.15656F, -2.24944F, 17.35932F)
            .texOffs(40, 34).addBox(-6.0F, 50.12892F, -16.13955F, 12.0F, 2.0F, 28.0F, CubeDeformation.NONE, 7.5F, 0.0F, 0.0F)
            .texOffs(60, 0).addBox(-6.0F, 50.12892F, -16.13955F, 12.0F, 2.0F, 28.0F, new CubeDeformation(-0.01F), 5.0F, 0.0F, 0.0F)
            .texOffs(92, 30).addBox(-6.0F, 50.12892F, -13.13955F, 12.0F, 2.0F, 25.0F, new CubeDeformation(-0.01F))
            .texOffs(0, 91).addBox(-6.0F, 50.12892F, -13.13955F, 12.0F, 2.0F, 25.0F, new CubeDeformation(-0.01F), -5.0F, 0.0F, 0.0F)
            .texOffs(98, 57).addBox(-6.0F, 49.12892F, -11.13955F, 12.0F, 4.0F, 23.0F, new CubeDeformation(-0.01F), -15.0F, 0.0F, 0.0F)
            .texOffs(0, 64).addBox(-6.0F, 50.12892F, -13.13955F, 12.0F, 2.0F, 25.0F, new CubeDeformation(-0.01F), -20.0F, 0.0F, 0.0F)
            .texOffs(49, 66).addBox(-6.0F, 50.12892F, -13.13955F, 12.0F, 2.0F, 25.0F, new CubeDeformation(-0.01F), -10.0F, 0.0F, 0.0F)
            .texOffs(0, 0).addBox(-6.0F, 43.12892F, 7.86045F, 12.0F, 15.0F, 4.0F, CubeDeformation.NONE, -5.0F, 0.0F, 0.0F)
            .texOffs(40, 42).addBox(-6.0F, 55.96905F, 10.39136F, 12.0F, 1.0F, 2.0F, CubeDeformation.NONE, -60.0F, 0.0F, 0.0F)
            .texOffs(96, 93).addBox(-8.0F, 41.08628F, 8.07641F, 2.0F, 16.0F, 2.0F, CubeDeformation.NONE, -7.45392F, 1.08089F, 4.88206F)
            .texOffs(65, 93).addBox(6.0F, 41.08628F, 8.07641F, 2.0F, 16.0F, 2.0F, CubeDeformation.NONE, -7.45392F, -1.08089F, -4.88206F), 
            PartPose.offsetAndRotation(0.0F, 45.0F, 1.0F, 50.0F * (float)(Math.PI / 180.0F), 0.0F, 0.0F));
    
        PartDefinition arm1 = body.addOrReplaceChild("Arm1", CubeListBuilder.create()
            .texOffs(91, 122).addBox(-12.0F, 19.0F, -1.0F, 5.0F, 22.0F, 5.0F)
            .texOffs(66, 76).addBox(-7.0F, 20.0F, -1.0F, 1.0F, 2.0F, 3.0F)
            .texOffs(92, 40).addBox(-16.0F, 21.0F, -1.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F), 0.0F, 0.0F, -30.0F)
            .texOffs(49, 64).addBox(-7.0F, 37.0F, -1.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.5F), 0.0F, 0.0F, -30.0F), 
            PartPose.offsetAndRotation(-7.0F, 40.0F, 3.0F, 0.0F, 0.0F, 5.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition espada = arm1.addOrReplaceChild("Espada", CubeListBuilder.create()
            .texOffs(33, 45).addBox(-9.0F, 18.0F, -8.0F, 2.0F, 2.0F, 15.0F)
            .texOffs(0, 42).addBox(-9.0F, 14.0F, -9.0F, 2.0F, 7.0F, 1.0F)
            .texOffs(52, 45).addBox(-9.0F, 14.0F, -14.0F, 2.0F, 7.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(0, 118).addBox(-9.0F, 12.0F, -40.0F, 2.0F, 9.0F, 27.0F, new CubeDeformation(-0.5F))
            .texOffs(0, 0).addBox(-8.0F, 11.0F, -47.0F, 0.0F, 8.0F, 34.0F)
            .texOffs(0, 91).addBox(-9.0F, 13.0F, -42.0F, 2.0F, 7.0F, 7.0F, new CubeDeformation(-0.51F), -20.0F, 0.0F, 0.0F)
            .texOffs(49, 93).addBox(-9.0F, 15.0F, -44.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(-0.52F), -2.5F, 0.0F, 0.0F)
            .texOffs(98, 64).addBox(-9.0F, 17.5F, -47.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(-0.52F), 27.7167F, 0.80668F, -2.52344F), 
            PartPose.offset(-7.0F, 19.0F, 0.0F));
    
        PartDefinition arm2 = body.addOrReplaceChild("Arm2", CubeListBuilder.create()
            .texOffs(0, 118).addBox(7.0F, 19.0F, -1.0F, 5.0F, 22.0F, 5.0F)
            .texOffs(40, 45).addBox(6.0F, 20.0F, -1.0F, 1.0F, 2.0F, 3.0F)
            .texOffs(92, 30).addBox(11.0F, 21.0F, -1.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.2F), 0.0F, 0.0F, 30.0F)
            .texOffs(49, 64).mirror().addBox(0.0F, 36.0F, -1.0F, 7.0F, 7.0F, 5.0F, new CubeDeformation(0.5F), 0.0F, 0.0F, 30.0F), 
            PartPose.offsetAndRotation(7.0F, 40.0F, 3.0F, 0.0F, 0.0F, -5.0F * (float)(Math.PI / 180.0F)));
    
        PartDefinition leg1 = body.addOrReplaceChild("Leg1", CubeListBuilder.create()
            .texOffs(60, 0).addBox(-6.5F, 0.0F, -1.0F, 6.0F, 21.0F, 6.0F)
            .texOffs(0, 105).addBox(-6.5F, 0.0F, -4.0F, 6.0F, 3.0F, 3.0F), 
            PartPose.offset(-6.0F, 19.0F, 2.0F));
    
        PartDefinition leg2 = body.addOrReplaceChild("Leg2", CubeListBuilder.create()
            .texOffs(0, 60).addBox(0.5F, 0.0F, -1.0F, 6.0F, 21.0F, 6.0F)
            .texOffs(98, 84).addBox(0.5F, 0.0F, -4.0F, 6.0F, 3.0F, 3.0F), 
            PartPose.offset(6.0F, 19.0F, 2.0F));
    
        return LayerDefinition.create(meshdefinition, 176, 176);
    }

    @Override
    public void setupAnim(PyramidHeadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}