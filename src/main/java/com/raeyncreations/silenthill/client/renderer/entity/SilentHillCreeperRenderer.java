package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.SilentHillCreeperModel;
import com.raeyncreations.jssilenthill.entity.SilentHillCreeper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilentHillCreeperRenderer extends MobRenderer<SilentHillCreeper, SilentHillCreeperModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/silent_hill_creeper.png");

    public SilentHillCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new SilentHillCreeperModel(context.bakeLayer(SilentHillCreeperModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SilentHillCreeper entity) {
        return TEXTURE;
    }
}