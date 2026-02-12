package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.StraightjacketModel;
import com.raeyncreations.jssilenthill.entity.Straightjacket;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StraightjacketRenderer extends MobRenderer<Straightjacket, StraightjacketModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/straightjacket.png");

    public StraightjacketRenderer(EntityRendererProvider.Context context) {
        super(context, new StraightjacketModel(context.bakeLayer(StraightjacketModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Straightjacket entity) {
        return TEXTURE;
    }
}