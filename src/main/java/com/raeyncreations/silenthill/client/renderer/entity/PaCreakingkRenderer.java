package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.PaCreakingkModel;
import com.raeyncreations.jssilenthill.entity.PaCreakingk;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingkRenderer extends MobRenderer<PaCreakingk, PaCreakingkModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/creakingk.png");

    public PaCreakingkRenderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingkModel(context.bakeLayer(PaCreakingkModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingk entity) {
        return TEXTURE;
    }
}
