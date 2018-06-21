package com.mindcoding.welcomeback;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MinecraftHook {
	private boolean sentFor = false;
	private boolean last = false;
	private String joinName;
	private EntityPlayer player;

	@SubscribeEvent
	public void ClientChatReceivedEvent(net.minecraftforge.client.event.ClientChatReceivedEvent message1)
	{
		try
		{
			if(!Minecraft.getMinecraft().getCurrentServerData().serverMOTD.toLowerCase().contains("hypixel"))
			{
				return;
			}
		}catch(Exception e)
		{
			
		}
		try {
			if(message1 == null || message1.message == null) return;
			IChatComponent message = message1.message;
			//System.out.println(message.getUnformattedText() + " " + sentFor);
			
			if(last)
			{
				last = false;
				message1.setCanceled(true);
			}			
			if(message.getUnformattedText().split(" ").length == 2 && message.getUnformattedText().split(" ")[1].equals("joined.")
					&& message.getUnformattedText().split(" ").length == 2 &&
					!sentFor)
			{
				joinName = message.getUnformattedText().split(" ")[0];
				sentFor = true;
				System.out.println(joinName);
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/g list");
				
				return;
			}
			else if(message.getUnformattedText().split(" ").length == 2 && message.getUnformattedText().split(" ")[1].equals("joined.")
					&&
					sentFor)
			{
				System.out.println("Here");
				return;
			}
			if(sentFor &&
					message.getUnformattedText().contains(joinName))
			{

				message1.setCanceled(true);
				new Thread(new Runnable() {
				    @Override public void run() {
				    	try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	StringBuilder builder = new StringBuilder();
				    	for(int i = 0; i<(int)(Math.random() * 5); i++)
				    	{
				    		builder.append("!");
				    	}
				    	if(Math.random() > .5)
				    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Welcome Back, " + joinName + builder.toString());
				    	else
				    		Minecraft.getMinecraft().thePlayer.sendChatMessage("/g chat Wb, " + joinName  + builder.toString());
				    		
				    }
				}).start();
								//sentFor = false;
			}
			if(sentFor)
			{
				message1.setCanceled(true);
				//message1.message.forEach(null)
				System.out.println("!!!!!" + message.getUnformattedText());
				//sentFor = false;
			}
			if(sentFor && message.getUnformattedText().contains("Online Members: "))
			{
				sentFor = false;
				message1.setCanceled(true);
				last = true;
			}

			//if(message.getUnformattedText())
		}catch(Exception e)
		{
		}
	}
	
}
