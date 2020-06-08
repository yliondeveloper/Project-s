package xyz.starmc.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class TellCMD implements CommandExecutor {
	public static List<Player> telloff = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(MessageAPI.onCommandFailed("/tell off|on ou /tell (jogador) (mensagem)"));
			return true;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		}
		if (Bukkit.getPlayer(args[0]) == sender) {
			sender.sendMessage("§c§lERRO §fVocê não pode enviar mensagem para si mesmo.");
			return true;
		}
		if (args[0].toLowerCase().equalsIgnoreCase("on")) {
			telloff.remove(sender);
			sender.sendMessage(String.valueOf("§a§lTELL §fVocê habilitou seu tell."));
		} else if (args[0].toLowerCase().equalsIgnoreCase("off")) {
			telloff.add((Player) sender);
			sender.sendMessage(String.valueOf("§c§lTELL §fVocê desabilitou seu tell."));
		}
		if (args.length > 1) {
			if (Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage("§c§lERRO §fJogador offline.");
				return true;
			}
			if (telloff.contains(Bukkit.getPlayer(args[0]))) {
				sender.sendMessage(String.valueOf("§c§lTELL §fO tell desse jogador está desabilitado."));
				return true;
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]).append(" ");
			}
			String allArgs = sb.toString().trim();
			sender.sendMessage("§f(§7Você §f» " + Bukkit.getPlayer(args[0]).getDisplayName() + "§f)§7:§e " + allArgs);
			Bukkit.getPlayer(args[0])
					.sendMessage("§f(§7De §f» " + ((Player) sender).getDisplayName() + "§f)§7:§e " + allArgs);
		}
		return false;
	}
}
