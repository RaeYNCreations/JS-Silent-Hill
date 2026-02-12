package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.Straightjacket;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class StraightjacketModel extends HumanoidModel<Straightjacket> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "straightjacket"), "main");

    public StraightjacketModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition monster = bone.addOrReplaceChild("Monster", CubeListBuilder.create()
            .texOffs(21, 22).addBox(-3.5F, 8.6993F, -0.9537F, 7.0F, 5.0F, 6.0F, CubeDeformation.NONE, 7.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 1.0F, 1.0F));
    
        // Add legs, body2, head2, arms, boobas, saia.
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}