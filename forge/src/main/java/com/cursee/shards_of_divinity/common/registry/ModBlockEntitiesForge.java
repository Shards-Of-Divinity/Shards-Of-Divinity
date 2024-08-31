package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.common.block.entity.SmoulderingAntiquatedLogBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntitiesForge {

    public static void register() {}

    public static final RegistryObject<BlockEntityType<SmoulderingAntiquatedLogBlockEntity>> SMOULDERING_ANTIQUATED_LOG_BLOCK_ENTITY =
            RegistryForge.BLOCK_ENTITY_TYPES.register("smouldering_antiquated_log", () -> BlockEntityType.Builder.of(SmoulderingAntiquatedLogBlockEntity::new, ModBlocksForge.SMOULDERING_ANTIQUATED_LOG.get()).build(null));
}
