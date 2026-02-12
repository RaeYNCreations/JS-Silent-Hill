package com.raeyncreations.silenthill;

import com.raeyncreations.silenthill.entity.ModEntities;
import com.raeyncreations.silenthill.item.ModCreativeModeTabs;
import com.raeyncreations.silenthill.item.ModItems;
import com.raeyncreations.silenthill.item.ModSpawnEggs;
import com.raeyncreations.silenthill.sound.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SilentHillMod.MOD_ID)
public class SilentHillMod {
    public static final String MOD_ID = "silenthill";
    public static final Logger LOGGER = LoggerFactory.getLogger(SilentHillMod.class);

    public SilentHillMod(IEventBus modEventBus) {
        LOGGER.info("Initializing Silent Hill Mod");
        
        // Register mod content
        ModItems.register(modEventBus);
        ModSpawnEggs.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Silent Hill Mod common setup");
    }
}
