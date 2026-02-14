package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.AirScreamerModel;
import com.raeyncreations.silenthill.entity.AirScreamerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AirScreamerRenderer extends MobRenderer<AirScreamerEntity, AirScreamerModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/air_screamer.png");

    public AirScreamerRenderer(EntityRendererProvider.Context context) {
        super(context, new AirScreamerModel(context.bakeLayer(AirScreamerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(AirScreamerEntity entity) {
        return TEXTURE;
    }
}