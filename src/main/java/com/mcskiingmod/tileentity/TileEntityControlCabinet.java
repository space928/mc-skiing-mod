package com.mcskiingmod.tileentity;

import com.mcskiingmod.blocks.BaseEnergyContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class TileEntityControlCabinet extends TileEntity {
    private final BaseEnergyContainer container;

    public TileEntityControlCabinet() {
        this.container = new BaseEnergyContainer();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if((facing == EnumFacing.DOWN || facing == EnumFacing.NORTH || facing == EnumFacing.WEST) && capability == CapabilityEnergy.ENERGY)
            return (T) this.container;

        return super.getCapability(capability, facing);
    }
}
