package com.mcskiingmod.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.Locale;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSwissArmyKnife extends ItemBase {

    public ItemSwissArmyKnife(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            Block block = te.getBlockType();
            player.sendMessage(new TextComponentString("Selected '" + block.getLocalizedName() + "' @ " + pos));
            return EnumActionResult.PASS;
        }
        return super.onItemUse(player, world, pos, hand, facing, x, y, z);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return super.onItemRightClick(world, player, hand);
    }
}
