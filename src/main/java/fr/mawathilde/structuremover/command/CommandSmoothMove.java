package fr.mawathilde.structuremover.command;

import fr.mawathilde.structuremover.entity.EntityMovingBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandSmoothMove extends CommandBase {

    @Override
    public String getName() {
        return "smoothmove";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.smoothmove.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 9) throw new WrongUsageException("commands.smoothmove.usage");
        BlockPos pos1 = parseBlockPos(sender, args, 0, false);
        BlockPos pos2 = parseBlockPos(sender, args, 3, false);
        int offsetX = parseInt(args[6]);
        int offsetY = parseInt(args[7]);
        int offsetZ = parseInt(args[8]);
        int seconds = args.length >= 10 ? parseInt(args[9]) : 1;
        sender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
        final World world = sender.getEntityWorld();
        BlockPos blockPos1 = new BlockPos(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
        BlockPos blockPos2 = new BlockPos(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));
        int blocksNumber = 0;
        for (int z = blockPos1.getZ(); z <= blockPos2.getZ(); z++) {
            for (int y = blockPos1.getY(); y <= blockPos2.getY(); y++) {
                for (int x = blockPos1.getX(); x <= blockPos2.getX(); x++) {
                    final BlockPos source = new BlockPos(x, y, z);
                    if(!world.isBlockLoaded(source)) throw new CommandException("commands.setblock.outOfWorld");
                    if(world.isAirBlock(source)) return;
                    final BlockPos target = source.add(offsetX, offsetY, offsetZ);
                    final IBlockState state = world.getBlockState(source);
                    world.setBlockToAir(source);
                    final EntityMovingBlock entityMovingBlock = new EntityMovingBlock(world, source.getX()+0.5, source.getY(), source.getZ()+0.5, state);
                    world.spawnEntity(entityMovingBlock);
                    //world.setBlockState(target, state);
                    blocksNumber++;
                }
            }
        }
        sender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, blocksNumber);
        notifyCommandListener(sender, this, "commands.smoothmove.success", blocksNumber);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length > 0 && args.length <= 3) {
            return getTabCompletionCoordinate(args, 0, targetPos);
        } else if (args.length > 3 && args.length <= 6) {
            return getTabCompletionCoordinate(args, 3, targetPos);
        }
        return Collections.emptyList();
    }

}
