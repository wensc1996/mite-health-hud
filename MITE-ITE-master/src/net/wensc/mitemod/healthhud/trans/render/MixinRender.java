package net.wensc.mitemod.healthhud.trans.render;

import net.minecraft.bgl;
import net.minecraft.bgm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = bgm.class, priority = 2000)
public class MixinRender {
    @Shadow
    protected bgl b;
    public bgl getRenderManager() {
        return this.b;
    }
}
