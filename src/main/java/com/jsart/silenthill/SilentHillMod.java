package com.jsart.silenthill;

import com.jsart.silenthill.entity.ModEntities;
import com.jsart.silenthill.item.ModCreativeModeTabs;
import com.jsart.silenthill.item.ModItems;
import com.jsart.silenthill.item.ModSpawnEggs;
import com.jsart.silenthill.sound.ModSounds;
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
