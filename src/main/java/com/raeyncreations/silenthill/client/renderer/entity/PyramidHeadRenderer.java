package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.PyramidHeadModel;
import com.raeyncreations.silenthill.entity.PyramidHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PyramidHeadRenderer extends MobRenderer<PyramidHeadEntity, PyramidHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/pyramid_head.png");

    public PyramidHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new PyramidHeadModel(context.bakeLayer(PyramidHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PyramidHeadEntity entity) {
        return TEXTURE;
    }
}