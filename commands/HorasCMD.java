package xyz.starmc.commands;

import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HorasCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fApenas jogadores podem executar esse comando.");
			return true;
		}
		sender.sendMessage("§a§l   HORÁRIO/DATA DO SERVIDOR");
		Calendar calendar = Calendar.getInstance();
		sender.sendMessage("\n§eData: §f" + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "\n§eHorário: §f" + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + "\n");
		return false;
	}

}
