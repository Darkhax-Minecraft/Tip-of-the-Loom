package net.darkhax.tipoftheloom.fabric;

import net.darkhax.tipoftheloom.common.impl.TipOfTheLoomMod;
import net.fabricmc.api.ClientModInitializer;

public class TipOfTheLoomFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TipOfTheLoomMod.LOG.debug("Initializing TipOfTheLoom");
    }
}