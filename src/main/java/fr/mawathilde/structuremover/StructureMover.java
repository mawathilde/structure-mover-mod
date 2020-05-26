package fr.mawathilde.structuremover;

import fr.mawathilde.structuremover.proxy.IProxy;
import fr.mawathilde.structuremover.util.Reference;
import fr.mawathilde.structuremover.util.handler.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID)
public class StructureMover {

    @SidedProxy(modId = Reference.MOD_ID, clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());
        proxy.preInit(event);
    }

}
