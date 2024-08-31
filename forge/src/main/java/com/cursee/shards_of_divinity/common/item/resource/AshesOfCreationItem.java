package com.cursee.shards_of_divinity.common.item.resource;

import com.cursee.shards_of_divinity.Constants;
import com.cursee.shards_of_divinity.common.registry.ModBlocksForge;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Predicate;

public class AshesOfCreationItem extends Item {

    public AshesOfCreationItem() {
        super(new Properties().fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        final Level level = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        final Player player = context.getPlayer();
        final InteractionHand hand = context.getHand();

        if (level.isClientSide() || player == null) {
            return InteractionResult.PASS;
        }

        if (level.getBlockState(pos).getTags().anyMatch(Predicate.isEqual(BlockTags.LOGS))) {

            level.setBlock(pos, ModBlocksForge.ANTIQUATED_LOG.get().defaultBlockState(), Block.UPDATE_ALL);

            player.sendSystemMessage(Component.translatable("message." + Constants.MOD_ID + ".antiquated_log"));

            final ItemStack stack = player.getItemInHand(hand);
            stack.shrink(1);
            player.setItemInHand(hand, stack);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
