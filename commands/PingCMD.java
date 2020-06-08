package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import xyz.starmc.system.LagAPI;

public class PingCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fApenas jogadores podem executar esse comando.");
			return true;
		} else {
			if (args.length == 0) {
				String classific = getClassific((Player) sender);
				if (classific.equalsIgnoreCase("§4HORRÍVEL") || classific.equalsIgnoreCase("§4PÉSSIMO")
						|| classific.equalsIgnoreCase("§eMÉDIO")) {
					sender.sendMessage("§a§lPING §fSeu ping atual é de §a" + CachePing((Player) sender)
							+ "§ams \n§a§lPING §fClassificação: §f(" + getClassific((Player) sender) + "§f)");
					if (LagAPI.Tps > 19.25) {
						sender.sendMessage(
								"§a§lPING §fO servidor está totalmente estável, o problema atual é em sua internet, tente reiniciar seu roteador para melhorar sua estabilidade na rede.");
					}
				} else {
					sender.sendMessage("§a§lPING §fSeu ping atual é de §a" + CachePing((Player) sender)
							+ "§ams \n§a§lPING §fClassificação: §f(" + getClassific((Player) sender) + "§f)");
				}
				return true;
			} else {
				if (args.length > 1) {
					sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /ping (jogador) ou /ping");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if (t == null) {
						sender.sendMessage("§c§lERRO §fJogador offline.");
						return true;
					}
					sender.sendMessage("§a§lPING §fO ping do jogador §a" + t.getName() + "§f é de §a" + CachePing(t)
							+ "§ams \n§a§lPING §fClassificação: §f(" + getClassific(t) + "§f)");
				}
			}
		}
		return false;

	}

	public static int CachePing(Player p) {
		int Ping = ((CraftPlayer) p).getHandle().ping;
		if (Ping >= 100) {
			return Ping - 50;
		} else {
			if (Ping >= 90) {
				return Ping - 30;
			} else {
				if (Ping >= 70) {
					return Ping - 29;
				} else {
					if (Ping >= 60) {
						return Ping - 40;
					} else {
						if (Ping >= 50) {
							return Ping - 35;
						} else {
							if (Ping >= 40) {
								return Ping - 22;
							} else {
								if (Ping >= 30) {
									return Ping - 18;
								} else {
									if (Ping >= 14) {
										return Ping - 10;
									} else {
										if (Ping <= 13) {
											return Ping;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return Ping;
	}

	public static String getClassific(Player p) {
		if (CachePing(p) <= 60) {
			return "§aÓTIMO";
		}
		if (CachePing(p) <= 90) {
			return "§2BOM";
		}
		if (CachePing(p) <= 150) {
			return "§eMÉDIO";
		}
		if (CachePing(p) <= 220) {
			return "§4PÉSSIMO";
		}
		if (CachePing(p) <= 500) {
			return "§4HORRÍVEL";
		}
		return "§4HORRÍVEL";
	}
}