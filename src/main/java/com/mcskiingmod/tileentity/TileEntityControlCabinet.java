package com.mcskiingmod.tileentity;

import com.mcskiingmod.blocks.BaseEnergyContainer;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityControlCabinet extends TileEntity implements ITickable, IPeripheral {
    private final BaseEnergyContainer container  = new BaseEnergyContainer();

    public TileEntityControlCabinet() {
    }

    @Override
    public void update() {
//        System.out.println(this.container.getEnergyStored());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if((facing != EnumFacing.SOUTH) && capability == CapabilityEnergy.ENERGY)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if((facing != EnumFacing.SOUTH) && capability == CapabilityEnergy.ENERGY)
            return (T) this.getContainer();

        return super.getCapability(capability, facing);
    }

    public BaseEnergyContainer getContainer() {
        return container;
    }

    @Nonnull
    @Override
    public String getType() {
        return "Control_Cabinet";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return new String[0];
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computer, @Nonnull ILuaContext context, int method, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        return new Object[0];
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        if(other == null)
            return false;

        return (this == other);
    }

    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        IPeripheral.super.attach(computer);
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        IPeripheral.super.detach(computer);
    }
}
