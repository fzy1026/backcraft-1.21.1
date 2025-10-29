package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.cham.backcraft.datagen.ModItemTags.CROWBAR_TAG;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blocktags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blocktags, Backcraft.MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CROWBAR_TAG)
                .add(ModItems.CROWBAR.get());
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.CROWBAR.get());
    }
}
