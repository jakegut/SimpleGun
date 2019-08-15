package com.borgdude.simplegun;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.borgdude.paintball.managers.PaintballManager;

import net.md_5.bungee.api.ChatColor;

public class SimpleGun extends JavaPlugin{
    
    private SimpleGun plugin;
    
    private PaintballManager paintballManager;

    @Override
    public void onEnable() {
        plugin = this;
        if(!setupPaintball()) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Unable to startup SimpleGuns!");
        }
        
        paintballManager.registerGun(new TestGun(this));
    }

    private boolean setupPaintball() {
        RegisteredServiceProvider<PaintballManager> rsp = getServer().getServicesManager().getRegistration(PaintballManager.class);
        if(rsp == null) return false;
        paintballManager = rsp.getProvider();
        return paintballManager != null;
    }

    
}
