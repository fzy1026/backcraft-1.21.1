package com.cham.backcraft.entity;

import com.cham.backcraft.Backcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityRegister {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(Registries.ENTITY_TYPE, Backcraft.MODID);

    public static final DeferredHolder<EntityType<?>,EntityType<Smiler>> SMILER =
            ENTITIES.register("smiler",()->EntityType.Builder.of(Smiler::new, MobCategory.MONSTER)
                    .sized(1f,2f)
                    .build("smiler"));
    public static DeferredRegister<EntityType<?>> getRegister(){
        return ENTITIES;
    }
    public static EntityType getSmilerType(){
        return SMILER.get();
    }


}
