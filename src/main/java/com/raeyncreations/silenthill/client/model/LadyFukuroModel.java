package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.LadyFukuro;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
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
            .texOffs(23, 34).addBox(-3.5F, 14.6993F, -0.9537F, 7.0F, 3.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 15.0F, 1.0F).rotation(-17.5F, 0.0F, 0.0F));
    
        // Add legs, body, head, arms, boobas, saia, monster, etc.
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}