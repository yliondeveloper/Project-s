package xyz.starmc.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class GamemodeCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		}
		if (!sender.hasPermission("potpvp.gm")) {
			sender.sendMessage(MessageAPI.onCommandNoPerm);
			return true;
		} else {
			if (args.length != 1) {
				sender.sendMessage(MessageAPI.onCommandFailed("/gm 1:2"));
				return true;
			} else {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("0")) {
						p.setGameMode(GameMode.ADVENTURE);
						sender.sendMessage("§3§lGM §fVocê alterou seu modo de jogo para Sobrevivência.");
						p.setAllowFlight(false);
						p.setFlying(false);
					}
					if (args[0].equalsIgnoreCase("1")) {
						p.setGameMode(GameMode.CREATIVE);
						sender.sendMessage("§3§lGM §fVocê alterou seu modo de jogo para Criativo.");
					}
				}
			}
		}
		return false;
	}
}