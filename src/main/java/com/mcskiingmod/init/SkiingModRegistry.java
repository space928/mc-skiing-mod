package com.mcskiingmod.init;

import java.util.*;

import com.mcskiingmod.blocks.BlockBase;
import com.mcskiingmod.items.*;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import org.reflections.Reflections;

/**
 * This class is responsible for automatically registering all objects annotated with {@code AutoRegisterObject}
 */
public class SkiingModRegistry {
    /**
     * Extracts all registrable objects using reflection and sorts them into a hashmap.
     * @return a dictionary containing lists of each object to be registered.
     */
    public static HashMap<RegisterableObjectType, ArrayList<IRegisterable>> getAutoRegistrable() {
        HashMap<RegisterableObjectType, ArrayList<IRegisterable>> registry = new HashMap<>();

        Reflections reflection = new Reflections("com.mcskiingmod.*");
        for(Class cls : reflection.getTypesAnnotatedWith(AutoRegisterObject.class)) {
            if(Arrays.stream(cls.getInterfaces()).anyMatch(x->x == IRegisterable.class))
            {
                IRegisterable ireg = IRegisterable.class.cast(cls);

                AutoRegisterObject reg = (AutoRegisterObject) cls.getAnnotation(AutoRegisterObject.class);
                switch (reg.objType())
                {
                    case ITEM:
                        appendRegisterableObject(registry, RegisterableObjectType.ITEM, ireg);
                        break;
                    case BLOCK:
                        appendRegisterableObject(registry, RegisterableObjectType.BLOCK, ireg);
                        break;
                }
            }
        }
    }

    private static void appendRegisterableObject (HashMap<RegisterableObjectType, ArrayList<IRegisterable>> map,
                                                  RegisterableObjectType key,
                                                  IRegisterable obj) {
        if(map.containsKey(key))
        {
            map.get(key).add(obj);
        }else
        {
            ArrayList<IRegisterable> arr = new ArrayList<>();
            arr.add(obj);
            map.put(key, arr);
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        /*for(IRegisterable<Item> item : ITEMS) {
            registry.register(item.getRegistrableObject());
        }*/
    }

    public static void registerModels() {
        /*for(IRegisterable<Item> item : ITEMS) {
            item.registerItemModel();
        }

        for(IRegisterable<BlockBase> block : BLOCKS) {
            block.registerItemModel();
        }*/
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        /*for(IRegisterable<BlockBase> block : BLOCKS) {
            registry.register(block.getRegistrableObject());
        }*/
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        /*for(IRegisterable<BlockBase> block : BLOCKS) {
            registry.register(block.getRegistrableObject().createItemBlock());
        }*/
    }

    /*public static void registerEntities(IForgeRegistry<EntityEntry> registry){
        for(IRegisterable<Entity> entity : ENTITIES) {
            registry.register(block.getRegistrableObject());
        }
    }*/
}
