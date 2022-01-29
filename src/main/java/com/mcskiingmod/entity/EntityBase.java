package com.mcskiingmod.entity;

import com.mcskiingmod.items.ICreativeTabbable;
import com.mcskiingmod.items.IRegisterable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBase extends Entity implements IRegisterable<EntityBase>, ICreativeTabbable<EntityBase>
{
    public EntityBase(World worldIn)
    {
        super(worldIn);
    }

    @Override public EntityBase setCreativeTab(CreativeTabs tab)
    {
        return this;
    }

    @Override public void registerItemModel()
    {

    }

    @Override public void updateRegistryAndLocalizedName(String name)
    {

    }

    @Override protected void entityInit()
    {

    }

    @Override protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }
}
