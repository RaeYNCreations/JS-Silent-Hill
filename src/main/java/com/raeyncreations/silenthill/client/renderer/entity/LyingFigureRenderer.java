package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.LyingFigureModel;
import com.raeyncreations.silenthill.entity.LyingFigureEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigureRenderer extends MobRenderer<LyingFigureEntity, LyingFigureModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/lying_figure.png");

    public LyingFigureRenderer(EntityRendererProvider.Context context) {
        super(context, new LyingFigureModel(context.bakeLayer(LyingFigureModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigureEntity entity) {
        return TEXTURE;
    }
}