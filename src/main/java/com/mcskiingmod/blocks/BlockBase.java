package com.mcskiingmod.blocks;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.BlocksRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

	protected String name;
	
	public BlockBase(Material material, String name) {
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);

		this.name = name;
		
		BlocksRegistry.BLOCKS.add(this);
	}
	
	public void registerItemModel(Item itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, CreativeTabs.BUILDING_BLOCKS, name);
	}
	
	public Item createItemBlock() {
		ItemBlock itemBlock = new ItemBlock(this);
		itemBlock.setRegistryName(getRegistryName());
		return itemBlock;
	}

	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
