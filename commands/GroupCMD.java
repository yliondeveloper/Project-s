package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.permssions.PermEntry;
import xyz.starmc.sql.SQLPerms;

public class GroupCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fApenas jogadores podem executar esse comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			sender.sendMessage("§c§lERRO §fComando incorreto, utilize /group (nick) ou /checkgroup (nick)");
			return true;
		}
		if (!PermEntry.ExtraPermissions.containsKey(p)) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
			return true;
		}
		if (PermEntry.getPermissions(p).contains("checkgroup")) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage("\n §e§lINFORMAÇÕES DE: §f" + args[0]);
				sender.sendMessage("");
				sender.sendMessage("§eUUID:§7 " + SQLPerms.getUUID(args[0]));
				sender.sendMessage("§eGrupo:§7 " + SQLPerms.getGroupConnection(args[0]));
				sender.sendMessage("§eOP: §7Não idetentificado.");
				sender.sendMessage("");
				return true;
			} else {
				sender.sendMessage("\n §e§lINFORMAÇÕES DE: §f" + args[0]);
				sender.sendMessage("");
				sender.sendMessage("§eUUID:§7 " + target.getUniqueId());
				sender.sendMessage("§eGrupo:§7 " + PermEntry.getGroup(target));
				if (target.isOp()) {
					sender.sendMessage("§eOP: §aAtivo");
				} else {
					sender.sendMessage("§eOP: §cDesativado");
				}
				sender.sendMessage("");
			}
		} else {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
		}
		return false;
	}

}
