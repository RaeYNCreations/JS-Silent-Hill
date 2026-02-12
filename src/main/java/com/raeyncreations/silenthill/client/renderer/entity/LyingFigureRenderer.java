package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.LyingFigureModel;
import com.raeyncreations.jssilenthill.entity.LyingFigure;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigureRenderer extends MobRenderer<LyingFigure, LyingFigureModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/lying_figure.png");

    public LyingFigureRenderer(EntityRendererProvider.Context context) {
        super(context, new LyingFigureModel(context.bakeLayer(LyingFigureModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigure entity) {
        return TEXTURE;
    }
}