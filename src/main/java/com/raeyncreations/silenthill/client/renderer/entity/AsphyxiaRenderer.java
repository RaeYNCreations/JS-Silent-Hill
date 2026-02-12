package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.AsphyxiaModel;
import com.raeyncreations.silenthill.entity.AsphyxiaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AsphyxiaRenderer extends MobRenderer<AsphyxiaEntity, AsphyxiaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/asphyxia.png");

    public AsphyxiaRenderer(EntityRendererProvider.Context context) {
        super(context, new AsphyxiaModel(context.bakeLayer(AsphyxiaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(AsphyxiaEntity entity) {
        return TEXTURE;
    }
}