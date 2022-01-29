package com.mcskiingmod.proxy;

import com.mcskiingmod.Main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Client-side proxy
 * @author ezric
 *
 */
public class ClientProxy extends CommonProxy{
	/**
	 * Find the mod ID in the assets folder in [mod id]/models/[item id].json
	 */
	@Override
	public void registerItemRenderer(Item item, CreativeTabs creativeTab, String itemId) {
		//RenderingRegistry.registerEntityRenderingHandler(EntitySkis.class, RenderSkis::new);
		int metadataValue = 0;
		ModelLoader.setCustomModelResourceLocation(item, metadataValue, new ModelResourceLocation(Main.MOD_ID + ":" + itemId, "inventory"));
	}
}
