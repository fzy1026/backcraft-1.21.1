package com.cham.backcraft.entity.smiler;// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.cham.backcraft.Backcraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class SmilerModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Backcraft.MODID, "smilermodel"), "main");
	private final ModelPart eye1;
	private final ModelPart eye2;
	private final ModelPart upteeth;
	private final ModelPart downteeth;
    private final ModelPart root;
    private final AnimationState chewAnimationState = new AnimationState();

    public static final AnimationDefinition chew = AnimationDefinition.Builder.withLength(0.96F).looping()
            .addAnimation("eye1", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 1.0F, -0.25F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.96F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("eye2", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.25F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.96F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("upteeth", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, 0.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.96F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("downteeth", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.48F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

	public SmilerModel(ModelPart root) {
        this.root = root;
		this.eye1 = root.getChild("eye1");
		this.eye2 = root.getChild("eye2");
		this.upteeth = root.getChild("upteeth");
		this.downteeth = root.getChild("downteeth");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eye1 = partdefinition.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(4, 12).addBox(-0.5F, -2.0F, -2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 6).addBox(-0.5F, -2.0F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 12).addBox(-0.5F, -3.0F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 8).addBox(-0.5F, -3.0F, -2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.75F));

		PartDefinition eye2 = partdefinition.addOrReplaceChild("eye2", CubeListBuilder.create().texOffs(12, 10).addBox(-0.5F, -2.25F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 12).addBox(-0.5F, -2.25F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-0.5F, -3.25F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 14).addBox(-0.5F, -3.25F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.75F));

		PartDefinition upteeth = partdefinition.addOrReplaceChild("upteeth", CubeListBuilder.create().texOffs(4, 3).addBox(-0.5F, -1.75F, 4.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(-0.5F, -0.75F, 3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.5F, 0.25F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 14).addBox(-0.5F, 0.75F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 14).addBox(-0.5F, 1.0F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(-0.5F, 0.5F, -2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-0.5F, -0.25F, -4.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-0.5F, -1.75F, -5.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.75F));

		PartDefinition downteeth = partdefinition.addOrReplaceChild("downteeth", CubeListBuilder.create().texOffs(8, 3).addBox(-0.5F, -0.25F, -5.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, 2.5F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 6).addBox(-0.5F, 2.25F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 9).addBox(-0.5F, 1.75F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(-0.5F, 0.5F, 3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-0.5F, -0.25F, 4.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(-0.5F, 2.0F, -2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 3).addBox(-0.5F, 1.0F, -4.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.75F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.chewAnimationState.startIfStopped((int)ageInTicks);
        this.animate(this.chewAnimationState, chew, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        upteeth.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        downteeth.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        eye1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        eye2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}