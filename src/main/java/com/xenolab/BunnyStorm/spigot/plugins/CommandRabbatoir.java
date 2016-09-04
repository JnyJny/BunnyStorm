package com.xenolab.BunnyStorm.spigot.plugins;

import java.util.logging.Level;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;


public class CommandRabbatoir implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args)
    {
      if ( sender instanceof Player ) {
	return onPlayerCommand((Player)sender, command, label, args);
      }
      
      if (sender instanceof ServerOperator) {
	return onConsoleCommand(sender, command, label, args);
      }
      
      return false;
    }


    private boolean onConsoleCommand(CommandSender sender, Command command, String label, String [] args)
    {
      CommandArguments cmdArgs = new CommandArguments(sender.getServer(), args);

      if (cmdArgs.getPlayer() == null) {
	sender.sendMessage("Unable to determine a location for rabbatoir!");
	return true;
      }

      sender.sendMessage(rabbatoir(cmdArgs.getPlayer(), cmdArgs.getRadius()));
      return true;
    }

    private boolean onPlayerCommand(Player player, Command command, String label, String [] args)
    {
      CommandArguments cmdArgs = new CommandArguments(null, args);

      player.sendMessage(rabbatoir(player, cmdArgs.getRadius()));

      return true;
    }

    private String rabbatoir(Player player, int radius)
    {
      Location location = player.getLocation();
      World world = player.getWorld();
      int count = 0;
      
      for (Entity entity : world.getNearbyEntities(location, radius, radius, radius)) {
	if (entity instanceof Rabbit) {
	  Rabbit rabbit = (Rabbit)entity;
	  rabbit.remove();	// instead of setHealth(0)
	  count += 1;
	  continue;
	}
      }

      switch (count) {
	case 0:
	  return String.format("No rabbits to kill within %d blocks", radius);
	default:
	  return String.format("%d rabbits fall victim to the Rabbatoir!",count);
      }
    }
}


