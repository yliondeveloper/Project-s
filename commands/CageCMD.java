package xyz.starmc.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class CageCMD implements CommandExecutor {

	public static Map<Player, Player> cage = new HashMap<Player, Player>();

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
					sender.sendMessage(MessageAPI.onCommandFailed("/cage (nick)"));
					return true;
				}
				if (args.length == 1) {
					if (cage.containsKey(Bukkit.getPlayerExact(args[0]))) {
						sender.sendMessage("§c§lCAGE §fEsse jogador já está em uma cage.");
						return true;
					}
					if (Bukkit.getPlayerExact(args[0]).hasPermission("potpvp.admin")) {
						sender.sendMessage("§c§lCAGE §fVocê não pode puxar um membro da equipe para a cage.");
						return true;
					}
					cage.put((Bukkit.getPlayerExact(args[0])), (Player) sender);
					if (Bukkit.getPlayerExact(args[0]) != null) {
						Bukkit.getPlayerExact(args[0]).getLocation().add(0, 13, 0).getBlock().setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0]).getLocation().add(0, 11, 1).getBlock().setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0]).getLocation().add(1, 11, 0).getBlock().setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0]).getLocation().add(0, 11, -1).getBlock()
								.setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0]).getLocation().add(-1, 11, 0).getBlock()
								.setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0]).getLocation().add(0, 10, 0).getBlock().setType(Material.BEDROCK);
						Bukkit.getPlayerExact(args[0])
								.teleport(Bukkit.getPlayerExact(args[0]).getLocation().add(0, 11, -0.05));
						sender.sendMessage("§a§lCAGE §fVocê puxou o jogador §a "
								+ Bukkit.getPlayerExact(args[0]).getName() + " §fpara uma cage.");
						Bukkit.getPlayerExact(args[0]).sendMessage(
								"§e§lCAGE §fVocê foi puxado por um staffer em uma cage!\n§e§lCAGE §fSiga os passos do staffer e não deslogue do servidor.");
					} else {
						sender.sendMessage("§c§lERRO §fJogador offline. ");
					}
				}

			}
		}

		return false;
	}
}