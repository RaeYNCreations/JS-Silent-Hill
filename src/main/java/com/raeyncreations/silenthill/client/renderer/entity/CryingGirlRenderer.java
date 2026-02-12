package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.CryingGirlModel;
import com.raeyncreations.jssilenthill.entity.CryingGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CryingGirlRenderer extends MobRenderer<CryingGirl, CryingGirlModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/crying_girl.png");

    public CryingGirlRenderer(EntityRendererProvider.Context context) {
        super(context, new CryingGirlModel(context.bakeLayer(CryingGirlModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(CryingGirl entity) {
        return TEXTURE;
    }
}