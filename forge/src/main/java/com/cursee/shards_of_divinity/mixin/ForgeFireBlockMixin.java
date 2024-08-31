package com.cursee.shards_of_divinity.mixin;

import com.cursee.shards_of_divinity.common.registry.ModBlocksForge;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class ForgeFireBlockMixin extends BaseFireBlock {

    @Inject(method = "tick",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/FireBlock;tryCatchFire(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;ILnet/minecraft/util/RandomSource;ILnet/minecraft/core/Direction;)V",
            ordinal = 0))
    private void injected(BlockState pState, ServerLevel pLevel, BlockPos firePosition, RandomSource pRandom, CallbackInfo ci) {

        BlockPos[] toCheck = new BlockPos[] {firePosition.north(), firePosition.east(), firePosition.south(), firePosition.west(), firePosition.above(), firePosition.below()};

        for (BlockPos pos : toCheck) {
            if (pLevel.getBlockState(pos).getBlock() == ModBlocksForge.ANTIQUATED_LOG.get()) {
                pLevel.setBlock(pos, ModBlocksForge.SMOULDERING_ANTIQUATED_LOG.get().defaultBlockState(), Block.UPDATE_ALL);
                pLevel.setBlock(firePosition, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    public ForgeFireBlockMixin(Properties pProperties, float pFireDamage) {
        super(pProperties, pFireDamage);
    }

    @Override
    protected boolean canBurn(BlockState pState) {
        return false;
    }
}
