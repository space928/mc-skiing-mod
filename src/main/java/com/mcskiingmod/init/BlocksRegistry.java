package com.mcskiingmod.init;

import java.util.ArrayList;

import com.mcskiingmod.blocks.BlockBase;
import com.mcskiingmod.blocks.BlockCompressedCobblestone;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlocksRegistry {
	public static final ArrayList<BlockBase> BLOCKS = new ArrayList<BlockBase>();
	public static final BlockCompressedCobblestone compressedCobblestone = new BlockCompressedCobblestone("compressed_cobblestone");
	
	public static void register(IForgeRegistry<Block> registry) {
		for(BlockBase block : BLOCKS) {
			registry.register(block);
		}
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for(BlockBase block : BLOCKS) {
			registry.register(block.createItemBlock());
		}
	}
	
	public static void registerModels() {
		for(BlockBase block : BLOCKS) {
			block.registerItemModel(Item.getItemFromBlock(block));
		}
	}
}
