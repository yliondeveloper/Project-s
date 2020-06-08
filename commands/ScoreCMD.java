package xyz.starmc.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import xyz.starmc.score.TeamScore;
import xyz.starmc.system.MessageAPI;

public class ScoreCMD implements CommandExecutor {

	public static List<String> Scoreboard = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		} else {
			if (((Player) sender).getScoreboard().getObjective("Scoreboard") != null
					& !Scoreboard.contains(sender.getName())) {
				sender.sendMessage("§c§lSCORE §fScoreBoard desabilitada com sucesso.");
				((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				Scoreboard.add(sender.getName());
			} else {
				((Player) sender).getScoreboard().getObjective("Scoreboard").unregister();
				Scoreboard.remove(sender.getName());
				sender.sendMessage("§a§lSCORE §fScoreBoard habilitada com sucesso.");

				TeamScore.addScore((Player) sender);
			}
		}
		return false;

	}

}
