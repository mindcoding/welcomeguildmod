package com.mindcoding.welcomeback;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "Guild Welcome Back Mod";
    public static final String VERSION = "0.01";
    
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {


    }
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new MinecraftHook());  	
    }
    
}