package com.cham.backcraft.datagen;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import com.cham.backcraft.effect.ModMobEffects;
import com.cham.backcraft.entity.ModEntityRegister;
import com.cham.backcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider {
    public ModZhCnLangProvider(PackOutput output) {
        super(output, Backcraft.MODID, "zh_cn");
    }

    public void addPotionTranslations(String id, String name) {
        add("item.minecraft.potion.effect." + id, name);
        add("item.minecraft.splash_potion.effect." + id, "喷溅型" + name);
        add("item.minecraft.lingering_potion.effect." + id, "滞留型" + name);
        add("item.minecraft.tipped_arrow.effect." + id, name + "药箭");
    }

    @Override
    protected void addTranslations() {


        add(ModItems.A_BOX_OF_DUMB_GUM.get(), "一盒傻瓜口香糖");
        add(ModItems.DUMB_GUM.get(), "傻瓜口香糖");
        add(ModItems.FIRE_SALT.get(), "火盐");
        //add(ModItems.ALMOND_WATER.get(),"杏仁水");
        add(ModItems.CROWBAR.get(), "撬棍");
        add(ModItems.SMILER_TOOTH.get(), "笑魇牙");
        add(ModItems.ROYAL_RATIONS.get(), "皇家口粮");
        add(ModMobEffects.IMMUNITY.get(), "免疫");
        add(ModMobEffects.DIZZINESS.get(), "晕眩");
        add(ModBlocks.FIRE_SALT_ORE.get(), "火盐矿石");
        add(ModBlocks.YELLOW_WALL.get(), "黄色墙壁");
        add(ModBlocks.FIRE_SALT_BLOCK.get(), "火盐块");
        add(ModBlocks.SKELETON_BLOCK.get(), "骨架方块");
        add(ModEntityRegister.SMILER.get(), "笑魇");
        add(ModItems.SMILER_SPAWN_EGG.get(), "笑魇刷怪蛋");
        add(ModEntityRegister.WINDOW.get(), "窗户");
        add(ModItems.WINDOW_SPAWN_EGG.get(), "窗户刷怪蛋");
        add(ModItems.DEATHMOTH_SCALES.get(), "死亡飞蛾鳞片");
        add(ModBlocks.CRATE.get(),"板条箱");

        addPotionTranslations("almond_water", "杏仁水");

        add("itemGroup.backcraft_tab", "我的后室");
        add("enchantment.backcraft.stun", "击晕");
        add("enchantment.backcraft.lengthen", "加长");
        add("block_entity.backcraft.crate","板条箱");

    }
}
