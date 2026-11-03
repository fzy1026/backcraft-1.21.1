package com.cham.backcraft.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PainEffect extends MobEffect {
    private static final float BASE_DAMAGE = 1.0F;
    protected PainEffect(){
        super(MobEffectCategory.HARMFUL,0xffc8c7);
        this.addAttributeModifier(Attributes.MAX_HEALTH,
                ResourceLocation.fromNamespaceAndPath("backcraft", "effect.pain"),
                -4.0,
                AttributeModifier.Operation.ADD_VALUE
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(!entity.level().isClientSide){
            entity.hurt(entity.damageSources().magic(),BASE_DAMAGE+(float)amplifier);
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % (20>>amplifier) == 0;
    }
}
