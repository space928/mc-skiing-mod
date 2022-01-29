package com.mcskiingmod.blocks;

import com.mcskiingmod.Main;
import net.minecraft.block.material.Material;

public class BlockCompressedCobblestone extends BlockBase {

	public BlockCompressedCobblestone(String name) {
		super(Material.ROCK, name);
		setHardness(3f);
		setResistance(5f);
		super.setCreativeTab(Main.SKIING_MOD_TAB);
		
	}

}
