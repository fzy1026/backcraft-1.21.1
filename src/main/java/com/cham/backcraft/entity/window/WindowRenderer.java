package com.cham.backcraft.entity.window;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;

public class WindowRenderer extends MobRenderer<Mob, WindowModel<Mob>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("backcraft", "textures/entity/window.png");

    public WindowRenderer(EntityRendererProvider.Context context) {
        super(context, new WindowModel<>(context.bakeLayer(WindowModel.LAYER_LOCATION)), 0.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Mob entity) {
        return TEXTURE;
    }


}
