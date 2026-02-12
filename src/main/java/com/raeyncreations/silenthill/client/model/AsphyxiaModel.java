package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.Asphyxia;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class AsphyxiaModel extends HumanoidModel<Asphyxia> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "asphyxia"), "main");

    public AsphyxiaModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F).rotation(17.5F, 0.0F, 0.0F));
    
        PartDefinition corpo1 = bone.addOrReplaceChild("Corpo1", CubeListBuilder.create()
            .texOffs(42, 42).addBox(-3.5F, 11.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE, 10.0F, 0.0F, 0.0F), PartPose.offset(0.0F, 13.0F, 1.0F).rotation(-17.5F, 0.0F, 0.0F));
    
        // Add legs, body2, head2, arms, boobas, etc. - pattern: addOrReplaceChild with cubes and poses.
    
        return LayerDefinition.create(meshdefinition, 96, 96);
    }
}