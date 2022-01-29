package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IRegisterable, ICreativeTabbable {
	
	protected String name;	
	
	public ItemBase(String name) {
		this.name = name;	
		
		updateRegistryAndLocalizedName(name);
	}
	
	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, CreativeTabs.BUILDING_BLOCKS, name);
	}

	
	public ItemBase(ToolMaterial material, String name) {
		this.name = name;
	}
	
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public void updateRegistryAndLocalizedName(String name) {
		//used for language files
		setUnlocalizedName(name);
		//used for registering the item and its models
		setRegistryName(name);
		
		ItemsRegistry.ITEMS.add(this);
	}	
}
