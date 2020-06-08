package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.http.HttpAPI;

public class DemoteCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.getName().equalsIgnoreCase("Sopa_Quente")) {
			if (args.length == 0) {
				sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /demote (nick)");
				return true;
			}
			((Player) sender).chat("/groupset " + args[0] + " membro");
			HttpAPI.tweet("[STAFF] O staffer " + args[0] + " foi removido da equipe.", sender);
		} else {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
			return true;
		}
		return false;

	}
}
