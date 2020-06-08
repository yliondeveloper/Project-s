package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.permssions.PermEntry;
import xyz.starmc.sql.SQLPerms;

public class AlternateGroupCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(
					"§c§lERRO §fComando incorreto, utilize /groupset (nick) (grupo) ou /setgroup (grupo) (nick)");
			return true;
		}
		if (!(sender instanceof Player)) {
			SQLPerms.updateGroup(args[1], args[0]);
			sender.sendMessage("§a§lPLUGIN §fGrupo alterado com sucesso!");
			sender.sendMessage("§a§lPLUGIN §fInformações: Jogador: §a" + args[1] + " §fGrupo: §a" + args[0]);
			return true;
		}
		Player target = sender.getServer().getPlayer(args[0]);
		Player p = (Player) sender;
		if (!PermEntry.ExtraPermissions.containsKey(p)) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
			return true;
		}
		String permChangerGroup = PermEntry.getPermissions(p);
		if (args[1].equalsIgnoreCase("Membro")) {
			if (!permChangerGroup.contains("Membro")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}

		if (args[1].equalsIgnoreCase("Discloser")) {
			if (!permChangerGroup.contains("Discloser")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}
		if (args[1].equalsIgnoreCase("Spro")) {
			if (!permChangerGroup.contains("Spro")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}
		if (args[1].equalsIgnoreCase("Pro")) {
			if (!permChangerGroup.contains("Pro")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Youtuber")) {
			if (!permChangerGroup.contains("Youtuber")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Youtuber+")) {
			if (!permChangerGroup.contains("Youtuber+")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("YoutuberManager")) {
			if (!permChangerGroup.contains("YoutuberManager")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Ultra")) {
			if (!permChangerGroup.contains("Ultra")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Ultimate")) {
			if (!permChangerGroup.contains("Ultimate")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Crystal")) {
			if (!permChangerGroup.contains("Crystal")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}

		if (args[1].equalsIgnoreCase("evento")) {
			if (!permChangerGroup.contains("evento")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}

		if (args[1].equalsIgnoreCase("Helper") || args[1].equalsIgnoreCase("Ajudante")) {
			if (!permChangerGroup.contains("Helper")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}

			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Helper+") || (args[1].equalsIgnoreCase("Ajudante+"))) {
			if (!permChangerGroup.contains("Helper")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}

			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Trial")) {
			if (!permChangerGroup.contains("Trial")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Trialgc")) {
			if (!permChangerGroup.contains("ModGC")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Mod")) {
			if (!permChangerGroup.contains("Mod")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Modgc")) {
			if (!permChangerGroup.contains("ModGC")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Admin")) {
			if (!permChangerGroup.contains("Admin")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Admingc")) {
			if (!permChangerGroup.contains("AdminGC")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Gerente")) {
			if (!permChangerGroup.contains("Gerente")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Gerentegc")) {
			if (!permChangerGroup.contains("GerenteGC")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Coordenador")) {
			if (!permChangerGroup.contains("Coordenador")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Diretor")) {
			if (!permChangerGroup.contains("Diretor")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;

		}
		if (args[1].equalsIgnoreCase("Subdono")) {
			if (!permChangerGroup.contains("Diretor")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}
		if (args[1].equalsIgnoreCase("dono")) {
			if (!sender.getName().equalsIgnoreCase("yLionCodes")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}
		if (args[1].equalsIgnoreCase("ceo")) {
			if (!sender.getName().equalsIgnoreCase("yLionCodes")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!(target == null)) {
				target.kickPlayer("§a§lGROUP\n§fSeu grupo foi alterado para " + args[1].toUpperCase()
						+ "\n§fGrupo alterado por: §a" + sender.getName());
			}
			SQLPerms.updateGroup(args[0], args[1]);
			sender.sendMessage("§a§lGROUP §fGrupo alterado com sucesso.");
			return true;
		}
		sender.sendMessage("§c§lERRO §fO grupo §c" + args[1] + "§f não foi encontrado.");
		return true;
	}

}
