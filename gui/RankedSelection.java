package xyz.starmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RankedSelection implements Listener {

	public static void SelectionGuiActive(Player p) {
		Inventory inv = Bukkit.createInventory((InventoryHolder) null, 9, "§ePractice §7(§aLista - 2§7)");

		ItemStack vinha = new ItemStack(Material.VINE);
		ItemMeta vinhaa = vinha.getItemMeta();
		vinha.setDurability((short) 4);
		vinhaa.setDisplayName("§aVinha");
		vinha.setItemMeta(vinhaa);

		ItemStack fence = new ItemStack(Material.IRON_FENCE);
		ItemMeta fencee = fence.getItemMeta();
		fencee.setDisplayName("§7Fence");
		fence.setItemMeta(fencee);

		ItemStack fulliron = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta fullironn = fulliron.getItemMeta();
		fullironn.setDisplayName("§7Practice: §7(§aFull-Iron§7)");
		fulliron.setItemMeta(fullironn);
		inv.addItem(new ItemStack[] { fulliron });

		ItemStack none = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta nonee = none.getItemMeta();
		nonee.setDisplayName("§7Practice: §7(§aDefault§7)");
		none.setItemMeta(nonee);
		inv.addItem(new ItemStack[] { none });

		ItemStack fulldima = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta fulldimaa = fulldima.getItemMeta();
		fulldimaa.setDisplayName("§7Practice: §7(§aFull-Diamond§7)");
		fulldima.setItemMeta(fulldimaa);
		inv.addItem(new ItemStack[] { fulldima });

		ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta vidroo = vidro.getItemMeta();
		vidro.setDurability((short) 4);
		vidroo.setDisplayName("§e§lTeck§f§lMC");
		vidro.setItemMeta(vidroo);

		inv.setItem(0, fence);
		inv.setItem(1, vinha);
		inv.setItem(2, fence);
		inv.setItem(4, fulliron);
		inv.setItem(3, fulldima);
		inv.setItem(5, none);
		inv.setItem(6, fence);
		inv.setItem(7, vinha);
		inv.setItem(8, fence);
		p.openInventory(inv);
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onPlayerCLickInventry(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§ePractice §7(§aLista - 2§7)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {

			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.VINE) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
				IronRanked.FullIronActive(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
				NullRanked.NoneActive(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
				DiamondRanked.DiamondActive(p);
				return;
			}
		}
	}
}
