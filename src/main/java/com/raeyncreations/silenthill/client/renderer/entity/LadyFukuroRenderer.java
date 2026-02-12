package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.LadyFukuroModel;
import com.raeyncreations.jssilenthill.entity.LadyFukuro;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LadyFukuroRenderer extends MobRenderer<LadyFukuro, LadyFukuroModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/lady_fukuro.png");

    public LadyFukuroRenderer(EntityRendererProvider.Context context) {
        super(context, new LadyFukuroModel(context.bakeLayer(LadyFukuroModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LadyFukuro entity) {
        return TEXTURE;
    }
}