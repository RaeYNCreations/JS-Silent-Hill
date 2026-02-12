package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.PyramidHead;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class PyramidHeadModel extends HumanoidModel<PyramidHead> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "pyramid_head"), "main");

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
            .texOffs(0, 19).addBox(-7.0F, 31.0F, -3.0F, 14.0F, 10.0F, 1.0F), PartPose.offset(0.0F, 47.0F, 2.0F));
    
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-7.0F, 43.8516F, -19.6703F, 14.0F, 2.0F, 32.0F, new CubeDeformation(0.1F), -22.5F, 0.0F, 0.0F)
            .texOffs(49, 105).addBox(2.0F, 42.8516F, -12.6703F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 103).addBox(-5.0F, 42.8516F, -6.6703F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 98).addBox(-3.0F, 42.8516F, -14.6703F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(104, 93).addBox(-6.0F, 42.8516F, 0.3297F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(92, 57).addBox(3.0F, 42.8516F, 4.3297F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(92, 50).addBox(-1.0F, 42.8516F, 2.3297F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            .texOffs(98, 75).addBox(1.0F, 42.8516F, -2.6703F, 4.0F, 1.0F, 4.0F, CubeDeformation.NONE, -22.5F, 0.0F, 0.0F)
            // Add more cubes for head details, ears, hat, etc.
            , PartPose.offset(0.0F, 45.0F, 1.0F).rotation(50.0F, 0.0F, 0.0F));
    
        // Add arms, legs, espada.
    
        return LayerDefinition.create(meshdefinition, 176, 176);
    }
}