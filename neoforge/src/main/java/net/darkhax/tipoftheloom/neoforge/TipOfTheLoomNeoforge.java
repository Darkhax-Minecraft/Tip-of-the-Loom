package net.darkhax.tipoftheloom.neoforge;

import net.darkhax.tipoftheloom.common.impl.TipOfTheLoomMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(TipOfTheLoomMod.MOD_ID)
public class TipOfTheLoomNeoforge {
    public TipOfTheLoomNeoforge() {
        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            TipOfTheLoomMod.LOG.debug("Initializing TipOfTheLoom");
        }
    }
}