package com.raeyncreations.silenthill.client.animation;

/**
 * Context holder for MoLang expression evaluation.
 * Contains all query variable values and entity state information.
 * Thread-safe through immutable design - create new instances for updates.
 * 
 * <p>Example usage:
 * <pre>
 * MoLangContext context = new MoLangContext.Builder()
 *     .lifeTime(entity.tickCount / 20.0f)
 *     .health(entity.getHealth())
 *     .maxHealth(entity.getMaxHealth())
 *     .build();
 * 
 * float result = expression.evaluate(context);
 * </pre>
 * 
 * @author RaEyn Creations
 */
public final class MoLangContext {
    // Time-based queries
    private final float lifeTime;
    private final float animTime;
    
    // Movement queries
    private final boolean isMoving;
    private final float movementSpeed;
    private final boolean isOnGround;
    private final boolean isInWater;
    
    // Rotation queries
    private final float yaw;
    private final float pitch;
    private final float targetXRotation;
    private final float targetYRotation;
    
    // Health queries
    private final float health;
    private final float maxHealth;
    private final float hurtTime;
    
    // State queries
    private final boolean isSneaking;
    private final boolean isSprinting;
    
    private MoLangContext(Builder builder) {
        this.lifeTime = builder.lifeTime;
        this.animTime = builder.animTime;
        this.isMoving = builder.isMoving;
        this.movementSpeed = builder.movementSpeed;
        this.isOnGround = builder.isOnGround;
        this.isInWater = builder.isInWater;
        this.yaw = builder.yaw;
        this.pitch = builder.pitch;
        this.targetXRotation = builder.targetXRotation;
        this.targetYRotation = builder.targetYRotation;
        this.health = builder.health;
        this.maxHealth = builder.maxHealth;
        this.hurtTime = builder.hurtTime;
        this.isSneaking = builder.isSneaking;
        this.isSprinting = builder.isSprinting;
    }
    
    /**
     * Create a context from a Minecraft LivingEntity.
     * Uses reflection to avoid direct dependency on Minecraft classes.
     * 
     * <p><b>Security Note:</b> This method uses setAccessible(true) to access private fields.
     * It should only be used with trusted entity objects from the Minecraft runtime.
     * The reflection access is necessary to avoid compile-time dependencies on Minecraft classes
     * while still extracting entity state for animations.
     * 
     * @param entity The entity to extract state from (must be net.minecraft.world.entity.LivingEntity)
     * @return A new context with entity state
     */
    public static MoLangContext fromEntity(Object entity) {
        try {
            // Use reflection to access entity properties
            Class<?> entityClass = entity.getClass();
            
            // Get methods via reflection
            float tickCount = getFieldFloat(entity, "tickCount");
            float deltaMovement = getVectorLength(entity, "getDeltaMovement");
            boolean onGround = getFieldBoolean(entity, "onGround");
            boolean inWater = invokeMethod(entity, "isInWater", false);
            float yRot = invokeMethod(entity, "getYRot", 0f);
            float xRot = invokeMethod(entity, "getXRot", 0f);
            float health = invokeMethod(entity, "getHealth", 20f);
            float maxHealth = invokeMethod(entity, "getMaxHealth", 20f);
            float hurtTime = getFieldFloat(entity, "hurtTime");
            boolean crouching = invokeMethod(entity, "isCrouching", false);
            boolean sprinting = invokeMethod(entity, "isSprinting", false);
            
            return new Builder()
                .lifeTime(tickCount / 20.0f)
                .animTime(tickCount / 20.0f)
                .isMoving(deltaMovement > 0.001f)
                .movementSpeed(deltaMovement)
                .isOnGround(onGround)
                .isInWater(inWater)
                .yaw(yRot)
                .pitch(xRot)
                .targetXRotation(xRot)
                .targetYRotation(yRot)
                .health(health)
                .maxHealth(maxHealth)
                .hurtTime(hurtTime / 20.0f)
                .isSneaking(crouching)
                .isSprinting(sprinting)
                .build();
        } catch (Exception e) {
            // Fallback to default context if reflection fails
            return new Builder().build();
        }
    }
    
    private static float getFieldFloat(Object obj, String fieldName) {
        try {
            // Try public field first
            var field = obj.getClass().getField(fieldName);
            Object value = field.get(obj);
            if (value instanceof Number) {
                return ((Number) value).floatValue();
            }
        } catch (NoSuchFieldException e) {
            // Try private/protected field
            try {
                var field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value instanceof Number) {
                    return ((Number) value).floatValue();
                }
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
        }
        return 0f;
    }
    
