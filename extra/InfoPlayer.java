package xyz.starmc.extra;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLXp;

public class InfoPlayer implements Listener {
	
	public static void getItem(Inventory inv, Material mat, int quantidade, int modo, String nome, int lugar) {
		ItemStack item = new ItemStack(mat, quantidade, (short) modo);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(nome);
		item.setItemMeta(itemmeta);
		inv.setItem(lugar, item);
	}

	public static void InfoOpen(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§eSuas Informações");

		ItemStack Wins = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta Winss = Wins.getItemMeta();
		ArrayList<String> Winsss = new ArrayList<String>();
		Winsss.add("§7" + SQLCache.Victory.get(p.getName()));
		Winss.setLore(Winsss);
		Winss.setDisplayName("§eVitórias");
		Wins.setItemMeta(Winss);

		ItemStack Defeats = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
        SkullMeta Defeatss = (SkullMeta) Defeats.getItemMeta();
		ArrayList<String> Defeatsss = new ArrayList<String>();
		Defeatsss.add("§7" + SQLCache.Defeats.get(p.getName()));
		Defeatss.setLore(Defeatsss);
		Defeatss.setDisplayName("§eDerrotas");
		Defeats.setItemMeta(Defeatss);

		ItemStack Xp = new ItemStack(Material.EXP_BOTTLE, 1);
		ItemMeta Xpp = Xp.getItemMeta();
		ArrayList<String> Xppp = new ArrayList<String>();
		Xppp.add("§7" + SQLXp.getXp(p));
		Xpp.setLore(Xppp);
		Xpp.setDisplayName("§eXP");
		Xp.setItemMeta(Xpp);

		ItemStack Rank = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta Rankk = Rank.getItemMeta();
		ArrayList<String> Elooo = new ArrayList<String>();
		Elooo.add("§7" + SQLXp.getRankComplete(SQLXp.getXp(p)));
		Rankk.setLore(Elooo);
		Rankk.setDisplayName("§eRank");
		Rank.setItemMeta(Rankk);

		ItemStack vidroc = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
		ItemMeta kvidroc = vidroc.getItemMeta();
		kvidroc.setDisplayName("§7§LINFO");
		vidroc.setItemMeta(kvidroc);

		inv.setItem(0, vidroc);
		inv.setItem(1, vidroc);
		inv.setItem(2, vidroc);
		inv.setItem(3, vidroc);
		inv.setItem(4, vidroc);
		inv.setItem(5, vidroc);
		inv.setItem(6, vidroc);
		inv.setItem(7, vidroc);
		inv.setItem(8, vidroc);
		inv.setItem(9, vidroc);
		inv.setItem(10, Wins);
		inv.setItem(11, vidroc);
		inv.setItem(12, Defeats);
		inv.setItem(13, vidroc);
		inv.setItem(14, Xp);
		inv.setItem(15, vidroc);
		inv.setItem(16, Rank);
		inv.setItem(17, vidroc);
		inv.setItem(18, vidroc);
		inv.setItem(19, vidroc);
		inv.setItem(20, vidroc);
		inv.setItem(21, vidroc);
		inv.setItem(22, vidroc);
		inv.setItem(23, vidroc);
		inv.setItem(24, vidroc);
		inv.setItem(25, vidroc);
		inv.setItem(26, vidroc);
		p.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCLickInventry(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§eSuas Informações") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.getWhoClicked().closeInventory();
				return;
			}
		}
	}

}
