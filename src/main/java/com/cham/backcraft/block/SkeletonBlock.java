package com.cham.backcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SkeletonBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATER_LOGGED = BlockStateProperties.WATERLOGGED;
    public SkeletonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING,Direction.NORTH)
                .setValue(WATER_LOGGED,false)
        );

    }

    // 简化后的碰撞箱定义
    private static final VoxelShape SHAPE = Shapes.or(
            /*
            // 第一组：垂直排列的方块
            Block.box(6, 0, 14, 10, 2, 16),
            Block.box(6, 3, 14, 10, 5, 16),
            Block.box(6, 6, 14, 10, 8, 16),
            Block.box(6, 9, 14, 10, 11, 16),
            Block.box(6, 12, 14, 10, 14, 16),
            Block.box(6, 15, 14, 10, 16, 16),

            // 第二组：较窄的垂直方块
            Block.box(6.5, 2, 14, 9.5, 3, 16),
            Block.box(6.5, 5, 14, 9.5, 6, 16),
            Block.box(6.5, 8, 14, 9.5, 9, 16),
            Block.box(6.5, 11, 14, 9.5, 12, 16),
            Block.box(6.5, 14, 14, 9.5, 15, 16),

            // 第三组：复杂的框架结构
            Block.box(1.25, 0.25, 0, 6.25, 1.75, 1.25),
            Block.box(0, 0.25, 0, 1.25, 1.75, 14.25),
            Block.box(0, 0.25, 14.25, 6, 1.75, 15.75),
            Block.box(10, 0.25, 14.25, 16, 1.75, 15.75),
            Block.box(14.75, 0.25, 0, 16, 1.75, 14.25),
            Block.box(9.75, 0.25, 0, 14.75, 1.75, 1.25),
            Block.box(7, 6, 0, 9, 12.75, 2),
            Block.box(6.5, 12.75, 0, 9.5, 15.25, 2),
            Block.box(7.5, 5, 0.25, 8.5, 6, 1.75),

            // 第四组：重复的框架结构（不同高度）
            Block.box(1.25, 3.25, 0, 6, 4.75, 1.25),
            Block.box(0, 3.25, 0, 1.25, 4.75, 14.25),
            Block.box(0, 3.25, 14.25, 6, 4.75, 15.75),
            Block.box(10, 3.25, 14.25, 16, 4.75, 15.75),
            Block.box(14.75, 3.25, 0, 16, 4.75, 14.25),
            Block.box(9.75, 3.25, 0, 14.75, 4.75, 1.25),

            // 第五组：更多重复结构
            Block.box(1.25, 6.25, 0, 6, 7.75, 1.25),
            Block.box(0, 6.25, 0, 1.25, 7.75, 14.25),
            Block.box(0, 6.25, 14.25, 6, 7.75, 15.75),
            Block.box(10, 6.25, 14.25, 16, 7.75, 15.75),
            Block.box(14.75, 6.25, 0, 16, 7.75, 14.25),
            Block.box(9.75, 6.25, 0, 14.75, 7.75, 1.25),

            // 第六组
            Block.box(1.25, 9.25, 0, 7, 10.75, 1.25),
            Block.box(0, 9.25, 0, 1.25, 10.75, 14.25),
            Block.box(0, 9.25, 14.25, 6, 10.75, 15.75),
            Block.box(10, 9.25, 14.25, 16, 10.75, 15.75),
            Block.box(14.75, 9.25, 0, 16, 10.75, 14.25),
            Block.box(9, 9.25, 0, 14.75, 10.75, 1.25),

            // 第七组
            Block.box(1.25, 12.25, 0, 6.5, 13.75, 1.25),
            Block.box(0, 12.25, 0, 1.25, 13.75, 14.25),
            Block.box(0, 12.25, 14.25, 6, 13.75, 15.75),
            Block.box(10, 12.25, 14.25, 16, 13.75, 15.75),
            Block.box(14.75, 12.25, 0, 16, 13.75, 14.25),
            Block.box(9.5, 12.25, 0, 14.75, 13.75, 1.25)

             */
            //改用更简单的碰撞箱
            Block.box(0, 0, 0, 2, 16, 16),
            Block.box(0, 0, 14, 16, 16, 16),
            Block.box(14, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 2)
    );


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATER_LOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATER_LOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING,context.getHorizontalDirection().getOpposite())
                .setValue(WATER_LOGGED,fluidState.getType() == Fluids.WATER);
    }
}

