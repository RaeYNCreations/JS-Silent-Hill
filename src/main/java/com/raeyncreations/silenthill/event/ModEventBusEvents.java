package com.raeyncreations.silenthill.event;

import com.raeyncreations.silenthill.SilentHillMod;
import com.raeyncreations.silenthill.entity.*;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = SilentHillMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PYRAMID_HEAD.get(), PyramidHeadEntity.createAttributes().build());
        event.put(ModEntities.MANNEQUIN.get(), MannequinEntity.createAttributes().build());
        event.put(ModEntities.LYING_FIGURE.get(), LyingFigureEntity.createAttributes().build());
        event.put(ModEntities.LYING_FIGURE_2.get(), LyingFigure2Entity.createAttributes().build());
        event.put(ModEntities.LYING_FIGURE_3.get(), LyingFigure3Entity.createAttributes().build());
        event.put(ModEntities.CRYING_GIRL.get(), CryingGirlEntity.createAttributes().build());
        event.put(ModEntities.STRAIGHTJACKET.get(), StraightjacketEntity.createAttributes().build());
        event.put(ModEntities.SILENT_HILL_CREEPER.get(), SilentHillCreeperEntity.createAttributes().build());
        event.put(ModEntities.LADY_FUKURO.get(), LadyFukuroEntity.createAttributes().build());
        event.put(ModEntities.DOUBLE_HEAD.get(), DoubleHeadEntity.createAttributes().build());
        event.put(ModEntities.ASPHYXIA.get(), AsphyxiaEntity.createAttributes().build());
        event.put(ModEntities.WORM_HEAD.get(), WormHeadEntity.createAttributes().build());
        event.put(ModEntities.AIR_SCREAMER.get(), AirScreamerEntity.createAttributes().build());
        event.put(ModEntities.VICTISM16.get(), Victism16Entity.createAttributes().build());
        event.put(ModEntities.MANDARIN.get(), MandarinEntity.createAttributes().build());
        event.put(ModEntities.MANDARIN_DAMAGE.get(), MandarinDamageEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        // Register all entities with standard monster spawn rules
        event.register(ModEntities.PYRAMID_HEAD.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.MANNEQUIN.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.LYING_FIGURE.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.LYING_FIGURE_2.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.LYING_FIGURE_3.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.CRYING_GIRL.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.STRAIGHTJACKET.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.SILENT_HILL_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.LADY_FUKURO.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.DOUBLE_HEAD.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.ASPHYXIA.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.WORM_HEAD.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.AIR_SCREAMER.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.VICTISM16.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.MANDARIN.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.MANDARIN_DAMAGE.get(), SpawnPlacementTypes.ON_GROUND, 
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, 
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
