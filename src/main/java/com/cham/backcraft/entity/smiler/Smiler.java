package com.cham.backcraft.entity.smiler;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Smiler extends PathfinderMob {
    public Smiler(EntityType<? extends PathfinderMob> entityType, Level level){
        super(entityType,level);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1,new MeleeAttackGoal(this,1.2D,true));
        this.goalSelector.addGoal(3,new LookAtPlayerGoal(this, Player.class,16.0F));
        this.goalSelector.addGoal(4,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this, Player.class,true));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,20)
                .add(Attributes.MOVEMENT_SPEED,0.7D)
                .add(Attributes.ATTACK_DAMAGE,6.0D)
                .add(Attributes.FOLLOW_RANGE,20.0D);
    }
}
