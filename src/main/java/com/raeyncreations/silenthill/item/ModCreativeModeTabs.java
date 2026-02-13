package com.raeyncreations.silenthill.item;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.entity.ModEntities;
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
                    output.accept(ModItems.SAFE_HAVEN_OBELISK.get());
                    
                    // Add spawn eggs
                    output.accept(ModSpawnEggs.PYRAMID_HEAD_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.MANNEQUIN_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.LYING_FIGURE_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.LYING_FIGURE_2_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.LYING_FIGURE_3_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.CRYING_GIRL_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.STRAIGHTJACKET_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.SILENT_HILL_CREEPER_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.LADY_FUKURO_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.DOUBLE_HEAD_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.ASPHYXIA_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.WORM_HEAD_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.AIR_SCREAMER_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.VICTISM16_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.MANDARIN_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.MANDARIN_DAMAGE_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.PA_CREAKINGK_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.PA_CREAKINGK2_SPAWN_EGG.get());
                    output.accept(ModSpawnEggs.PA_CREAKINGKANOTHER_SPAWN_EGG.get());
                }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
