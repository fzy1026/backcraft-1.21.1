package com.cham.backcraft.item;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.entity.ModEntityRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Backcraft.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //public static final DeferredItem<Item> ALMOND_WATER =
            //ITEMS.register("almond_water", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DUMB_GUM =
            ITEMS.register("dumb_gum", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .alwaysEdible()
                    .fast()
                    .effect(new MobEffectInstance(MobEffects.BLINDNESS, 500, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.WEAKNESS, 500, 0), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, 0), 1.0F)
                    .build()
            )));
    public static final DeferredItem<SpawnEggItem> SMILER_SPAWN_EGG =
            ITEMS.register("smiler_spawn_egg", () -> new SpawnEggItem(ModEntityRegister.SMILER.get(), 0x000000, 0xffe0e2, new Item.Properties()));
    public static final DeferredItem<SpawnEggItem> WINDOW_SPAWN_EGG =
            ITEMS.register("window_spawn_egg", () -> new SpawnEggItem(ModEntityRegister.WINDOW.get(), 0x000000, 0xffe0e2, new Item.Properties()));

    /*
    食物相关构建函数：
    .nutrition(int nutrition) 回复的饱食度
    .saturationModifier(float saturationModifier) 回复的饱和度
    .alwaysEdible() 总能吃
    .fast() 快速吃
    .effect(MobEffectInstance effect, float probability) 提供效果，并设定提供几率
    .usingConvertsTo(ItemLike item) 吃后转变为
    .build()收尾，构建
     */
    public static final DeferredItem<Item> FIRE_SALT =
            ITEMS.register("fire_salt", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> A_BOX_OF_DUMB_GUM =
            ITEMS.register("a_box_of_dumb_gum", () -> new Item(new Item.Properties()));
    public static final DeferredItem<CrowbarItem> CROWBAR =
            ITEMS.register("crowbar", () -> new CrowbarItem(Tiers.IRON,new Item.Properties().attributes(CrowbarItem.createAttributes(Tiers.IRON, 7, -3.5F))));
    public static final DeferredItem<Item> SMILER_TOOTH =
            ITEMS.register("smiler_tooth",()->new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROYAL_RATIONS =
            ITEMS.register(
                    "royal_rations",
                    ()-> new Item(
                            new Item.Properties().food(
                            new FoodProperties.Builder()
                                    .nutrition(14)
                                    .saturationModifier(20)
                                    .fast()
                                    .effect(new MobEffectInstance(MobEffects.REGENERATION,300,0),1.0F)
                                    .build()
                            )
                    )
            );
    public static final DeferredItem<Item> DEATHMOTH_SCALES =
            ITEMS.register("deathmoth_scales",()->new Item(new Item.Properties()));
}
