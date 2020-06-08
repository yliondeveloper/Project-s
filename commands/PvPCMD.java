package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.starmc.system.MessageAPI;

public class PvPCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (cmd.equalsIgnoreCase("pvp")) {
			if (!sender.hasPermission("potpvp.pvp")) {
				sender.sendMessage(MessageAPI.onCommandNoPerm);
				return true;
			} else {
				if (args.length == 0) {
					sender.sendMessage(MessageAPI.onCommandFailed("/pvp (off) & (on)"));
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) {
					Bukkit.getWorld("PotPvP").setPVP(true);
					Bukkit.broadcastMessage("§a§lDANO §fO dano do servidor foi habilitado.");
				}
				if (args[0].equalsIgnoreCase("off")) {
					Bukkit.getWorld("PotPvP").setPVP(false);
					Bukkit.broadcastMessage("§c§lDANO §fO dano do servidor foi desabilitado.");
				}
			}
		}
		return false;
	}
}