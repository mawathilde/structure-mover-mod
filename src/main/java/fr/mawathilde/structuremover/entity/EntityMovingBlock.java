package fr.mawathilde.structuremover.entity;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

@SuppressWarnings({"Guava", "deprecation"})
public class EntityMovingBlock extends Entity {

    protected static final DataParameter<Optional<IBlockState>> BLOCK = EntityDataManager.createKey(EntityMovingBlock.class, DataSerializers.OPTIONAL_BLOCK_STATE);

    protected BlockPos source;
    protected BlockPos target;
    protected int ticks;

    protected Vec3i targetDistance;
    protected Vector3f distance = new Vector3f(0, 0, 0);

    public EntityMovingBlock(World world) {
        super(world);
        setSize(1.0F, 1.0F);
        this.isImmuneToFire = true;
        this.noClip = true;
    }

    public EntityMovingBlock(World world, double x, double y, double z, IBlockState state, BlockPos source, BlockPos target, int ticks) {
        this(world);
        setPosition(x, y, z);
        setBlock(state);
        this.source = source;
        this.target = target;
        this.ticks = Math.max(ticks, 1);
    }

    public void setTarget(BlockPos target) {
        this.target = target;
    }

    public BlockPos getTarget() {
        return target;
    }

    public BlockPos getSource() {
        return source;
    }

    public void setSource(BlockPos source) {
        this.source = source;
    }

    public Optional<IBlockState> getBlock() {
        return dataManager.get(BLOCK);
    }

    public IBlockState getBlockValue() {
        return dataManager.get(BLOCK).or(Blocks.STONE.getDefaultState());
    }

    public void setBlock(IBlockState state) {
        dataManager.set(BLOCK, Optional.of(state));
    }

    @Override
    protected void entityInit() {
        dataManager.register(BLOCK, Optional.of(Blocks.STONE.getDefaultState()));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        Block block = Block.getBlockFromName(nbt.getString("Block"));
        if(block == null) block = Blocks.STONE;
        setBlock(block.getStateFromMeta(nbt.getInteger("Meta")));
        setSource(NBTUtil.getPosFromTag(nbt.getCompoundTag("SourcePos")));
        setTarget(NBTUtil.getPosFromTag(nbt.getCompoundTag("TargetPos")));
        ticks = nbt.getInteger("Ticks");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
        final IBlockState state = getBlock().or(Blocks.STONE.getDefaultState());
        if(state.getBlock().getRegistryName() == null) return;
        nbt.setString("Block", state.getBlock().getRegistryName().toString());
        nbt.setInteger("Meta", state.getBlock().getMetaFromState(state));
        nbt.setTag("SourcePos", NBTUtil.createPosTag(source));
        nbt.setTag("TargetPos", NBTUtil.createPosTag(target));
        nbt.setInteger("Ticks", ticks);
    }

    @Override
    public void onUpdate() {
        if(!world.isRemote && isEntityAlive()){
            if(targetDistance == null){
                targetDistance = new Vec3i(target.getX()-source.getX(), target.getY()-source.getY(), target.getZ()-source.getZ());
            }
            if(verifyValue(distance.x, targetDistance.getX()) && verifyValue(distance.y, targetDistance.getY()) && verifyValue(distance.z, targetDistance.getZ())){
                world.setBlockState(target, getBlockValue());
                setDead();
                return;
            }
            final float xSpeed = (float) targetDistance.getX()/ticks;
            final float ySpeed = (float) targetDistance.getY()/ticks;
            final float zSpeed = (float) targetDistance.getZ()/ticks;
            move(MoverType.SELF, xSpeed, ySpeed, zSpeed);
            distance.set(distance.x+xSpeed, distance.y+ySpeed, distance.z+zSpeed);
        }
    }

    protected boolean verifyValue(float value, float max){
        return max < 0 ? value <= max : value >= max;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    public boolean hitByEntity(Entity entity) {
        return entity instanceof EntityPlayer && this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) entity), 0.0F);
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

    public float getCollisionBorderSize() {
        return 0.0F;
    }


    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        final IBlockState state = getBlockValue();
        return new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
    }

    public boolean ignoreItemEntityData(){
        return true;
    }

}
