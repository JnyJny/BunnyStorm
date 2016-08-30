package com.xenolab.BunnyStorm.spigot.plugins;

import java.util.List;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

public class BunnyStormPlugin extends JavaPlugin {
    
  static final String pluginShortName = "BunnyStorm";
    
  @Override
  public void onEnable() {
    //getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
    this.getCommand("bunnystorm").setExecutor(new CommandBunnyStorm());
    this.getCommand("rabbatoir").setExecutor(new CommandRabbatoir());
  }

  @Override
  public void onDisable() {
    getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    this.getCommand("bunnystorm").setExecutor(null); // not sure what the right
    this.getCommand("rabbatoir").setExecutor(null);  // action here is.
  }
}
