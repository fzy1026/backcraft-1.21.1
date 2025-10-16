package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
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
        add(ModItems.ALMOND_WATER.get(),"杏仁水");

        add(ModBlocks.FIRE_SALT_ORE.get(),"火盐矿石");
        add(ModBlocks.YELLOW_WALL.get(),"黄色墙壁");
        add(ModBlocks.FIRE_SALT_BLOCK.get(),"火盐块");

        add("itemGroup.backcraft_tab","我的后世");

    }
}
