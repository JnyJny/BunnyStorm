package com.xenolab.BunnyStorm.spigot.plugins;

import java.util.List;
import java.util.logging.Level;
import java.util.Collection;
import java.util.Random;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;


public class BunnyStormPlugin extends JavaPlugin {
    
  static final String pluginShortName = "BunnyStorm";
    
  // This code is called after the server starts and after the /reload command
  @Override
  public void onEnable() {
    //getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
    this.getCommand("bunnystorm").setExecutor(new CommandBunnyStorm());
    this.getCommand("rabbatoir").setExecutor(new CommandRabbatoir());
  }

  // This code is called before the server stops and after the /reload command
  @Override
  public void onDisable() {
    getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
  }

//  @Override
//  public boolean onCommand(CommandSender sender,
//			   Command command,
//			   String label,
//			   String [] arguments)
//  {
//
//    getLogger().log(Level.INFO,"cmd name:  {0}",command.getName());
//    getLogger().log(Level.INFO,"cmd label: {0}",command.getLabel());
//	
//    for (String alias : command.getAliases()) {
//      getLogger().log(Level.INFO,"cmd alias: {0}",alias);
//    }
//	
//
//    return true;
//  }




  /*

  
    }
  */
    
}
