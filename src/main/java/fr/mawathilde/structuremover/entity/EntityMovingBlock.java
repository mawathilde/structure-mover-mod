package fr.mawathilde.structuremover.entity;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings({"Guava", "deprecation"})
public class EntityMovingBlock extends Entity {

    protected static final DataParameter<Optional<IBlockState>> BLOCK = EntityDataManager.createKey(EntityMovingBlock.class, DataSerializers.OPTIONAL_BLOCK_STATE);
    protected static final DataParameter<BlockPos> TARGET = EntityDataManager.createKey(EntityMovingBlock.class, DataSerializers.BLOCK_POS);

    public EntityMovingBlock(World world) {
        super(world);
        setSize(1.0F, 1.0F);
    }

    public EntityMovingBlock(World world, double x, double y, double z, IBlockState state) {
        super(world);
        setSize(1.0F, 1.0F);
        setPosition(x, y, z);
        setBlock(state);
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public void setTarget(BlockPos target) {
        dataManager.set(TARGET, target);
    }

    public BlockPos getTarget() {
        return dataManager.get(TARGET);
    }

    public Optional<IBlockState> getBlock() {
        return dataManager.get(BLOCK);
    }

    public void setBlock(IBlockState state) {
        dataManager.set(BLOCK, Optional.of(state));
    }

    @Override
    protected void entityInit() {
        dataManager.register(BLOCK, Optional.of(Blocks.STONE.getDefaultState()));
        dataManager.register(TARGET, BlockPos.ORIGIN);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        Block block = Block.getBlockFromName(nbt.getString("Block"));
        if(block == null) block = Blocks.AIR;
        setBlock(block.getStateFromMeta(nbt.getInteger("Meta")));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
        final IBlockState state = getBlock().or(Blocks.AIR.getDefaultState());
        if(state.getBlock().getRegistryName() == null) return;
        nbt.setString("Block", state.getBlock().getRegistryName().toString());
        nbt.setInteger("Meta", state.getBlock().getMetaFromState(state));
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    public boolean canBeAttackedWithItem() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox() {
        return isEntityAlive() ? getEntityBoundingBox() : null;
    }

    public boolean ignoreItemEntityData(){
        return true;
    }

}
