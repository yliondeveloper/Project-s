package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fApenas jogadores podem executar esse comando.");
			return true;
		}
		sender.sendMessage(
				"\n §e§lINFO: \n \n§b§lTWITTER » §f@StarMC_ \n§3§lDISCORD » §fbit.ly/star_dc \n§6§lLOJA » §floja.starpvp.com.br \n \n");
		return false;
	}

}
