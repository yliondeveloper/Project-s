package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ApplyCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		Player p = (Player) sender;
		TextComponent trial = new TextComponent("  TRIAL: ");
		trial.setBold(true);
		trial.setColor(ChatColor.DARK_PURPLE);

		TextComponent trialp = new TextComponent("(");
		trialp.setBold(false);
		trialp.setColor(ChatColor.GRAY);

		TextComponent trialp2 = new TextComponent(")");
		trialp2.setBold(false);
		trialp2.setColor(ChatColor.GRAY);

		TextComponent trialclick = new TextComponent("Clique");
		trialclick.setBold(false);
		trialclick.setColor(ChatColor.YELLOW);

		trialclick.setClickEvent(
				new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mc-starpvp.com.br/aplicacao.html"));
		trialclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.GREEN + "Clique aqui para abrir o formulário.").create()));

		trial.addExtra(trialp);
		trial.addExtra(trialclick);
		trial.addExtra(trialp2);

		TextComponent trialgc = new TextComponent("  MODGC: ");
		trialgc.setBold(true);
		trialgc.setColor(ChatColor.DARK_PURPLE);

		TextComponent trialgcp = new TextComponent("(");
		trialgcp.setBold(false);
		trialgcp.setColor(ChatColor.GRAY);

		TextComponent trialgcp2 = new TextComponent(")");
		trialgcp2.setBold(false);
		trialgcp2.setColor(ChatColor.GRAY);

		TextComponent trialgcclick = new TextComponent("Clique");
		trialgcclick.setBold(false);
		trialgcclick.setColor(ChatColor.YELLOW);

		trialgcclick
				.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mc-starpvp.com.br/aplicacao.html"));
		trialgcclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.GREEN + "Clique aqui para abrir o formulário.").create()));

		trialgc.addExtra(trialgcp);
		trialgc.addExtra(trialgcclick);
		trialgc.addExtra(trialgcp2);

		TextComponent ajudante = new TextComponent("  AJUDANTE: ");
		ajudante.setBold(true);
		ajudante.setColor(ChatColor.DARK_BLUE);

		TextComponent ajudantep = new TextComponent("(");
		ajudantep.setBold(false);
		ajudantep.setColor(ChatColor.GRAY);

		TextComponent ajudantep2 = new TextComponent(")");
		ajudantep2.setBold(false);
		ajudantep2.setColor(ChatColor.GRAY);

		TextComponent ajudanteclick = new TextComponent("Clique");
		ajudanteclick.setBold(false);
		ajudanteclick.setColor(ChatColor.YELLOW);

		ajudanteclick
				.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mc-starpvp.com.br/aplicacao.html"));
		ajudanteclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.GREEN + "Clique aqui para abrir o formulário.").create()));

		ajudante.addExtra(ajudantep);
		ajudante.addExtra(ajudanteclick);
		ajudante.addExtra(ajudantep2);

		TextComponent builder = new TextComponent("  BUILDER: ");
		builder.setBold(true);
		builder.setColor(ChatColor.YELLOW);

		TextComponent builderp = new TextComponent("(");
		builderp.setBold(false);
		builderp.setColor(ChatColor.GRAY);

		TextComponent builderp2 = new TextComponent(")");
		builderp2.setBold(false);
		builderp2.setColor(ChatColor.GRAY);

		TextComponent builderclick = new TextComponent("Clique");
		builderclick.setBold(false);
		builderclick.setColor(ChatColor.YELLOW);

		builderclick
				.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mc-starpvp.com.br/aplicacao.html"));
		builderclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.GREEN + "Clique aqui para abrir o formulário.").create()));

		builder.addExtra(builderp);
		builder.addExtra(builderclick);
		builder.addExtra(builderp2);
		
		TextComponent youtuber = new TextComponent("  YOUTUBER-MANAGER: ");
		youtuber.setBold(true);
		youtuber.setColor(ChatColor.GREEN);

		TextComponent youtuberp = new TextComponent("(");
		youtuberp.setBold(false);
		youtuberp.setColor(ChatColor.GRAY);

		TextComponent youtuberp2 = new TextComponent(")");
		youtuberp2.setBold(false);
		youtuberp2.setColor(ChatColor.GRAY);

		TextComponent youtuberclick = new TextComponent("Clique");
		youtuberclick.setBold(false);
		youtuberclick.setColor(ChatColor.YELLOW);

		youtuberclick
				.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mc-starpvp.com.br/aplicacao.html"));
		youtuberclick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.GREEN + "Clique aqui para abrir o formulário.").create()));

		youtuber.addExtra(youtuberp);
		youtuber.addExtra(youtuberclick);
		youtuber.addExtra(youtuberp2);

		sender.sendMessage("");
		sender.sendMessage("§e  §eFormularios de aplicação para a equipe.");
		sender.sendMessage("");
		p.spigot().sendMessage(trial);
		p.spigot().sendMessage(trialgc);
		p.spigot().sendMessage(ajudante);
		p.spigot().sendMessage(builder);
		p.spigot().sendMessage(youtuber);
		sender.sendMessage("");
		return true;
	}

}
