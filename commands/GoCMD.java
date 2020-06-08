package xyz.starmc.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.main.Main;

public class GoCMD implements CommandExecutor {

	public static boolean onTeleport = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("potpvp.go")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /go (servidor)");
			return true;

		} else {
			for (Player todos : Bukkit.getOnlinePlayers()) {
				if (args[0].equalsIgnoreCase(todos.getName())) {
					onTeleport = false;
				}
			}
			if (onTeleport == false) {
				onTeleport = true;
				sender.sendMessage("§c§lERRO §fVocê não pode teletransportar a um jogador.");
				return true;
			} else {
				sender.sendMessage("§a§lGO §fVocê está sendo teleportado para o servidor §a" + args[0]);
				sendPluginMessage((Player) sender, new String[] { "Connect", args[0] });
				return true;
			}
		}

	}

	public static void sendPlayer(Player player, final String server) {
		sendPluginMessage(player, new String[] { "Connect", server });
	}

	public static void sendPluginMessage(Player player, String... messages) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteArray);
		try {
			for (String message : messages) {
				output.writeUTF(message);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		player.sendPluginMessage(Main.getInstance(), "BungeeCord", byteArray.toByteArray());
	}
}
