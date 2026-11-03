package com.cham.backcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FireSaltBlock extends Block {
    public FireSaltBlock(Properties properties) {
        super(properties);
    }
    public static final float BASIC_EXPLOSION_RADIUS = 10.0F;
    public static final float MAX_EXPLOSION_RADIUS = 30.0F;

    @Override
    public void stepOn(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if(level.isClientSide) return;
        if(level.random.nextInt(60) == 0&&state.is(ModBlocks.FIRE_SALT_BLOCK)) {
            detonate(level,pos,entity,BASIC_EXPLOSION_RADIUS);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        if(level.isClientSide) return;
        detonate(level,pos,entity,BASIC_EXPLOSION_RADIUS+(MAX_EXPLOSION_RADIUS-5)*fallDistance/(fallDistance+1));
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private void detonate(Level level, BlockPos pos,Entity source,float radius) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(),3);
        level.explode(source,
                pos.getX(),pos.getY(),pos.getZ(),
                radius,
                Level.ExplosionInteraction.TNT
        );

    }

    @Override
    public boolean canDropFromExplosion(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        return false;
    }

    @Override
    public void wasExploded(@NotNull Level level, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        super.wasExploded(level, pos, explosion);
        if(level.isClientSide) return;

        level.explode(null,
                pos.getX(), pos.getY(), pos.getZ(),
                BASIC_EXPLOSION_RADIUS,
                Level.ExplosionInteraction.TNT);
    }
}
