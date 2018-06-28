package com.mindcoding.welcomeback;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ToggleWelcome extends CommandBase implements ICommand {
	@Override
	public String getCommandName() {
		return "togglewelcome";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public void processCommand(ICommandSender ics, String[] args) throws CommandException {
		MinecraftHook.welcomeEnabled = !MinecraftHook.welcomeEnabled;
		if (MinecraftHook.welcomeEnabled)
			ics.addChatMessage(new ChatComponentText("Welcome messages are now enabled")
					.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
		else {
			ics.addChatMessage(new ChatComponentText("Welcome messages are now disabled")
					.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
		}
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Toggles whether to send Welcome messages";
	}
}