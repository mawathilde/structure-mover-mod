package fr.mawathilde.structuremover.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandSmoothMove extends CommandBase {

    @Override
    public String getName() {
        return "smoothmove";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.smoothmove.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        
    }

}
