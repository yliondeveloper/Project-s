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

public class ExtraGUI implements Listener {

	public static void getItem(Inventory inv, Material mat, int quantidade, int modo, String nome, int lugar) {
		ItemStack item = new ItemStack(mat, quantidade, (short) modo);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(nome);
		item.setItemMeta(itemmeta);
		inv.setItem(lugar, item);
	}

	public static void ExtraGUIOpen(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§fExtra (§aClique§f)");

		ItemStack Quests = new ItemStack(Material.MAP, 1);
		ItemMeta Questss = Quests.getItemMeta();
		ArrayList<String> Questsss = new ArrayList<String>();
		Questsss.add("");
		Questsss.add("§7 - Realize conquistas e receba prêmios.");
		Questsss.add("");
		Questss.setLore(Questsss);
		Questss.setDisplayName("§eQuests");
		Quests.setItemMeta(Questss);

		ItemStack Perf = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta Perff = (SkullMeta) Perf.getItemMeta();
		Perff.setOwner(p.getName());
		ArrayList<String> Perfff = new ArrayList<String>();
		Perfff.add("");
		Perfff.add("§7- Acesse seu perfil e veja suas informações.");
		Perfff.add("");
		Perff.setLore(Perfff);
		Perff.setDisplayName("§ePerfil");
		Perf.setItemMeta(Perff);

		ItemStack Chat = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta Chatt = Chat.getItemMeta();
		ArrayList<String> Chattt = new ArrayList<String>();
		Chattt.add("");
		Chattt.add("§7- Acesse o menu de titulos e configure como irá usar seu chat.");
		Chattt.add("");
		Chatt.setLore(Chattt);
		Chatt.setDisplayName("§eTítulos");
		Chat.setItemMeta(Chatt);

		@SuppressWarnings("deprecation")
		ItemStack Config = new ItemStack(Material.getMaterial(356), 1);
		ItemMeta Configg = Config.getItemMeta();
		ArrayList<String> Configgg = new ArrayList<String>();
		Configgg.add("");
		Configgg.add("§7- Acesse o menu de titulos e configure como irá usar seu chat.");
		Configgg.add("");
		Configg.setLore(Configgg);
		Configg.setDisplayName("§eConfigurações");
		Config.setItemMeta(Configg);

		ItemStack vidroa = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
		ItemMeta kvidroa = vidroa.getItemMeta();
		kvidroa.setDisplayName("§E§LEXTRA");
		vidroa.setItemMeta(kvidroa);

		ItemStack vidroc = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
		ItemMeta kvidroc = vidroc.getItemMeta();
		kvidroc.setDisplayName("§7§LEXTRA");
		vidroc.setItemMeta(kvidroc);

		inv.setItem(0, vidroa);
		inv.setItem(1, vidroc);
		inv.setItem(2, vidroc);
		inv.setItem(3, vidroa);
		inv.setItem(4, vidroa);
		inv.setItem(5, vidroa);
		inv.setItem(6, vidroc);
		inv.setItem(7, vidroc);
		inv.setItem(8, vidroa);
		inv.setItem(9, vidroc);
		inv.setItem(10, Quests);
		inv.setItem(11, vidroc);
		inv.setItem(12, Perf);
		inv.setItem(13, vidroc);
		inv.setItem(14, Chat);
		inv.setItem(15, vidroc);
		inv.setItem(16, Config);
		inv.setItem(17, vidroc);
		inv.setItem(18, vidroa);
		inv.setItem(19, vidroc);
		inv.setItem(20, vidroc);
		inv.setItem(21, vidroa);
		inv.setItem(22, vidroa);
		inv.setItem(23, vidroa);
		inv.setItem(24, vidroc);
		inv.setItem(25, vidroc);
		inv.setItem(26, vidroa);
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCLickInventry(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§fExtra (§aClique§f)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.PAPER) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.MAP) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getType() == Material.NAME_TAG) {
				Title.OpenTitle((Player) e.getWhoClicked());
				return;
			}
			if (e.getCurrentItem().getType() == Material.getMaterial(356)) {
				Config.OpenConfig((Player) e.getWhoClicked());
				return;
			}
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				InfoPlayer.InfoOpen((Player) e.getWhoClicked());
				return;
			}
		}
	}

}
