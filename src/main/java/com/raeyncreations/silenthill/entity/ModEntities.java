package com.raeyncreations.silenthill.entity;

import com.raeyncreations.silenthill.SilentHillMod;
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

    // Lying Figure variants - Silent Hill 2 enemies
    public static final DeferredHolder<EntityType<?>, EntityType<LyingFigureEntity>> LYING_FIGURE = 
        ENTITY_TYPES.register("lyingfigure", () -> EntityType.Builder.of(LyingFigureEntity::new, MobCategory.MONSTER)
                .sized(0.8f, 1.0f)
                .clientTrackingRange(8)
                .build("lyingfigure"));

    public static final DeferredHolder<EntityType<?>, EntityType<LyingFigure2Entity>> LYING_FIGURE_2 = 
        ENTITY_TYPES.register("lyingfigure2", () -> EntityType.Builder.of(LyingFigure2Entity::new, MobCategory.MONSTER)
                .sized(0.8f, 1.0f)
                .clientTrackingRange(8)
                .build("lyingfigure2"));

    public static final DeferredHolder<EntityType<?>, EntityType<LyingFigure3Entity>> LYING_FIGURE_3 = 
        ENTITY_TYPES.register("lyingfigure3", () -> EntityType.Builder.of(LyingFigure3Entity::new, MobCategory.MONSTER)
                .sized(0.8f, 1.0f)
                .clientTrackingRange(8)
                .build("lyingfigure3"));

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

    // Silent Hill Creeper - small, deadly variant
    public static final DeferredHolder<EntityType<?>, EntityType<SilentHillCreeperEntity>> SILENT_HILL_CREEPER = 
        ENTITY_TYPES.register("silenthill_creeper", () -> EntityType.Builder.of(SilentHillCreeperEntity::new, MobCategory.MONSTER)
                .sized(0.4f, 0.3f)
                .clientTrackingRange(8)
                .build("silenthill_creeper"));

    // Lady Fukuro
    public static final DeferredHolder<EntityType<?>, EntityType<LadyFukuroEntity>> LADY_FUKURO = 
        ENTITY_TYPES.register("ladyfukuro", () -> EntityType.Builder.of(LadyFukuroEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("ladyfukuro"));

    // Double Head
    public static final DeferredHolder<EntityType<?>, EntityType<DoubleHeadEntity>> DOUBLE_HEAD = 
        ENTITY_TYPES.register("doublehead", () -> EntityType.Builder.of(DoubleHeadEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.95f)
                .clientTrackingRange(8)
                .build("doublehead"));

    // Asphyxia
    public static final DeferredHolder<EntityType<?>, EntityType<AsphyxiaEntity>> ASPHYXIA = 
        ENTITY_TYPES.register("asphyxia", () -> EntityType.Builder.of(AsphyxiaEntity::new, MobCategory.MONSTER)
                .sized(1.0f, 2.0f)
                .clientTrackingRange(8)
                .build("asphyxia"));

    // Worm Head
    public static final DeferredHolder<EntityType<?>, EntityType<WormHeadEntity>> WORM_HEAD = 
        ENTITY_TYPES.register("wormhead", () -> EntityType.Builder.of(WormHeadEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("wormhead"));

    // Air Screamer
    public static final DeferredHolder<EntityType<?>, EntityType<AirScreamerEntity>> AIR_SCREAMER = 
        ENTITY_TYPES.register("airscreamer", () -> EntityType.Builder.of(AirScreamerEntity::new, MobCategory.MONSTER)
                .sized(0.8f, 0.8f)
                .clientTrackingRange(10)
                .build("airscreamer"));

    // Victim
    public static final DeferredHolder<EntityType<?>, EntityType<Victism16Entity>> VICTISM16 = 
        ENTITY_TYPES.register("victism16", () -> EntityType.Builder.of(Victism16Entity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("victism16"));

    // Mandarin variants
    public static final DeferredHolder<EntityType<?>, EntityType<MandarinEntity>> MANDARIN = 
        ENTITY_TYPES.register("mandarin", () -> EntityType.Builder.of(MandarinEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("mandarin"));

    public static final DeferredHolder<EntityType<?>, EntityType<MandarinDamageEntity>> MANDARIN_DAMAGE = 
        ENTITY_TYPES.register("mandarindamage", () -> EntityType.Builder.of(MandarinDamageEntity::new, MobCategory.MONSTER)
                .sized(0.6f, 1.8f)
                .clientTrackingRange(8)
                .build("mandarindamage"));

    // Pa Creaking variants
    public static final DeferredHolder<EntityType<?>, EntityType<PaCreakingkEntity>> PA_CREAKINGK = 
        ENTITY_TYPES.register("pa_creakingk", () -> EntityType.Builder.of(PaCreakingkEntity::new, MobCategory.MONSTER)
                .sized(0.5f, 1.6f)
                .clientTrackingRange(8)
                .build("pa_creakingk"));

    public static final DeferredHolder<EntityType<?>, EntityType<PaCreakingk2Entity>> PA_CREAKINGK2 = 
        ENTITY_TYPES.register("pa_creakingk2", () -> EntityType.Builder.of(PaCreakingk2Entity::new, MobCategory.MONSTER)
                .sized(0.5f, 1.6f)
                .clientTrackingRange(8)
                .build("pa_creakingk2"));

    public static final DeferredHolder<EntityType<?>, EntityType<PaCreakingkanotherEntity>> PA_CREAKINGKANOTHER = 
        ENTITY_TYPES.register("pa_creakingkanother", () -> EntityType.Builder.of(PaCreakingkanotherEntity::new, MobCategory.MONSTER)
                .sized(0.5f, 1.6f)
                .clientTrackingRange(8)
                .build("pa_creakingkanother"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
