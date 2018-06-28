package com.mindcoding.welcomeback;

import java.awt.Color;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ToggleWb extends CommandBase implements ICommand
{
	@Override
	public String getCommandName()
	{
		return "togglewb";
	}
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }	
	@Override
	public void processCommand(ICommandSender ics, String[] args) throws CommandException
	{
		MinecraftHook.wbEnabled = !MinecraftHook.wbEnabled;
		if(MinecraftHook.wbEnabled)
			ics.addChatMessage(new ChatComponentText("Welcome backs are now enabled")
					.setChatStyle(new ChatStyle()
							.setColor(EnumChatFormatting.GREEN)));
		else
		{
			ics.addChatMessage(new ChatComponentText("Welcome backs are now disabled")					.setChatStyle(new ChatStyle()
					.setColor(EnumChatFormatting.RED)));			
		}
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Toggles whether to send Welcome back messages";
	}
}