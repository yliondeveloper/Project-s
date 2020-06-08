package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		sender.sendMessage(
				"§a\n§c§l            REGRAS \n \n§c1. §7Não utilize nenhum tipo Cheat ou Trapassa.\n§c2. §7Não faça ofensas a equipe ou a jogadores.\n§c3. §7Não abuse de bug's, reporte a equipe. \n§c4. §7Não divulgue outros servidores.\n§c5. §7Não tenha nenhum tipo de Trapassa em seu computador.\n§c6. §7Não ofenda o servidor.\n§c7. §7Não ameaçe o servidor, equipe e jogadores.\n§c8. §7Nao peça cargos.\n§c9. §7Não forje nenhum tipo de Trapassa.\n§c10. §7Evite spam.\n§cNão obedecer essas regras pode te levar a ter punições em nossa network.");
		return true;
	}

}