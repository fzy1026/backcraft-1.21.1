package com.cham.backcraft.datagen;

import com.cham.backcraft.block.ModBlocks;
import com.cham.backcraft.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider(HolderLookup.Provider registries){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),registries);
    }

    @Override
    protected void generate(){
        dropSelf(ModBlocks.YELLOW_WALL.get());
        dropSelf(ModBlocks.SKELETON_BLOCK.get());
        dropSelf(ModBlocks.FIRE_SALT_BLOCK.get());
        dropSelf(ModBlocks.CRATE.get());
        add(ModBlocks.FIRE_SALT_ORE.get(),
                block -> createMultiplyOreDrop(ModBlocks.FIRE_SALT_ORE.get(),ModItems.FIRE_SALT.get(),1,4));

    }
    //若矿石在无附魔情况下仅掉落单个矿，则可调用API createOreDrop(Block block, Item item)
    public LootTable.Builder createMultiplyOreDrop(Block block, Item item, float countmin, float countmax) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(countmin,countmax)))
                                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }



    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}
