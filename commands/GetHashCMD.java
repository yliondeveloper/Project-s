package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.starmc.gui.DiamondRanked;
import xyz.starmc.gui.DiamondUnRanked;
import xyz.starmc.gui.IronRanked;
import xyz.starmc.gui.IronUnRanked;
import xyz.starmc.gui.NullRanked;
import xyz.starmc.gui.NullUnRanked;
import xyz.starmc.listener.Listener;
import xyz.starmc.system.MessageAPI;

public class GetHashCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (cmd.equalsIgnoreCase("hashmap")) {
			if (sender.hasPermission("potpvp.gethash")) {
				sender.sendMessage("§eHashMaps usadas no momento:");
				sender.sendMessage("§fIRON HASHMAP:§7 " + IronUnRanked.party.size());
				sender.sendMessage("§fDIAMOND HASHMAP:§7 " + DiamondUnRanked.party.size());
				sender.sendMessage("§fNULL HASMAP:§7 " + NullUnRanked.party.size());
				sender.sendMessage("§fSUMO HASHMAP:§7 " + Listener.Sumo.size());

				sender.sendMessage("§fIRON RANQUEADO HASHMAP:§7 " + IronRanked.party.size());
				sender.sendMessage("§fDIAMOND RANQUEADO HASHMAP:§7 " + DiamondRanked.party.size());
				sender.sendMessage("§fNULL RANQUEADO HASMAP:§7 " + NullRanked.party.size());

				sender.sendMessage("§fEM RUNNING:§7 " + Listener.RunningParty.size());
				sender.sendMessage("§fSPAWN:§7 " + Listener.Spawn.size());
				return true;
			} else {
				sender.sendMessage(MessageAPI.onCommandNoPerm);
				return true;
			}

		}
		return false;
	}

}
