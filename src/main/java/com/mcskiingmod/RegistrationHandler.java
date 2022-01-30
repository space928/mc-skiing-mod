package com.mcskiingmod;

import com.mcskiingmod.client.renderer.entity.RenderSkis;
import com.mcskiingmod.client.renderer.entity.RendererBase;
import com.mcskiingmod.entity.EntityBase;
import com.mcskiingmod.entity.EntitySkis;
import com.mcskiingmod.init.BlocksRegistry;
import com.mcskiingmod.init.ItemsRegistry;
import com.mcskiingmod.tileentity.TileEntityControlCabinet;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

import java.lang.reflect.InvocationTargetException;

/**
 * Class for handling all the Forge registration events of the mod.
 */
@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class RegistrationHandler
{
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        BlocksRegistry.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) throws Exception
    {
        try
        {
            IForgeRegistry<Item> registry = event.getRegistry();
            ItemsRegistry.register(registry);
            BlocksRegistry.registerItemBlocks(event.getRegistry());
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            String message = ex.getMessage();
            throw ex;
        } catch (Throwable ex)
        {
            String message = ex.getMessage();
            throw ex;
        }

    }

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> event)
    {
        EntityEntry skisEntityEntry = EntityEntryBuilder.create()
                .entity(EntitySkis.class)
                .id(new ResourceLocation(Main.MOD_ID, "skis"), 0)
                .name("skis")
                .tracker(64, 20, true)
                .build();
        event.getRegistry().register(skisEntityEntry);
    }

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event)
    {
        ItemsRegistry.registerModels();
        BlocksRegistry.registerModels();
    }

    public static void registerTileEntities() {
        registerTileEntity(TileEntityControlCabinet.class, "control_cabinet");
    }

    public static void registerEntityRenderers(){
        registerEntityRenderer(RenderSkis.class, EntitySkis.class, "entity_skis");
    }

    static void registerTileEntity(Class<? extends TileEntity> clazz, String name)
    {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(Main.MOD_ID, name));
    }

    private static <T extends EntityBase> void registerEntityRenderer(Class<? extends RendererBase<T>> type, Class<T> entityType, String name)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityType, manager -> {
            try
            {
                return type.getConstructor(RenderManager.class, String.class).newInstance(manager, name);
            } catch (Exception e) {
                FMLLog.log.log(Level.ERROR, "Failed to register entity renderer: " + type.toString());
            }
            return null;
        });
    }
}
