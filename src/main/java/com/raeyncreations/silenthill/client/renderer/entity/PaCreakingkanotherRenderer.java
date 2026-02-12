package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.PaCreakingkanotherModel;
import com.raeyncreations.jssilenthill.entity.PaCreakingkanother;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingkanotherRenderer extends MobRenderer<PaCreakingkanother, PaCreakingkanotherModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/creakingkanother.png");

    public PaCreakingkanotherRenderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingkanotherModel(context.bakeLayer(PaCreakingkanotherModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingkanother entity) {
        return TEXTURE;
    }
}
