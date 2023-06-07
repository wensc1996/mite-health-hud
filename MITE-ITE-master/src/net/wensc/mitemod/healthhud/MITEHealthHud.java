package net.wensc.mitemod.healthhud;


import net.wensc.mitemod.healthhud.trans.TransMarker;
import net.wensc.mitemod.healthhud.util.Configs;
import net.xiaoyu233.fml.AbstractMod;


import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod(MixinEnvironment.Side.CLIENT)
public class MITEHealthHud extends AbstractMod {

    public MITEHealthHud() {
    }

    public void preInit() {
    }


    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("mite-health-hud", TransMarker.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }


    public void postInit() {
        super.postInit();
        Configs.loadConfigs();
    }


    public String modId() {
        return "mite-health-hud";
    }

    public int modVerNum() {
        return 3;
    }

    public String modVerStr() {
        return "0.0.3";
    }
}
