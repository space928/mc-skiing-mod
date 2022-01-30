package com.mcskiingmod.items;

import com.mcskiingmod.Main;
import com.mcskiingmod.init.ICreativeTabbable;
import com.mcskiingmod.init.IRegisterable;
import com.mcskiingmod.init.ItemsRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Base class for all items defined by mc-skiing-mod
 * Implements the methods necessary for registration by Forge.
 */
public abstract class ItemBase extends Item implements IRegisterable<Item>, ICreativeTabbable<ItemBase>
{
	
	protected String name;

	/**
	 * Constructs a new item with a given name and registers it automatically.
	 * @param name registry and localisation name of the item
	 */
	public ItemBase(String name) {
		this.name = name;	
		
		updateRegistryAndLocalizedName(name);
	}

	/**
	 * Sets which tab the item belongs to.
	 * @param tab creative menu tab to assign the item to
	 * @return itself
	 */
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	public void registerItemModel() {
		Main.proxy.registerItemRenderer(this, CreativeTabs.BUILDING_BLOCKS, name);
	}
	
	@Override
	public void updateRegistryAndLocalizedName(String name) {
		//used for language files
		setTranslationKey(name);
		//used for registering the item and its models
		setRegistryName(name);
		
		ItemsRegistry.ITEMS.add(this);
	}

	@Override public ItemBase getRegistrableObject()
	{
		return this;
	}
}
