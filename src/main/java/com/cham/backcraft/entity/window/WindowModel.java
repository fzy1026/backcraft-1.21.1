package com.cham.backcraft.entity.window;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.cham.backcraft.Backcraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class WindowModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Backcraft.MODID, "windowmodel"), "main");
    private final ModelPart Right;
    private final ModelPart Left;
    private final ModelPart root;

    public WindowModel(ModelPart root) {
        this.Right = root.getChild("Right");
        this.Left = root.getChild("Left");
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Right = partdefinition.addOrReplaceChild("Right", CubeListBuilder.create().texOffs(0, 17).addBox(0.0F, -7.5F, 0.0F, 8.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 4).addBox(0.0F, -7.5F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 6).addBox(0.0F, 7.5F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 23).addBox(7.0F, -6.5F, -1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 23).addBox(0.0F, -6.5F, -1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 7.0F));

        PartDefinition Left = partdefinition.addOrReplaceChild("Left", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -7.5F, 0.0F, 8.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(-8.0F, -7.5F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 2).addBox(-8.0F, 7.5F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 8).addBox(-1.0F, -6.5F, -1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 8).addBox(-8.0F, -6.5F, -1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 7.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack,@NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
