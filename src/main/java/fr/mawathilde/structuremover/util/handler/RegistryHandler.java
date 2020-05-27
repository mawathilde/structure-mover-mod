package fr.mawathilde.structuremover.util.handler;

import fr.mawathilde.structuremover.init.BlockInit;
import fr.mawathilde.structuremover.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class RegistryHandler {

    @SubscribeEvent
    public void onBlocksRegister(RegistryEvent.Register<Block> event){
        BlockInit.register(event.getRegistry());
    }

    @SubscribeEvent
    public void onItemsRegister(RegistryEvent.Register<Item> event){
        ItemInit.register(event.getRegistry());
    }

}
