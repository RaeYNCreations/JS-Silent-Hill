package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.AsphyxiaModel;
import com.raeyncreations.jssilenthill.entity.Asphyxia;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AsphyxiaRenderer extends MobRenderer<Asphyxia, AsphyxiaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/asphyxia.png");

    public AsphyxiaRenderer(EntityRendererProvider.Context context) {
        super(context, new AsphyxiaModel(context.bakeLayer(AsphyxiaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Asphyxia entity) {
        return TEXTURE;
    }
}