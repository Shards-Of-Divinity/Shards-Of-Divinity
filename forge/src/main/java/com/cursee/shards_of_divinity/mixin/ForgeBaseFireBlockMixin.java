package com.cursee.shards_of_divinity.mixin;

import com.cursee.shards_of_divinity.common.registry.ModItemsForge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseFireBlock.class)
public class ForgeBaseFireBlockMixin extends Block {

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void shards_of_divinity$craftEssenceOfCreation(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {

        final BaseFireBlock instance = (BaseFireBlock) (Object) this;

        if (!(level instanceof ServerLevel) || !(instance instanceof SoulFireBlock) || !(entity instanceof ItemEntity itemEntity) || itemEntity.getItem().getItem() != ModItemsForge.AMALGAMATED_ESSENCE.get()) {
            ci.cancel();
            return;
        }

        itemEntity.setInvulnerable(true);

        final ItemStack stack = itemEntity.getItem();
        int stackCount = stack.getCount();

        while (stackCount > 0) {

            if (level.random.nextInt(64) == 1) {
                final ItemEntity freshEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItemsForge.ASHES_OF_CREATION.get()));
                freshEntity.setInvulnerable(true);
                level.addFreshEntity(freshEntity);
            }

            stackCount--;
            stack.setCount(stackCount);
        }

        itemEntity.discard();

        final boolean mobGriefing = level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
        level.explode(null, pos.getX(), pos.getY() + 1.0f, pos.getZ(), 0.5f, mobGriefing, Level.ExplosionInteraction.BLOCK);
    }

    public ForgeBaseFireBlockMixin(Properties pProperties) {
        super(pProperties);
    }
}
