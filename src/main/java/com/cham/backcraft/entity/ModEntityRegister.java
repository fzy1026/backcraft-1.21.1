package com.cham.backcraft.entity;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.entity.smiler.Smiler;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityRegister {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Backcraft.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Smiler>> SMILER =
            ENTITIES.register("smiler", () -> EntityType.Builder.of(Smiler::new, MobCategory.MONSTER)
                    .sized(1f, 2f)
                    .build("smiler"));


    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }

    public static EntityType<Smiler> getSmilerType() {
        return SMILER.get();
    }


}
