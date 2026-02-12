package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.SilentHillCreeperEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class SilentHillCreeperModel extends HumanoidModel<SilentHillCreeperEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "silent_hill_creeper"), "main");

    public SilentHillCreeperModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition section_2 = partdefinition.addOrReplaceChild("section_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.5F));
    
        PartDefinition section_0 = section_2.addOrReplaceChild("section_0", CubeListBuilder.create()
            .texOffs(14, 17).addBox(-2.0F, 0.0F, -4.4F, 4.0F, 1.0F, 2.0F)
            .texOffs(0, 9).addBox(-3.0F, 0.1F, -9.4F, 6.0F, 0.0F, 7.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
    
        PartDefinition section_1 = section_2.addOrReplaceChild("section_1", CubeListBuilder.create()
            .texOffs(0, 17).addBox(-2.0F, 0.0F, -2.4F, 4.0F, 1.0F, 5.0F)
            .texOffs(0, 0).addBox(-4.0F, 0.1F, -2.4F, 8.0F, 0.0F, 8.0F), PartPose.offset(0.0F, 0.0F, -2.0F));
    
        return LayerDefinition.create(meshdefinition, 32, 32);
    }
}