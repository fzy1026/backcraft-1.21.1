package com.cham.backcraft.item;

import com.cham.backcraft.Backcraft;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Backcraft.MODID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> ALMOND_WATER =
            ITEMS.register("almond_water",()->new Item(new Item.Properties()));


}
