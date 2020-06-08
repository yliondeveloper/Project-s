package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class TpallCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		} else {
			if (!sender.hasPermission("potpvp.tpall")) {
				sender.sendMessage(MessageAPI.onCommandNoPerm);
				return true;
			} else {
				Player s = (Player) sender;
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.teleport(s.getLocation());
				}
				Bukkit.getServer().broadcastMessage(
						"§3§LTPALL §fTodos os teleportados foram puxados por §3" + sender.getName() + "§f!");
			}
		}
		return false;
	}
}