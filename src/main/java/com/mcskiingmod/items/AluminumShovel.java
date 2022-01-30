package com.mcskiingmod.items;

import com.mcskiingmod.init.AutoRegisterObject;
import com.mcskiingmod.init.RegisterableObjectType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.Item;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

@AutoRegisterObject(objType = RegisterableObjectType.ITEM, regInstance = new AluminumShovel("aluminum_shovel"))
public class AluminumShovel extends ItemShovelBase {

	public static final Item.ToolMaterial ALUMINUM_TOOL_MATERIAL = EnumHelper.addToolMaterial("ALUMINUM", 3, 99999, 9999, 9999999, 14);
	
	public AluminumShovel(String name) {
		super(ALUMINUM_TOOL_MATERIAL, name);
		super.setCreativeTab(SKIING_MOD_TAB);
	}

}
