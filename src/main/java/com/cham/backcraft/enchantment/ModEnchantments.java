package com.cham.backcraft.enchantment;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.datagen.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.minecraft.world.level.block.Block;

public class ModEnchantments {
    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Backcraft.MODID, name));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageTypeHolderGetter = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantmentHolderGetter = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> itemHolderGetter = context.lookup(Registries.ITEM);
        HolderGetter<Block> blockHolderGetter = context.lookup(Registries.BLOCK);
        register(
                context,
                STUN,
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(ModItemTags.CROWBAR_TAG),
                                10,
                                3,
                                Enchantment.dynamicCost(1, 11),
                                Enchantment.dynamicCost(21, 8),
                                1,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
        );

        register(
                context,
                LENGTHEN,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        itemHolderGetter.getOrThrow(ModItemTags.CROWBAR_TAG),
                                        10,
                                        2,
                                        Enchantment.dynamicCost(1, 11),
                                        Enchantment.dynamicCost(21, 8),
                                        1,
                                        EquipmentSlotGroup.MAINHAND
                                )

                        )
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ResourceLocation.fromNamespaceAndPath(Backcraft.MODID,"backcraft.lengthen"),
                                        Attributes.ENTITY_INTERACTION_RANGE,
                                        LevelBasedValue.perLevel(0.25F),
                                        AttributeModifier.Operation.ADD_VALUE
                                )
                        )
        );

    }

    public static final ResourceKey<Enchantment> STUN = key("stun");
    public static final ResourceKey<Enchantment> LENGTHEN = key("lengthen");
}
