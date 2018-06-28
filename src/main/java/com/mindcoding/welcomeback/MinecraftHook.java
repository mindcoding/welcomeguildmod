package com.mindcoding.welcomeback;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MinecraftHook {
	private boolean sentFor = false;
	private boolean last = false;
	private int msgSent = 0;
	private String joinName;
	private EntityPlayer player;
	public static boolean wbEnabled = true;
	public static boolean welcomeEnabled = true;
	public static int welcomeCount = 0;
	public static MessageThread thread = null;

	@SubscribeEvent
	public void ClientChatReceivedEvent(net.minecraftforge.client.event.ClientChatReceivedEvent message1) {

		try {
			if (!Minecraft.getMinecraft().getCurrentServerData().serverMOTD.toLowerCase().contains("hypixel")) {
				// return;
			}
		} catch (Exception e) {

		}
		try {
			if (message1 == null || message1.message == null)
				return;
			IChatComponent message = message1.message;
			// System.out.println(message.getUnformattedText() + " " + sentFor);
			if (!message.getUnformattedText().contains(":")
					&& message.getUnformattedText().contains("joined the guild!")) {
				if (!welcomeEnabled)
					return;
				int count = (int) (Math.random() * 4);
				if (count == 0)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Welcome ❤");
				if (count == 1)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Welcome!!!");
				if (count == 2)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Welcomeeeee");
				if (count == 3)
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Welcome, hope you enjoy the guild");
			}
			if (!wbEnabled)
				return;
			if (last) {
				last = false;
				message1.setCanceled(true);
			}
			if (message.getUnformattedText().split(" ").length == 2
					&& message.getUnformattedText().split(" ")[1].equals("joined.")
					&& message.getUnformattedText().split(" ").length == 2 && !sentFor) {
				joinName = message.getUnformattedText().split(" ")[0];
				sentFor = true;
				System.out.println(joinName);
				msgSent = 0;
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/g list");

				return;
			} else if (message.getUnformattedText().split(" ").length == 2
					&& message.getUnformattedText().split(" ")[1].equals("joined.") && sentFor) {
				System.out.println("Here");
				return;
			}

			if (message.getUnformattedText().contains("§2Guild >") && (message.getUnformattedText().contains("Welcome")
					|| message.getUnformattedText().contains("Wb"))) {
				System.out.println("I am here outside");
				if (thread != null && !thread.bExit) {
					System.out.println("I am here inside");
					// Comment out for the msg mod
					thread.exit(true);
				}
			}

			if (sentFor && message.getUnformattedText().contains(joinName)) {

				message1.setCanceled(true);
				welcomeCount = 0;
				thread = new MessageThread(joinName);
				new Thread(thread).start();
				;
				// sentFor = false;
			}
			if (sentFor) {
				message1.setCanceled(true);
				// message1.message.forEach(null)
				msgSent++;
				System.out.println("!!!!!" + message.getUnformattedText());
				// sentFor = false;
			}
			if (sentFor && message.getUnformattedText().contains("Online Members: ")) {
				sentFor = false;
				message1.setCanceled(true);
				last = true;
				msgSent = 0;
			}
			if (msgSent > 25) {
				sentFor = false;
				msgSent = 0;
			}

			// if(message.getUnformattedText())
		} catch (Exception e) {
		}
	}

}
