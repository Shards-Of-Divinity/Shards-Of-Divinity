package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.common.block.basic.AntiquatedLogBlock;
import com.cursee.shards_of_divinity.common.block.basic.SmoulderingAntiquatedLogBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

public class ModBlocksForge {

    public static void register() {}

    public static final RegistryObject<Block> ANTIQUATED_LOG = RegistryForge.registerBlock("antiquated_log", () -> new AntiquatedLogBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SMOULDERING_ANTIQUATED_LOG = RegistryForge.registerBlock("smouldering_antiquated_log", () -> new SmoulderingAntiquatedLogBlock(BlockBehaviour.Properties.of().forceSolidOn().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 6.0F).lightLevel(adjustableLightEmission())));

    private static ToIntFunction<BlockState> adjustableLightEmission() {
        return (pBlockState) -> pBlockState.getValue(BlockStateProperties.AGE_3) > 0 ? pBlockState.getValue(BlockStateProperties.AGE_3) * 5 : 3;
    }
}
