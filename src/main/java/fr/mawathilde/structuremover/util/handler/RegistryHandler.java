package fr.mawathilde.structuremover.util.handler;

import fr.mawathilde.structuremover.init.EntityInit;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

public final class RegistryHandler{

    @SubscribeEvent
    public void onEntitiesRegister(RegistryEvent.Register<EntityEntry> event){
        EntityInit.register(event.getRegistry());
    }

}
