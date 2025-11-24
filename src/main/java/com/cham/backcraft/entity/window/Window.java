package com.cham.backcraft.entity.window;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class Window extends Monster {
    public Window(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<Direction> DATA_ATTACH_FACE =
            SynchedEntityData.defineId(Window.class, EntityDataSerializers.DIRECTION);
    private BlockPos DATA_ATTACH_POSE;

    // 设置附着面
    public void setAttachFace(Direction face) {
        this.getEntityData().set(DATA_ATTACH_FACE, face);
    }

    public Direction getAttachFace() {
        return this.getEntityData().get(DATA_ATTACH_FACE);
    }

    @Override
    protected void registerGoals() {
        /*
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3,new LookAtPlayerGoal(this, Player.class,16.0F));
        this.goalSelector.addGoal(4,new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Player.class,true));
         */
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0D);
    }

    @Override
    protected int getBaseExperienceReward() {
        return 2;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ATTACH_FACE, Direction.NORTH);
    }


    @Override
    protected @NotNull AABB getAttackBoundingBox() {
        return super.getAttackBoundingBox();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.DATA_ATTACH_POSE == null)
            return;

        // 更新附着位置

        // 根据附着面计算实际位置
        this.setPos(
                this.DATA_ATTACH_POSE.getX()+0.5,
                this.DATA_ATTACH_POSE.getY(),
                this.DATA_ATTACH_POSE.getZ()+0.5
        );
        Direction direction = this.getAttachFace();
        switch (direction) {
            case NORTH -> this.setYRot(180.0F);
            case SOUTH -> this.setYRot(0.0F);
            case EAST -> this.setYRot(270.0F);
            case WEST -> this.setYRot(90.0F);
            default -> this.setYRot(180.0F);
        }
    }

}
