package com.raeyncreations.silenthill;

import com.raeyncreations.silenthill.block.ModBlocks;
import com.raeyncreations.silenthill.entity.ModEntities;
import com.raeyncreations.silenthill.item.MobSuppressionSystem;
import com.raeyncreations.silenthill.item.ModCreativeModeTabs;
import com.raeyncreations.silenthill.item.ModItems;
import com.raeyncreations.silenthill.item.ModSpawnEggs;
import com.raeyncreations.silenthill.particle.ModParticles;
import com.raeyncreations.silenthill.sound.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SilentHillMod.MOD_ID)
public class SilentHillMod {
    public static final String MOD_ID = "silenthill";
    public static final Logger LOGGER = LoggerFactory.getLogger(SilentHillMod.class);

    public SilentHillMod(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Silent Hill Mod");
        
        // Register mod content
        ModItems.register(modEventBus);
        ModSpawnEggs.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModParticles.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        
        // Register configs
        modContainer.registerConfig(ModConfig.Type.COMMON, SilentHillConfig.COMMON_SPEC);
        
        // Register event handlers
        NeoForge.EVENT_BUS.register(MobSuppressionSystem.class);
        
        modEventBus.addListener(this::commonSetup);
        
        LOGGER.info("Silent Hill Mod initialized successfully!");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Silent Hill Mod common setup");
    }
}
