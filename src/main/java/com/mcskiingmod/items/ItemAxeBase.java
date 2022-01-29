package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public abstract class ItemAxeBase extends ItemAxe implements IRegisterable<Item> {

	protected String name;
	
	protected ItemAxeBase(ToolMaterial material, String name) {
		super(material, 9999, 9999);
			
		this.name = name;
		
		updateRegistryAndLocalizedName(name);
	}
	
	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public void updateRegistryAndLocalizedName(String name) {
		
		setRegistryName(name);
		setUnlocalizedName(name);
		
		ItemsRegistry.ITEMS.add(this);
	}

	@Override public ItemAxeBase getRegistrableObject()
	{
		return this;
	}
}
