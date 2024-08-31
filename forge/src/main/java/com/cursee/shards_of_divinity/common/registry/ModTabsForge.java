package com.cursee.shards_of_divinity.common.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

public class ModTabsForge {

    public static void register() {}

    public static final RegistryObject<CreativeModeTab> SHARDS_OF_DIVINITY_TAB = RegistryForge.registerCreativeModeTab("shards_of_divinity_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> new ItemStack(ModItemsForge.ASHES_OF_CREATION.get()))
            .title(Component.translatable("itemGroup.shardsOfDivinity"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItemsForge.AMALGAMATED_ESSENCE.get());
                output.accept(ModItemsForge.ASHES_OF_CREATION.get());
                output.accept(ModItemsForge.ANTIQUATED_LOG.get());
            })
            .build());
}
