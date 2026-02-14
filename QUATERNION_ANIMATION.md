# Quaternion-Based Animation System

This document describes the quaternion-based rotation system for smoother animations in the Silent Hill mod.

## Overview

The quaternion system provides superior rotation interpolation compared to traditional Euler angle linear interpolation. It eliminates gimbal lock and produces smoother, more natural-looking animations.

## Features

### 1. **Quaternion Class** (`Quaternion.java`)
A complete quaternion implementation with:
- **Components**: x, y, z, w (Hamilton notation)
- **Creation**: identity(), fromEuler(), fromEulerDegrees(), fromAxisAngle()
- **Conversion**: toEuler(), toEulerDegrees()
- **Interpolation**: slerp() - Spherical Linear Interpolation
- **Operations**: multiply(), normalize(), conjugate(), inverse(), dot()
- **Utilities**: length(), lengthSquared(), isNormalized()

### 2. **Enhanced AnimationChannel**
New method: `getQuaternionAt(float time)`
- Uses quaternion slerp for smooth rotation interpolation
- Converts Euler keyframes to quaternions automatically
- Backwards compatible with existing `getRotationAtTime()` method

### 3. **Updated AnimationHelper**
Two animation application methods:
- `applyAnimationWithQuaternion()` - Uses quaternion slerp (recommended)
- `applyAnimation()` - Traditional linear interpolation (backwards compatible)

## Usage

### Basic Usage

```java
// Get quaternion rotation at specific time
AnimationChannel channel = animation.getChannelForBone("head");
Quaternion rotation = channel.getQuaternionAt(animTime);

// Convert to Euler angles for ModelPart
float[] euler = rotation.toEuler();
modelPart.xRot = euler[0];
modelPart.yRot = euler[1];
modelPart.zRot = euler[2];
```

### Using with AnimationHelper

```java
// Apply animation with quaternion interpolation
AnimationHelper.applyAnimationWithQuaternion(
    modelPart,
    "head",
    animationState,
    bedrockAnimation,
    ageInTicks
);

// Or use traditional method for backwards compatibility
AnimationHelper.applyAnimation(
    modelPart,
    "head",
    animationState,
    bedrockAnimation,
    ageInTicks
);
```

### Creating Quaternions

```java
// From Euler angles (radians)
Quaternion q1 = Quaternion.fromEuler(pitch, yaw, roll);

// From Euler angles (degrees)
Quaternion q2 = Quaternion.fromEulerDegrees(45, 30, 60);

// From axis-angle
Quaternion q3 = Quaternion.fromAxisAngle(0, 1, 0, Math.PI / 2); // 90° around Y

// Identity (no rotation)
Quaternion identity = Quaternion.identity();
```

### Interpolation

```java
// Spherical linear interpolation for smooth rotation
Quaternion start = Quaternion.fromEulerDegrees(0, 0, 0);
Quaternion end = Quaternion.fromEulerDegrees(90, 45, 30);

// Interpolate halfway
Quaternion mid = Quaternion.slerp(start, end, 0.5f);

// Interpolate with custom time value (0.0 to 1.0)
Quaternion custom = Quaternion.slerp(start, end, 0.75f);
```

### Composing Rotations

```java
// Multiply quaternions to compose rotations
Quaternion rotX = Quaternion.fromEulerDegrees(90, 0, 0);
Quaternion rotY = Quaternion.fromEulerDegrees(0, 90, 0);

// Apply X rotation first, then Y
Quaternion combined = rotX.multiply(rotY);
```

## Advantages Over Euler Angles

### 1. **No Gimbal Lock**
Quaternions don't suffer from gimbal lock, a problem where two rotation axes align and lose a degree of freedom.

### 2. **Smoother Interpolation**
Slerp provides constant angular velocity interpolation, creating more natural-looking animations.

```java
// Euler lerp - can have non-uniform speed
float[] eulerMid = AnimationKeyframe.lerp(eulerStart, eulerEnd, 0.5f);

// Quaternion slerp - constant speed, smoother
Quaternion quatMid = Quaternion.slerp(quatStart, quatEnd, 0.5f);
```

### 3. **Shorter Path**
Slerp automatically takes the shortest path between rotations.

