package fr.mawathilde.structuremover.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockStructureMover extends Block {

    public BlockStructureMover(String name) {
        super(Material.ROCK, MapColor.GRAY);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setBlockUnbreakable();
    }

}
