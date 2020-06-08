package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class UncageCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		} else {
			if (!sender.hasPermission("potpvp.cage")) {
				sender.sendMessage(MessageAPI.onCommandNoPerm);
				return true;
			} else {
				if (args.length == 0) {
					sender.sendMessage(MessageAPI.onCommandFailed("/uncage (nick)"));
					return true;
				}
				if (args.length == 1) {
					if (!CageCMD.cage.containsKey(Bukkit.getPlayerExact(args[0]))) {
						sender.sendMessage("§c§lCAGE §fEsse jogador não está em uma cage.");
						return true;
					}
					if (Bukkit.getPlayerExact(args[0]) != null) {
					CageCMD.cage.remove(Bukkit.getPlayerExact(args[0]));
					Bukkit.getPlayerExact(args[0]).chat("/spawn");
					sender.sendMessage("§a§lCAGE §fVocê liberou o jogador §a "+ Bukkit.getPlayerExact(args[0]).getName() + " §fda cage.");		
					sender.sendMessage("§a§lCAGE §fVocê liberado da cage por um staffer, bom jogo!");
					} else {
						sender.sendMessage("§c§lERRO §fJogador offline. ");
					}
				}

			}
		}
		return false;
	}

}
