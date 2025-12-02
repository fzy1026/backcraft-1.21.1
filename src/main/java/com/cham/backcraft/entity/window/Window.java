package com.cham.backcraft.entity.window;

import com.cham.backcraft.Backcraft;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Window extends Monster {

    private static final EntityDataAccessor<Direction> DATA_ATTACH_FACE =
            SynchedEntityData.defineId(Window.class, EntityDataSerializers.DIRECTION);
    private static final EntityDataAccessor<BlockPos> DATA_ATTACH_POS =
            SynchedEntityData.defineId(Window.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> DATA_HAS_ATTACHED =
            SynchedEntityData.defineId(Window.class,EntityDataSerializers.BOOLEAN);


    public Window(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    // 设置附着面
    public void setAttachFace(Direction face) {
        this.getEntityData().set(DATA_ATTACH_FACE, face);
    }

    public Direction getAttachFace() {
        return this.getEntityData().get(DATA_ATTACH_FACE);
    }

    public void setAttachPos(BlockPos pos){
        this.getEntityData().set(DATA_ATTACH_POS,pos);
    }

    public BlockPos getAttachPos(){
        return this.getEntityData().get(DATA_ATTACH_POS);
    }

    public void setHasAttached(Boolean b)
    {
        this.getEntityData().set(DATA_HAS_ATTACHED,b);
    }

    public Boolean getHasAttached()
    {
        return this.getEntityData().get(DATA_HAS_ATTACHED);
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
        builder.define(DATA_ATTACH_POS,BlockPos.ZERO);
        builder.define(DATA_HAS_ATTACHED,false);
    }


    @Override
    protected @NotNull AABB getAttackBoundingBox() {
        return super.getAttackBoundingBox();
    }


    @Override
    public void onAddedToLevel() {
        super.onAddedToLevel();
        Backcraft.LOGGER.info("on Added To Level run!");
        tryAutoAttach();
    }

    private void tryAutoAttach() {
        if (this.level().isClientSide ||getHasAttached()) {
            return; // 客户端不执行，或已经初始化过
        }

        Backcraft.LOGGER.info("Try to Attach!");
        BlockPos currentPos = this.blockPosition();
        Direction attachFace = findNearestWall(currentPos);

        if (attachFace != null) {
            // 计算附着位置（墙壁的位置）
            Backcraft.LOGGER.info("Faind wall in");
            System.out.println(attachFace);
            BlockPos wallPos = currentPos.relative(attachFace);
            setAttachPos(wallPos);
            setAttachFace(attachFace.getOpposite()); // 实体附着在墙壁的对面

            // 标记已初始化
            setHasAttached(true);

            // 立即更新位置
            updatePositionBasedOnAttachment();
        } else {
            Backcraft.LOGGER.info("No Wall is finded!");
            // 四格内都没有墙壁，用注释标出（根据用户要求）
            // [!] 注意：这里四格内没有找到可附着的墙壁
            // 实体将保持当前位置，但不会附着
        }
    }

    // [+] 新增：寻找最近的墙壁
    @Nullable
    private Direction findNearestWall(BlockPos center) {
        // 只检查东南西北四个方向（根据用户要求）
        Direction[] directionsToCheck = {
                Direction.NORTH,
                Direction.SOUTH,
                Direction.EAST,
                Direction.WEST
        };

        for (Direction dir : directionsToCheck) {
            BlockPos checkPos = center.relative(dir);

            // 检查这个位置是否有方块（非空气）
            if (isValidWall(checkPos)) {
                return dir;
            }
        }

        return null; // 没有找到合适的墙壁
    }

    // [+] 新增：检查位置是否适合作为墙壁
    private boolean isValidWall(BlockPos pos) {
        if (!this.level().isLoaded(pos)) {
            return false; // 区块未加载，无法检查
        }

        BlockState state = this.level().getBlockState(pos);

        // 检查方块是否可碰撞（大多数固体方块）
        // 你也可以添加更多条件，比如排除某些透明方块
        return !state.isAir() && state.isSolidRender(this.level(), pos);
    }

    // [+] 新增：根据附着信息更新实体位置
    private void updatePositionBasedOnAttachment() {
        BlockPos attachPos = this.getAttachPos();
        Direction attachFace = this.getAttachFace();

        BlockPos EntityPos = null;
        if (attachPos != null) {
            EntityPos = attachPos.relative(attachFace);
        }

        this.setPos(EntityPos.getX()+0.5,EntityPos.getY(),EntityPos.getZ()+0.5);
        switch (attachFace) {
            case NORTH -> this.setYRot(180.0F);
            case SOUTH -> this.setYRot(0.0F);
            case EAST -> this.setYRot(270.0F);
            case WEST -> this.setYRot(90.0F);
            default -> this.setYRot(0.0F);
        }
        this.setXRot(0.0F);
        this.yHeadRot = this.getYRot();
        this.yBodyRot = this.getYRot();
        this.refreshDimensions();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            if (!getHasAttached()) {
                tryAutoAttach();
            }
            else{
                updatePositionBasedOnAttachment();
                this.setDeltaMovement(0, 0, 0);
            }
        }
        else {
            if (getHasAttached()) {
                updatePositionBasedOnAttachment();
            }
        }
    }


    public boolean manuallyAttachToNearestWall() {
        if (getHasAttached()) {
            return false;
        }

        tryAutoAttach();
        return getHasAttached();
    }

}
