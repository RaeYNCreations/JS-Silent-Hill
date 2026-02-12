package com.raeyncreations.jssilenthill.client.renderer;

import com.raeyncreations.jssilenthill.JSilentHillMod;
import com.raeyncreations.jssilenthill.client.model.MandarinDamageModel;
import com.raeyncreations.jssilenthill.entity.MandarinDamage;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandarinDamageRenderer extends MobRenderer<MandarinDamage, MandarinDamageModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JSilentHillMod.MODID, "textures/entity/mandarin_damage.png");

    public MandarinDamageRenderer(EntityRendererProvider.Context context) {
        super(context, new MandarinDamageModel(context.bakeLayer(MandarinDamageModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MandarinDamage entity) {
        return TEXTURE;
    }
}