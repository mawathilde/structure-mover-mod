package fr.mawathilde.structuremover.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockStructureMover extends Block {

    public BlockStructureMover(String name) {
        super(Material.ROCK, MapColor.GRAY);
        setRegistryName(name);
        setUnlocalizedName(name);
    }

}
