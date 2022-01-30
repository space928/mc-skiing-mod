package com.mcskiingmod.init;

import java.util.ArrayList;

import com.mcskiingmod.blocks.BlockBase;
import com.mcskiingmod.blocks.BlockCompressedCobblestone;

import com.mcskiingmod.blocks.BlockControlCabinet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * This class is responsible for registering all the derivatives of {@code BlockBase} in the Forge registry.
 */
public class BlocksRegistry {
	public static final ArrayList<IRegisterable<BlockBase>> BLOCKS = new ArrayList<>();
	public static final BlockCompressedCobblestone compressedCobblestone = new BlockCompressedCobblestone("compressed_cobblestone");
	public static final BlockControlCabinet controlCabinet = new BlockControlCabinet("control_cabinet");

	// =============== Instantiate all blocks to be registered
	//This still seems to have a bug with not being registered properly. It doesn't seem point to the correct resource?
	//https://github.com/TheGreyGhost/MinecraftByExample/blob/1-12-2-final/src/main/java/minecraftbyexample/mbe01_block_simple/StartupCommon.java
	//public static final BlockCompressedCobblestone compressedCobblestone = new BlockCompressedCobblestone("compressed_cobblestone");

	// ===============

	public static void register(IForgeRegistry<Block> registry) {
		for(IRegisterable<BlockBase> block : BLOCKS) {
			registry.register(block.getRegistrableObject());
		}
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for(IRegisterable<BlockBase> block : BLOCKS) {
			registry.register(block.getRegistrableObject().createItemBlock());
		}
	}
	
	public static void registerModels() {
		for(IRegisterable<BlockBase> block : BLOCKS) {
			block.registerItemModel();
		}
	}
}
