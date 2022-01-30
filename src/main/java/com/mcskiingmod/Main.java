package com.mcskiingmod;


import com.mcskiingmod.client.renderer.entity.RenderSkis;
import com.mcskiingmod.entity.EntitySkis;
import com.mcskiingmod.init.BlocksRegistry;
import com.mcskiingmod.init.ItemsRegistry;
import com.mcskiingmod.items.ItemSkis;
import com.mcskiingmod.proxy.CommonProxy;

import com.mcskiingmod.tileentity.TileEntityControlCabinet;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
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
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		registerTileEntities();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		
	}

	private void registerTileEntities() {
		RegistrationHandler.registerTileEntity(TileEntityControlCabinet.class, "control_cabinet");
	}

	/**
	 * Class for handling all the Forge registration events of the mod.
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
		public static void registerEntity(RegistryEvent.Register<EntityEntry> event) {
			EntityEntry skisEntityEntry = EntityEntryBuilder.create()
					.entity(EntitySkis.class)
					.id(new ResourceLocation(MOD_ID, "skis"), 0)
					.name("skis")
					.tracker(64,20,true)
					.build();
			event.getRegistry().register(skisEntityEntry);
		}
		
		@SubscribeEvent
		public static void registerItems(ModelRegistryEvent event) {
			ItemsRegistry.registerModels();
			BlocksRegistry.registerModels();
		}

		private static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
			GameRegistry.registerTileEntity(clazz, new ResourceLocation(MOD_ID, name));
		}
	}
}

