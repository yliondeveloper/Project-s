package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;
import xyz.starmc.system.MessageAPI;

public class StopCMD implements CommandExecutor {

	public int numbercount = 0;

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!sender.hasPermission("simulator.stop")) {
			sender.sendMessage(MessageAPI.onCommandNoPerm);
			return true;
		}
		numbercount = 11;
		new BukkitRunnable() {
			public void run() {
				numbercount = numbercount - 1;
				if (numbercount == 10) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 10 segundos...");
				}
				if (numbercount == 5) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 5 segundos...");
				}
				if (numbercount == 4) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 4 segundos...");
				}
				if (numbercount == 3) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 3 segundos...");
				}
				if (numbercount == 2) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 2 segundos...");
					for(Player todos : Bukkit.getOnlinePlayers()) {
						GoCMD.sendPlayer(todos, "lobby");
					}
				}
				if (numbercount == 1) {
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando o servidor em 1 segundos...");
					Bukkit.broadcastMessage("§c§lRESTART §fReiniciando...");
					Bukkit.shutdown();
				}
			}
		}.runTaskTimer(Main.getPlugin(), 20 * 1, 20 * 1);
		return false;
	}

}