package net.darkhax.tipoftheloom.common.impl.config;

import net.darkhax.pricklemc.common.api.annotations.Value;

public class Config {

    @Value(comment = "Determines if the mod should be enabled and apply its features.")
    public boolean enabled = true;

    @Value(comment = "Determines if the tooltip should still be displayed on patterns that have been clicked/selected.")
    public boolean show_on_selected_pattern = true;

    @Value(comment = "These properties control the mod name section of the pattern tooltip.", writeDefault = false)
    public ModNameTooltipConfig mod_name = new ModNameTooltipConfig();

    @Value(comment = "These properties control the debug ID being displayed on the pattern tooltip.", writeDefault = false)
    public DebugIdConfig debug_id = new DebugIdConfig();
}