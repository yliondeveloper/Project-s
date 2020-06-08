package xyz.starmc.clan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.sql.SQLClan;

public class MessageClan implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("c")) {
			if (args.length == 0) {
				sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /c (mensagem)");
				return true;
			}
			String msg = "";
			for (String msg2 : args) {
				msg = ((msg)) + msg2 + " ";
			}
			if (!SQLClan.clan.get(sender.getName()).equalsIgnoreCase("Nenhum")) {
				for (Player jogadores : Bukkit.getOnlinePlayers()) {
					if (SQLClan.clan.get(jogadores.getName()).equalsIgnoreCase(SQLClan.clan.get(sender.getName()))) {
						jogadores.sendMessage("§e[CLAN] §f" + sender.getName() + " §7» §f" + msg);
					}
				}
			} else {
				sender.sendMessage("§c§lCLAN §fVocê não pertence a nenhum clan.");
			}
		}
		return false;
	}

}
