package com.cursee.shards_of_divinity.common.registry;

import com.cursee.shards_of_divinity.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryForge {

    protected static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    protected static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    protected static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Constants.MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MOD_ID);

    public static void register(IEventBus modEventBus) {

        ModBlocksForge.register();
        ModItemsForge.register();
        ModTabsForge.register();

        ModBlockEntitiesForge.register();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        BLOCK_ENTITY_TYPES.register(modEventBus);
    }

    protected static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    protected static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    protected static <T extends Block> RegistryObject<T> registerBlockAndBlockItem(String name, Supplier<T> block) {
        registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return registerBlock(name, block);
    }

    protected static <T extends CreativeModeTab> RegistryObject<T> registerCreativeModeTab(String tabID, Supplier<T> tab) {
        return CREATIVE_MODE_TABS.register(tabID, tab);
    }

    protected static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntityType(String entityTypeID, Supplier<T> blockEntityType) {
        return BLOCK_ENTITY_TYPES.register(entityTypeID, blockEntityType);
    }
}
