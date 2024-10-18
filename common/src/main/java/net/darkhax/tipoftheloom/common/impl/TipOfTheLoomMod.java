package net.darkhax.tipoftheloom.common.impl;

import net.darkhax.bookshelf.common.api.ModEntry;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.service.Services;
import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.darkhax.tipoftheloom.common.impl.config.Config;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipOfTheLoomMod {

    public static final String MOD_ID = "tipoftheloom";
    public static final String MOD_NAME = "TipOfTheLoom";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final CachedSupplier<Config> CONFIG = CachedSupplier.cache(() -> ConfigManager.load(MOD_ID, new Config()));
    private static final Map<String, String> MOD_NAMES = new HashMap<>();

    public static void getPatternTooltips(Holder<BannerPattern> pattern, boolean isSelected, DyeItem dye, List<Component> tooltip, TooltipFlag flags) {
        final Config config = CONFIG.get();
        if (config.enabled && (!isSelected || config.show_on_selected_pattern)) {
            // Display Name
            tooltip.add(Component.translatable(pattern.value().translationKey() + "." + dye.getDyeColor().getName()));
            pattern.unwrapKey().ifPresent(key -> {
                // Debug ID
                if (config.debug_id.enabled && (flags.isAdvanced() || !config.debug_id.require_advanced_mode)) {
                    tooltip.add(Component.literal(key.location().toString()).withStyle(config.debug_id.display_style));
                }
                // Mod Name
                if (config.mod_name.enabled && (config.mod_name.display_on_vanilla_patterns || !ResourceLocation.DEFAULT_NAMESPACE.equals(key.location().getNamespace()))) {
                    tooltip.add(Component.literal(getModName(key.location().getNamespace())).withStyle(config.mod_name.display_style));
                }
            });
        }
    }

    private static String getModName(String modid) {
        return MOD_NAMES.computeIfAbsent(modid, id -> {
            // TODO Bookshelf needs a better way to do this.
            for (ModEntry mod : Services.PLATFORM.getLoadedMods()) {
                if (mod.modId().equals(modid)) {
                    return mod.name();
                }
            }
            return StringUtils.capitalize(modid);
        });
    }
}