package com.cursee.shards_of_divinity;

import com.cursee.shards_of_divinity.client.block.entity.renderer.SmoulderingAntiquatedLogBlockEntityRenderer;
import com.cursee.shards_of_divinity.common.registry.ModBlockEntityTypesForge;
import com.cursee.shards_of_divinity.common.registry.RegistryForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ShardsOfDivinityForge {
    
    public ShardsOfDivinityForge() {
    
        ShardsOfDivinity.init();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RegistryForge.register(modEventBus);
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntityTypesForge.SMOULDERING_ANTIQUATED_LOG_BLOCK_ENTITY.get(), SmoulderingAntiquatedLogBlockEntityRenderer::new);
        }
    }
}