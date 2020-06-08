package xyz.starmc.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import xyz.starmc.system.MessageAPI;

public class TpCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(MessageAPI.onCommandConsoleBlock);
				return true;
			} else {
				if (sender.hasPermission("potpvp.tp")) {
					if (args.length == 0) {
						sender.sendMessage(
								"§c§lERRO §fComando incorreto, utilize: /tp (jogador) ou /tp (jogador) (alvo)");
					} else if (args.length == 1) {
						if (sender.getServer().getPlayer(args[0]) != null) {
							((Entity) sender).teleport(sender.getServer().getPlayer(args[0]).getLocation());
							sender.sendMessage("§a§lTP §fTeleportado para o jogador(a) §a"
									+ sender.getServer().getPlayer(args[0]).getName() + "§f com sucesso!");
						} else {
							sender.sendMessage("§c§lERRO §fJogador offline.");
						}
					} else if (args.length == 2) {
						if (sender.getServer().getPlayer(args[0]) != null) {
							if (sender.getServer().getPlayer(args[1]) != null) {
								sender.getServer().getPlayer(args[0])
										.teleport(sender.getServer().getPlayer(args[1]).getLocation());
							} else {
								sender.sendMessage("§c§lERRO §fJogador offline.");
							}
						} else
							sender.sendMessage("§c§lERRO §fJogador offline.");
					} else if (args.length == 3) {
						double x2 = args[0].startsWith("~")
								? ((Entity) sender).getLocation().getX() + Integer.parseInt(args[0].substring(1))
								: Integer.parseInt(args[0]);
						double y2 = args[1].startsWith("~")
								? ((Entity) sender).getLocation().getY() + Integer.parseInt(args[1].substring(1))
								: Integer.parseInt(args[1]);
						double z2 = args[2].startsWith("~")
								? ((Entity) sender).getLocation().getZ() + Integer.parseInt(args[2].substring(1))
								: Integer.parseInt(args[2]);
						if ((x2 > 30000000.0D) || (y2 > 30000000.0D) || (z2 > 30000000.0D) || (x2 > -30000000.0D)
								|| (y2 > -30000000.0D) || (z2 > -30000000.0D)) {
							sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /tp (jogador) (alvo)");
							return true;
						}
						((Entity) sender).teleport(new Location(((Location) sender).getWorld(), x2, y2, z2,
								((Entity) sender).getLocation().getYaw(), ((Entity) sender).getLocation().getPitch()));
					}
				} else if (args.length == 4) {
					double x2 = args[1].startsWith("~")
							? sender.getServer().getPlayer(args[0]).getLocation().getX()
									+ Integer.parseInt(args[0].substring(1))
							: Integer.parseInt(args[1]);
					double y2 = args[2].startsWith("~")
							? sender.getServer().getPlayer(args[0]).getLocation().getY()
									+ Integer.parseInt(args[1].substring(1))
							: Integer.parseInt(args[2]);
					double z2 = args[3].startsWith("~")
							? sender.getServer().getPlayer(args[0]).getLocation().getZ()
									+ Integer.parseInt(args[2].substring(1))
							: Integer.parseInt(args[3]);
					if ((x2 > 30000000.0D) || (y2 > 30000000.0D) || (z2 > 30000000.0D) || (x2 > -30000000.0D)
							|| (y2 > -30000000.0D) || (z2 > -30000000.0D)
							|| (sender.getServer().getPlayer(args[0]) == null)) {
						sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /tp (jogador) (alvo)");
						return true;
					}
					sender.getServer().getPlayer(args[0])
							.teleport(new Location(((Location) sender).getWorld(), x2, y2, z2,
									sender.getServer().getPlayer(args[0]).getLocation().getYaw(),
									sender.getServer().getPlayer(args[0]).getLocation().getPitch()));
				}
			}
			return false;

	}
}