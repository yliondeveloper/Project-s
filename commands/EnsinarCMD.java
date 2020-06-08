package xyz.starmc.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.fileapi.FileWriter;

public class EnsinarCMD implements CommandExecutor {

	public static List<Player> registerbot = new ArrayList<Player>();
	public static Map<Player, String> pergunta = new HashMap<Player, String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§c§lERRO §fComando incorreto, utilize: /ensinar (pergunta)");
			return true;
		} else {
			if (!sender.hasPermission("potpvp.bot")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar esse comando.");
				return true;
			}
			if (!registerbot.contains((Player) sender)) {
				registerbot.add((Player) sender);
				String msg = "";
				for (String msg2 : args) {
					msg = ((msg)) + msg2 + " ";
				}
				pergunta.put((Player) sender, msg);
				sender.sendMessage(
						"§e§lBOT §fVocê criou uma pergunta, agora adicione a reposta utilizando /ensinar (resposta)");
			} else {
				String msg = "";
				for (String msg2 : args) {
					msg = ((msg)) + msg2 + " ";
				}
				try {
					FileWriter.FilePacket(msg, pergunta.get((Player) sender));
				} catch (IOException e) {
					e.printStackTrace();
					sender.sendMessage("§c§lERRO §fOcorreu um erro ao salvar esta resposta.");
					return true;
				}
				sender.sendMessage("§e§lBOT §fA resposta para esta pergunta foi criada!");
				pergunta.remove((Player) sender);
				registerbot.remove((Player) sender);
			}

		}
		return false;
	}

}
