package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public abstract class ItemPickaxeBase extends ItemPickaxe implements IRegisterable {

	protected String name;
	
	public ItemPickaxeBase(ToolMaterial material, String name) {
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
		setUnlocalizedName(name);
		
		ItemsRegistry.ITEMS.add(this);
	}
}
