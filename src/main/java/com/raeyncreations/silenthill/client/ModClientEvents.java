package com.raeyncreations.silenthill.client;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.MannequinModel;
import com.raeyncreations.silenthill.client.model.PyramidHeadModel;
import com.raeyncreations.silenthill.client.renderer.entity.*;
import com.raeyncreations.silenthill.entity.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SilentHillMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PYRAMID_HEAD.get(), PyramidHeadRenderer::new);
        event.registerEntityRenderer(ModEntities.MANNEQUIN.get(), MannequinRenderer::new);
        event.registerEntityRenderer(ModEntities.LYING_FIGURE.get(), LyingFigureRenderer::new);
        event.registerEntityRenderer(ModEntities.CRYING_GIRL.get(), CryingGirlRenderer::new);
        event.registerEntityRenderer(ModEntities.STRAIGHTJACKET.get(), StraightjacketRenderer::new);
        event.registerEntityRenderer(ModEntities.SILENT_HILL_CREEPER.get(), SilentHillCreeperRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PyramidHeadModel.LAYER_LOCATION, PyramidHeadModel::createBodyLayer);
        event.registerLayerDefinition(MannequinModel.LAYER_LOCATION, MannequinModel::createBodyLayer);
    }
}
