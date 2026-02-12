package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.PaCreakingk2Model;
import com.raeyncreations.silenthill.entity.PaCreakingk2Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingk2Renderer extends MobRenderer<PaCreakingk2Entity, PaCreakingk2Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/creakingk2.png");

    public PaCreakingk2Renderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingk2Model(context.bakeLayer(PaCreakingk2Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingk2Entity entity) {
        return TEXTURE;
    }
}
