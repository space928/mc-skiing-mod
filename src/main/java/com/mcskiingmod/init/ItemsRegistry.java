package com.mcskiingmod.init;

import java.util.ArrayList;

import com.mcskiingmod.items.*;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * This class is responsible for registering all the derivatives of {@code IRegisterable<Item>} (any {@code Item}s
 * implementing {@code IRegisterable}) in the Forge registry.
 */
public class ItemsRegistry {

	public static final ArrayList<IRegisterable<Item>> ITEMS = new ArrayList<>();

	// =============== Instantiate all blocks to be registered
	public static final AluminumIngot aluminumIngot = new AluminumIngot("aluminum_ingot");	
	public static final AluminumSword aluminumSword = new AluminumSword("aluminum_sword");
	public static final AluminumPickaxe aluminumPickaxe = new AluminumPickaxe("aluminum_pickaxe");
	public static final AluminumAxe aluminumAxe = new AluminumAxe("aluminum_axe");
	public static final AluminumShovel aluminumShovel = new AluminumShovel("aluminum_shovel");
	public static final AluminumHoe aluminumHoe = new AluminumHoe("aluminum_hoe");
	public static final ItemSwissArmyKnife swissArmyKnife = new ItemSwissArmyKnife("swiss_army_knife");
	public static final ItemSkis skis = new ItemSkis("skis", net.minecraft.entity.item.EntityBoat.Type.OAK);

	// ===============

	public static void register(IForgeRegistry<Item> registry) {		
		//registry.registerAll(aluminumIngot, aluminumSword, aluminumPickaxe, aluminumAxe, aluminumShovel, aluminumHoe, skis);
		for(IRegisterable<Item> item : ITEMS) {
			registry.register(item.getRegistrableObject());
		}
	}

	public static void registerModels() {
		for(IRegisterable<Item> item : ITEMS) {
			item.registerItemModel();
		}
	}

}
