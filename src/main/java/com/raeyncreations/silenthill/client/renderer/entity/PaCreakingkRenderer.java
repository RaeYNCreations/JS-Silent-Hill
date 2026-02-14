package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.PaCreakingkModel;
import com.raeyncreations.silenthill.entity.PaCreakingkEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingkRenderer extends MobRenderer<PaCreakingkEntity, PaCreakingkModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/creakingk.png");

    public PaCreakingkRenderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingkModel(context.bakeLayer(PaCreakingkModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingkEntity entity) {
        return TEXTURE;
    }
}
