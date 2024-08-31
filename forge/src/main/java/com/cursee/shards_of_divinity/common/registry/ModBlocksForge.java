package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.common.block.basic.AntiquatedLogBlock;
import com.cursee.shards_of_divinity.common.block.basic.SmoulderingAntiquatedLogBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksForge {

    public static void register() {}

    public static final RegistryObject<Block> ANTIQUATED_LOG = RegistryForge.registerBlock("antiquated_log", () -> new AntiquatedLogBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SMOULDERING_ANTIQUATED_LOG = RegistryForge.registerBlock("smouldering_antiquated_log", () -> new SmoulderingAntiquatedLogBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
}
