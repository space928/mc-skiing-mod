package com.mcskiingmod;

import com.mcskiingmod.init.ItemsRegistry;
import com.mcskiingmod.items.ItemSkis;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.ItemStack;

public class SkiingModTab extends CreativeTabs {

        public SkiingModTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemsRegistry.skis);
    }
}
