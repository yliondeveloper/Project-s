package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PricesCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		sender.sendMessage(
				"\n�e�lTabela de VIP's e PRE�OS dispon�veis em nossa loja:\n \n\n�f- �c�lUNBAN �f- R$ 6,00\n�f- �e�lVIP �e�lULTRA �e(1 m�s) �f- R$ 15,00\n�f- �d�lVIP �d�lULTIMATE �d(1 m�s) �f- R$ 25,00\n�f- �5�lVIP �5�lCRYSTAL �5(1 m�s) �f- R$ 35,00\n \n�eSite para adquirir nossos produtos (�7loja.starpvp.com.br�e)");
		return true;
	}
}
