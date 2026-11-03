package com.cham.backcraft.item;

import com.cham.backcraft.effect.ModMobEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {


    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION,"backcraft");

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    public static final DeferredHolder<Potion,Potion> ALMOND_WATER =
            POTIONS.register("almond_water",()->new Potion(new MobEffectInstance(ModMobEffects.IMMUNITY,3600,0)));
    public static final DeferredHolder<Potion,Potion> LIQUID_PAIN =
            POTIONS.register("liquid_pain",()->new Potion(new MobEffectInstance(ModMobEffects.PAIN,3600,0)));

}
