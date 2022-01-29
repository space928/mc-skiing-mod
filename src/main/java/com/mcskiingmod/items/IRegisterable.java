package com.mcskiingmod.items;

/**
 * Implements the methods necessary to be registered in Forge.
 * The generic type in the interface is a hack so that any kind of object (be that Blocks, Items, etc...)
 * can be added to the registry. These objects need to be added separately to non-polymorphic registers.
 * Hence, the interface must keep track of the original type of the registrable object.
 */
public interface IRegisterable<T> {
	/**
	 * Registers the item model renderer.
	 */
	void registerItemModel();

	/**
	 * Adds the object to the registry and localization register if needed.
	 * @param name
	 */
	void updateRegistryAndLocalizedName(String name);

	/**
	 *
	 * @return the object to be added to the registry
	 */
	T getRegistrableObject();
}
