package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.CryingGirlEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class CryingGirlModel extends HumanoidModel<CryingGirlEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "crying_girl"), "main");

    public CryingGirlModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));
    
        PartDefinition rightleg2 = bone.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(0, 9).addBox(-4.0F, 0.2F, -2.9537F, 4.0F, 3.0F, 6.0F), PartPose.offset(-1.9F, 15.6993F, 1.0463F));
    
        PartDefinition leftleg2 = bone.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(0, 0).addBox(0.0F, 0.2F, -2.9537F, 4.0F, 3.0F, 6.0F), PartPose.offset(2.1F, 15.6993F, 1.0463F));
    
        return LayerDefinition.create(meshdefinition, 32, 32);
    }
}