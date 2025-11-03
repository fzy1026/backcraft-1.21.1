package com.cham.backcraft.enchantment;

import com.cham.backcraft.effect.ModMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class ModEnchantmentEventHandler {
    @SubscribeEvent
    public static void StunEvent(LivingDamageEvent.Pre event)
    {
        DamageSource source = event.getSource();
        Entity entity  = event.getEntity();
        if(source.getEntity() instanceof Player player && entity instanceof LivingEntity target)
        {
            ItemStack itemStack = player.getMainHandItem();
            //byd能晕就能杀还是太超模了
            if(player.getAttackStrengthScale(0.0F) < 1.0F)
                return;
            if(ModEnchantmentHelper.getEnchantmentLevel(itemStack,ModEnchantments.STUN) > 0)
            {
                int level = ModEnchantmentHelper.getEnchantmentLevel(itemStack,ModEnchantments.STUN);
                double chance = 0.3 + level*0.1;
                if(chance > 1)
                    chance = 1;
                int effectTime = level * 10;
                if(target.getRandom().nextFloat() < chance){
                    target.addEffect(new MobEffectInstance(ModMobEffects.DIZZINESS,effectTime,level-1,false,true));
                }
            }
        }
    }
}
