package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RanksCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		sender.sendMessage(
				"§e§lRANK's GLOBAIS:\n\n§f- §7Unranked (§f-§7) §f- §70 XP\n§f- §aPrimary (§a⚌§7) §f- §71000 XP\n§f- §7Silver (✳) §f- §72000 XP\n§f- §6Gold §7(§6✴§7) §f- §73500 XP\n§f- §bDiamond §7(§b❂§7) §f- §75000 XP\n§f- §2Emerald §7(§2✽§7) §f- §710000 XP\n§f- §cMaster §7(§c✾§7) §f- §715000 XP\n§f- §4Legendary §7(§4✪§7) §f- §720000 XP\n§f- §1Extreme §7(§1✯§7) §f- §750000 XP");
		return true;
	}

}
