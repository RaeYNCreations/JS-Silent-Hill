package com.jsart.silenthill.client.renderer.entity;

import com.jsart.silenthill.entity.SilentHillCreeperEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SilentHillCreeperRenderer extends CreeperRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("silenthill", "textures/entity/silenthill_creeper.png");

    public SilentHillCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SilentHillCreeperEntity entity) {
        return TEXTURE;
    }
}
