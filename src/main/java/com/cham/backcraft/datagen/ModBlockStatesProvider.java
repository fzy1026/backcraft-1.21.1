package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Backcraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.YELLOW_WALL.get(), cubeAll(ModBlocks.YELLOW_WALL.get()));
        simpleBlockWithItem(ModBlocks.FIRE_SALT_ORE.get(), cubeAll(ModBlocks.FIRE_SALT_ORE.get()));
        simpleBlockWithItem(ModBlocks.FIRE_SALT_BLOCK.get(), cubeAll(ModBlocks.FIRE_SALT_BLOCK.get()));
        //simpleBlockWithItem(ModBlocks.CRATE.get(), cubeAll(ModBlocks.CRATE.get()));

        horizontalBlock(ModBlocks.SKELETON_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/skeleton_block")));
    }
}
