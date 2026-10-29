package com.cham.backcraft.block.FireSaltBlock;

import com.cham.backcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Properties;

public class FireSaltBlock extends Block {
    public FireSaltBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(level.isClientSide) return;
        if(level.random.nextInt(60) == 0&&state.is(ModBlocks.FIRE_SALT_BLOCK)) {
            level.explode(entity,
                    pos.getX(),pos.getY(),pos.getZ(),
                    40.0F,
                    Level.ExplosionInteraction.TNT);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(level.isClientSide) return;
        if(state.is(ModBlocks.FIRE_SALT_BLOCK)) {
            level.explode(entity,
                    pos.getX(),pos.getY(),pos.getZ(),
                    40.0F,
                    Level.ExplosionInteraction.TNT);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }
}
