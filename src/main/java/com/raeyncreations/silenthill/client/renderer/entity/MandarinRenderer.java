package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.MandarinModel;
import com.raeyncreations.silenthill.entity.MandarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandarinRenderer extends MobRenderer<MandarinEntity, MandarinModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/mandarin.png");

    public MandarinRenderer(EntityRendererProvider.Context context) {
        super(context, new MandarinModel(context.bakeLayer(MandarinModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MandarinEntity entity) {
        return TEXTURE;
    }
}