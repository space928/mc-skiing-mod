package com.mcskiingmod.items;

import com.mcskiingmod.entity.EntitySkis;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

public class ItemSkis extends ItemBase{
    private final EntityBoat.Type type;

    public ItemSkis(String name, EntityBoat.Type type) {
        super(name);
        this.type = type;
        super.setCreativeTab(SKIING_MOD_TAB);
        this.maxStackSize = 1;
        this.setTranslationKey("skis_" + type.getName());
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand enumHand) {
        ItemStack heldItem = entityPlayer.getHeldItem(enumHand);

        float playerPitch = entityPlayer.prevRotationPitch + (entityPlayer.rotationPitch - entityPlayer.prevRotationPitch);
        float playerYaw = entityPlayer.prevRotationYaw + (entityPlayer.rotationYaw - entityPlayer.prevRotationYaw);
        double playerX = entityPlayer.prevPosX + (entityPlayer.posX - entityPlayer.prevPosX);
        double playerY = entityPlayer.prevPosY + (entityPlayer.posY - entityPlayer.prevPosY) + (double)entityPlayer.getEyeHeight();
        double playerZ = entityPlayer.prevPosZ + (entityPlayer.posZ - entityPlayer.prevPosZ);
        Vec3d playerCoords = new Vec3d(playerX, playerY, playerZ);

        float cosPlayerYaw = MathHelper.cos(-playerYaw * 0.017453292F - 3.1415927F);
        float sinPlayerYaw = MathHelper.sin(-playerYaw * 0.017453292F - 3.1415927F);
        float cosPlayerPitch = -MathHelper.cos(-playerPitch * 0.017453292F);
        float sinPlayerPitch = MathHelper.sin(-playerPitch * 0.017453292F);
        float sinYawXCosPitch = sinPlayerYaw * cosPlayerPitch;
        float cosYawXCostPitch = cosPlayerYaw * cosPlayerPitch;
        Vec3d playerCoordWithDirection = playerCoords.add((double)sinYawXCosPitch * 5.0D, (double)sinPlayerPitch * 5.0D, (double)cosYawXCostPitch * 5.0D);

        RayTraceResult rayTraceResult = world.rayTraceBlocks(playerCoords, playerCoordWithDirection, true);
        if (rayTraceResult == null) {
            return new ActionResult(EnumActionResult.PASS, heldItem);
        } else {
            Vec3d lvt_26_1_ = entityPlayer.getLook(1.0F);
            boolean inBoundingBox = false;
            List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(entityPlayer, entityPlayer.getEntityBoundingBox().expand(lvt_26_1_.x * 5.0D, lvt_26_1_.y * 5.0D, lvt_26_1_.z * 5.0D).grow(1.0D));

            for(int i = 0; i < entityList.size(); ++i) {
                Entity entity = (Entity)entityList.get(i);
                if (entity.canBeCollidedWith()) {
                    AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());
                    if (entityBoundingBox.contains(playerCoords)) {
                        inBoundingBox = true;
                    }
                }
            }

            if (inBoundingBox) {
                return new ActionResult(EnumActionResult.PASS, heldItem);
            } else if (rayTraceResult.typeOfHit != net.minecraft.util.math.RayTraceResult.Type.BLOCK) {
                return new ActionResult(EnumActionResult.PASS, heldItem);
            } else {
                Block raytracedBlock = world.getBlockState(rayTraceResult.getBlockPos()).getBlock();
                boolean isWater = raytracedBlock == Blocks.WATER || raytracedBlock == Blocks.FLOWING_WATER;
                EntitySkis entityBoat = new EntitySkis(world, rayTraceResult.hitVec.x, isWater ? rayTraceResult.hitVec.y - 0.12D : rayTraceResult.hitVec.y, rayTraceResult.hitVec.z);
                entityBoat.setBoatType(this.type);
                entityBoat.rotationYaw = entityPlayer.rotationYaw;
                if (!world.getCollisionBoxes(entityBoat, entityBoat.getEntityBoundingBox().grow(-0.1D)).isEmpty()) {
                    return new ActionResult(EnumActionResult.FAIL, heldItem);
                } else {
                    if (!world.isRemote) {
                        world.spawnEntity(entityBoat);
                    }

                    if (!entityPlayer.capabilities.isCreativeMode) {
                        heldItem.shrink(1);
                    }

                    entityPlayer.addStat(StatList.getObjectUseStats(this));
                    return new ActionResult(EnumActionResult.SUCCESS, heldItem);
                }
            }
        }
    }
}
