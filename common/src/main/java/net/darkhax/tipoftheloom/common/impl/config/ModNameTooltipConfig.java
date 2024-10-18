package net.darkhax.tipoftheloom.common.impl.config;

import net.darkhax.pricklemc.common.api.annotations.Value;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;

public class ModNameTooltipConfig {

    @Value(comment = "Determines if the name of the mod that added the pattern should be displayed in the tooltip.")
    public boolean enabled = true;

    @Value(comment = "Determines if patterns added by vanilla Minecraft should have their name added.")
    public boolean display_on_vanilla_patterns = true;

    @Value(comment = "The display style of the mod name. This controls the color, format, font, and other visual properties.")
    public Style display_style = Style.EMPTY.withColor(ChatFormatting.BLUE);
}
