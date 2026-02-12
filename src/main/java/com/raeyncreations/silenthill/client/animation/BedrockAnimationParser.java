package com.raeyncreations.silenthill.client.animation;

import com.google.gson.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses Bedrock JSON animation files into BedrockAnimation objects.
 * Caches parsed animations for performance.
 */
public class BedrockAnimationParser {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Map<String, Map<String, BedrockAnimation>> ANIMATION_CACHE = new HashMap<>();
    
    /**
     * Parse animations from a JSON file in the resource pack.
     * @param resourcePath Path to the animation JSON file (e.g., "/animations/entity.json")
     * @return Map of animation name to BedrockAnimation object
     */
    public static Map<String, BedrockAnimation> parseAnimationFile(String resourcePath) {
        // Check cache first
        if (ANIMATION_CACHE.containsKey(resourcePath)) {
            return ANIMATION_CACHE.get(resourcePath);
        }
        
        Map<String, BedrockAnimation> animations = new HashMap<>();
        
        try {
            InputStream stream = BedrockAnimationParser.class.getResourceAsStream(resourcePath);
            if (stream == null) {
                System.err.println("Animation file not found: " + resourcePath);
                return animations;
            }
            
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            JsonObject root = GSON.fromJson(reader, JsonObject.class);
            
            if (root == null || !root.has("animations")) {
                System.err.println("Invalid animation file format: " + resourcePath);
                return animations;
            }
            
            JsonObject animationsObject = root.getAsJsonObject("animations");
            
            for (Map.Entry<String, JsonElement> entry : animationsObject.entrySet()) {
                String animationName = entry.getKey();
                JsonObject animationData = entry.getValue().getAsJsonObject();
                
                try {
                    BedrockAnimation animation = parseAnimation(animationName, animationData);
                    animations.put(animationName, animation);
                } catch (Exception e) {
                    System.err.println("Error parsing animation '" + animationName + "': " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            // Cache the results
            ANIMATION_CACHE.put(resourcePath, animations);
            
        } catch (Exception e) {
            System.err.println("Error reading animation file '" + resourcePath + "': " + e.getMessage());
            e.printStackTrace();
        }
        
        return animations;
    }
    
    private static BedrockAnimation parseAnimation(String name, JsonObject data) {
        float animationLength = data.has("animation_length") 
            ? data.get("animation_length").getAsFloat() 
            : 0;
        
        boolean loop = data.has("loop") 
            ? data.get("loop").getAsBoolean() 
            : false;
        
        BedrockAnimation animation = new BedrockAnimation(name, animationLength, loop);
        
        if (data.has("bones")) {
            JsonObject bones = data.getAsJsonObject("bones");
            
            for (Map.Entry<String, JsonElement> entry : bones.entrySet()) {
                String boneName = entry.getKey();
                JsonObject boneData = entry.getValue().getAsJsonObject();
                
                AnimationChannel channel = parseBoneChannel(boneName, boneData);
                animation.addBoneChannel(channel);
            }
        }
        
        return animation;
    }
    
    private static AnimationChannel parseBoneChannel(String boneName, JsonObject boneData) {
        AnimationChannel channel = new AnimationChannel(boneName);
        
        // Parse rotation keyframes
        if (boneData.has("rotation")) {
            parseKeyframes(channel, boneData.get("rotation"), KeyframeType.ROTATION);
        }
        
        // Parse position keyframes
        if (boneData.has("position")) {
            parseKeyframes(channel, boneData.get("position"), KeyframeType.POSITION);
        }
        
        // Parse scale keyframes
        if (boneData.has("scale")) {
            parseKeyframes(channel, boneData.get("scale"), KeyframeType.SCALE);
        }
        
        return channel;
    }
    
    private static void parseKeyframes(AnimationChannel channel, JsonElement keyframeData, KeyframeType type) {
        if (keyframeData.isJsonArray()) {
            // Single keyframe at time 0
            float[] values = parseVector(keyframeData.getAsJsonArray());
            AnimationKeyframe keyframe = createKeyframe(0, type, values);
            channel.addKeyframe(keyframe);
            
        } else if (keyframeData.isJsonObject()) {
            // Multiple keyframes with time keys
            JsonObject keyframes = keyframeData.getAsJsonObject();
            
            for (Map.Entry<String, JsonElement> entry : keyframes.entrySet()) {
                try {
                    float time = Float.parseFloat(entry.getKey());
                    JsonElement valueElement = entry.getValue();
                    
                    float[] values;
                    if (valueElement.isJsonArray()) {
                        values = parseVector(valueElement.getAsJsonArray());
                    } else if (valueElement.isJsonObject()) {
                        // Handle pre/post keyframe data (use "post" if available)
                        JsonObject keyframeObj = valueElement.getAsJsonObject();
                        if (keyframeObj.has("post")) {
                            values = parseVector(keyframeObj.getAsJsonArray("post"));
                        } else if (keyframeObj.has("pre")) {
                            values = parseVector(keyframeObj.getAsJsonArray("pre"));
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    
                    AnimationKeyframe keyframe = createKeyframe(time, type, values);
                    channel.addKeyframe(keyframe);
                    
                } catch (NumberFormatException e) {
                    System.err.println("Invalid keyframe time: " + entry.getKey());
                }
            }
        }
    }
    
    private static float[] parseVector(JsonArray array) {
        float[] values = new float[3];
        
        for (int i = 0; i < Math.min(3, array.size()); i++) {
            JsonElement element = array.get(i);
            
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
                values[i] = element.getAsFloat();
            } else if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                // Handle MoLang expression
                String expr = element.getAsString();
                if (MoLangExpression.isMoLangExpression(expr)) {
                    MoLangExpression molang = new MoLangExpression(expr);
                    values[i] = molang.evaluate(0); // Evaluate with time=0 for static value
                } else {
                    try {
                        values[i] = Float.parseFloat(expr);
                    } catch (NumberFormatException e) {
                        values[i] = 0;
                    }
                }
            } else {
                values[i] = 0;
            }
        }
        
        return values;
    }
    
    private static AnimationKeyframe createKeyframe(float time, KeyframeType type, float[] values) {
        switch (type) {
            case ROTATION:
                return new AnimationKeyframe(time, values, null, null);
            case POSITION:
                return new AnimationKeyframe(time, null, values, null);
            case SCALE:
                return new AnimationKeyframe(time, null, null, values);
            default:
                return new AnimationKeyframe(time, null, null, null);
        }
    }
    
    /**
     * Clear the animation cache (useful for resource reloading).
     */
    public static void clearCache() {
        ANIMATION_CACHE.clear();
    }
    
    /**
     * Get a specific animation from a file.
     * @param resourcePath Path to the animation JSON file
     * @param animationName Name of the animation
     * @return The animation or null if not found
     */
    public static BedrockAnimation getAnimation(String resourcePath, String animationName) {
        Map<String, BedrockAnimation> animations = parseAnimationFile(resourcePath);
        return animations.get(animationName);
    }
    
    private enum KeyframeType {
        ROTATION, POSITION, SCALE
    }
}
