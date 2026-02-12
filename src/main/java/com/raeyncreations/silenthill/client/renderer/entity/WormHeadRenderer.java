package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.WormHeadModel;
import com.raeyncreations.silenthill.entity.WormHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WormHeadRenderer extends MobRenderer<WormHeadEntity, WormHeadModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/worm_head.png");

    public WormHeadRenderer(EntityRendererProvider.Context context) {
        super(context, new WormHeadModel(context.bakeLayer(WormHeadModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WormHeadEntity entity) {
        return TEXTURE;
    }
}