    private static boolean getFieldBoolean(Object obj, String fieldName) {
        try {
            // Try public field first
            var field = obj.getClass().getField(fieldName);
            Object value = field.get(obj);
            if (value instanceof Boolean) {
                return (Boolean) value;
            }
        } catch (NoSuchFieldException e) {
            // Try private/protected field
            try {
                var field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value instanceof Boolean) {
                    return (Boolean) value;
                }
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T invokeMethod(Object obj, String methodName, T defaultValue) {
        try {
            var method = obj.getClass().getMethod(methodName);
            return (T) method.invoke(obj);
        } catch (Exception ignored) {
        }
        return defaultValue;
    }
    
    private static float getVectorLength(Object obj, String methodName) {
        try {
            var method = obj.getClass().getMethod(methodName);
            Object vec = method.invoke(obj);
            if (vec != null) {
                var lengthMethod = vec.getClass().getMethod("length");
                Object length = lengthMethod.invoke(vec);
                if (length instanceof Number) {
                    return ((Number) length).floatValue();
                }
            }
        } catch (Exception ignored) {
        }
        return 0f;
    }
    
    /**
     * Get the value of a query variable.
     * @param queryName The query name (without "query." prefix)
     * @return The query value, or 0 if unknown
     */
    public float getQuery(String queryName) {
        switch (queryName) {
            // Time
            case "life_time": return lifeTime;
            case "anim_time": return animTime;
            
            // Movement
            case "is_moving": return isMoving ? 1.0f : 0.0f;
            case "movement_speed": return movementSpeed;
            case "is_on_ground": return isOnGround ? 1.0f : 0.0f;
            case "is_in_water": return isInWater ? 1.0f : 0.0f;
            
            // Rotation
            case "yaw": return yaw;
            case "pitch": return pitch;
            case "target_x_rotation": return targetXRotation;
            case "target_y_rotation": return targetYRotation;
            
            // Health
            case "health": return health;
            case "max_health": return maxHealth;
            case "hurt_time": return hurtTime;
            
            // State
            case "is_sneaking": return isSneaking ? 1.0f : 0.0f;
            case "is_sprinting": return isSprinting ? 1.0f : 0.0f;
            
            default: return 0.0f;
        }
    }
    
    // Getters for all fields
    public float getLifeTime() { return lifeTime; }
    public float getAnimTime() { return animTime; }
    public boolean isMoving() { return isMoving; }
    public float getMovementSpeed() { return movementSpeed; }
    public boolean isOnGround() { return isOnGround; }
    public boolean isInWater() { return isInWater; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }
    public float getTargetXRotation() { return targetXRotation; }
    public float getTargetYRotation() { return targetYRotation; }
    public float getHealth() { return health; }
    public float getMaxHealth() { return maxHealth; }
    public float getHurtTime() { return hurtTime; }
    public boolean isSneaking() { return isSneaking; }
    public boolean isSprinting() { return isSprinting; }
    
    /**
     * Builder for creating MoLangContext instances.
     */
    public static class Builder {
        private float lifeTime = 0;
        private float animTime = 0;
        private boolean isMoving = false;
        private float movementSpeed = 0;
        private boolean isOnGround = true;
        private boolean isInWater = false;
        private float yaw = 0;
        private float pitch = 0;
        private float targetXRotation = 0;
        private float targetYRotation = 0;
        private float health = 20;
        private float maxHealth = 20;
        private float hurtTime = 0;
        private boolean isSneaking = false;
        private boolean isSprinting = false;
        
        public Builder lifeTime(float lifeTime) {
            this.lifeTime = lifeTime;
            return this;
        }
        
        public Builder animTime(float animTime) {
            this.animTime = animTime;
            return this;
        }
        
        public Builder isMoving(boolean isMoving) {
            this.isMoving = isMoving;
            return this;
        }
        
        public Builder movementSpeed(float movementSpeed) {
            this.movementSpeed = movementSpeed;
            return this;
        }
        
        public Builder isOnGround(boolean isOnGround) {
            this.isOnGround = isOnGround;
            return this;
        }
        
        public Builder isInWater(boolean isInWater) {
            this.isInWater = isInWater;
            return this;
        }
        
        public Builder yaw(float yaw) {
            this.yaw = yaw;
            return this;
        }
        
        public Builder pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }
        
        public Builder targetXRotation(float targetXRotation) {
            this.targetXRotation = targetXRotation;
            return this;
        }
        
        public Builder targetYRotation(float targetYRotation) {
            this.targetYRotation = targetYRotation;
            return this;
        }
        
        public Builder health(float health) {
            this.health = health;
            return this;
        }
        
        public Builder maxHealth(float maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }
        
        public Builder hurtTime(float hurtTime) {
            this.hurtTime = hurtTime;
            return this;
        }
        
        public Builder isSneaking(boolean isSneaking) {
            this.isSneaking = isSneaking;
            return this;
        }
        
        public Builder isSprinting(boolean isSprinting) {
            this.isSprinting = isSprinting;
            return this;
        }
        
        public MoLangContext build() {
            return new MoLangContext(this);
        }
    }
}
