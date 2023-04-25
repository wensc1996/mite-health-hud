package net.wensc.mitemod.healthhud.trans.render;

import net.minecraft.*;
import net.wensc.mitemod.healthhud.util.Configs;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = bhb.class, priority = 2000)
public abstract class MixinRendererLivingEntity extends bgm{

//    @Redirect(method = "b(Lnet/minecraft/EntityLiving;DDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/bhb;b(Lnet/minecraft/EntityLiving;)Z"))
//    public boolean isShowHealthHud(bhb _this,EntityLiving entityLiving) {
//        return true;
//    }


    @Inject(method = "b(Lnet/minecraft/EntityLiving;DDD)V", at = @At("HEAD"))
    public void forceShowHealthHud(EntityLiving par1EntityLivingBase, double par2, double par4, double par6, CallbackInfo callbackInfo) {
        if(!(par1EntityLivingBase instanceof EntityInvisibleStalker) && Minecraft.getClientPlayer().canSeeEntity(par1EntityLivingBase, false)) {
            float var8 = 1.6F;
            float var9 = 0.016666668F * var8;
            double var10 = par1EntityLivingBase.getDistanceSqToEntity(this.getRenderManager().h);
            float var12 = par1EntityLivingBase.isSneaking() ? 32.0F : 64.0F;
            if (var10 < (double)(var12 * var12))
            {
                String var13 = par1EntityLivingBase.getTranslatedEntityName();
                if (!par1EntityLivingBase.isSneaking())
                {
                    this.judgeInBedShowHealth(par1EntityLivingBase, par2, par4, par6, var13, var9, var10);
                }
            }
        }
    }

    protected void judgeInBedShowHealth(EntityLiving par1EntityLivingBase, double par2, double par4, double par6, String par8Str, float par9, double par10) {
        if (par1EntityLivingBase.inBed()) {
            this.renderEntityLivingHealthHud(par1EntityLivingBase, par8Str, par2, par4 - 1.5, par6, 64);
        } else {
            this.renderEntityLivingHealthHud(par1EntityLivingBase, par8Str, par2, par4, par6, 64);
        }
    }


    protected void renderEntityLivingHealthHud(EntityLiving par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9) {
        bgl renderManager = this.getRenderManager();
        double var10 = par1EntityLivingBase.getDistanceSqToEntity(renderManager.h);
        if (var10 <= (double)(par9 * par9)) {
            avi var12 = this.a();
            float var13 = 1.6F;
            float var14 = 0.016666668F * var13;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + par1EntityLivingBase.height + 0.5F, (float)par7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-renderManager.j, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(renderManager.k, renderManager.l.aa == 2 ? -1.0F : 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var14, -var14, var14);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            bfq var15 = bfq.a;
            byte var16 = 0;

            GL11.glDisable(3553);
            var15.b();

            var16 = -10;
            var15.a(0, 255, 0, 200);
            var15.a((double)(-Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(var16), 0.0D);
            var15.a((double)(-Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(7 + var16), 0.0D);
            var15.a((double)(Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(7 + var16), 0.0D);
            var15.a((double)(Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(var16), 0.0D);

            var15.a(255, 0, 0, 200);
            var15.a((double)(-Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(var16), 0.0D);
            var15.a((double)(-Configs.wenscConfig.healthHalfWidth.ConfigValue), (double)(7 + var16), 0.0D);
            float rate = 2f * (float)Configs.wenscConfig.healthHalfWidth.ConfigValue / par1EntityLivingBase.getMaxHealth() * par1EntityLivingBase.getHealth() - (float)Configs.wenscConfig.healthHalfWidth.ConfigValue;
            var15.a((double)(rate), (double)(7 + var16), 0.0D);
            var15.a((double)(rate), (double)(var16), 0.0D);

            var15.a();
            GL11.glEnable(3553);

            var16 = -11;
            String health = (int)par1EntityLivingBase.getHealth() + "/" + (int)par1EntityLivingBase.getMaxHealth();
            var12.b(health, -var12.a(health) / 2, var16, 16777215);

            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }


//    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
//            method = "a(Lnet/minecraft/EntityLiving;Ljava/lang/String;DDDI)V" ,
//            at = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/bfq;a()I",
//                    shift = At.Shift.BEFORE))
//    public void injectHealthHud(EntityLiving par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9, CallbackInfo ci, double var10, avi var12,  float var13, float var14, bfq var15, byte var16, int var17) {
//        var16 = -10;
//        var15.a(0, 0, 255, 100);
//        var15.a((double)(-20), (double)(var16), 0.0D);
//        var15.a((double)(-20), (double)(7 + var16), 0.0D);
//        var15.a((double)(20), (double)(7 + var16), 0.0D);
//        var15.a((double)(20), (double)(var16), 0.0D);
//
//        var15.a(255, 99, 71, 100);
//        var15.a((double)(-20), (double)(var16), 0.0D);
//        var15.a((double)(-20), (double)(7 + var16), 0.0D);
//        float rate = 40f / par1EntityLivingBase.getMaxHealth() * par1EntityLivingBase.getHealth() - 20f;
//        var15.a((double)(rate), (double)(7 + var16), 0.0D);
//        var15.a((double)(rate), (double)(var16), 0.0D);
//    }
//
//    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
//            method = "a(Lnet/minecraft/EntityLiving;Ljava/lang/String;DDDI)V" ,
//            at = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/avi;b(Ljava/lang/String;III)I", ordinal = 0, shift = At.Shift.BEFORE))
//    public void showHealthNum(EntityLiving par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9, CallbackInfo ci, double var10, avi var12,  float var13, float var14, bfq var15, byte var16, int var17) {
//        var16 = -11;
//        String health = (int)par1EntityLivingBase.getHealth() + "/" + (int)par1EntityLivingBase.getMaxHealth();
//        var12.b(health, -var12.a(health) / 2, var16, 16738922);
//    }
}
