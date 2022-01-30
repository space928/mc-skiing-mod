package com.mcskiingmod.entity;

import com.mcskiingmod.init.ICreativeTabbable;
import com.mcskiingmod.init.IRegisterable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityBase extends Entity implements IRegisterable<Entity>, ICreativeTabbable<EntityBase>
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

    @Override public Entity getRegistrableObject()
    {
        return this;
    }
}
