package xyz.starmc.commands;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.event.*;

import xyz.starmc.system.MessageAPI;

public final class BuildCMD implements Listener, CommandExecutor {
	public static List<Player> embuild = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		} else {
			Player p = (Player) sender;
			if (cmd.equalsIgnoreCase("build")) {
				if (!p.hasPermission("potpvp.build")) {
					sender.sendMessage(MessageAPI.onCommandNoPerm);
					return true;
				} else {
					if (args.length == 0) {
						if (!BuildCMD.embuild.contains(p)) {
							BuildCMD.embuild.add(p);
							p.sendMessage("§a§lBUILD §fVocê ativou o modo construção.");
						} else {
							BuildCMD.embuild.remove(p);
							p.sendMessage("§c§lBUILD §fVocê desativou o modo construção.");
						}
					} else {
						Player t = Bukkit.getPlayer(args[0]);
						if (t == null) {
							p.sendMessage("§c§lERRO §fJogador offline.");
							return true;
						}
						if (!BuildCMD.embuild.contains(t)) {
							BuildCMD.embuild.add(t);
							p.sendMessage("§a§lBUILD §fVocê ativou o modo construção para §a" + t.getName());
						} else {
							BuildCMD.embuild.remove(t);
							p.sendMessage("§c§lBUILD §fVocê desativou o modo construção para §c" + t.getName());
						}
					}
				}
			}
		}
		return false;
	}
}
