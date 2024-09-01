package com.cursee.shards_of_divinity.common.block.entity;

import com.cursee.shards_of_divinity.common.block.basic.SmoulderingAntiquatedLogBlock;
import com.cursee.shards_of_divinity.common.registry.ModBlockEntityTypesForge;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SmoulderingAntiquatedLogBlockEntity extends BlockEntity {

    private static final String TICK_AGE_ID = "tickAge";
    private static final int tickAgeDelay = 100;

    public int tickAge = 0;


    public SmoulderingAntiquatedLogBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypesForge.SMOULDERING_ANTIQUATED_LOG_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

//    public void tick(Level pLevel1, BlockPos pPos, BlockState pState1) {
////        RandomSource randomsource = pLevel1.getRandom();
////        SimpleParticleType simpleparticletype = ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
////        pLevel1.addAlwaysVisibleParticle(simpleparticletype, true, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
////        pLevel1.addParticle(ParticleTypes.SMOKE, (double) pPos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1), (double) pPos.getY() + 0.4D, (double) pPos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
//
//        System.out.println("ticking");
//    }

    public void tick() {

        if (this.level == null) return;

        final Level level = this.level;
        final BlockPos pos = this.worldPosition;

        final BlockState blockEntityBlockState = level.getBlockState(pos);

        final int ageValue = blockEntityBlockState.getValue(SmoulderingAntiquatedLogBlock.AGE);

        if (ageValue < SmoulderingAntiquatedLogBlock.MAX_AGE) {

            if (this.tickAge >= SmoulderingAntiquatedLogBlockEntity.tickAgeDelay - level.random.nextInt(50)) {
                level.setBlock(pos, blockEntityBlockState.trySetValue(SmoulderingAntiquatedLogBlock.AGE, ageValue + 1), Block.UPDATE_ALL);
                this.tickAge = 0;
                this.setChanged();
            }

            this.tickAge++;
            this.setChanged();
        }
        else {

            if (this.tickAge >= SmoulderingAntiquatedLogBlockEntity.tickAgeDelay - level.random.nextInt(50)) {
                level.playSound(null, pos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 0.2f, 0.2f);
                final LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                bolt.moveTo(pos.getX(), pos.getY(), pos.getZ());
                level.addFreshEntity(bolt);
                level.destroyBlock(pos, false, null, 0);
            }

            this.tickAge++;
            this.setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt(TICK_AGE_ID, this.tickAge);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.tickAge = pTag.getInt(TICK_AGE_ID);
    }

    // Referenced by the renderer to determine which faces to show.
    public boolean shouldRenderFace(Direction pFace) {
        return pFace.getAxis() == Direction.Axis.X || pFace.getAxis() == Direction.Axis.Y || pFace.getAxis() == Direction.Axis.Z;
    }

    //    @Override
//    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
//        return ClientboundBlockEntityDataPacket.create(this);
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
//        super.onDataPacket(net, pkt);
//    }
//
//    @Override
//    public @NotNull CompoundTag getUpdateTag() {
//        return this.saveWithoutMetadata();
//    }
}
