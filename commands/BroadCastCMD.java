package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadCastCMD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("potpvp.broadcast")) {
			if (args.length == 0) {
				sender.sendMessage("§c§lERRO §fComando incorreto, utilize:§7 /bc (mensagem)");
				return true;
			}
			String msg = "";
			for (String msg2 : args) {
				msg = ((msg)) + msg2 + " ";
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§e§lStar§f§lMC §7» " + ChatColor.RESET + msg.replace("&", "§"));
			Bukkit.broadcastMessage("");
			return true;
		} else {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
		}
		return false;
	}
}
