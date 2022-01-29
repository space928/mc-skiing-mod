package com.mcskiingmod.blocks;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.BlocksRegistry;

import com.mcskiingmod.items.ItemMachineBlock;
import com.mcskiingmod.items.IRegisterable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Base class for all blocks defined by mc-skiing-mod
 * Implements the methods necessary for registration by Forge.
 */
public class BlockBase extends Block implements IRegisterable<BlockBase> {

    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        updateRegistryAndLocalizedName(name);
    }

    public void registerItemModel(Item itemBlock) {
        Main.proxy.registerItemRenderer(itemBlock, CreativeTabs.BUILDING_BLOCKS, name);
    }

    public Item createItemBlock() {
        ItemBlock itemBlock = new ItemMachineBlock(this);
        itemBlock.setRegistryName(getRegistryName());
        return itemBlock;
    }

    ///// Interface implementations
    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void registerItemModel() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), CreativeTabs.BUILDING_BLOCKS, name);
    }

    @Override
    public void updateRegistryAndLocalizedName(String name) {
        setTranslationKey(name);
        setRegistryName(name);

        this.name = name;
        BlocksRegistry.BLOCKS.add(this);
    }

    @Override
    public BlockBase getRegistrableObject() {
        return this;
    }
}
