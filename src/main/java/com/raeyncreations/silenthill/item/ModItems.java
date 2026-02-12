package com.raeyncreations.silenthill.item;

import com.raeyncreations.silenthill.SilentHillMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(Registries.ITEM, SilentHillMod.MOD_ID);

    // Silent Hill Axe - custom weapon from the addon
    public static final DeferredHolder<Item, Item> SILENT_HILL_AXE = ITEMS.register("silent_hill_axe",
            () -> new SilentHillAxeItem(new Item.Properties()
                    .durability(500)
                    .fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
