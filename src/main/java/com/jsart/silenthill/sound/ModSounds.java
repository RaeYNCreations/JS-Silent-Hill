package com.jsart.silenthill.sound;

import com.jsart.silenthill.SilentHillMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = 
        DeferredRegister.create(Registries.SOUND_EVENT, SilentHillMod.MOD_ID);

    // Mannequin sounds
    public static final DeferredHolder<SoundEvent, SoundEvent> MANNEQUIN_IDLE = 
        registerSound("mob.mannequin.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> MANNEQUIN_HURT = 
        registerSound("mob.mannequin.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> MANNEQUIN_DEATH = 
        registerSound("mob.mannequin.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> MANNEQUIN_ANGRY = 
        registerSound("mob.mannequin.angry");

    // Pyramid Head sounds
    public static final DeferredHolder<SoundEvent, SoundEvent> PYRAMIDHEAD_HIT = 
        registerSound("mob.pyramidhead.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> PYRAMIDHEAD_THROW = 
        registerSound("mob.pyramidhead.throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> PYRAMIDHEAD_CRACK = 
        registerSound("mob.pyramidhead.crack");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
