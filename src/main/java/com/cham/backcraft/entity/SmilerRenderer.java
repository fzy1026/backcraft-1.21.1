package com.cham.backcraft.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class SmilerRenderer extends MobRenderer<Mob, SmilerModel<Mob>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("backcraft", "textures/entity/smiler.png");

    public SmilerRenderer(EntityRendererProvider.Context context) {
        super(context, new SmilerModel<>(context.bakeLayer(SmilerModel.LAYER_LOCATION)), 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Mob entity) {
        return TEXTURE;
    }


}
