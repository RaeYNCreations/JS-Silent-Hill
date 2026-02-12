package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.Victism16Model;
import com.raeyncreations.silenthill.entity.Victism16Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Victism16Renderer extends MobRenderer<Victism16Entity, Victism16Model> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/victism16.png");

    public Victism16Renderer(EntityRendererProvider.Context context) {
        super(context, new Victism16Model(context.bakeLayer(Victism16Model.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Victism16Entity entity) {
        return TEXTURE;
    }
}