### 4. **Numerical Stability**
Quaternions are more numerically stable for repeated operations.

## Implementation Details

### Slerp Algorithm
The implementation handles several edge cases:

1. **Negative Dot Product**: Takes shorter path by negating one quaternion
2. **Near-Parallel Quaternions**: Falls back to linear interpolation for numerical stability
3. **Clamping**: Ensures interpolation factor is in [0, 1]
4. **Normalization**: Results are always normalized

```java
public static Quaternion slerp(Quaternion q1, Quaternion q2, float t) {
    // Clamp t
    t = Math.max(0.0f, Math.min(1.0f, t));
    
    // Compute dot product
    float dot = q1.dot(q2);
    
    // Take shorter path
    if (dot < 0.0f) {
        q2 = negate(q2);
        dot = -dot;
    }
    
    // Linear interpolation for very close quaternions
    if (dot > 0.9995f) {
        return lerp(q1, q2, t).normalize();
    }
    
    // Spherical linear interpolation
    float theta = acos(dot);
    float sinTheta = sin(theta);
    float w1 = sin((1-t) * theta) / sinTheta;
    float w2 = sin(t * theta) / sinTheta;
    
    return w1*q1 + w2*q2;
}
```

### Euler Conversion
Uses YXZ rotation order (Minecraft/Bedrock standard):

```java
// To Euler (handles gimbal lock)
public float[] toEuler() {
    // Gimbal lock detection
    float sinp = 2 * (w*x - y*z);
    if (abs(sinp) >= 1) {
        pitch = copySign(PI/2, sinp); // ±90°
    } else {
        pitch = asin(sinp);
    }
    
    roll = atan2(2*(w*z + x*y), 1 - 2*(y*y + z*z));
    yaw = atan2(2*(w*y + x*z), 1 - 2*(x*x + y*y));
    
    return [pitch, yaw, roll];
}
```

## Performance Considerations

### Memory Allocation
The quaternion class is immutable to prevent accidental modification. Operations return new instances.

```java
// Each operation creates a new quaternion
Quaternion q1 = Quaternion.identity();
Quaternion q2 = q1.normalize();  // New instance
Quaternion q3 = q2.multiply(q1); // New instance
```

### Optimization Tips

1. **Reuse Quaternions**: Cache frequently-used quaternions
2. **Avoid Unnecessary Conversions**: Work in quaternion space when possible
3. **Batch Operations**: Process multiple bones together

```java
// Good - reuse identity
private static final Quaternion IDENTITY = Quaternion.identity();

// Good - cache intermediate values
Quaternion rotation = channel.getQuaternionAt(time);
float[] euler = rotation.toEuler();

// Avoid - repeated conversions
for (int i = 0; i < 100; i++) {
    Quaternion q = Quaternion.fromEuler(x, y, z).toEuler(); // Wasteful
}
```

## Testing

Comprehensive unit tests are provided in `QuaternionTest.java`:

- Identity and creation methods
- Euler conversion round-trips
- Axis-angle conversion
- Slerp interpolation (edge cases included)
- Quaternion multiplication
- Normalization
- Inverse and conjugate
- Gimbal lock handling
- Numerical stability

Run tests with:
```bash
./gradlew test
```

## Migration Guide

### Migrating from Linear Interpolation

**Before:**
```java
AnimationHelper.applyAnimation(part, boneName, state, animation, ticks);
```

**After:**
```java
AnimationHelper.applyAnimationWithQuaternion(part, boneName, state, animation, ticks);
```

### Existing Code Compatibility

The system is fully backwards compatible:
- `getRotationAtTime()` still works with linear interpolation
- `applyAnimation()` uses traditional method
- New methods are additions, not replacements

## References

- [Quaternions and spatial rotation](https://en.wikipedia.org/wiki/Quaternions_and_spatial_rotation)
- [Slerp - Wikipedia](https://en.wikipedia.org/wiki/Slerp)
- [Understanding Quaternions](https://www.3dgep.com/understanding-quaternions/)
- [Quaternion Mathematics](https://danceswithcode.net/engineeringnotes/quaternions/quaternions.html)

## License

Part of the JS-Silent-Hill mod. See main project for license information.
