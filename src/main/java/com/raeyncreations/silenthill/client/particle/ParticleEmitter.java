package com.raeyncreations.silenthill.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Multi-stage particle emitter that can spawn sub-particles.
 * Supports Bedrock-style particle chains and complex effects.
 */
public class ParticleEmitter {
    private final ClientLevel level;
    private final List<EmitterStage> stages;
    private int currentStage;
    private int particlesEmitted;
    
    public ParticleEmitter(ClientLevel level) {
        this.level = level;
        this.stages = new ArrayList<>();
        this.currentStage = 0;
        this.particlesEmitted = 0;
    }
    
    /**
     * Add an emitter stage.
     * @param stage The emitter stage to add
     * @return This emitter for chaining
     */
    public ParticleEmitter addStage(EmitterStage stage) {
        stages.add(stage);
        return this;
    }
    
    /**
     * Update the emitter and spawn particles as needed.
     * @param deltaTime Time since last update in seconds
     */
    public void update(float deltaTime) {
        if (currentStage >= stages.size()) {
            return; // All stages complete
        }
        
        EmitterStage stage = stages.get(currentStage);
        stage.update(deltaTime, level);
        
        if (stage.isComplete()) {
            currentStage++;
        }
    }
    
    /**
     * Check if all emitter stages are complete.
     */
    public boolean isComplete() {
        return currentStage >= stages.size();
    }
    
    /**
     * Represents one stage of particle emission.
     */
    public static class EmitterStage {
        private final ParticleSpawner spawner;
        private final EmissionMode mode;
        private final int particleCount;
        private final float duration; // For continuous emission
        private final float rate; // Particles per second for continuous
        
        private float elapsedTime;
        private int emittedCount;
        
        public EmitterStage(ParticleSpawner spawner, EmissionMode mode, 
                           int particleCount, float duration, float rate) {
            this.spawner = spawner;
            this.mode = mode;
            this.particleCount = particleCount;
            this.duration = duration;
            this.rate = rate;
            this.elapsedTime = 0;
            this.emittedCount = 0;
        }
        
        /**
         * Create an instant emission stage (spawn all particles at once).
         */
        public static EmitterStage instant(ParticleSpawner spawner, int count) {
            return new EmitterStage(spawner, EmissionMode.INSTANT, count, 0, 0);
        }
        
        /**
         * Create a continuous emission stage (spawn particles over time).
         */
        public static EmitterStage continuous(ParticleSpawner spawner, float duration, float rate) {
            return new EmitterStage(spawner, EmissionMode.CONTINUOUS, -1, duration, rate);
        }
        
        public void update(float deltaTime, ClientLevel level) {
            elapsedTime += deltaTime;
            
            switch (mode) {
                case INSTANT:
                    if (emittedCount == 0) {
                        for (int i = 0; i < particleCount; i++) {
                            spawner.spawnParticle(level);
                        }
                        emittedCount = particleCount;
                    }
                    break;
                    
                case CONTINUOUS:
                    if (elapsedTime <= duration) {
                        int targetCount = (int)(elapsedTime * rate);
                        while (emittedCount < targetCount) {
                            spawner.spawnParticle(level);
                            emittedCount++;
                        }
                    }
                    break;
            }
        }
        
        public boolean isComplete() {
            switch (mode) {
                case INSTANT:
                    return emittedCount >= particleCount;
                case CONTINUOUS:
                    return elapsedTime >= duration;
                default:
                    return true;
            }
        }
    }
    
    /**
     * Emission mode for particle stages.
     */
    public enum EmissionMode {
        INSTANT,    // Spawn all particles at once
        CONTINUOUS  // Spawn particles over time
    }
    
    /**
     * Interface for spawning particles.
     */
    @FunctionalInterface
    public interface ParticleSpawner {
        void spawnParticle(ClientLevel level);
    }
}
