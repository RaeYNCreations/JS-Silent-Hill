package com.raeyncreations.silenthill;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Silent Hill Mod configuration
 */
public class SilentHillConfig {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;
    
    // Easy access to config values
    public static ModConfigSpec.IntValue SAFE_HAVEN_RADIUS;
    public static ModConfigSpec.BooleanValue SAFE_HAVEN_DESPAWN_ENABLED;
    
    static {
        Pair<Common, ModConfigSpec> commonPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }
    
    /**
     * Common configuration (server-side)
     */
    public static class Common {
        public Common(ModConfigSpec.Builder builder) {
            builder.comment("Safe Haven Obelisk Settings").push("safe_haven");
            
            SAFE_HAVEN_RADIUS = builder
                .comment("Radius of Safe Haven protection in blocks")
                .defineInRange("radius", 32, 1, 128);
                
            SAFE_HAVEN_DESPAWN_ENABLED = builder
                .comment("Enable mob despawning inside Safe Haven zones (removes mobs that enter after spawning)")
                .define("despawn_enabled", true);
                
            builder.pop();
        }
    }
}
