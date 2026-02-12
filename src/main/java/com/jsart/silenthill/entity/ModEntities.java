package com.jsart.silenthill.entity;

import com.jsart.silenthill.SilentHillMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
        DeferredRegister.create(Registries.ENTITY_TYPE, SilentHillMod.MOD_ID);

    // Pyramid Head - iconic Silent Hill enemy
    public static final DeferredHolder<EntityType<?>, EntityType<PyramidHeadEntity>> PYRAMID_HEAD = 
        ENTITY_TYPES.register("pyramidhead", () -> EntityType.Builder.of(PyramidHeadEntity::new, MobCategory.MONSTER)
                .sized(1.2f, 4.4f)
                .clientTrackingRange(10)
                .build("pyramidhead"));

    // Mannequin - Silent Hill enemy
    public static final DeferredHolder<EntityType<?>, EntityType<MannequinEntity>> MANNEQUIN = 
        ENTITY_TYPES.register("mannequin", () -> EntityType.Builder.of(MannequinEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("mannequin"));

    // Lying Figure - Silent Hill 2 enemy
    public static final DeferredHolder<EntityType<?>, EntityType<LyingFigureEntity>> LYING_FIGURE = 
        ENTITY_TYPES.register("lyingfigure", () -> EntityType.Builder.of(LyingFigureEntity::new, MobCategory.MONSTER)
                .sized(0.8f, 1.0f)
                .clientTrackingRange(8)
                .build("lyingfigure"));

    // Crying Girl
    public static final DeferredHolder<EntityType<?>, EntityType<CryingGirlEntity>> CRYING_GIRL = 
        ENTITY_TYPES.register("cryinggirl", () -> EntityType.Builder.of(CryingGirlEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("cryinggirl"));

    // Straightjacket
    public static final DeferredHolder<EntityType<?>, EntityType<StraightjacketEntity>> STRAIGHTJACKET = 
        ENTITY_TYPES.register("straightjacket", () -> EntityType.Builder.of(StraightjacketEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.95f)
                .clientTrackingRange(8)
                .build("straightjacket"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
