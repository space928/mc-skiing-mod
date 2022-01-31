package com.mcskiingmod.tileentity;

import com.mcskiingmod.blocks.BaseEnergyContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

import static com.mcskiingmod.Main.SKIING_MOD_TAB;

public class TileEntityControlCabinet extends TileEntity implements ITickable {
    private final BaseEnergyContainer container  = new BaseEnergyContainer();
    private List<TileEntity> otherCabinets = new LinkedList<>();

    public TileEntityControlCabinet() {
    }

    @Override
    public void update() {
        otherCabinets = new LinkedList<>();
        for (EnumFacing side: EnumFacing.VALUES) {
            TileEntity te = CheckSide(side);
            if (te!=null)
                otherCabinets.add(te);
        }
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

    private TileEntity CheckSide(EnumFacing side) {
        int x = 0;
        int y = 0;
        int z = 0;
        switch (side) {
            case NORTH:
                z = 1;
                break;
            case SOUTH:
                z = -1;
                break;
            case EAST:
                x = 1;
                break;
            case WEST:
                x = -1;
                break;
            case UP:
                y = 2;
                break;
            case DOWN:
                y = -2;
                break;
        }
        TileEntity te = world.getTileEntity(new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z));
        if (te == null)
            return null;

        if (te.getBlockType().getCreativeTab() == SKIING_MOD_TAB)
            return te;

        return null;
    }
}
