package fr.mawathilde.structuremover.init;

import fr.mawathilde.structuremover.block.BlockStructureMover;
import fr.mawathilde.structuremover.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlockInit {

    @GameRegistry.ObjectHolder("structure_mover")
    public static BlockStructureMover STRUCTURE_MOVER = null;

    public static void register(IForgeRegistry<Block> registry){
        registry.register(new BlockStructureMover("structure_mover"));
    }

}
