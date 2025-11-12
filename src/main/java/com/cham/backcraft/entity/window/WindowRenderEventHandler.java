package com.cham.backcraft.entity.window;

import com.cham.backcraft.entity.ModEntityRegister;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class WindowRenderEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntityRegister.WINDOW.get(),
                WindowRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                WindowModel.LAYER_LOCATION,
                WindowModel::createBodyLayer
        );
    }
}
