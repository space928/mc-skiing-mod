package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.item.ItemSword;

public abstract class ItemSwordBase extends ItemSword implements IRegisterable {

	protected String name;
	
	public ItemSwordBase(ToolMaterial material, String name) {
		super(material);
		
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
}
