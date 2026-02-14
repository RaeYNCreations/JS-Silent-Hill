package com.raeyncreations.silenthill.client;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.client.model.*;
import com.raeyncreations.silenthill.client.particle.AdvancedSilentBloodParticle;
import com.raeyncreations.silenthill.client.particle.AdvancedVenenoParticle;
import com.raeyncreations.silenthill.client.renderer.entity.*;
import com.raeyncreations.silenthill.entity.ModEntities;
import com.raeyncreations.silenthill.particle.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = SilentHillMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PYRAMID_HEAD.get(), PyramidHeadRenderer::new);
        event.registerEntityRenderer(ModEntities.MANNEQUIN.get(), MannequinRenderer::new);
        event.registerEntityRenderer(ModEntities.LYING_FIGURE.get(), LyingFigureRenderer::new);
        event.registerEntityRenderer(ModEntities.LYING_FIGURE_2.get(), LyingFigure2Renderer::new);
        event.registerEntityRenderer(ModEntities.LYING_FIGURE_3.get(), LyingFigure3Renderer::new);
        event.registerEntityRenderer(ModEntities.CRYING_GIRL.get(), CryingGirlRenderer::new);
        event.registerEntityRenderer(ModEntities.STRAIGHTJACKET.get(), StraightjacketRenderer::new);
        event.registerEntityRenderer(ModEntities.SILENT_HILL_CREEPER.get(), SilentHillCreeperRenderer::new);
        event.registerEntityRenderer(ModEntities.LADY_FUKURO.get(), LadyFukuroRenderer::new);
        event.registerEntityRenderer(ModEntities.DOUBLE_HEAD.get(), DoubleHeadRenderer::new);
        event.registerEntityRenderer(ModEntities.ASPHYXIA.get(), AsphyxiaRenderer::new);
        event.registerEntityRenderer(ModEntities.WORM_HEAD.get(), WormHeadRenderer::new);
        event.registerEntityRenderer(ModEntities.AIR_SCREAMER.get(), AirScreamerRenderer::new);
        event.registerEntityRenderer(ModEntities.VICTISM16.get(), Victism16Renderer::new);
        event.registerEntityRenderer(ModEntities.MANDARIN.get(), MandarinRenderer::new);
        event.registerEntityRenderer(ModEntities.MANDARIN_DAMAGE.get(), MandarinDamageRenderer::new);
        event.registerEntityRenderer(ModEntities.PA_CREAKINGK.get(), PaCreakingkRenderer::new);
        event.registerEntityRenderer(ModEntities.PA_CREAKINGK2.get(), PaCreakingk2Renderer::new);
        event.registerEntityRenderer(ModEntities.PA_CREAKINGKANOTHER.get(), PaCreakingkanotherRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PyramidHeadModel.LAYER_LOCATION, PyramidHeadModel::createBodyLayer);
        event.registerLayerDefinition(MannequinModel.LAYER_LOCATION, MannequinModel::createBodyLayer);
        event.registerLayerDefinition(LyingFigureModel.LAYER_LOCATION, LyingFigureModel::createBodyLayer);
        event.registerLayerDefinition(LyingFigure2Model.LAYER_LOCATION, LyingFigure2Model::createBodyLayer);
        event.registerLayerDefinition(LyingFigure3Model.LAYER_LOCATION, LyingFigure3Model::createBodyLayer);
        event.registerLayerDefinition(CryingGirlModel.LAYER_LOCATION, CryingGirlModel::createBodyLayer);
        event.registerLayerDefinition(StraightjacketModel.LAYER_LOCATION, StraightjacketModel::createBodyLayer);
        event.registerLayerDefinition(SilentHillCreeperModel.LAYER_LOCATION, SilentHillCreeperModel::createBodyLayer);
        event.registerLayerDefinition(LadyFukuroModel.LAYER_LOCATION, LadyFukuroModel::createBodyLayer);
        event.registerLayerDefinition(DoubleHeadModel.LAYER_LOCATION, DoubleHeadModel::createBodyLayer);
        event.registerLayerDefinition(AsphyxiaModel.LAYER_LOCATION, AsphyxiaModel::createBodyLayer);
        event.registerLayerDefinition(WormHeadModel.LAYER_LOCATION, WormHeadModel::createBodyLayer);
        event.registerLayerDefinition(AirScreamerModel.LAYER_LOCATION, AirScreamerModel::createBodyLayer);
        event.registerLayerDefinition(Victism16Model.LAYER_LOCATION, Victism16Model::createBodyLayer);
        event.registerLayerDefinition(MandarinModel.LAYER_LOCATION, MandarinModel::createBodyLayer);
        event.registerLayerDefinition(MandarinDamageModel.LAYER_LOCATION, MandarinDamageModel::createBodyLayer);
        event.registerLayerDefinition(PaCreakingkModel.LAYER_LOCATION, PaCreakingkModel::createBodyLayer);
        event.registerLayerDefinition(PaCreakingk2Model.LAYER_LOCATION, PaCreakingk2Model::createBodyLayer);
        event.registerLayerDefinition(PaCreakingkanotherModel.LAYER_LOCATION, PaCreakingkanotherModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        // Use advanced particles with Bedrock features
        event.registerSpriteSet(ModParticles.SILENT_BLOOD.get(), AdvancedSilentBloodParticle.Provider::new);
        event.registerSpriteSet(ModParticles.SILENT_BLOOD_2.get(), AdvancedSilentBloodParticle.Provider::new);
        event.registerSpriteSet(ModParticles.VENENO.get(), AdvancedVenenoParticle.Provider::new);
    }
}
