package net.darkhax.tipoftheloom.common.mixin;

import net.darkhax.tipoftheloom.common.impl.TipOfTheLoomMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.LoomScreen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LoomScreen.class)
public abstract class MixinLoom extends AbstractContainerScreen<LoomMenu> {

    @Shadow
    private boolean displayPatterns;

    @Shadow
    private int startRow;

    public MixinLoom(LoomMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/LoomScreen;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;II)V", shift = At.Shift.AFTER))
    private void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo cbi) {
        if (this.displayPatterns && this.getMenu().getCarried().isEmpty() && this.hoveredSlot == null && this.getMenu().getDyeSlot().getItem().getItem() instanceof DyeItem dye) {
            final int regionStartX = this.leftPos + 60;
            final int regionStartY = this.topPos + 13;
            final List<Holder<BannerPattern>> patterns = this.menu.getSelectablePatterns();
            for (int gridY = 0; gridY < 4; gridY++) {
                for (int gridX = 0; gridX < 4; gridX++) {
                    final int slotIndex = (gridY + this.startRow) * 4 + gridX;
                    if (slotIndex >= patterns.size()) {
                        return;
                    }
                    final int buttonX = regionStartX + gridX * 14;
                    final int buttonY = regionStartY + gridY * 14;
                    if (mouseX >= buttonX && mouseY >= buttonY && mouseX < buttonX + 14 && mouseY < buttonY + 14) {
                        final List<Component> tooltip = new ArrayList<>();
                        TipOfTheLoomMod.getPatternTooltips(patterns.get(slotIndex), slotIndex == this.menu.getSelectedBannerPatternIndex(), dye, tooltip, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL);
                        if (!tooltip.isEmpty()) {
                            guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY);
                        }
                        return;
                    }
                }
            }
        }
    }
}
