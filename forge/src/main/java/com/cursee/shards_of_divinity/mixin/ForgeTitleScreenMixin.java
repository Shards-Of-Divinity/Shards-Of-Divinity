package com.cursee.shards_of_divinity.mixin;

import com.cursee.shards_of_divinity.Constants;
import com.cursee.shards_of_divinity.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ForgeTitleScreenMixin extends Screen {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {

        Constants.LOG.info("This line is printed by a mixin loaded in a {} instance!", Services.PLATFORM.getPlatformName());
        Constants.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }

    protected ForgeTitleScreenMixin(Component p_96550_) {
        super(p_96550_);
    }
}