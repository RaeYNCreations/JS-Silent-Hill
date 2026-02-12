package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.DoubleHeadModel;
import com.raeyncreations.jssilenthill.entity.DoubleHead;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DoubleHeadRenderer extends MobRenderer<DoubleHead, DoubleHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/double_head.png");

    public DoubleHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new DoubleHeadModel(context.bakeLayer(DoubleHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(DoubleHead entity) {
        return TEXTURE;
    }
}