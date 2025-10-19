package com.cham.backcraft.entity.smiler;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SmilerGlowLayer<T extends Smiler,M extends SmilerModel<T>> extends RenderLayer<T,M> {

    private static final ResourceLocation GLOW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("backcraft","textures/entity/smiler_glow.png");
    public SmilerGlowLayer(RenderLayerParent<T, M> renderer){
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource BufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isInvisible()) {
            VertexConsumer glowConsumer =
                    BufferSource.getBuffer(RenderType.entityTranslucentEmissive(GLOW_TEXTURE));
            this.getParentModel().renderToBuffer(poseStack, glowConsumer, 1572860, OverlayTexture.NO_OVERLAY);
        }
    }
}
