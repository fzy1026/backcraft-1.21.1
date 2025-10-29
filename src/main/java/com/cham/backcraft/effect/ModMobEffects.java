package com.cham.backcraft.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, "backcraft");

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

    public static final DeferredHolder<MobEffect, ImmunityEffect> IMMUNITY =
            EFFECTS.register("immunity", ImmunityEffect::new);
    public static final DeferredHolder<MobEffect, DizzinessEffect> DIZZINESS =
            EFFECTS.register("dizziness", DizzinessEffect::new);

}
