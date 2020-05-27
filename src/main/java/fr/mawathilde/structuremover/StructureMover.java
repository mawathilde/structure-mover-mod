package fr.mawathilde.structuremover;

import fr.mawathilde.structuremover.command.CommandSmoothMove;
import fr.mawathilde.structuremover.proxy.IProxy;
import fr.mawathilde.structuremover.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID)
public class StructureMover {

    @SidedProxy(modId = Reference.MOD_ID, clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandSmoothMove());
    }

}
