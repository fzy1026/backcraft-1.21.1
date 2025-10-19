package com.cham.backcraft.item;

import com.cham.backcraft.effect.ModMobEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModPotions {


    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION,"backcraft");

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    public static final Supplier<Potion> ALMOND_WATER =
            POTIONS.register("almond_water",()->new Potion(new MobEffectInstance(ModMobEffects.EffectHolder(ModMobEffects.IMMUNITY),3600,0)));

}
