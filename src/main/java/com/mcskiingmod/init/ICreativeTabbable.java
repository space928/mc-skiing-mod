package com.mcskiingmod.init;

import net.minecraft.creativetab.CreativeTabs;

/**
 * Implements methods necessary to be added to a tb in the creative menu.
 * @param <T> type of the object being added to the creative menu (usually a derivative of Item)
 */
public interface ICreativeTabbable<T> {
	/**
	 * Sets the tab of the creative menu the item belongs to.
	 * @param tab
	 * @return the item  being added to the creative tab
	 */
	T setCreativeTab(CreativeTabs tab);
}
