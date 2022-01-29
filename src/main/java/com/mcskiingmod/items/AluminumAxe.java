package com.mcskiingmod.items;

import com.mcskiingmod.SkiingModTab;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

public class AluminumAxe extends ItemAxeBase {
	
	public static final Item.ToolMaterial ALUMINUM_TOOL_MATERIAL = EnumHelper.addToolMaterial("ALUMINUM", 3, 99999, 9999, 9999999, 14); 
	
	public AluminumAxe(String name) {
		super(ALUMINUM_TOOL_MATERIAL, name);
		super.setCreativeTab(SKIING_MOD_TAB);
	}
}
