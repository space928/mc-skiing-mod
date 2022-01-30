package com.mcskiingmod.tileentity;

import com.mcskiingmod.blocks.BaseEnergyContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class TileEntityControlCabinet extends TileEntity implements ITickable {
    private final BaseEnergyContainer container  = new BaseEnergyContainer();

    public TileEntityControlCabinet() {
    }

    @Override
    public void update() {
        System.out.println(this.container.getEnergyStored());
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
            return (T) this.getContainer();

        return super.getCapability(capability, facing);
    }

    public BaseEnergyContainer getContainer() {
        return container;
    }
}
