package com.mcskiingmod;


import com.mcskiingmod.client.renderer.entity.RenderSkis;
import com.mcskiingmod.entity.EntitySkis;
import com.mcskiingmod.init.BlocksRegistry;
import com.mcskiingmod.init.ItemsRegistry;
import com.mcskiingmod.items.ItemSkis;
import com.mcskiingmod.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid= Main.MOD_ID, version = Main.VERSION, name = Main.NAME)
public class Main {
	public static final String MOD_ID = "mc-skiing-mod";
	public static final String VERSION = "1.0";
	public static final String NAME = "Thomas' Skiing Mod";
	public static final CreativeTabs SKIING_MOD_TAB = new SkiingModTab("SkiingMod");

	@Instance
	public static Main main;	
	
	/**
	 * must include package paths
	 */
	@SidedProxy(serverSide = "com.mcskiingmod.proxy.CommonProxy", clientSide = "com.mcskiingmod.proxy.ClientProxy")
	public static CommonProxy proxy;  
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntitySkis.class, RenderSkis::new);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		
	}
	
	/**
	 * This is something we need to immediately load in our event bus at mod construction time (built)
	 * @author ezric
	 *
	 */
	@EventBusSubscriber
	public static class RegistrationHandler{
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			BlocksRegistry.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerItem(RegistryEvent.Register<Item> event) throws Exception {
			try {
				IForgeRegistry<Item> registry = event.getRegistry();
				ItemsRegistry.register(registry);
				BlocksRegistry.registerItemBlocks(event.getRegistry());
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				String message = ex.getMessage();
				throw ex;
			}
			catch(Throwable ex) {
				String message = ex.getMessage();
				throw ex;
			}
		}
		
		@SubscribeEvent
		public static void registerItems(ModelRegistryEvent event) {
			ItemsRegistry.registerModels();
			BlocksRegistry.registerModels();
		}
		
		
	}
}

