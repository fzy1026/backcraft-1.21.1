package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Backcraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.FIRE_SALT.get());
        basicItem(ModItems.DUMB_GUM.get());
        //basicItem(ModItems.ALMOND_WATER.get());
        basicItem(ModItems.A_BOX_OF_DUMB_GUM.get());
        basicItem(ModItems.CROWBAR.get());
        basicItem(ModItems.SMILER_TOOTH.get());
        basicItem(ModItems.ROYAL_RATIONS.get());

        withExistingParent(ModItems.SMILER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
