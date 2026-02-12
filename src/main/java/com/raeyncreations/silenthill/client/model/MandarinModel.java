package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.Mandarin;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class MandarinModel extends HumanoidModel<Mandarin> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "mandarin"), "main");

    public MandarinModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(0, 43).addBox(-3.5F, 14.6993F, -0.9537F, 7.0F, 8.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(28, 0).addBox(-3.5F, 12.4993F, -2.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F), 2.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 3.0F, 1.0F));
    
        // Add legs, body, head, arms, booba, saia.
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}