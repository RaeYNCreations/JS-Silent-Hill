package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.Victism16;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class Victism16Model extends HumanoidModel<Victism16> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "victism16"), "main");

    public Victism16Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F).rotation(50.0F, 0.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(24, 12).addBox(-3.5F, 12.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE, -12.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 0.0F, 1.0F));
    
        // Add legs, body, head, arms, booba, saia.
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}