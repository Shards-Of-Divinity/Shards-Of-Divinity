package com.cursee.shards_of_divinity;

import com.cursee.shards_of_divinity.common.registry.ModBlocksForge;
import com.cursee.shards_of_divinity.common.registry.RegistryForge;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ShardsOfDivinityForge {
    
    public ShardsOfDivinityForge() {
    
        ShardsOfDivinity.init();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RegistryForge.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(this::onLivingDeath);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerClone);
    }

    private void onLivingDeath(LivingDeathEvent event) {
        event.getEntity().sendSystemMessage(Component.literal("You...").withStyle(ChatFormatting.BLACK));
    }

    private void onPlayerClone(PlayerEvent.Clone event) {

        event.getEntity().sendSystemMessage(Component.literal("You are not from here...").withStyle(ChatFormatting.BLACK));

    }
}