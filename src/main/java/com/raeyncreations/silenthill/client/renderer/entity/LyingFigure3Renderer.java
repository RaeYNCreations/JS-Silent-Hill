package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.LyingFigure3Model;
import com.raeyncreations.silenthill.entity.LyingFigure3Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LyingFigure3Renderer extends MobRenderer<LyingFigure3Entity, LyingFigure3Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/lyingfigure3.png");

    public LyingFigure3Renderer(EntityRendererProvider.Context context) {
        super(context, new LyingFigure3Model(context.bakeLayer(LyingFigure3Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LyingFigure3Entity entity) {
        return TEXTURE;
    }
}
