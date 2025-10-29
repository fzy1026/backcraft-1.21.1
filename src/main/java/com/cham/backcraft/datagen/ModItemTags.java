package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    private static TagKey<Item> addTagByName(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Backcraft.MODID,name));
    }

    public static final TagKey<Item> CROWBAR_TAG = addTagByName("crowbar");
}
