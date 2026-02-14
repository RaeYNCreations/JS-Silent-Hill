package com.raeyncreations.silenthill.client.renderer.entity;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.CryingGirlModel;
import com.raeyncreations.silenthill.entity.CryingGirlEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CryingGirlRenderer extends MobRenderer<CryingGirlEntity, CryingGirlModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "textures/entity/crying_girl.png");

    public CryingGirlRenderer(EntityRendererProvider.Context context) {
        super(context, new CryingGirlModel(context.bakeLayer(CryingGirlModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(CryingGirlEntity entity) {
        return TEXTURE;
    }
}