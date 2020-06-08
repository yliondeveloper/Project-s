package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopClanCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fApenas jogadores podem executar esse comando.");
			return true;
		}
		((Player) sender).chat("/clan top");
		return false;
	}

}
