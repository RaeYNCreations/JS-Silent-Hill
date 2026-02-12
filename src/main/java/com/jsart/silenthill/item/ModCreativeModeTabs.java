package com.jsart.silenthill.item;

import com.jsart.silenthill.SilentHillMod;
import com.jsart.silenthill.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SilentHillMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SILENT_HILL_TAB = 
        CREATIVE_MODE_TABS.register("silent_hill", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.silenthill"))
                .icon(() -> new ItemStack(ModItems.SILENT_HILL_AXE.get()))
                .displayItems((parameters, output) -> {
                    // Add custom items
                    output.accept(ModItems.SILENT_HILL_AXE.get());
                    
                    // Add spawn eggs
                    output.accept(ModSpawnEggs.PYRAMID_HEAD_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.MANNEQUIN_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.LYING_FIGURE_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.CRYING_GIRL_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.STRAIGHTJACKET_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.SILENT_HILL_CREEPER_SPAWN_EGG.get());
                }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
