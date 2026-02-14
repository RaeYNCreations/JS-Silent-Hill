package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.SilentHillCreeperModel;
import com.raeyncreations.silenthill.entity.SilentHillCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilentHillCreeperRenderer extends MobRenderer<SilentHillCreeperEntity, SilentHillCreeperModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/silent_hill_creeper.png");

    public SilentHillCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new SilentHillCreeperModel(context.bakeLayer(SilentHillCreeperModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SilentHillCreeperEntity entity) {
        return TEXTURE;
    }
}