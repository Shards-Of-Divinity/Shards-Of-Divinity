package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.common.item.resource.AshesOfCreationItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsForge {

    public static void register() {}

    public static final RegistryObject<Item> AMALGAMATED_ESSENCE = RegistryForge.registerItem("amalgamated_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ASHES_OF_CREATION = RegistryForge.registerItem("ashes_of_creation", AshesOfCreationItem::new);
    public static final RegistryObject<Item> ANTIQUATED_LOG = RegistryForge.registerItem("antiquated_log", () -> new BlockItem(ModBlocksForge.ANTIQUATED_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> SMOULDERING_ANTIQUATED_LOG = RegistryForge.registerItem("smouldering_antiquated_log", () -> new BlockItem(ModBlocksForge.SMOULDERING_ANTIQUATED_LOG.get(), new Item.Properties()));

}
