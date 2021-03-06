package com.mcskiingmod.items;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

public class AluminumHoe extends ItemShovelBase{

	public static final Item.ToolMaterial ALUMINUM_TOOL_MATERIAL =
			EnumHelper.addToolMaterial("ALUMINUM", 3, 99999, 9999, 9999999, 14);

	public AluminumHoe(String name) {
		super(ALUMINUM_TOOL_MATERIAL, name);
		super.setCreativeTab(SKIING_MOD_TAB);
	}	
}
