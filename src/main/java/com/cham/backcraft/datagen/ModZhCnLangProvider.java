package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import com.cham.backcraft.effect.ModMobEffects;
import com.cham.backcraft.entity.ModEntityRegister;
import com.cham.backcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider {
    public ModZhCnLangProvider(PackOutput output){
        super(output, Backcraft.MODID,"zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.A_BOX_OF_DUMB_GUM.get(),"一盒傻瓜口香糖");
        add(ModItems.DUMB_GUM.get(),"傻瓜口香糖");
        add(ModItems.FIRE_SALT.get(),"火盐");
        //add(ModItems.ALMOND_WATER.get(),"杏仁水");
        add(ModItems.CROWBAR.get(),"撬棍");
        add(ModItems.SMILER_TOOTH.get(),"笑魇牙");

        add(ModMobEffects.IMMUNITY.get(),"免疫");


        add(ModBlocks.FIRE_SALT_ORE.get(),"火盐矿石");
        add(ModBlocks.YELLOW_WALL.get(),"黄色墙壁");
        add(ModBlocks.FIRE_SALT_BLOCK.get(),"火盐块");
        add(ModBlocks.SKELETON_BLOCK.get(),"骨架方块");


        add(ModEntityRegister.SMILER.get(),"笑魇");
        add(ModItems.SMILER_SPAWN_EGG.get(),"笑魇刷怪蛋");

        add("itemGroup.backcraft_tab","我的后室");
        add("item.minecraft.potion.effect.almond_water","杏仁水");
        add("item.minecraft.splash_potion.effect.almond_water","喷溅型杏仁水");
        add("item.minecraft.lingering_potion.effect.almond_water","滞留型杏仁水");
        add("item.minecraft.tipped_arrow.effect.almond_water","杏仁水药箭");

    }
}
