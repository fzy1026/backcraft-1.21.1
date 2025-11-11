package com.cham.backcraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class CrowbarItem extends TieredItem {

    public CrowbarItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }


    @Override
    public int getEnchantmentValue() {
        return getTier().getEnchantmentValue();
    }

    @Override
    public boolean canEquip(@NotNull ItemStack stack,@NotNull  EquipmentSlot armorType,@NotNull  LivingEntity entity) {
        return super.canEquip(stack, armorType, entity);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, Level level,@NotNull  BlockState state,@NotNull  BlockPos pos,@NotNull  LivingEntity miningEntity) {
        // 如果不是在客户端，并且方块不是"瞬间破坏"的，就消耗耐久
        if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(2, miningEntity,
                    EquipmentSlot.MAINHAND);
        }
        return true;
    }


    @Override
    public boolean hurtEnemy(ItemStack stack,@NotNull  LivingEntity target,@NotNull  LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker,
                EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, BlockState state) {
        if (state.is(BlockTags.DOORS)) {
            return 10.0F;
        }
        return 1.0F;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();

        if (state.is(Blocks.IRON_DOOR)) {
            if (player != null) {
                BlockState newState = state.cycle(DoorBlock.OPEN);
                level.setBlock(pos, newState, 3);

                boolean isOpen = newState.getValue(DoorBlock.OPEN);
                level.playSound(null, pos,
                        isOpen ? SoundEvents.IRON_DOOR_OPEN : SoundEvents.IRON_DOOR_CLOSE,
                        SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);

                level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);

                context.getItemInHand().hurtAndBreak(5, player,
                        EquipmentSlot.MAINHAND);

                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(context);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return createAttributes(tier, (float) attackDamage, attackSpeed);
    }


    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID, (attackDamage + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState state,@NotNull Level level, @NotNull BlockPos pos, Player player) {
        return !player.isCreative();
    }


    @Override
    public void postHurtEnemy(ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }


    @Override
    public boolean isBookEnchantable(@NotNull ItemStack stack,@NotNull ItemStack book) {
        return this.isEnchantable(stack);
    }
}
