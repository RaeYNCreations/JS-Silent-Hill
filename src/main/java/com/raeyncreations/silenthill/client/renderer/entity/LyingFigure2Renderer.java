package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.LyingFigure2Model;
import com.raeyncreations.jssilenthill.entity.LyingFigure2;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigure2Renderer extends MobRenderer<LyingFigure2, LyingFigure2Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/lying_figure2.png");

    public LyingFigure2Renderer(EntityRendererProvider.Context context) {
        super(context, new LyingFigure2Model(context.bakeLayer(LyingFigure2Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigure2 entity) {
        return TEXTURE;
    }
}