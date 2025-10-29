package com.cham.backcraft.enchantment;

import com.llamalad7.mixinextras.lib.apache.commons.ObjectUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.Nullable;

public class ModEnchantmentHelper {
    public static int getEnchantmentLevel(ItemStack itemStack, ResourceKey<Enchantment> enchantmentResourceKey){
        ItemEnchantments itemEnchantments = itemStack.getOrDefault(DataComponents.ENCHANTMENTS,ItemEnchantments.EMPTY);

        for(Object2IntMap.Entry<Holder<Enchantment>> entry: itemEnchantments.entrySet()){
            @Nullable ResourceKey<Enchantment> enchantmentKey = entry.getKey().getKey();
            int value = entry.getIntValue();
            if(enchantmentKey != null && enchantmentKey.isFor(enchantmentResourceKey.registryKey())) {
                return value;
            }
        }
        return 0;
    }
}
