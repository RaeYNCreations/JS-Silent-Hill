package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.PaCreakingk2;
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

public class PaCreakingk2Model extends HumanoidModel<PaCreakingk2> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "pa_creakingk2"), "main");

    public PaCreakingk2Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(31, 20).addBox(-3.5F, 11.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE)
            .texOffs(0, 20).addBox(-3.5F, 9.4993F, -2.9537F, 7.0F, 5.0F, 8.0F, new CubeDeformation(-2.1F)), 
            PartPose.offset(0.0F, 0.0F, 1.0F));
    
        // Add remaining geometry converted from pa_creakingk2.json
        // TODO: Convert full bedrock geometry to Java CubeListBuilder format
        // - LeftLeg2, RightLeg2, body, head, RightArm, LeftArm
    
        return LayerDefinition.create(meshdefinition, 80, 80);
    }
}
