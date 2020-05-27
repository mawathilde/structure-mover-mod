package fr.mawathilde.structuremover.init;

import fr.mawathilde.structuremover.item.ItemBlockBase;
import fr.mawathilde.structuremover.util.IModelLoader;
import fr.mawathilde.structuremover.util.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ItemInit {

    public static void register(IForgeRegistry<Item> registry){
        register(registry, new ItemBlockBase(BlockInit.STRUCTURE_MOVER));
    }

    private static void register(IForgeRegistry<Item> registry, Item item){
        registry.register(item);
        if(item instanceof IModelLoader) ((IModelLoader) item).registerModels();
    }

}
