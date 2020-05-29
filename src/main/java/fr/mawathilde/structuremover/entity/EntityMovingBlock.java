package fr.mawathilde.structuremover.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Objects;

public class EntityMovingBlock extends Entity {

    private IBlockState state;

    public EntityMovingBlock(World world) {
        super(world);
    }

    public EntityMovingBlock(World world, IBlockState state) {
        super(world);
        this.state = state;
    }

    public IBlockState getBlock() {
        return state;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        final int i = compound.getByte("Data") & 255;
        if (compound.hasKey("Block", 8)){
            this.state = Objects.requireNonNull(Block.getBlockFromName(compound.getString("Block"))).getStateFromMeta(i);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        final Block block = this.state != null ? this.state.getBlock() : Blocks.AIR;
        final ResourceLocation resourceLocation = block.getRegistryName();
        compound.setByte("Data", (byte)block.getMetaFromState(state));
        compound.setString("Block", resourceLocation == null ? "" : resourceLocation.toString());
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    public boolean canBePushed() {
        return false;
    }

}
