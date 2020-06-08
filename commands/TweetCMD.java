package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.http.HttpAPI;
import xyz.starmc.permssions.PermEntry;

public class TweetCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!PermEntry.ExtraPermissions.containsKey((Player) sender)) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
			return true;
		}
		if (PermEntry.getPermissions((Player) sender).contains("tweet")) {
			if (args.length == 0) {
				sender.sendMessage("§c§lERRO §fComando incorreto, utilize:§7 /tweet (tweet)");
				return true;
			}
			String msg = "";
			for (String msg2 : args) {
				msg = ((msg)) + msg2 + " ";
			}
			HttpAPI.tweet(msg, sender);
		} else {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
		}
		return false;
	}
}
