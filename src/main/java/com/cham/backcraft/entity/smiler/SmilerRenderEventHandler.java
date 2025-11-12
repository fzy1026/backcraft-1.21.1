package com.cham.backcraft.entity.smiler;

import com.cham.backcraft.entity.ModEntityRegister;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class SmilerRenderEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(
                ModEntityRegister.SMILER.get(),
                SmilerRenderer::new
        );
    }


    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(
                SmilerModel.LAYER_LOCATION,
                SmilerModel::createBodyLayer
        );
    }

}
