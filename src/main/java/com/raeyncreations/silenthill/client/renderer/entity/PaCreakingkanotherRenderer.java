package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.PaCreakingkanotherModel;
import com.raeyncreations.silenthill.entity.PaCreakingkanotherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingkanotherRenderer extends MobRenderer<PaCreakingkanotherEntity, PaCreakingkanotherModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/creakingkanother.png");

    public PaCreakingkanotherRenderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingkanotherModel(context.bakeLayer(PaCreakingkanotherModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingkanotherEntity entity) {
        return TEXTURE;
    }
}
