package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.gui.YoutuberGUI;

public class YoutuberCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		YoutuberGUI.YoutubeActive((Player) sender);
		return true;
	}

}
