package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.entity.LyingFigureEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigureRenderer extends HumanoidMobRenderer<LyingFigureEntity, HumanoidModel<LyingFigureEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("silenthill", "textures/entity/lyingfigure.png");

    public LyingFigureRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigureEntity entity) {
        return TEXTURE;
    }
}
