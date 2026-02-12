package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.Victism16Model;
import com.raeyncreations.jssilenthill.entity.Victism16;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Victism16Renderer extends MobRenderer<Victism16, Victism16Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/victism16.png");

    public Victism16Renderer(EntityRendererProvider.Context context) {
        super(context, new Victism16Model(context.bakeLayer(Victism16Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Victism16 entity) {
        return TEXTURE;
    }
}