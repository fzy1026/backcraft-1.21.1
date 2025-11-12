package com.cham.backcraft.entity;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.entity.smiler.Smiler;
import com.cham.backcraft.entity.window.Window;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


@EventBusSubscriber(modid = Backcraft.MODID)
public class ModEntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Backcraft.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Smiler>> SMILER =
            ENTITIES.register("smiler", () -> EntityType.Builder.of(Smiler::new, MobCategory.MONSTER)
                    .sized(0.7f, 1.7f)
                    .build("smiler"));
    public static final DeferredHolder<EntityType<?>, EntityType<Window>> WINDOW =
            ENTITIES.register("window", () -> EntityType.Builder.of(Window::new, MobCategory.MONSTER)
                    .sized(0.7f, 1.7f)
                    .build("window"));



    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }

    @SubscribeEvent
    private static void addEntityAttributes(EntityAttributeCreationEvent event){
        event.put(
                SMILER.get(),
                Smiler.createAttributes().build()
        );
        event.put(WINDOW.get(),
                Window.createAttributes().build());

    }

}
