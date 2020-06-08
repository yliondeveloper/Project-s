package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.fileapi.FileAPI;
import xyz.starmc.system.MessageAPI;

public class ReloadBotCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!sender.hasPermission("potpvp.bot")) {
			sender.sendMessage(MessageAPI.onCommandNoPerm);
			return true;
		}
		sender.sendMessage("§e§lBOT §fIniciando protocólos de leitura de arquivo...");
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (EnsinarCMD.pergunta.containsKey(todos)) {
				sender.sendMessage(
						"§e§lBOT §fAs configurações do bot foram recarregadas porfavor registre novamente sua pergunta.");
				EnsinarCMD.pergunta.remove(todos);
			}
			if (EnsinarCMD.registerbot.contains(todos)) {
				EnsinarCMD.registerbot.remove(todos);
			}
			FileAPI.botfunction.clear();
		}
		sender.sendMessage("§e§lBOT §fProtocólos iniciados com sucesso!");
		sender.sendMessage("§e§lBOT §fEfetuando recarregamento APIFile...");
		try {
			FileAPI.Converter();
		} catch (Exception e) {
			sender.sendMessage("§c§lERRO §fNão foi possível encontrar a pasta do Bot.");
		}
		sender.sendMessage("§e§lBOT §fAPIFile recarregada com sucesso.");
		sender.sendMessage("§e§lBOT §fAs configurações foram recarregadas com sucesso.");
		return true;
	}

}
