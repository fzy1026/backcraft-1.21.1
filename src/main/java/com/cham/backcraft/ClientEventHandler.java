package com.cham.backcraft;

import com.cham.backcraft.entity.SmilerModel;
import com.cham.backcraft.entity.SmilerRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Backcraft.MODID,bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(
                com.cham.backcraft.entity.ModEntityRegister.getSmilerType(),
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
