//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mcskiingmod.client.model;

import com.mcskiingmod.entity.EntitySkis;
import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSkis extends ModelBase implements IMultipassModel
{
    public ModelRenderer[] boatSides = new ModelRenderer[5];
    public ModelRenderer[] paddles = new ModelRenderer[2];
    public ModelRenderer noWater;
    private final int patchList = GLAllocation.generateDisplayLists(1);

    public ModelSkis() {
        this.boatSides[0] = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.boatSides[1] = (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64);
        this.boatSides[2] = (new ModelRenderer(this, 0, 27)).setTextureSize(128, 64);
        this.boatSides[3] = (new ModelRenderer(this, 0, 35)).setTextureSize(128, 64);
        this.boatSides[4] = (new ModelRenderer(this, 0, 43)).setTextureSize(128, 64);
        this.boatSides[0].addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.boatSides[0].setRotationPoint(0.0F, 3.0F, 1.0F);
        this.boatSides[1].addBox(-13.0F, -7.0F, -1.0F, 18, 6, 2, 0.0F);
        this.boatSides[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
        this.boatSides[2].addBox(-8.0F, -7.0F, -1.0F, 16, 6, 2, 0.0F);
        this.boatSides[2].setRotationPoint(15.0F, 4.0F, 0.0F);
        this.boatSides[3].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.boatSides[3].setRotationPoint(0.0F, 4.0F, -9.0F);
        this.boatSides[4].addBox(-14.0F, -7.0F, -1.0F, 28, 6, 2, 0.0F);
        this.boatSides[4].setRotationPoint(0.0F, 4.0F, 9.0F);
        this.boatSides[0].rotateAngleX = 1.5707964F;
        this.boatSides[1].rotateAngleY = 4.712389F;
        this.boatSides[2].rotateAngleY = 1.5707964F;
        this.boatSides[3].rotateAngleY = 3.1415927F;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
        this.paddles[1].rotateAngleY = 3.1415927F;
        this.paddles[0].rotateAngleZ = 0.19634955F;
        this.paddles[1].rotateAngleZ = 0.19634955F;
        this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28, 16, 3, 0.0F);
        this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.noWater.rotateAngleX = 1.5707964F;
    }

    public void render(Entity p_render_1_, float p_render_2_, float p_render_3_, float p_render_4_, float p_render_5_, float p_render_6_, float p_render_7_) {
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        EntitySkis lvt_8_1_ = (EntitySkis)p_render_1_;
        this.setRotationAngles(p_render_2_, p_render_3_, p_render_4_, p_render_5_, p_render_6_, p_render_7_, p_render_1_);

        for(int lvt_9_1_ = 0; lvt_9_1_ < 5; ++lvt_9_1_) {
            this.boatSides[lvt_9_1_].render(p_render_7_);
        }

        this.renderPaddle(lvt_8_1_, 0, p_render_7_, p_render_2_);
        this.renderPaddle(lvt_8_1_, 1, p_render_7_, p_render_2_);
    }

    public void renderMultipass(Entity p_renderMultipass_1_, float p_renderMultipass_2_, float p_renderMultipass_3_, float p_renderMultipass_4_, float p_renderMultipass_5_, float p_renderMultipass_6_, float p_renderMultipass_7_) {
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.colorMask(false, false, false, false);
        this.noWater.render(p_renderMultipass_7_);
        GlStateManager.colorMask(true, true, true, true);
    }

    protected ModelRenderer makePaddle(boolean p_makePaddle_1_) {
        ModelRenderer lvt_2_1_ = (new ModelRenderer(this, 62, p_makePaddle_1_ ? 0 : 20)).setTextureSize(128, 64);
        float lvt_6_1_ = -5.0F;
        lvt_2_1_.addBox(-1.0F, 0.0F, -5.0F, 2, 2, 18);
        lvt_2_1_.addBox(p_makePaddle_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1, 6, 7);
        return lvt_2_1_;
    }

    protected void renderPaddle(EntitySkis p_renderPaddle_1_, int p_renderPaddle_2_, float p_renderPaddle_3_, float p_renderPaddle_4_) {
        float lvt_5_1_ = p_renderPaddle_1_.getRowingTime(p_renderPaddle_2_, p_renderPaddle_4_);
        ModelRenderer lvt_6_1_ = this.paddles[p_renderPaddle_2_];
        lvt_6_1_.rotateAngleX = (float)MathHelper.clampedLerp(-1.0471975803375244D, -0.2617993950843811D, (double)((MathHelper.sin(-lvt_5_1_) + 1.0F) / 2.0F));
        lvt_6_1_.rotateAngleY = (float)MathHelper.clampedLerp(-0.7853981852531433D, 0.7853981852531433D, (double)((MathHelper.sin(-lvt_5_1_ + 1.0F) + 1.0F) / 2.0F));
        if (p_renderPaddle_2_ == 1) {
            lvt_6_1_.rotateAngleY = 3.1415927F - lvt_6_1_.rotateAngleY;
        }

        lvt_6_1_.render(p_renderPaddle_3_);
    }
}
