package com.mcskiingmod.client.renderer.entity;

import com.mcskiingmod.client.model.ModelSkis;
import com.mcskiingmod.entity.EntitySkis;
import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderSkis extends RendererBase<EntitySkis> {
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/boat/boat_oak.png"), new ResourceLocation("textures/entity/boat/boat_spruce.png"), new ResourceLocation("textures/entity/boat/boat_birch.png"), new ResourceLocation("textures/entity/boat/boat_jungle.png"), new ResourceLocation("textures/entity/boat/boat_acacia.png"), new ResourceLocation("textures/entity/boat/boat_darkoak.png")};
    protected ModelBase modelSkis = new ModelSkis();

    public RenderSkis(RenderManager renderManager, String name) {
        super(renderManager, name);
        this.shadowSize = 0.5F;
    }

    public void doRender(EntitySkis entitySkis, double X, double Y, double Z, float pitch, float yaw) {
        GlStateManager.pushMatrix();
        this.setupTranslation(X, Y, Z);
        this.setupRotation(entitySkis, pitch, yaw);
        this.bindEntityTexture(entitySkis);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entitySkis));
        }

        this.modelSkis.render(entitySkis, yaw, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entitySkis, X, Y, Z, pitch, yaw);
    }

    public void setupRotation(EntitySkis entitySkis, float pitch, float yaw) {
        GlStateManager.rotate(180.0F - pitch, 0.0F, 1.0F, 0.0F);
        float timeBasedYaw = (float)entitySkis.getTimeSinceHit() - yaw;
        float damageBasedYaw = entitySkis.getDamageTaken() - yaw;
        if (damageBasedYaw < 0.0F) {
            damageBasedYaw = 0.0F;
        }

        if (timeBasedYaw > 0.0F) {
            GlStateManager.rotate(MathHelper.sin(timeBasedYaw) * timeBasedYaw * damageBasedYaw / 10.0F * (float)entitySkis.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double X, double Y, double Z) {
        GlStateManager.translate((float)X, (float)Y + 0.375F, (float)Z);
    }

    protected ResourceLocation getEntityTexture(EntitySkis entitySkis) {
        return BOAT_TEXTURES[entitySkis.getBoatType().ordinal()];
    }

    public boolean isMultipass() {
        return true;
    }

    public void renderMultipass(EntitySkis entitySkis, double X, double Y, double Z, float pitch, float yaw) {
        GlStateManager.pushMatrix();
        this.setupTranslation(X, Y, Z);
        this.setupRotation(entitySkis, pitch, yaw);
        this.bindEntityTexture(entitySkis);
        ((IMultipassModel)this.modelSkis).renderMultipass(entitySkis, yaw, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}
