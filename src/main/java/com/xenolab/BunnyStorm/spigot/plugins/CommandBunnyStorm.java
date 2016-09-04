package com.xenolab.BunnyStorm.spigot.plugins;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;

public class CommandBunnyStorm implements CommandExecutor
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
      sender.sendMessage("Unable to determine location for the bunnystorm!");
      return true;
    }

    sender.sendMessage(bunnyStorm(cmdArgs.getPlayer(), cmdArgs.getCount(), cmdArgs.getRadius()));
    
    return true;
  }

  private boolean onPlayerCommand(Player player, Command command, String label, String [] args)
  {
    CommandArguments cmdArgs = new CommandArguments(player.getServer(), args);

    player.sendMessage(bunnyStorm(player, cmdArgs.getCount(), cmdArgs.getRadius()));

    return true;
  }
    

  private String bunnyStorm(Player player, int numOfRabbits, int radius)
  {
    Location location = player.getLocation();
    World world = player.getWorld();
    int count = 0;

    for (int i=0; i < numOfRabbits; i++) {
      spawnRabbit(world,
		  randomLocationInRadius(location, radius),
		  0.4, 0.01);
      count ++;
    }

    return String.format("%d rabbits (%d expected) in that storm!", count, numOfRabbits);
  }


  private double randomRange(double lo, double hi)
  {
    return (Math.random() * (hi - lo)) + lo;
  }

  private Location randomLocationInRadius(Location base, double radius)
  {
    double x = base.getX() + randomRange(-radius, radius);
    double y = base.getY() + randomRange(-radius, radius);
    double z = base.getZ() + randomRange(-radius, radius);
    return new Location(base.getWorld(), x, y, z);
  }

  private Rabbit spawnRabbit(World world, Location location, double percentBaby, double percentKiller)
  {

    Block block = world.getHighestBlockAt(location);
	
    Rabbit r = world.spawn(block.getLocation(), Rabbit.class);
	
    r.setAdult();
	
    if ( (percentBaby > 0.0) && (Math.random() < percentBaby))
      r.setBaby();
    else {
      if ( (percentKiller > 0.0) && (Math.random() < percentKiller)) {
	r.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
      }
    }
	
    return r;
  }

}


