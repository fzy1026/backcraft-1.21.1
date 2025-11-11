package com.cham.backcraft.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class ModEnchantmentHelper {
    public static int getEnchantmentLevel(ItemStack itemStack, ResourceKey<Enchantment> enchantmentResourceKey) {
        ItemEnchantments itemEnchantments = itemStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        if (itemEnchantments == ItemEnchantments.EMPTY)
            return 0;

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemEnchantments.entrySet()) {
            Holder<Enchantment> holder = entry.getKey();
            int value = entry.getIntValue();
            if (holder.is(enchantmentResourceKey)) {
                return value;
            }
        }
        return 0;
    }
}
