package com.jsart.silenthill.item;

import com.jsart.silenthill.SilentHillMod;
import com.jsart.silenthill.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSpawnEggs {
    public static final DeferredRegister<Item> SPAWN_EGGS = 
        DeferredRegister.create(Registries.ITEM, SilentHillMod.MOD_ID);

    public static final DeferredHolder<Item, DeferredSpawnEggItem> PYRAMID_HEAD_SPAWN_EGG = 
        SPAWN_EGGS.register("pyramidhead_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.PYRAMID_HEAD, 0x8B4513, 0x4A4A4A, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> MANNEQUIN_SPAWN_EGG = 
        SPAWN_EGGS.register("mannequin_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.MANNEQUIN, 0xF5DEB3, 0xD2B48C, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> LYING_FIGURE_SPAWN_EGG = 
        SPAWN_EGGS.register("lyingfigure_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.LYING_FIGURE, 0x8B7765, 0x6B5D52, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> CRYING_GIRL_SPAWN_EGG = 
        SPAWN_EGGS.register("cryinggirl_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.CRYING_GIRL, 0xFFB6C1, 0x696969, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> STRAIGHTJACKET_SPAWN_EGG = 
        SPAWN_EGGS.register("straightjacket_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.STRAIGHTJACKET, 0xFFFAF0, 0x8B8B83, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        SPAWN_EGGS.register(eventBus);
    }
}
