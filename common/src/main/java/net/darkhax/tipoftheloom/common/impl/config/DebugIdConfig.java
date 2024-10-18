package net.darkhax.tipoftheloom.common.impl.config;

import net.darkhax.pricklemc.common.api.annotations.Value;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;

public class DebugIdConfig {

    @Value(comment = "Determines if the debug ID of the pattern should be displayed on the pattern tooltip.")
    public boolean enabled = true;

    @Value(comment = "Determines if the player must have advanced tooltips (F3+H) enabled.")
    public boolean require_advanced_mode = true;

    @Value(comment = "The visual style of the debug ids. This controls the color, format, font, and other visual properties.")
    public Style display_style = Style.EMPTY.withColor(ChatFormatting.DARK_GRAY);
}
