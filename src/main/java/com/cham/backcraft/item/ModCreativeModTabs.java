package com.cham.backcraft.item;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Backcraft.MODID);
    public static final Supplier<CreativeModeTab> BACKCRAFT_TAB =
            CREATIVE_MODE_TABS.register("backcraft_tab",()->CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModItems.ALMOND_WATER.get()))
                    .title(Component.translatable("itemGroup.backcraft_tab"))
                    .displayItems((parameters,output)->{
                        output.accept(ModItems.ALMOND_WATER);
                        output.accept(ModBlocks.YELLOW_WALL);
                        output.accept(ModItems.DUMB_GUM);
                        output.accept(ModItems.A_BOX_OF_DUMB_GUM);
                        output.accept(ModItems.FIRE_SALT);
                        output.accept(ModBlocks.FIRE_SALT_ORE);
                        output.accept(ModItems.CROWBAR);
                        output.accept(ModBlocks.FIRE_SALT_BLOCK);
                    })
                    .build());
}
