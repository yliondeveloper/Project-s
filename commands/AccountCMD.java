package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.score.TeamScore;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLClan;
import xyz.starmc.sql.SQLXp;

public class AccountCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 2) {
			sender.sendMessage("§c§lERRO §fComando incorreto, utilize /account ou /account (jogador)!");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§e§lACCOUNT");
			sender.sendMessage("");
			sender.sendMessage("§fSuas informações:");
			sender.sendMessage("§fGrupo: " + TeamScore.getGroup((Player) sender));
			sender.sendMessage("§fClan: §7" + SQLClan.getClan((Player) sender));
			sender.sendMessage("§f");
			sender.sendMessage("§fVitórias: §e" + SQLCache.getVictory((Player) sender));
			sender.sendMessage("§fDerrotas: §e" + SQLCache.getDefeats((Player) sender));
			sender.sendMessage("§f");
			sender.sendMessage("§fRank: " + SQLXp.getRankComplete(SQLXp.getXp((Player) sender)));
			sender.sendMessage("§fXP: §a" + SQLXp.getXp((Player) sender));
			sender.sendMessage("§f");
			return true;
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(
						"§c§lACCOUNT §fNão é possível ver as informações desse jogador pois ele está offline.");
				return true;
			}
			sender.sendMessage("§e§lACCOUNT");
			sender.sendMessage("");
			sender.sendMessage("§fInformações de: §e" + target.getName());
			sender.sendMessage("§fGrupo: " + TeamScore.getGroup(target));
			sender.sendMessage("§fClan: §7" + SQLClan.getClan(target));
			sender.sendMessage("§f");
			sender.sendMessage("§fVitórias: §e" + SQLCache.getVictory(target));
			sender.sendMessage("§fDerrotas: §e" + SQLCache.getDefeats(target));
			sender.sendMessage("§f");
			sender.sendMessage("§fRank: " + SQLXp.getRankComplete(SQLXp.getXp(target)));
			sender.sendMessage("§fXP: §a" + SQLXp.getXp(target));
			sender.sendMessage("§f");
			return true;
		}
	}

}
