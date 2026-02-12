package com.jsart.silenthill.client.renderer.entity;

import com.jsart.silenthill.entity.CryingGirlEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class CryingGirlRenderer extends HumanoidMobRenderer<CryingGirlEntity, HumanoidModel<CryingGirlEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("silenthill", "textures/entity/cryinggirl.png");

    public CryingGirlRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CryingGirlEntity entity) {
        return TEXTURE;
    }
}
