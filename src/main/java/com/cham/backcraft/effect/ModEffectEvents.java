package com.cham.backcraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ModEffectEvents {
    private static final Set<UUID> affectedMobs = new HashSet<>();

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event){
        if(event.getEffectInstance() == null)
            return;
        LivingEntity entity = event.getEntity();
        MobEffect effect = event.getEffectInstance().getEffect().value();

        if(effect instanceof DizzinessEffect && entity instanceof Mob mob){
            mob.setNoAi(false);
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Remove event){
        if(event.getEffectInstance() == null)
            return;
        LivingEntity entity = event.getEntity();
        MobEffect effect = event.getEffectInstance().getEffect().value();

        if(effect instanceof DizzinessEffect && entity instanceof Mob mob){
            mob.setNoAi(false);
        }
    }

}
