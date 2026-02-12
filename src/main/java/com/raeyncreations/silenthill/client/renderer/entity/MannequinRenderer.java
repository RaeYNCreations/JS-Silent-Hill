package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.MannequinModel;
import com.raeyncreations.silenthill.entity.MannequinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MannequinRenderer extends MobRenderer<MannequinEntity, MannequinModel<MannequinEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/mannequin.png");

    public MannequinRenderer(EntityRendererProvider.Context context) {
        super(context, new MannequinModel<>(context.bakeLayer(MannequinModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(MannequinEntity entity) {
        return TEXTURE;
    }
}
