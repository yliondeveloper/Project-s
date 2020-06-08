package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.starmc.system.MessageAPI;

public class ChatCMD implements Listener, CommandExecutor {
	public static boolean chat = true;

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!sender.hasPermission("potpvp.chat")) {
			sender.sendMessage(MessageAPI.onCommandNoPerm);
			return true;
		} else {
			if (args.length == 0) {
				sender.sendMessage(MessageAPI.onCommandFailed("/chat (off) & (on) ou /chat ativar & desativar"));
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("dasativar")) {
				chat = false;
				for (Player todos : Bukkit.getOnlinePlayers()) {
					if (todos.hasPermission("potpvp.staff")) {
						todos.sendMessage("§c§lSTAFF §f" + sender.getName() + "§f desabilitou o chat.");
					} else {
						todos.sendMessage("§c§lCHAT §fO chat foi desabilitado.");
					}
				}
			} else if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("ativar")) {
				chat = true;
				for (Player todos : Bukkit.getOnlinePlayers()) {
					if (todos.hasPermission("potpvp.staff")) {
						todos.sendMessage("§a§lSTAFF §f" + sender.getName() + "§f habilitou o chat.");
					} else {
						todos.sendMessage("§a§lCHAT §fO chat foi habilitado.");
					}
				}
			}
		}
		return true;
	}
}