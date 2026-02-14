package com.raeyncreations.silenthill.item;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.entity.ModEntities;
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

    public static final DeferredHolder<Item, DeferredSpawnEggItem> SILENT_HILL_CREEPER_SPAWN_EGG = 
        SPAWN_EGGS.register("silenthill_creeper_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.SILENT_HILL_CREEPER, 0x0DA70B, 0x000000, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> LYING_FIGURE_2_SPAWN_EGG = 
        SPAWN_EGGS.register("lyingfigure2_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.LYING_FIGURE_2, 0x8B7765, 0x5C4033, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> LYING_FIGURE_3_SPAWN_EGG = 
        SPAWN_EGGS.register("lyingfigure3_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.LYING_FIGURE_3, 0x8B7765, 0x4A3728, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> LADY_FUKURO_SPAWN_EGG = 
        SPAWN_EGGS.register("ladyfukuro_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.LADY_FUKURO, 0xF5DEB3, 0x8B4513, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> DOUBLE_HEAD_SPAWN_EGG = 
        SPAWN_EGGS.register("doublehead_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.DOUBLE_HEAD, 0xCD853F, 0x8B4513, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> ASPHYXIA_SPAWN_EGG = 
        SPAWN_EGGS.register("asphyxia_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.ASPHYXIA, 0x8B7765, 0x696969, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> WORM_HEAD_SPAWN_EGG = 
        SPAWN_EGGS.register("wormhead_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.WORM_HEAD, 0xD2691E, 0x8B4513, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> AIR_SCREAMER_SPAWN_EGG = 
        SPAWN_EGGS.register("airscreamer_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.AIR_SCREAMER, 0x8B4513, 0x654321, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> VICTISM16_SPAWN_EGG = 
        SPAWN_EGGS.register("victism16_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.VICTISM16, 0xFFE4C4, 0xDEB887, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> MANDARIN_SPAWN_EGG = 
        SPAWN_EGGS.register("mandarin_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.MANDARIN, 0xFF8C00, 0xD2691E, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> MANDARIN_DAMAGE_SPAWN_EGG = 
        SPAWN_EGGS.register("mandarindamage_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.MANDARIN_DAMAGE, 0xDC143C, 0x8B0000, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> PA_CREAKINGK_SPAWN_EGG = 
        SPAWN_EGGS.register("pa_creakingk_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.PA_CREAKINGK, 0x696969, 0x2F4F4F, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> PA_CREAKINGK2_SPAWN_EGG = 
        SPAWN_EGGS.register("pa_creakingk2_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.PA_CREAKINGK2, 0x708090, 0x2F4F4F, new Item.Properties()));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> PA_CREAKINGKANOTHER_SPAWN_EGG = 
        SPAWN_EGGS.register("pa_creakingkanother_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.PA_CREAKINGKANOTHER, 0x778899, 0x2F4F4F, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        SPAWN_EGGS.register(eventBus);
    }
}
