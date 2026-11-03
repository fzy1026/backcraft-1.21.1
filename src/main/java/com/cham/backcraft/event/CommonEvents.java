package com.cham.backcraft.event;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.item.ModPotions;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class CommonEvents {
    private static final float CORRUPTION_CHANCE = 1.0f/2000.0f;


    @SubscribeEvent
    public static void AlmondWaterCorruption(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if(player.level().isClientSide) return;
        Inventory inventory = player.getInventory();
        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack itemStack = inventory.getItem(i);
            PotionContents potionContents = itemStack.get(DataComponents.POTION_CONTENTS);
            if(potionContents != null && potionContents.is(ModPotions.ALMOND_WATER))
            {
                RandomSource randomSource = player.getRandom();
                float chance = randomSource.nextFloat();
                Backcraft.LOGGER.info("{} : {}",chance,CORRUPTION_CHANCE);
                if(chance<= CORRUPTION_CHANCE)
                {
                    itemStack.set(DataComponents.POTION_CONTENTS,potionContents.withPotion(ModPotions.LIQUID_PAIN)) ;
                    inventory.setItem(i, itemStack);
                }
            }
        }

    }
}
