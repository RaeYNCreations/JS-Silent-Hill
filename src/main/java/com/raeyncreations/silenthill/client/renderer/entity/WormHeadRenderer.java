package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.WormHeadModel;
import com.raeyncreations.jssilenthill.entity.WormHead;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WormHeadRenderer extends MobRenderer<WormHead, WormHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/worm_head.png");

    public WormHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new WormHeadModel(context.bakeLayer(WormHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WormHead entity) {
        return TEXTURE;
    }
}