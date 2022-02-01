package com.mcskiingmod.items;

import com.mcskiingmod.SkiingModTab;
import net.minecraft.block.Block;
import net.minecraft.client.resources.Locale;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

public class ItemSwissArmyKnife extends ItemBase {
    private BlockPos SelectedBlockPos;
    private Block SelectedBlock;
    private Timer clickTimer;

    public ItemSwissArmyKnife(String name) {
        super(name);
        super.setCreativeTab(SKIING_MOD_TAB);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (isSelected&&clickTimer != null)
            clickTimer.updateTimer();
        super.onUpdate(itemStack, world, entity, itemSlot, isSelected);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float x, float y, float z, EnumHand hand) {
        if (clickTimer == null) {
            clickTimer = new Timer(20);
        } else {
            if (clickTimer.elapsedTicks < 1)
                return super.onItemUseFirst(player, world, pos, facing, x, y, z, hand);
        }

        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            Block block = te.getBlockType();
            if (block.getCreativeTab() == SKIING_MOD_TAB) {
                player.sendStatusMessage(new TextComponentString("Selected '" + block.getLocalizedName() + "' @ " + pos), true);
                if (SelectedBlock == null) {
                    SelectedBlock = block;
                    SelectedBlockPos = pos;
                } else {
                    // Run Wand stuff - Currently distance measurement test code, to be replaced with linking code
                    player.sendStatusMessage(new TextComponentString("Distance Between Points: " + new Vec3d(SelectedBlockPos).distanceTo(new Vec3d(pos)) + "m"), true);


                    SelectedBlock = null;
                    SelectedBlockPos = null;
                }
                return EnumActionResult.PASS;
            }
        }
        return super.onItemUseFirst(player, world, pos, facing, x, y, z, hand);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return super.onItemRightClick(world, player, hand);
    }
}
