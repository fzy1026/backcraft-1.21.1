package com.cham.backcraft.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
import java.util.List;

public class ImmunityEffect extends MobEffect {
    protected ImmunityEffect() {
        super(MobEffectCategory.BENEFICIAL,0xccf0ff);
    }

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
                    MobEffects.DARKNESS,
                    ModMobEffects.DIZZINESS
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
