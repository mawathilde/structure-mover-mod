package fr.mawathilde.structuremover.util.handler;

import fr.mawathilde.structuremover.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class RegistryHandler {

    @SubscribeEvent
    public void onBlocksRegister(RegistryEvent.Register<Block> event){
        BlockInit.register(event.getRegistry());
    }

}
