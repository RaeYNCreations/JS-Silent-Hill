package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.MandarinDamageModel;
import com.raeyncreations.silenthill.entity.MandarinDamageEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandarinDamageRenderer extends MobRenderer<MandarinDamageEntity, MandarinDamageModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/mandarin_damage.png");

    public MandarinDamageRenderer(EntityRendererProvider.Context context) {
        super(context, new MandarinDamageModel(context.bakeLayer(MandarinDamageModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MandarinDamageEntity entity) {
        return TEXTURE;
    }
}