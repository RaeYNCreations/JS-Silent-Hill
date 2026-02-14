package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.LyingFigure2Model;
import com.raeyncreations.silenthill.entity.LyingFigure2Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigure2Renderer extends MobRenderer<LyingFigure2Entity, LyingFigure2Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/lying_figure2.png");

    public LyingFigure2Renderer(EntityRendererProvider.Context context) {
        super(context, new LyingFigure2Model(context.bakeLayer(LyingFigure2Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigure2Entity entity) {
        return TEXTURE;
    }
}