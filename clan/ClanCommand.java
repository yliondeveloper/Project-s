package xyz.starmc.clan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import xyz.starmc.main.Main;
import xyz.starmc.score.TeamScore;
import xyz.starmc.sql.SQLClan;
import xyz.starmc.sql.SQLXp;

public class ClanCommand implements CommandExecutor {

	public static List<String> cooldown = new ArrayList<String>();
	public static Map<Player, String> convite = new HashMap<Player, String>();

	public static String getInvalidChars(String string) {
		return Pattern.compile("[A-Za-z]").matcher(string).replaceAll("");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			sender.sendMessage(
					"§e§lSISTEMA DE CLANS\n§f/clan criar (nome) (tag) - §7Cria um clan.\n§f/clan deletar - §7Deleta seu clan.\n§f/clan info (clan) - §7Verifica as informações do seu clan. \n§f/clan convidar (jogador) -§7 Convida um jogador para seu clan.\n§f/clan expulsar (jogador) -§7 Expulsa um membro do clan\n§f/clan aceitar (clan) -§7 Aceita o convite de um clan.\n§f/clan sair -§7 Sai do clan atual.\n§f/clan promover (jogador) -§7 Promove um membro de seu clan.\n§f/clan rebaixar - §7Rebaixa o cargo de um superior de seu clan.\n§f/c (mensagem) -§7 Envia um mensagem no chat do clan.");
			return true;
		}
		if (args[0].equalsIgnoreCase("criar")) {
			if (args.length < 2 || args.length == 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan criar (nome) (tag)!");
				return true;
			}
			if (SQLXp.cachexp.get(p.getName()) < 2000) {
				sender.sendMessage("§c§lCLAN §fVocê precisa ter acima de 2.000XP para criar um clan!");
				return true;
			}
			if (!SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
				p.sendMessage("§c§lCLAN §fVocê já está em um clan!");
				return true;
			}
			String tag = args[2].replace("&", "§").replace("§k", "").replace("§l", "").replace("§m", "").replace("§o",
					"");
			String tag2 = ChatColor.stripColor(tag);
			if (tag2.length() > 3 || tag2.length() < 3) {
				p.sendMessage("§c§lCLAN §fA tag do clan deve ter conter apenas 3 caracteres.");
				return true;
			}
			if (!SQLClan.checkName(args[1])) {
				p.sendMessage(
						"§c§lCLAN §fNão é possível utilizar esse tipo de caractere para criar um clan e o limite do nome de clan são de 12 letras!");
				return true;
			}
			if (SQLClan.checkClan(args[1])) {
				sender.sendMessage("§c§lCLAN §fJá existe um clan com esse nome, tente outro!");
				return true;
			}
			if (SQLClan.checkTag(args[2])) {
				sender.sendMessage("§c§lCLAN §fJá existe um clan com essa tag, tente outra!");
				return true;
			}
			if (!getInvalidChars(args[2]).isEmpty()) {
				sender.sendMessage(
						"§c§lCLAN §fA tag na qual você tentou utilizar em seu clan possui caracteres inválidas, tente outra.");
				return true;
			}
			SQLClan.clan.replace(p.getName(), args[1]);
			SQLClan.cargo.replace(p.getName(), "Dono");
			if (!SQLClan.tag.containsKey(p.getName())) {
				SQLClan.tag.put(p.getName(), args[2]);
			}
			new BukkitRunnable() {
				public void run() {
					SQLClan.registerClan(args[1], args[2], SQLXp.getXp(p));
					SQLClan.updatePlayer(p);
				}
			}.runTaskAsynchronously(Main.getInstance());
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			((Player) sender).getScoreboard().getObjective("dummy").unregister();
			TeamScore.addScore(p);
			sender.sendMessage("§a§lCLAN §fSeu clan foi criado com sucesso!");
			Bukkit.broadcastMessage("§a§lCLAN §fO jogador §a" + p.getName() + "§f criou o clan §a" + args[1] + "§f!");
			return true;
		}
		if (args[0].equalsIgnoreCase("sair")) {
			if (args.length < 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan sair");
				return true;
			}
			if (SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")) {
				p.sendMessage("§c§lCLAN §fNão é possível sair do seu clan sendo um dono, apenas pode deleta-lo!");
				return true;
			}
			if (SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
				p.sendMessage("§c§lCLAN §fVocê não está em nenhum clan.");
				return true;
			}
			sender.sendMessage("§a§lCLAN §fVocê saiu do clan com sucesso!");
			for (Player jogadores : Bukkit.getOnlinePlayers()) {
				if (SQLClan.clan.get(jogadores.getName()).equalsIgnoreCase(SQLClan.clan.get(sender.getName()))) {
					jogadores.sendMessage("§c§lCLAN §fO jogador §c" + sender.getName() + " §fsaiu do clan!");
				}
			}
			if (SQLClan.tag.containsKey(p.getName())) {
				SQLClan.tag.remove(p.getName());
			}
			SQLClan.clan.replace(p.getName(), "Nenhum");
			SQLClan.cargo.replace(p.getName(), "Membro");
			new BukkitRunnable() {
				public void run() {
					SQLClan.updatePlayer((Player) sender);
					try {
						SQLClan.removeXp(SQLXp.getXp((Player) sender), SQLClan.clan.get(p.getName()));
					} catch (SQLException e) {
					}
				}
			}.runTaskAsynchronously(Main.getInstance());
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			((Player) sender).getScoreboard().getObjective("dummy").unregister();
			TeamScore.addScore(p);
			return true;

		}
		if (args[0].equalsIgnoreCase("deletar")) {
			if (args.length > 1) {
				p.sendMessage("§c§lERRO §fComando incorreto, utilize /clan deletar");
				return true;
			}
			if (!SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")) {
				p.sendMessage("§c§lCLAN §fApenas o dono pode deletar o clan!");
				return true;
			}
			sender.sendMessage("§a§lCLAN §fSeu clan foi deletado com sucesso!");

			String clanname = SQLClan.clan.get(p.getName());
			new BukkitRunnable() {
				public void run() {
					try {
						SQLClan.clan.replace(p.getName(), "Nenhum");
						SQLClan.cargo.replace(p.getName(), "Membro");
						if (SQLClan.tag.containsKey(p.getName())) {
							SQLClan.tag.remove(p.getName());
						}
						SQLClan.deleteClan(clanname);
						PreparedStatement ps = SQLClan.con.prepareStatement(
								"SELECT `NICK`, `ClanName`, `CargoName` FROM `Clan` WHERE `ClanName`='" + clanname
										+ "'");
						ResultSet rs = ps.executeQuery();

						while (rs.next()) {
							SQLClan.removeAllClans(rs.getString("NICK"));
						}
					} catch (SQLException localSQLException) {
						localSQLException.printStackTrace();
					}
					for (Player todos : Bukkit.getOnlinePlayers()) {
						if (SQLClan.clan.containsKey(todos.getName())) {
							if (SQLClan.clan.get(todos.getName()).equalsIgnoreCase(clanname)) {
								todos.sendMessage("§c§lCLAN §fO dono §c" + p.getName() + " §fdeletou o clan!");
								SQLClan.clan.replace(todos.getName(), "Nenhum");
								SQLClan.cargo.replace(todos.getName(), "Membro");

								if (SQLClan.tag.containsKey(todos.getName())) {
									SQLClan.tag.remove(todos.getName());
								}
							}
						}
					}
					SQLClan.updatePlayer(p);
				}
			}.runTaskAsynchronously(Main.instance);
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			((Player) sender).getScoreboard().getObjective("dummy").unregister();
			TeamScore.addScore(p);
			return true;

		}
		if (args[0].equalsIgnoreCase("convidar")) {
			if (args.length < 2 || args.length < 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan convidar (nick)");
				return true;
			}
			if (SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")
					|| SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Admin")
							| (SQLClan.cargo.get(p.getName()) == ("Dono"))
					|| (SQLClan.cargo.get(p.getName()) == ("Admin"))) {

				Player target2 = Bukkit.getPlayer(args[1]);
				if (target2 == null) {
					sender.sendMessage("§c§lCLAN §fNão é possivel convidar um jogador offline.");
					return true;
				}
				if (SQLXp.getXp(target2) < 0) {
					sender.sendMessage("§c§lCLAN §fNão é possivel adicionar um membro ao seu clan com XP negativo.");
					return true;
				}
				if (!SQLClan.clan.get(target2.getName()).equalsIgnoreCase("Nenhum")) {
					sender.sendMessage("§c§lCLAN §fEsse jogador já pertence a outro clan.");
					return true;
				}
				if (SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
					p.sendMessage("§c§lCLAN §fVocê não está em nenhum clan.");
					return true;
				}
				convite.put(target2, SQLClan.clan.get(p.getName()));
				target2.sendMessage("§a§lCLAN §fVocê recebeu um convite do clan §a" + convite.get(target2)
						+ " §fuse /clan aceitar " + SQLClan.clan.get(p.getName()) + " para entrar!");
				sender.sendMessage("§a§lCLAN §fVocê convidou o jogador §a" + target2.getName() + " §fpara seu clan!");
			} else {
				sender.sendMessage("§c§lCLAN §fVocê não possui o cargo para recrutar membro para este clan.");
				return true;
			}

		}
		if (args[0].equalsIgnoreCase("aceitar")) {
			if (args.length < 2 || args.length < 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan aceitar (clan)");
				return true;
			}
			if (!convite.containsKey(sender)) {
				sender.sendMessage("§c§lCLAN §fVocê não possui nenhum convite.");
				return true;
			}
			if (!args[1].equalsIgnoreCase(convite.get(sender))) {
				sender.sendMessage("§c§lCLAN §fVocê não possui nenhum convite para esse clan.");
				return true;
			}
			if (SQLClan.tag.containsKey(p.getName())) {
				SQLClan.tag.remove(p.getName());
			}
			if (!SQLClan.tag.containsKey(p.getName())) {
				SQLClan.tag.put(p.getName(), SQLClan.getTagConnection(convite.get(sender)));
			}
			SQLClan.clan.replace(p.getName(), convite.get(sender));
			SQLClan.cargo.replace(p.getName(), "Membro");
			for (Player jogadores : Bukkit.getOnlinePlayers()) {
				if (SQLClan.clan.get(jogadores.getName()).equalsIgnoreCase(convite.get(sender))) {
					jogadores.sendMessage("§a§lCLAN §fO jogador §a" + sender.getName() + " §fagora faz parte do clan!");
				}
			}
			convite.remove(sender);
			sender.sendMessage(
					"§a§lCLAN §fVocê entrou no clan §a" + SQLClan.clan.get(sender.getName()) + " §fcom sucesso!");
			new BukkitRunnable() {
				public void run() {
					SQLClan.updatePlayer(p);
				}
			}.runTaskAsynchronously(Main.getPlugin());
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			((Player) sender).getScoreboard().getObjective("dummy").unregister();
			TeamScore.addScore(p);
			return true;
		}
		if (args[0].equalsIgnoreCase("promover")) {
			if (args.length < 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan promover (nick)");
				return true;
			}
			if (SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
				p.sendMessage("§c§lCLAN §fVocê não está em nenhum clan.");
				return true;
			}
			if (!SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")) {
				sender.sendMessage("§c§lCLAN §fVocê não é um dono para promover um membro do clan.");
				return true;
			}
			Player target3 = Bukkit.getPlayer(args[1]);
			if (target3 == null) {
				sender.sendMessage("§c§lCLAN §fNão é possível alterar o cargo de um jogador offline em seu clan.");
				return true;
			}
			if (!SQLClan.clan.get(target3.getName()).equalsIgnoreCase(SQLClan.clan.get(p.getName()))) {
				sender.sendMessage("§c§lCLAN §fEsse jogador não pertence ao seu clan.");
				return true;
			}
			sender.sendMessage("§a§lCLAN §fVocê promoveu o membro §a" + target3.getName()
					+ " §fpara o cargo administrador em seu clan!");
			target3.sendMessage("§a§lCLAN §fVocê foi promovido para o cargo administrador no clan!\n");
			SQLClan.cargo.replace(target3.getName(), "Admin");
			new BukkitRunnable() {
				public void run() {
					SQLClan.updatePlayer(target3);
				}
			}.runTaskAsynchronously(Main.getInstance());
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			((Player) sender).getScoreboard().getObjective("dummy").unregister();
			TeamScore.addScore(p);
			return true;
		}
		if (args[0].equalsIgnoreCase("rebaixar")) {
			if (args.length < 1 || args.length > 2) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan rebaixar (nick)");
				return true;
			}
			if (SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
				p.sendMessage("§c§lCLAN §fVocê não está em nenhum clan.");
				return true;
			}
			if (!SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")) {
				sender.sendMessage("§c§lCLAN §fVocê não é um dono para rebaixar um membro do clan.");
				return true;
			}
			Player target3 = Bukkit.getPlayer(args[1]);
			if (target3 == null) {
				sender.sendMessage("§c§lCLAN §fNão é possível alterar o cargo de um jogador offline em seu clan.");
				return true;
			}
			if (target3.getName().equalsIgnoreCase(sender.getName())) {
				sender.sendMessage("§c§lCLAN §fVocê não pode rebaixar a si mesmo.");
				return true;
			}
			if (!SQLClan.clan.get(target3.getName()).equalsIgnoreCase(SQLClan.clan.get(p.getName()))) {
				sender.sendMessage("§c§lCLAN §fEsse jogador não pertence ao seu clan.");
				return true;
			}
			if (!SQLClan.cargo.get(target3.getName()).equalsIgnoreCase("Admin")) {
				sender.sendMessage("§c§lCLAN §fVocê não pode rebaixar um jogador que já possui o cargo minimo.");
				return true;
			}
			sender.sendMessage("§a§lCLAN §fVocê rebaixou o administrador §a" + target3.getName()
					+ " §fpara o cargo administrador em seu clan!");
			target3.sendMessage("§c§lCLAN §fO dono do clan o rebaixou para o cargo membro!");
			SQLClan.cargo.replace(target3.getName(), "Membro");
			SQLClan.updatePlayer(target3);
			return true;
		}
		if (args[0].equalsIgnoreCase("info")) {
			if (args.length == 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan info (clan)");
				return true;
			}
			if (cooldown.contains(p.getName())) {
				p.sendMessage("§c§lERRO §fAguarde para utilizar este comando novamente.");
				return true;
			}
			cooldown.add(p.getName());
			new BukkitRunnable() {
				public void run() {
					cooldown.remove(p.getName());
				}
			}.runTaskLater(Main.getPlugin(), 20 * 10);
			new BukkitRunnable() {
				public void run() {
					if (!SQLClan.getTagConnection(args[1]).equalsIgnoreCase("Nenhuma")) {
						p.sendMessage("\n§7Informações do clan: §e" + args[1] + " (" + SQLClan.getTagConnection(args[1])
								+ ") " + "\n§7Membros:");
						p.sendMessage("");
						int totalx = 0;
						int membro = 0;
						int admin = 0;
						try {
							PreparedStatement ps = SQLClan.con.prepareStatement(
									"SELECT `NICK`, `ClanName`, `CargoName` FROM `Clan` WHERE `ClanName`='" + args[1]
											+ "'");
							ResultSet rs = ps.executeQuery();

							while (rs.next()) {
								String tempcachename = rs.getString("NICK");
								String tempcargo = rs.getString("CargoName");
								int xp = Integer.valueOf(SQLXp.getXpConnection(tempcachename));
								if (tempcargo.equalsIgnoreCase("Dono")) {
									p.sendMessage("§7" + tempcachename + ": §fXP: §a" + xp + "§7: §fCargo: §4Dono");
								} else {
									if (tempcargo.equalsIgnoreCase("Admin")) {
										p.sendMessage("§7" + tempcachename + ": §fXP: §a" + xp
												+ "§7: §fCargo: §cAdministrador");
										admin = admin + 1;
									} else {
										p.sendMessage(
												"§7" + tempcachename + ": §fXP: §a" + xp + "§7: §fCargo: §7Membro");
										membro = membro + 1;
									}
								}
								totalx = totalx + xp;
							}
						} catch (SQLException localSQLException) {
							localSQLException.printStackTrace();

						}
						p.sendMessage("");
						p.sendMessage("§fAdministradores (§7" + admin + "§f)");
						p.sendMessage("§fMembros (§7" + membro + "§f)");
						p.sendMessage("");
						p.sendMessage("§fXP total: §e" + totalx);
					} else if (!SQLClan.getClanTagConnection(args[1]).equalsIgnoreCase("Nenhuma")) {
						p.sendMessage("\n§7Informações do clan: §e" + SQLClan.getClanTagConnection(args[1]) + " ("
								+ args[1] + ") " + "\n§7Membros:");
						p.sendMessage("");
						int totalx = 0;
						int membro = 0;
						int admin = 0;
						try {
							PreparedStatement ps = SQLClan.con.prepareStatement(
									"SELECT `NICK`, `ClanName`, `CargoName` FROM `Clan` WHERE `ClanName`='"
											+ SQLClan.getClanTagConnection(args[1]) + "'");
							ResultSet rs = ps.executeQuery();

							while (rs.next()) {
								String tempcachename = rs.getString("NICK");
								String tempcargo = rs.getString("CargoName");
								int xp = Integer.valueOf(SQLXp.getXpConnection(tempcachename));
								if (tempcargo.equalsIgnoreCase("Dono")) {
									p.sendMessage("§7" + tempcachename + ": §fXP: §a" + xp + "§7: §fCargo: §4Dono");
								} else {
									if (tempcargo.equalsIgnoreCase("Admin")) {
										p.sendMessage("§7" + tempcachename + ": §fXP: §a" + xp
												+ "§7: §fCargo: §cAdministrador");
										admin = admin + 1;
									} else {
										p.sendMessage(
												"§7" + tempcachename + ": §fXP: §a" + xp + "§7: §fCargo: §7Membro");
										membro = membro + 1;
									}
								}
								totalx = totalx + xp;
							}
						} catch (SQLException localSQLException) {
							localSQLException.printStackTrace();

						}
						p.sendMessage("");
						p.sendMessage("§fAdministradores (§7" + admin + "§f)");
						p.sendMessage("§fMembros (§7" + membro + "§f)");
						p.sendMessage("");
						p.sendMessage("§fXP total: §e" + totalx);
					} else {
						sender.sendMessage("§c§lERRO §fEsse clan não existe.");
					}

				}
			}.runTaskAsynchronously(Main.getPlugin());

			return true;

		}


		if (args[0].equalsIgnoreCase("top")) {
			if (args.length == 0) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan top");
				return true;
			}
			sender.sendMessage("");
			sender.sendMessage("      §a§lTOP CLAN");
			sender.sendMessage("");
			for (String clans : Main.topclan) {
				sender.sendMessage("§c" + clans + "");
			}
			sender.sendMessage("");

		}

		if (args[0].equalsIgnoreCase("expulsar")) {
			if (args.length < 1) {
				p.sendMessage("§e§lCLAN §fUtilize §e/clan expulsar (nick)");
				return true;
			}
			if (SQLClan.clan.get(p.getName()).equalsIgnoreCase("Nenhum")) {
				p.sendMessage("§c§lCLAN §fVocê não está em nenhum clan.");
				return true;
			}
			if (!SQLClan.cargo.get(p.getName()).equalsIgnoreCase("Dono")) {
				sender.sendMessage("§c§lCLAN §fVocê não é um dono para expulsar um membro do clan.");
				return true;
			}
			Player target3 = Bukkit.getPlayer(args[1]);
			if (target3 == null) {
				sender.sendMessage("§c§lCLAN §fNão é possível expulsar um jogador offline em seu clan.");
				return true;
			}
			if (!SQLClan.clan.get(target3.getName()).equalsIgnoreCase(SQLClan.clan.get(p.getName()))) {
				sender.sendMessage("§c§lCLAN §fEsse jogador não pertence ao seu clan.");
				return true;
			}
			String clan = SQLClan.getClan(p);
			sender.sendMessage("§a§lCLAN §fVocê expulsou o jogador §a" + target3.getName() + " §f§f de seu clan.");
			target3.sendMessage("§c§lCLAN §fVocê foi expulso do clan.");
			SQLClan.cargo.replace(target3.getName(), "Membro");
			SQLClan.clan.replace(target3.getName(), "Nenhum");
			if (SQLClan.tag.containsKey(target3.getName())) {
				SQLClan.tag.remove(target3.getName());
			}
			for (Player jogadores : Bukkit.getOnlinePlayers()) {
				if (SQLClan.clan.get(jogadores.getName()).equalsIgnoreCase(SQLClan.clan.get(sender.getName()))) {
					jogadores.sendMessage("§c§lCLAN §fO jogador §c" + target3.getName() + " §ffoi expulso do clan.");
				}
			}
			new BukkitRunnable() {
				public void run() {
					SQLClan.updatePlayer(target3);
					try {
						SQLClan.removeXp(SQLXp.getXp(target3), clan);
					} catch (SQLException e) {
					}
				}
			}.runTaskAsynchronously(Main.getInstance());
			target3.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			target3.getScoreboard().getObjective("dummy").unregister();
			new BukkitRunnable() {
				public void run() {
					TeamScore.addScore(p);
				}
			}.runTaskLater(Main.getInstance(), 20 * 1);

			if (args[0].equalsIgnoreCase("erro")) {
				if (args.length < 1) {
					p.sendMessage("§e§lCLAN §fUtilize §e/clan erro");
					return true;
				}
				return true;
			}
		}
		return false;
	}
}
