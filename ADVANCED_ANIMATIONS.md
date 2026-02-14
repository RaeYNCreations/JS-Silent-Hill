# Advanced Bedrock-Style Animations & Particles

## Overview

This document describes the advanced animation and particle systems that bring Bedrock Edition features to the Java/NeoForge Silent Hill mod.

## Animation System Architecture

### Core Components

#### 1. AnimationKeyframe
Represents a single point in time with rotation, position, and scale transforms.
- Supports 3D rotation (Euler angles in degrees)
- Position offsets (x, y, z)
- Scale multipliers (x, y, z)
- Linear interpolation between keyframes

#### 2. AnimationChannel
Manages all keyframes for a single bone.
- Stores sorted keyframes by time
- Interpolates rotation, position, scale at any time point
- Handles edge cases (before first/after last keyframe)

#### 3. BedrockAnimation
Complete animation with all bone channels.
- Animation length in seconds
- Loop behavior (repeating vs one-shot)
- Named bone channels (matches Bedrock bone names)
- Time normalization (wraps loop, clamps one-shot)

#### 4. BedrockAnimationParser
Parses Bedrock JSON animation files from `resource_pack/animations/`.
```java
Map<String, BedrockAnimation> animations = 
    BedrockAnimationParser.parseAnimationFile("/animations/jsart_mannequin.animation.json");
```

Features:
- Gson-based JSON parsing
- Animation caching for performance
- MoLang expression evaluation in keyframes
- Error handling with fallback values

#### 5. AnimationState
Tracks animation state per entity instance.
- Current playing animation
- Elapsed time (in ticks)
- Blend weight for smooth transitions
- Configurable blend duration (default 0.2s)

Methods:
```java
state.playAnimation("walk");
state.update(deltaTicks);
float blendWeight = state.getBlendWeight();
```

#### 6. MoLangExpression
Basic MoLang expression evaluator for dynamic values.

Supported:
- Math functions: `cos`, `sin`, `abs`, `sqrt`, `floor`, `ceil`, `round`, `random`
- Query variables: `query.life_time`, `query.anim_time`
- Arithmetic: `+`, `-`, `*`, `/`, `%`
- Example: `"45.8 + math.cos(query.life_time * 2.5) * 10"`

Limitations:
- No parentheses in complex expressions (simple parser)
- Limited query variable support
- Thread-safe using ThreadLocalRandom

#### 7. AnimationHelper
Utility to apply animations to ModelParts.
```java
AnimationHelper.applyAnimation(
    modelPart,      // ModelPart to animate
    "head",         // Bone name from Bedrock
    state,          // AnimationState
    animation,      // BedrockAnimation
    ageInTicks      // Current time
);
```

### Usage Example

```java
// In Model class
private AnimationState animState;
private Map<String, BedrockAnimation> animations;

public ModelConstructor(ModelPart root) {
    super(root);
    this.animState = new AnimationState();
    this.animations = BedrockAnimationParser.parseAnimationFile(
        "/animations/jsart_mannequin.animation.json"
    );
}

@Override
public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, 
                     float ageInTicks, float netHeadYaw, float netHeadPitch) {
    super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    
    // Determine which animation to play
    String animName = limbSwingAmount > 0.1F ? "animation.mannequin.walk" : "animation.mannequin.idle";
    animState.playAnimation(animName);
    animState.update(1.0F); // Delta time in ticks
    
    // Apply to bones
    BedrockAnimation anim = animations.get(animName);
    if (anim != null) {
        AnimationHelper.applyAnimation(this.head, "head", animState, anim, ageInTicks);
        AnimationHelper.applyAnimation(this.body, "bone2", animState, anim, ageInTicks);
        // ... apply to other bones
    }
}
```

---

## Particle System Enhancements

### AdvancedSilentBloodParticle

Matches Bedrock `jsart:silentblood` specification:

