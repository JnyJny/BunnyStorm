package com.xenolab.BunnyStorm.spigot.plugins;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Server;
import java.util.logging.Level;

// console command:  bunnystorm player [radius] [count]
//  player command:  bunnystorm [radius] [count]

public class CommandArguments {
  Server    server;
  String [] arguments;
  public CommandArguments (Server server, String [] arguments) {
    this.server = server;
    this.arguments = arguments;
  }

  Player getPlayer()
  {
    if ((arguments.length > 0) && (this.server != null)) {
	return server.getPlayer(arguments[0]);
    }
    return null;
  }

  int getRadius()
  {
    try {
      switch(arguments.length) {
	case 0:
	  break;
	case 1:
	  return Integer.decode(arguments[0]);
	case 2:
	case 3:
	default:
	  return Integer.decode(arguments[1]);
      }
    }
    catch (Exception e) { }
    return 10;
  }


  int getCount()
  {
    try {
      switch(arguments.length) {
	case 0:
	case 1:
	  break;
	  
	case 2:
	  return Integer.decode(arguments[1]);
	  
	case 3:
	default:
	  return Integer.decode(arguments[2]);
      }
    }
    catch (Exception e) {}

    return 100;
  }

  
}

