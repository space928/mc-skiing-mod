# Skiing Mod Architecture in a Nutshell
## Important Classes
### Main
This is the entrypoint to the mod, it provides core functionality for initialisation and registration of all items, blocks, etc... in the mod.
This class uses `ItemRegistry` and `BlockRegistry` to automatically register all defined blocks and items.
### ClientProxy & CommonProxy
### IRegistrable\<T>
This interface is implemented by and object to be registered by Forge.
### CreativeTabbable\<T>
This interface is implemented by all items which show up in the creative menu.
### ItemRegistry
This class performs the automatic registration of all `Item` defined by the mod. All items to be registered automatically must be instantiated at least once somewhere since the construction triggers the registration to the `ItemRegistry`. By convention, we construct a dummy copy of each item in `ItemRegistry`:
```java
public static final AluminumHoe aluminumHoe = new AluminumHoe("aluminum_hoe");
public static final ItemSkis skis = new ItemSkis("skis", net.minecraft.entity.item.EntityBoat.Type.OAK);
[...]
```
**In future this should be replaced with annotations and reflection!** This is a stupid (and hardly even automated) way to do object registration.
### BlockRegistry
See `ItemRegistry`
### ItemBase
Base class for all Items in the mod to be derived from. Implements the boilerplate for registration.
### BlockBase
Base class for all Blocks in the mod to be derived from. Implements the boilerplate for registration.

## Other stuff
Check out: [Minecraft by example github](https://github.com/TheGreyGhost/MinecraftByExample/tree/1-12-2-final) for some nice Forge examples.

Made with ❤ and 🧀 by Adam and Thomas.