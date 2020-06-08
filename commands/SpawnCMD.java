
package xyz.starmc.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.starmc.system.FlagProtection;
import xyz.starmc.system.MessageAPI;

public class SpawnCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		if (cmd.equalsIgnoreCase("spawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(MessageAPI.onCommandConsoleBlock);
				return true;
			} else {
				((Player) sender).setAllowFlight(false);
				((Player) sender).setFlying(false);
				((Player) sender).teleport(((Player) sender).getWorld().getSpawnLocation());
				((Player) sender).getPlayer().getActivePotionEffects()
						.forEach(potionEffect -> ((Player) sender).removePotionEffect(potionEffect.getType()));
				((Player) sender).setFoodLevel(20);
				((Player) sender).setHealth(20);
				((Player) sender).getInventory().clear();
				((Player) sender).getInventory().setArmorContents(null);
				FlagProtection.setProtection((Player) sender, true);
				((Player) sender).setGameMode(GameMode.ADVENTURE);
				for (Player todos : xyz.starmc.listener.Listener.RunningParty) {
					todos.hidePlayer((Player) sender);
				}
				
				ItemStack selectionouro = new ItemStack(Material.GOLD_SWORD);
				ItemMeta selectionouro2 = selectionouro.getItemMeta();
				selectionouro2.setDisplayName("§eClassic Modes (§7Unranked§e)");
				selectionouro.setItemMeta(selectionouro2);
				
				ItemStack selection = new ItemStack(Material.IRON_SWORD);
				ItemMeta selection2 = selection.getItemMeta();
				selection2.setDisplayName("§ePotPvP - §7(§eUnranked§7)");
				selection.setItemMeta(selection2);
				
				ItemStack selectiontriple = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta selectiontriple2 = selection.getItemMeta();
				selectiontriple2.setDisplayName("§ePotPvP - §7(§eRanked§7)");
				selectiontriple.setItemMeta(selectiontriple2);
				
				ItemStack Extra = new ItemStack(Material.EMERALD);
				ItemMeta Extra2 = Extra.getItemMeta();
				Extra2.setDisplayName("§fExtra (§aClique§f)");
				Extra.setItemMeta(Extra2);

				ItemStack apple = new ItemStack(Material.APPLE);
				ItemMeta applee = apple.getItemMeta();
				applee.setDisplayName("§eSumo - §7(§eClique§7)");
				apple.setItemMeta(applee);
				((Player) sender).getInventory().setItem(2, selectionouro);
				((Player) sender).getInventory().setItem(0, selection);
				((Player) sender).getInventory().setItem(1, selectiontriple);
				((Player) sender).getInventory().setItem(3, apple);
				((Player) sender).getInventory().setItem(8, Extra);
			}
		}
		return false;
	}
}