**Features:**
- **4-point color gradient** (#FF5B1414 → #FF5A1818 → #8C3F0F0F → #42601414)
  - Interpolates smoothly based on particle lifetime
- **Velocity-based rotation**
  - Derives direction from velocity vector
  - Minimum speed threshold (0.2) before rotating
- **Collision detection with bounce**
  - Collision radius: 0.05 blocks
  - Restitution coefficient: 0.3 (30% bounce)
  - Sliding friction on ground
- **Linear acceleration**
  - Gravity: -20.0 (Bedrock units, scaled for Minecraft)
  - Drag coefficient: 2.5
- **Initial velocity randomization**
  - Speed: `Math.random(3, 5)` from Bedrock
- **Rotation/spin**
  - Initial spin: `Math.random(10)` degrees/tick

**Parameters from Bedrock JSON:**
```json
{
  "minecraft:particle_lifetime_expression": { "max_lifetime": 25.0 },
  "minecraft:particle_initial_speed": "Math.random(3,5)",
  "minecraft:particle_motion_dynamic": {
    "linear_acceleration": [0.0, -20.0, 0.0],
    "linear_drag_coefficient": 2.5
  },
  "minecraft:particle_motion_collision": { "collision_radius": 0.05 },
  "minecraft:particle_appearance_tinting": {
    "color": {
      "gradient": {
        "0.0": "#FF5B1414",
        "0.33": "#FF5A1818",
        "0.67": "#8C3F0F0F",
        "1.0": "#42601414"
      }
    }
  }
}
```

### AdvancedVenenoParticle

Matches Bedrock `jsart:veneno` (poison cloud):

**Features:**
- **Sphere emitter** with outward direction
- **No gravity** (floats in air like gas)
- **Size reduction over time**
  - Initial: 0.2 + random(0.2)
  - Reduces by 50% over lifetime
- **High collision drag** (20.0)
  - Low restitution (0.2) - doesn't bounce much
  - Stops quickly when hitting surfaces
- **Red poison gradient**
  - Start: #FF850808 (bright red)
  - End: #00000000 (transparent)
  - Fades smoothly over lifetime
- **Short random lifetime**
  - Duration: `Math.random(0.2, 0.8)` seconds (2-8 ticks)

**Parameters from Bedrock JSON:**
```json
{
  "minecraft:emitter_shape_sphere": {
    "radius": 0.5,
    "direction": "outwards"
  },
  "minecraft:particle_lifetime_expression": {
    "max_lifetime": "Math.random(0.2, 0.8)"
  },
  "minecraft:particle_motion_collision": {
    "collision_drag": 20.0,
    "coefficient_of_restitution": 0.2
  },
  "minecraft:particle_appearance_billboard": {
    "size": [
      "(0.2 + variable.particle_random_1*0.2) - (0.1 * variable.particle_age)",
      "(0.2 + variable.particle_random_1*0.2) - (0.1 * variable.particle_age)"
    ]
  }
}
```

---

## Bedrock Animation Files

### Available Animations

| Entity | Animations | Total Lines |
|--------|-----------|-------------|
| Pyramid Head | idle, walk, move, attack, look_at_target | 508 |
| Mannequin | idle (ear wiggle, leg movement) | 1,245 |
| Lying Figure | idle, walk, death, eat | 794 |
| Lying Figure 2 | idle, walk | 686 |
| Lying Figure 3 | idle, walk | 795 |
| Mandarin | idle, walk, mad, new1-5 | 1,539 |
| Asphyxia | idle (breathing), look_at_target | 1,745 |
| Lady Fukuro | Complex multi-bone | 2,160 |
| Straightjacket | Complex animations | 2,160 |
| Pa Creaking variants | Full animation sets | 3,254 each |
| Others | Basic idle/walk | 194-1,035 |

**Total:** 24,548 lines of animation data across 19 files

### Sample Animation Structure

```json
{
  "format_version": "1.8.0",
  "animations": {
    "animation.jsart_mannequin.idle": {
      "loop": true,
      "animation_length": 1,
      "bones": {
        "right_ear": {
          "rotation": {
            "0.0": [0, 0, -5],
            "0.5": [0, 0, -3.75],
            "1.0": [0, 0, -12.5]
          }
        },
        "leg_back_left": {
          "rotation": {
            "0.0": [2.5, 0, 0],
            "1.0": [0, 0, 0]
          }
        }
      }
    }
  }
}
```

---

## Performance Considerations

### Animation Caching
- Animations parsed once on mod load
- Cached in static HashMap
- No runtime JSON parsing overhead

### Interpolation Optimization
- Linear interpolation (lerp) for position/scale
- Simple Euler angle interpolation for rotation
- Could be enhanced with quaternion slerp for smoother rotation

### Particle Performance
- Particles use standard Minecraft rendering pipeline
- Batch rendered by texture
- Automatically culled outside viewport
- Collision detection only when needed (onGround check)

---

## Future Enhancements

### Animation System
1. **Animation Controllers** - State machine for smooth transitions
2. **Full MoLang Support** - Complete expression evaluator with variables
3. **Quaternion Rotation** - Smoother bone rotation with slerp
4. **IK Support** - Inverse kinematics for dynamic limb positioning
5. **Particle-Bone Attachment** - Particles follow bone transforms

### Particle System
1. **Additional Emitter Shapes** - Point, disc, cylinder
2. **Particle Chains** - Sub-emitters for complex effects
3. **UV Scrolling** - Animated texture coordinates
4. **Light Emission** - Particles that emit light
5. **Sound Triggers** - Particles can play sounds

---

## Testing

### Animation Testing
1. Spawn entity with `/summon silenthill:mannequin`
2. Observe idle animation (ear wiggle, leg movement)
3. Make entity walk (aggro or push)
4. Verify smooth transition from idle to walk
5. Check animation loops correctly

### Particle Testing
1. Use `/particle silenthill:silent_blood ~ ~1 ~` to spawn blood
2. Observe:
   - Falls with gravity
   - Bounces on ground
   - Color fades through gradient
   - Rotates based on velocity
3. Use `/particle silenthill:veneno ~ ~1 ~` for poison
4. Observe:
   - Floats in air (no gravity)
   - Shrinks over time
   - Fades to transparent
   - High drag on collision

---

## Technical Limitations

1. **Scale Transforms** - ModelPart doesn't support scale, requires custom rendering
2. **MoLang Complexity** - Current parser handles basic expressions only
3. **Bone Hierarchy** - Simplified parent-child relationships
4. **Animation Blending** - Basic blend weight, no advanced state machines
5. **Particle Complexity** - Some Bedrock features not portable (emitter looping, sub-emitters)

Despite limitations, the system provides ~80% visual fidelity to Bedrock Edition animations.

---

**Version:** 1.0.0  
**Mod Version:** 1.21.1 NeoForge  
**Author:** Silent Hill Mod Team  
**Date:** 2026-02-12
