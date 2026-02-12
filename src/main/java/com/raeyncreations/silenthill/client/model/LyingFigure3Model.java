package com.raeyncreations.jssilenthill.client.model;

import com.raeyncreations.jssilenthill.entity.LyingFigure3;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class LyingFigure3Model extends HumanoidModel<LyingFigure3> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "lying_figure3"), "main");

    public LyingFigure2Model(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));
    
        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create()
            .texOffs(18, 21).addBox(-3.5F, 13.6993F, -0.9537F, 7.0F, 4.0F, 4.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(23, 24).addBox(-0.5F, 14.6993F, -2.9537F, 0.0F, 1.0F, 2.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F)
            .texOffs(40, 24).addBox(-2.5F, 17.6993F, -0.9537F, 5.0F, 1.0F, 3.0F, CubeDeformation.NONE, 2.5F, 0.0F, 0.0F), PartPose.offset(0.0F, 15.0F, 1.0F));
    
        // Add corpo, bracos, cabeca, peito, legs.
    
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}