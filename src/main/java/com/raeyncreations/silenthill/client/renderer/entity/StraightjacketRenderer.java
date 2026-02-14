package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.StraightjacketModel;
import com.raeyncreations.silenthill.entity.StraightjacketEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StraightjacketRenderer extends MobRenderer<StraightjacketEntity, StraightjacketModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/straightjacket.png");

    public StraightjacketRenderer(EntityRendererProvider.Context context) {
        super(context, new StraightjacketModel(context.bakeLayer(StraightjacketModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(StraightjacketEntity entity) {
        return TEXTURE;
    }
}