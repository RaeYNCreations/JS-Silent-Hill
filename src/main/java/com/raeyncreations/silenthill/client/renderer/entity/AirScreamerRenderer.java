package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.AirScreamerModel;
import com.raeyncreations.jssilenthill.entity.AirScreamer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AirScreamerRenderer extends MobRenderer<AirScreamer, AirScreamerModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/air_screamer.png");

    public AirScreamerRenderer(EntityRendererProvider.Context context) {
        super(context, new AirScreamerModel(context.bakeLayer(AirScreamerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(AirScreamer entity) {
        return TEXTURE;
    }
}