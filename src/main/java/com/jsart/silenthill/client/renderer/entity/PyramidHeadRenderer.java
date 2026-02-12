package com.jsart.silenthill.client.renderer.entity;

import com.jsart.silenthill.SilentHillMod;
import com.jsart.silenthill.client.model.PyramidHeadModel;
import com.jsart.silenthill.entity.PyramidHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PyramidHeadRenderer extends MobRenderer<PyramidHeadEntity, PyramidHeadModel<PyramidHeadEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/pyramidhead.png");

    public PyramidHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new PyramidHeadModel<>(context.bakeLayer(PyramidHeadModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(PyramidHeadEntity entity) {
        return TEXTURE;
    }
}
