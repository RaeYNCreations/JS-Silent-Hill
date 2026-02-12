# Silent Hill Mod - Bedrock to NeoForge Conversion Summary

## Overview
This document outlines the conversion of the JS' Silent Hill Bedrock Edition addon to a NeoForge 1.21.1 mod.

## Converted Features

### Entities (16 Total)
All 16 custom entities from the Bedrock addon have been converted to Java entities:

1. **Pyramid Head** (`PyramidHeadEntity`) - The iconic executioner
   - 200 HP, 40 damage
   - Complete knockback resistance
   - Fire immunity
   - Custom large model with pyramid head

2. **Mannequin** (`MannequinEntity`) - Unsettling enemy
   - 30 HP, 6 damage
   - Custom sounds (idle, hurt, death)
   - Custom slim model

3. **Lying Figure** (3 variants)
   - `LyingFigureEntity`
   - `LyingFigure2Entity`
   - `LyingFigure3Entity`
   - Low-profile crawling enemies

4. **Crying Girl** (`CryingGirlEntity`)
5. **Straightjacket** (`StraightjacketEntity`)
6. **Lady Fukuro** (`LadyFukuroEntity`)
7. **Double Head** (`DoubleHeadEntity`)
8. **Asphyxia** (`AsphyxiaEntity`)
9. **Worm Head** (`WormHeadEntity`)
10. **Air Screamer** (`AirScreamerEntity`)
11. **Victism16** (`Victism16Entity`)
12. **Mandarin** (2 variants)
    - `MandarinEntity`
    - `MandarinDamageEntity`

### Items
- **Silent Hill Axe** - Custom weapon with 500 durability and high damage
- **Spawn Eggs** - For all 14+ entities

### Audio
- Custom sounds for Mannequin (idle, hurt, death, angry)
- Custom sounds for Pyramid Head (hit, throw, crack)
- Sounds properly registered in `sounds.json`

### Visual Assets
- Entity textures ported from Bedrock format
- Custom models created for Pyramid Head and Mannequin
- Remaining entities use humanoid models as base

### Mod Structure
```
src/main/
├── java/com/jsart/silenthill/
│   ├── SilentHillMod.java          # Main mod class
│   ├── entity/
│   │   ├── ModEntities.java         # Entity registration
│   │   └── [Entity classes]         # 16 entity implementations
│   ├── item/
│   │   ├── ModItems.java            # Item registration
│   │   ├── ModSpawnEggs.java        # Spawn egg registration
│   │   ├── ModCreativeModeTabs.java # Creative tab
│   │   └── SilentHillAxeItem.java  # Custom axe item
│   ├── sound/
│   │   └── ModSounds.java           # Sound registration
│   ├── client/
│   │   ├── ModClientEvents.java     # Client-side event handler
│   │   ├── model/                   # Custom models
│   │   └── renderer/entity/         # Entity renderers
│   └── event/
│       └── ModEventBusEvents.java   # Server-side events
└── resources/
    ├── META-INF/
    │   └── neoforge.mods.toml       # Mod metadata
    └── assets/silenthill/
        ├── lang/
        │   └── en_us.json           # Localization
        ├── sounds.json              # Sound definitions
        ├── sounds/                  # Audio files
        └── textures/                # Texture files
```

## Conversion Details

### From Bedrock Behavior Pack
- Entity behaviors → Java AI goals (MeleeAttackGoal, NearestAttackableTargetGoal, etc.)
- Entity components → Entity attributes (health, speed, damage)
- Scripts (JavaScript) → Java code
- Functions → Direct Java implementations

### From Bedrock Resource Pack
- Entity models (JSON) → Java model classes with LayerDefinitions
- Textures (PNG) → Copied directly (compatible format)
- Sounds (OGG) → Copied directly with proper registry
- Fog settings → Not yet implemented (requires custom renderer)

## Build Configuration
- **Gradle**: 8.9
- **NeoForge**: 21.1.73
- **Minecraft**: 1.21.1
- **Java**: 21 required
- Plugin: `net.neoforged.gradle.userdev:7.0.80`

## Not Yet Implemented
1. **Fog Effects**: The Silent Hill fog effect from the Bedrock addon requires custom rendering
2. **Advanced Behaviors**: Some complex Bedrock script behaviors may need further refinement
3. **Loot Tables**: Can be added as JSON files in `data/silenthill/loot_tables/`
4. **Spawn Rules**: Biome-specific spawning can be configured via JSON

## Testing Instructions
1. Build the mod: `./gradlew build`
2. Install NeoForge 1.21.1
3. Place the built JAR in the `mods` folder
4. Launch Minecraft
5. Use spawn eggs or natural spawning to test entities

## Notes
- All entities use standard monster AI behaviors
- Models are simplified versions - can be enhanced with more detailed geometry
- Sounds are directly ported from the Bedrock addon
- The mod maintains the horror atmosphere of the original addon
