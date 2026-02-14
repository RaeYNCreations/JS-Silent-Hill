package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.DoubleHeadModel;
import com.raeyncreations.silenthill.entity.DoubleHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DoubleHeadRenderer extends MobRenderer<DoubleHeadEntity, DoubleHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/double_head.png");

    public DoubleHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new DoubleHeadModel(context.bakeLayer(DoubleHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(DoubleHeadEntity entity) {
        return TEXTURE;
    }
}