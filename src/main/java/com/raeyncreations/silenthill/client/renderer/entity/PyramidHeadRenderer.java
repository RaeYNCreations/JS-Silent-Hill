package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.PyramidHeadModel;
import com.raeyncreations.jssilenthill.entity.PyramidHead;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PyramidHeadRenderer extends MobRenderer<PyramidHead, PyramidHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/pyramid_head.png");

    public PyramidHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new PyramidHeadModel(context.bakeLayer(PyramidHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PyramidHead entity) {
        return TEXTURE;
    }
}