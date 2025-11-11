package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {

    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Backcraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .addOptional(ModEnchantments.STUN.location())
                .addOptional(ModEnchantments.LENGTHEN.location());
    }
}
