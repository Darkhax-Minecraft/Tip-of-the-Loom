package net.darkhax.tipoftheloom.common.mixin;

import net.darkhax.tipoftheloom.common.impl.TipOfTheLoomMod;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.LoomScreen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(LoomScreen.class)
public abstract class MixinLoomScreen extends AbstractContainerScreen<LoomMenu> {

    public MixinLoomScreen(LoomMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;setTooltipForNextFrame(Lnet/minecraft/network/chat/Component;II)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci, int xo, int yo, Slot bannerSlot, Slot dyeSlot, Slot patternSlot, Slot resultSlot, int sy, Identifier sprite, int scrollerX, int scrollerY, int x, int y, List selectablePatterns, int row, int column, int actualRow, int index, int posX, int posY, Holder<BannerPattern> pattern, boolean isHighlighted, Identifier buttonSprite, DyeColor patternColor) {
        final List<Component> tooltip = new ArrayList<>();
        TipOfTheLoomMod.getPatternTooltips(pattern, index == this.menu.getSelectedBannerPatternIndex(), patternColor, tooltip, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL);
        graphics.setTooltipForNextFrame(tooltip.stream().map(Component::getVisualOrderText).toList(), mouseX, mouseY);
    }

    @Redirect(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;setTooltipForNextFrame(Lnet/minecraft/network/chat/Component;II)V"))
    private void preventTargetMethod(GuiGraphicsExtractor instance, Component component, int x, int y) {
        // Do nothing → prevents the original method call
    }
}
