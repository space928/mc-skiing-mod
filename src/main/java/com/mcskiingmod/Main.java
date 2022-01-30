package com.mcskiingmod;


import com.mcskiingmod.proxy.CommonProxy;

import com.mcskiingmod.tileentity.TileEntityControlCabinet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.launchwrapper.LogWrapper.log;

@Mod(modid= Main.MOD_ID, version = Main.VERSION, name = Main.NAME)
public class Main {
	public static final String MOD_ID = "mc-skiing-mod";
	public static final String VERSION = "1.0";
	public static final String NAME = "Thomas' Skiing Mod";
	public static final CreativeTabs SKIING_MOD_TAB = new SkiingModTab("SkiingMod");
	public static final Logger LOGGER = LogManager.getLogger("SkiingMod");

	@Instance
	public static Main main;

	/**
	 * must include package paths
	 */
	@SidedProxy(serverSide = "com.mcskiingmod.proxy.CommonProxy", clientSide = "com.mcskiingmod.proxy.ClientProxy")
	public static CommonProxy proxy;  
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		
	}
}

