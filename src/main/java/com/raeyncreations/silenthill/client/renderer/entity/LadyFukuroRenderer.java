package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.LadyFukuroModel;
import com.raeyncreations.silenthill.entity.LadyFukuroEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LadyFukuroRenderer extends MobRenderer<LadyFukuroEntity, LadyFukuroModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/lady_fukuro.png");

    public LadyFukuroRenderer(EntityRendererProvider.Context context) {
        super(context, new LadyFukuroModel(context.bakeLayer(LadyFukuroModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LadyFukuroEntity entity) {
        return TEXTURE;
    }
}