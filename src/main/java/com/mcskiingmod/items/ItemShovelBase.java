package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public abstract class ItemShovelBase extends ItemSpade implements IRegisterable<Item> {

	protected String name;
	
	public ItemShovelBase(ToolMaterial material, String name) {
		super(material);
		
		this.name = name;
		
		updateRegistryAndLocalizedName(name);
	}
	
	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, CreativeTabs.COMBAT, name);
	}

	@Override
	public void updateRegistryAndLocalizedName(String name) {
		setRegistryName(name);
		setTranslationKey(name);
		
		ItemsRegistry.ITEMS.add(this);
	}

	@Override public ItemShovelBase getRegistrableObject()
	{
		return this;
	}
}
