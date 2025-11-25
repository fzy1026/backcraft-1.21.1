package com.cham.backcraft.block;

import com.cham.backcraft.Backcraft;
import com.cham.backcraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Backcraft.MODID);

    public static final DeferredBlock<Block> YELLOW_WALL =
            registerBlock("yellow_wall",()->new Block(Block.Properties.ofFullCopy(Blocks.YELLOW_WOOL)));
    public static final DeferredBlock<Block> FIRE_SALT_ORE =
            registerBlock("fire_salt_ore",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> FIRE_SALT_BLOCK =
            registerBlock("fire_salt_block",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<SkeletonBlock> SKELETON_BLOCK =
            registerBlock("skeleton_block",()->new SkeletonBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .requiresCorrectToolForDrops()
                            .strength(2.0F)
                            .sound(SoundType.BONE_BLOCK)
                            .noOcclusion()
                    )
            );

    public static final DeferredBlock<Block> CRATE =
            registerBlock("crate",()->new Block(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .instrument(NoteBlockInstrument.XYLOPHONE)
                            .requiresCorrectToolForDrops()
                            .strength(2.0F)
                            .sound(SoundType.BONE_BLOCK)
                            .noOcclusion()
                    )
            );



    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> blocks = BLOCKS.register(name,block);
        registerBlockItems(name,blocks);
        return blocks;
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
    /*
    方块属性构造方法：
    .mapColor(DyeColor mapColor) / mapColor(MapColor mapColor) 方块在地图上的颜色，支持两种颜色选项
    .noCollission() 无碰撞体积
    .noOcclusion() 无遮挡
    .friction(float friction) 设定摩擦力 默认0.6 冰为0.98 蓝冰为0.989 无摩擦为1
    .speedFactor(float speedFactor) 设定速度乘数 默认1.0 灵魂沙为0.4
    .jumpFactor(float jumpFactor) 同理，跳跃系数 默认1.0 蜂蜜块为0.5
    .sound(SoundType soundType) 设定方块音效（破坏等）
    .lightLevel(ToIntFunction<BlockState> lightEmission) 设定光亮等级？用法中有混淆 看不懂
    .strength(float destroyTime, float explosionResistance) 强度 设定挖掘时间与爆炸抗性
    .strength(float strength) 同上
    .ignitedByLava() 会被熔岩点燃
    .requiresCorrectToolForDrops() 需要正确工具挖掘才会掉落
    .destroyTime(float destroyTime) 设定挖掘时间
    .explosionResistance(float explosionResistance) 设定爆炸抗性
    .instrument(NoteBlockInstrument instrument) 放于音符盒下时的音效
    .replaceable() 可被替换（如草，在放置方块时会将其自动破坏）

     */
}
