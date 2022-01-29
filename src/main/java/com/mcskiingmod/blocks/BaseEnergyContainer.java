package com.mcskiingmod.blocks;

import net.minecraftforge.energy.IEnergyStorage;

public class BaseEnergyContainer implements IEnergyStorage {
    private int stored;
    private int capacity;
    private int input;
    private int output;

    public BaseEnergyContainer() {
        this(250, 10, 10);
    }

    public BaseEnergyContainer(int capacity, int input, int output) {
        this.capacity = capacity;
        this.input = input;
        this.output = output;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        final int acceptedPower = Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), Math.min(this.getMaxInput(), maxReceive));

        if(!simulate)
            this.stored += acceptedPower;

        return this.canReceive() ? acceptedPower : 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        final int removedPower = Math.min(this.getEnergyStored(), Math.min(this.getMaxOutput(), maxExtract));

        if(!simulate)
            this.stored -= removedPower;
        return this.canExtract() ? removedPower : 0;
    }

    @Override
    public int getEnergyStored() {
        return this.stored;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    public void setMaxEnergyStored(int capacity) {
        this.capacity = capacity;

        if(this.stored > capacity)
            this.stored = capacity;
    }

    public int getMaxInput() {
        return this.input;
    }

    public void setMaxInput(int input) {
        this.input = input;
    }

    public int getMaxOutput() {
        return this.output;
    }

    public void setMaxOutput(int output) {
        this.output = output;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return this.getMaxInput() > 0;
    }
}
