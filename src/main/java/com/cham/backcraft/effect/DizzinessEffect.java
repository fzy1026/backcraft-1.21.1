package com.cham.backcraft.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DizzinessEffect extends MobEffect {
    protected DizzinessEffect() {
        super(MobEffectCategory.HARMFUL, 0xFEDB39);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath("backcraft", "effect.dizziness"), -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath("backcraft", "effect.dizziness"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.getType() == EntityType.PLAYER) {
            amplifier += 1;
            float rotate_y_speed = amplifier * 8 * ((float) Math.random());
            float rotate_x_speed = amplifier * Mth.PI;
            livingEntity.setYRot(livingEntity.getYRot() + rotate_y_speed);
            livingEntity.setXRot(livingEntity.getXRot() + 5 * Mth.sin(rotate_x_speed));
        } else if (livingEntity instanceof Mob mob) {
            mob.setNoAi(true);
            livingEntity.setNoActionTime(1);
            livingEntity.setXRot(70);
            livingEntity.setDeltaMovement(0, livingEntity.getDeltaMovement().y, 0);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }

    /*
    public void onEffectRemoved(LivingEntity livingEntity, int amplifier) {
        super.onEffectRemoved(livingEntity, amplifier);
        if (livingEntity instanceof Mob mob) {
            mob.setNoAi(false);
        }
    }
    */

}
