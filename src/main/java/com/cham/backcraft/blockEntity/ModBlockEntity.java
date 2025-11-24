package com.cham.backcraft.blockEntity;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Backcraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrateBlockEntity>> CRATE =
            BLOCK_ENTITY.register("crate", () -> BlockEntityType.Builder.of(CrateBlockEntity::new, ModBlocks.CRATE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITY.register(eventBus);
    }

}
