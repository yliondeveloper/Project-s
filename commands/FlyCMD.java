package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class FlyCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		}
		if (args.length > 1) {
			sender.sendMessage(MessageAPI.onCommandFailed("/fly ou /fly (jogador)"));
			return true;
		} else {
			if (!p.hasPermission("potpvp.fly")) {
				p.sendMessage(MessageAPI.onCommandNoPerm);
				return true;
			} else {
				if (args.length == 0) {
					if (!p.getAllowFlight()) {
						p.setAllowFlight(true);
						p.setFlying(true);
						sender.sendMessage("§a§lFLY §fVocê habilitou o modo voar.");
					} else {
						p.setAllowFlight(false);
						p.setFlying(false);
						sender.sendMessage("§c§lFLY §fVocê desabilitou o modo voar.");
					}
				} else {
					Player t = sender.getServer().getPlayer(args[0]);
					if (t == null) {
						sender.sendMessage("§c§lERRO §fJogador offline.");
						return true;
					} else {
						if (!t.getAllowFlight()) {
							t.setAllowFlight(true);
							t.setFlying(true);
							t.sendMessage(
									"§a§lFLY §fO staffer §a" + p.getDisplayName() + " §fhabilitou seu modo voar.");
							p.sendMessage("§a§lFLY §fVocê habilitou o modo voar de §a" + t.getDisplayName());
						} else {
							t.setAllowFlight(false);
							t.setAllowFlight(false);
							t.setFlying(false);
							t.sendMessage(
									"§c§lFLY §fO staffer §c" + p.getDisplayName() + " §cdesabilitou seu modo voar.");
							sender.sendMessage("§c§lFLY §fVocê desabilitou o modo voar de §c" + t.getDisplayName());
						}
					}
				}
			}

		}

		return false;
	}
}