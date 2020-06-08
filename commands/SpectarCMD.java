package xyz.starmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.starmc.gui.DiamondRanked;
import xyz.starmc.gui.DiamondUnRanked;
import xyz.starmc.gui.IronRanked;
import xyz.starmc.gui.IronUnRanked;
import xyz.starmc.gui.NullRanked;
import xyz.starmc.gui.NullUnRanked;
import xyz.starmc.gui.SelectionClassic;
import xyz.starmc.listener.Listener;

public class SpectarCMD implements CommandExecutor, org.bukkit.event.Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§c§lERRO §fComando incorreto, utilize /spectar (jogador)!");
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage("§c§lERRO §fJogador offline.");
			return true;
		}
		if (!Listener.RunningParty.contains(sender)) {
			if (!Listener.RunningParty.contains(target)) {
				sender.sendMessage("§c§lERRO §fEsse jogador não esta em uma batalha!");
			} else {
				Player p = (Player) sender;
				if (IronUnRanked.randomFB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (IronUnRanked.randomFC == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (IronUnRanked.randomFN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullUnRanked.randomB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullUnRanked.randomC == p.getUniqueId()) {
					NullUnRanked.randomC = null;
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullUnRanked.randomN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}

				if (DiamondUnRanked.randomDB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (DiamondUnRanked.randomDC == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (DiamondUnRanked.randomDN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (SelectionClassic.customBuild == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}

				if (IronRanked.randomFB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (IronRanked.randomFC == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (IronRanked.randomFN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullRanked.randomB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullRanked.randomC == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (NullRanked.randomN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (DiamondRanked.randomDB == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (DiamondRanked.randomDC == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}
				if (DiamondRanked.randomDN == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}

				if (Listener.sumorandom == p.getUniqueId()) {
					sender.sendMessage("§c§lSPEC §fVocê não pode spectar quando estiver na fila.");
					return true;
				}

				String combat = null;
				if (DiamondRanked.party.containsKey(target.getName())) {
					combat = DiamondRanked.party.get(target.getName());
				}
				if (SelectionClassic.party.containsKey(target.getName())) {
					combat = SelectionClassic.party.get(target.getName());
				}
				if (DiamondUnRanked.party.containsKey(target.getName())) {
					combat = DiamondUnRanked.party.get(target.getName());
				}
				if (IronRanked.party.containsKey(target.getName())) {
					combat = IronRanked.party.get(target.getName());
				}
				if (IronUnRanked.party.containsKey(target.getName())) {
					combat = IronUnRanked.party.get(target.getName());
				}
				if (NullRanked.party.containsKey(target.getName())) {
					combat = NullRanked.party.get(target.getName());
				}
				if (NullUnRanked.party.containsKey(target.getName())) {
					combat = NullUnRanked.party.get(target.getName());
				}
				if (Listener.Sumo.containsKey(target.getName())) {
					combat = Listener.Sumo.get(target.getName());
				}
				for(Player party : Listener.RunningParty) {
					party.hidePlayer((Player) sender);
					((Player) sender).hidePlayer(party);
				}
				((Player) sender).showPlayer(target);
				if (combat != null) {
					((Player) sender).showPlayer(Bukkit.getPlayer(combat));
				}
				((Player) sender).showPlayer(Bukkit.getPlayer(combat));
				Bukkit.getPlayer(combat).hidePlayer((Player) sender);
				target.hidePlayer((Player) sender);
	
				sender.sendMessage("§a§lSPEC §fVocê esta spectando o jogador §e" + target.getName());
				if (!((Player) sender).getAllowFlight()) {
					((Player) sender).setAllowFlight(true);
					((Player) sender).setFlying(true);
				}
				((Player) sender).teleport(target.getLocation());

				ItemStack diamond = new ItemStack(Material.IRON_INGOT);
				ItemMeta diamondd = diamond.getItemMeta();
				diamondd.setDisplayName("§cSair");
				diamond.setItemMeta(diamondd);
				((Player) sender).getInventory().clear();
				((Player) sender).getInventory().setItem(0, diamond);
			}
		} else {
			sender.sendMessage("§c§lERRO §fVocê não pode usar comandos em uma batalha.");
		}
		return false;
	}

}
