package com.cham.backcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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

