package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.PaCreakingk2Model;
import com.raeyncreations.jssilenthill.entity.PaCreakingk2;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaCreakingk2Renderer extends MobRenderer<PaCreakingk2, PaCreakingk2Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/creakingk2.png");

    public PaCreakingk2Renderer(EntityRendererProvider.Context context) {
        super(context, new PaCreakingk2Model(context.bakeLayer(PaCreakingk2Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PaCreakingk2 entity) {
        return TEXTURE;
    }
}
