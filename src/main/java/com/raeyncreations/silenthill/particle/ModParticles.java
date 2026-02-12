package com.raeyncreations.silenthill.particle;

import com.raeyncreations.silenthill.SilentHillMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, SilentHillMod.MOD_ID);

    // Blood particle effect - small blood drips
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SILENT_BLOOD =
            PARTICLE_TYPES.register("silent_blood", () -> new SimpleParticleType(true));

    // Blood splatter particle effect - larger blood spray
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SILENT_BLOOD_2 =
            PARTICLE_TYPES.register("silent_blood_2", () -> new SimpleParticleType(true));

    // Poison/venom particle effect
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VENENO =
            PARTICLE_TYPES.register("veneno", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
