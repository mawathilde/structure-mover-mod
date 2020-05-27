package fr.mawathilde.structuremover.item;

import fr.mawathilde.structuremover.util.IModelLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

public class ItemBlockBase extends ItemBlock implements IModelLoader {

    public ItemBlockBase(Block block) {
        super(block);
        setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        setUnlocalizedName(block.getUnlocalizedName());
    }

    @Override
    public void registerModels() {
        if(getRegistryName() == null) return;
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
