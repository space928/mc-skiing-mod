package com.mcskiingmod.client.renderer.entity;

import com.mcskiingmod.init.BlocksRegistry;
import com.mcskiingmod.init.IRegisterable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public abstract class RendererBase<T extends Entity> extends Render<T> //implements IRegisterable<RendererBase<T>>
{
    protected String name;

    protected RendererBase(RenderManager p_i46179_1_, String name)
    {
        super(p_i46179_1_);

        this.name = name;
        //updateRegistryAndLocalizedName(name);
    }

    /*@Override public void registerItemModel()
    {

    }

    @Override public void updateRegistryAndLocalizedName(String name)
    {
        //setTranslationKey(name);
        //setRegistryName(name);

        this.name = name;
    }

    @Override public RendererBase<T> getRegistrableObject()
    {
        return this;
    }*/
}
