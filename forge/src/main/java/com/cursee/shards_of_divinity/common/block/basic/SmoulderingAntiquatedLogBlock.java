package com.cursee.shards_of_divinity.common.block.basic;

import com.cursee.shards_of_divinity.common.block.entity.SmoulderingAntiquatedLogBlockEntity;
import com.cursee.shards_of_divinity.common.registry.ModBlockEntitiesForge;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class SmoulderingAntiquatedLogBlock extends Block implements EntityBlock {

    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0),
            Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0)};

    public SmoulderingAntiquatedLogBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new SmoulderingAntiquatedLogBlockEntity(pPos, pState);
        return ModBlockEntitiesForge.SMOULDERING_ANTIQUATED_LOG_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        return pLevel.isClientSide() ? null : (pLevel1, pPos, pState1, pBlockEntity) -> {
            if (pBlockEntity instanceof SmoulderingAntiquatedLogBlockEntity log) {
                log.tick();
            }
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITE[(Integer)state.getValue(AGE)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{AGE});
    }

    //    @Override
//    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
//        return Block.box(0,0,0,16,16,16);
//    }
//
//    @Override
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }

//    @Override
//    public void animateTick(BlockState pState, Level pLevel1, BlockPos pPos, RandomSource pRandom) {
//        RandomSource randomsource = pLevel1.getRandom();
//        SimpleParticleType simpleparticletype = ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
//        pLevel1.addAlwaysVisibleParticle(simpleparticletype, true, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), (double)pPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(), (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
//        pLevel1.addParticle(ParticleTypes.SMOKE, (double) pPos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1), (double) pPos.getY() + 0.4D, (double) pPos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
//    }


    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {

        for (BlockPos $$4 : EnchantmentTableBlock.BOOKSHELF_OFFSETS) {
            if (pRandom.nextInt(16) > 7) pLevel.addParticle(ParticleTypes.ENCHANT, (double) pPos.getX() + 0.5, (double) pPos.getY() + 2.0, (double) pPos.getZ() + 0.5, (double) ((float) $$4.getX() + pRandom.nextFloat()) - 0.5, (double) ((float) $$4.getY() - pRandom.nextFloat() - 1.0F), (double) ((float) $$4.getZ() + pRandom.nextFloat()) - 0.5);
        }

        for (int i = 0; i < 3; ++i) {

            int j = pRandom.nextInt(2) * 2 - 1;
            int k = pRandom.nextInt(2) * 2 - 1;

            double d0 = (double)pPos.getX() + 0.5D + 0.25D * (double)j;
            double d1 = (double)((float)pPos.getY() + pRandom.nextFloat());
            double d2 = (double)pPos.getZ() + 0.5D + 0.25D * (double)k;

            double d3 = (double)(pRandom.nextFloat() * (float)j);
            double d4 = ((double)pRandom.nextFloat() - 0.5D) * 0.125D;
            double d5 = (double)(pRandom.nextFloat() * (float)k);

            pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0, d1, d2, d3 * 0.1D, d4 * 0.1D, d5 * 0.1D);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.ENTITYBLOCK_ANIMATED;
        return RenderShape.MODEL;
    }
}
