package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import com.cham.backcraft.effect.ModMobEffects;
import com.cham.backcraft.entity.ModEntityRegister;
import com.cham.backcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, Backcraft.MODID, "en_us");
    }

    public void addPotionTranslations(String id, String name) {
        add("item.minecraft.potion.effect." + id, name);
        add("item.minecraft.splash_potion.effect." + id, "Splash " + name);
        add("item.minecraft.lingering_potion.effect." + id, "Lingering " + name);
        add("item.minecraft.tipped_arrow.effect." + id, name + " Tipped Arrow");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.A_BOX_OF_DUMB_GUM.get(), "A Box Of Dumb Gum");
        add(ModItems.DUMB_GUM.get(), "Dumb Gum");
        add(ModItems.FIRE_SALT.get(), "Fire Salt");
        //add(ModItems.ALMOND_WATER.get(),"Almond Water");
        add(ModItems.CROWBAR.get(), "Crowbar");
        add(ModItems.SMILER_TOOTH.get(), "Smiler Tooth");
        add(ModItems.ROYAL_RATIONS.get(), "Royal Rations");

        add(ModMobEffects.IMMUNITY.get(), "Immunity");
        add(ModMobEffects.DIZZINESS.get(), "Dizziness");


        add(ModBlocks.FIRE_SALT_ORE.get(), "Fire Salt Ore");
        add(ModBlocks.YELLOW_WALL.get(), "Yellow Wall");
        add(ModBlocks.FIRE_SALT_BLOCK.get(), "Fire Salt Block");
        add(ModBlocks.SKELETON_BLOCK.get(), "Skeleton Block");


        add(ModEntityRegister.SMILER.get(), "Smiler");
        add(ModItems.SMILER_SPAWN_EGG.get(), "Smiler Spawn Egg");

        add(ModEntityRegister.WINDOW.get(), "Window");
        add(ModItems.WINDOW_SPAWN_EGG.get(), "Window Spawn Egg");

        add(ModItems.DEATHMOTH_SCALES.get(), "Deathmoth Scales");

        addPotionTranslations("almond_water", "Almond Water");


        add("itemGroup.backcraft_tab", "Backcraft");
        add("enchantment.backcraft.stun", "Stun");
        add("enchantment.backcraft.lengthen", "加长");


    }
}
