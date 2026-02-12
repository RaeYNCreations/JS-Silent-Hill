package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.MandarinModel;
import com.raeyncreations.jssilenthill.entity.Mandarin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandarinRenderer extends MobRenderer<Mandarin, MandarinModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/mandarin.png");

    public MandarinRenderer(EntityRendererProvider.Context context) {
        super(context, new MandarinModel(context.bakeLayer(MandarinModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Mandarin entity) {
        return TEXTURE;
    }
}