package com.cham.backcraft.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ModMobEffects extends MobEffect {
    protected ModMobEffects(MobEffectCategory category, int color) {
        super(category, color);
    }

    public static Holder<MobEffect> EffectHolder(Supplier<ModMobEffects> effectsSupplier){
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effectsSupplier.get());
    }

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, "backcraft");

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

    public static final Supplier<ModMobEffects> IMMUNITY =
            EFFECTS.register("immunity", () -> new ModMobEffects(MobEffectCategory.HARMFUL, 0xccf0ff));

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            List<Holder<MobEffect>> BAD_EFFECTS = Arrays.asList(
                    MobEffects.MOVEMENT_SLOWDOWN,
                    MobEffects.DIG_SLOWDOWN,
                    MobEffects.CONFUSION,
                    MobEffects.BLINDNESS,
                    MobEffects.HUNGER,
                    MobEffects.WEAKNESS,
                    MobEffects.POISON,
                    MobEffects.WITHER,
                    MobEffects.LEVITATION,
                    MobEffects.UNLUCK,
                    MobEffects.BAD_OMEN,
                    MobEffects.DARKNESS
            );
            for (Holder<MobEffect> effect : BAD_EFFECTS) {
                entity.removeEffect(effect);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }

}
