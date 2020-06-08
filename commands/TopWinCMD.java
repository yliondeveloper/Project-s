package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.starmc.main.Main;

public class TopWinCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		sender.sendMessage("");
		sender.sendMessage("       §a§lTOP VITÓRIAS");
		sender.sendMessage("");
		for (String jogadores : Main.topwin) {			
			sender.sendMessage("§c" + jogadores + "");
		}
		return false;

	}

}
