package com.mindcoding.welcomeback;

import net.minecraft.client.Minecraft;

public class MessageThread extends Thread implements Runnable {
	boolean bExit = false;
	String joinName = "";

	public MessageThread(String joinName) {
		super();
		this.joinName = joinName;
	}

	public void exit(boolean bExit) {
		this.bExit = bExit;
		System.out.println("IT is now " + this.bExit);
	}

	@Override
	public void run() {
		try {

			Thread.sleep((long) (400 + Math.random() * 400));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder messageBuilder = new StringBuilder();
		if (Math.random() > .5) {
			messageBuilder.append("Welcome back");
		} else {
			messageBuilder.append("Wb");
		}

		if (Math.random() > .5) {
			messageBuilder.append(", " + joinName);
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < (int) (Math.random() * 5); i++) {
			builder.append("!");
		}

		if (builder.length() == 0) {
			if (Math.random() > .5) {
				messageBuilder.append(" <o/");
			} else {
				messageBuilder.append("❤");
			}
		}
		if (!bExit)
			Minecraft.getMinecraft().thePlayer
					.sendChatMessage("/g chat " + messageBuilder.toString() + builder.toString());
		// Minecraft.getMinecraft().thePlayer.sendChatMessage("/msg " + joinName
		// + " " + messageBuilder.toString()
		// + builder.toString()); for the msg mod

	}
}
