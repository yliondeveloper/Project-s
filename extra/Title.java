package xyz.starmc.extra;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.starmc.sql.SQLClan;

public class Title implements Listener {

	@EventHandler
	public static void PlayerJoin(PlayerJoinEvent e) {
		if (!RankXp.contains(e.getPlayer().getName())) {
			RankXp.add(e.getPlayer().getName());
		}
	}

	public static List<String> RankXp = new ArrayList<String>();
	public static List<String> Xp = new ArrayList<String>();
	public static List<String> ClanTag = new ArrayList<String>();

	public static void OpenTitle(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§eTítulos");

		ItemStack vidroa = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
		ItemMeta kvidroa = vidroa.getItemMeta();
		kvidroa.setDisplayName("§e§lTÍTULOS");
		vidroa.setItemMeta(kvidroa);

		ItemStack vidroc = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
		ItemMeta kvidroc = vidroc.getItemMeta();
		kvidroc.setDisplayName("§7§LTÍTULOS");
		vidroc.setItemMeta(kvidroc);

		if (RankXp.contains(p.getName())) {
			ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
			ItemMeta Rankk = Rank.getItemMeta();
			Rankk.setDisplayName("§7Rank: §aAtivado");
			Rank.setItemMeta(Rankk);
			inv.setItem(12, Rank);
		} else {
			ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
			ItemMeta Rankk = Rank.getItemMeta();
			Rankk.setDisplayName("§7Rank: §cDesativado");
			Rank.setItemMeta(Rankk);
			inv.setItem(12, Rank);
		}

		if (!RankXp.contains(p.getName()) && !Xp.contains(p.getName()) && !ClanTag.contains(p.getName())) {
			ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
			ItemMeta Rankk = Rank.getItemMeta();
			Rankk.setDisplayName("§7Rank: §aAtivado");
			Rank.setItemMeta(Rankk);
			inv.setItem(12, Rank);
		}

		if (ClanTag.contains(p.getName())) {
			ItemStack Clan = new ItemStack(Material.NAME_TAG);
			ItemMeta Clann = Clan.getItemMeta();
			Clann.setDisplayName("§7Clan: §aAtivado");
			Clan.setItemMeta(Clann);
			inv.setItem(14, Clan);
		} else {
			ItemStack Clan = new ItemStack(Material.NAME_TAG);
			ItemMeta Clann = Clan.getItemMeta();
			Clann.setDisplayName("§7Clan: §cDesativado");
			Clan.setItemMeta(Clann);
			inv.setItem(14, Clan);

		}

		if (Xp.contains(p.getName())) {
			ItemStack Xp = new ItemStack(Material.DIAMOND);
			ItemMeta Xpo = Xp.getItemMeta();
			Xpo.setDisplayName("§7Xp: §aAtivado");
			Xp.setItemMeta(Xpo);
			inv.setItem(13, Xp);
		} else {
			ItemStack Xp = new ItemStack(Material.DIAMOND);
			ItemMeta Xpo = Xp.getItemMeta();
			Xpo.setDisplayName("§7Xp: §cDesativado");
			Xp.setItemMeta(Xpo);
			inv.setItem(13, Xp);

		}

		inv.setItem(0, vidroa);
		inv.setItem(1, vidroc);
		inv.setItem(2, vidroc);
		inv.setItem(3, vidroc);
		inv.setItem(4, vidroc);
		inv.setItem(5, vidroc);
		inv.setItem(6, vidroc);
		inv.setItem(7, vidroc);
		inv.setItem(8, vidroa);
		inv.setItem(9, vidroa);
		inv.setItem(10, vidroa);
		inv.setItem(11, vidroc);
		inv.setItem(15, vidroc);
		inv.setItem(16, vidroa);
		inv.setItem(17, vidroa);
		inv.setItem(18, vidroa);
		inv.setItem(19, vidroc);
		inv.setItem(20, vidroc);
		inv.setItem(21, vidroc);
		inv.setItem(22, vidroc);
		inv.setItem(23, vidroc);
		inv.setItem(24, vidroc);
		inv.setItem(25, vidroc);
		inv.setItem(26, vidroa);
		inv.setItem(4, vidroa);
		p.openInventory(inv);
	}

	@EventHandler
	public static void QuitEvent(PlayerQuitEvent e) {
		if (RankXp.contains(e.getPlayer().getName())) {
			RankXp.remove(e.getPlayer().getName());
		}
		if (Xp.contains(e.getPlayer().getName())) {
			Xp.remove(e.getPlayer().getName());
		}
		if (ClanTag.contains(e.getPlayer().getName())) {
			ClanTag.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	public static void QuitEvent(PlayerKickEvent e) {
		if (RankXp.contains(e.getPlayer().getName())) {
			RankXp.remove(e.getPlayer().getName());
		}
		if (Xp.contains(e.getPlayer().getName())) {
			Xp.remove(e.getPlayer().getName());
		}
		if (ClanTag.contains(e.getPlayer().getName())) {
			ClanTag.remove(e.getPlayer().getName());
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCLickInventry(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§eTítulos") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Xp: §aAtivado")
					| e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Xp: §cDesativado")) {
				if (!Xp.contains(e.getWhoClicked().getName())) {
					Xp.add(e.getWhoClicked().getName());
					if (RankXp.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
						ItemMeta Rankk = Rank.getItemMeta();
						Rankk.setDisplayName("§7Rank: §cDesativado");
						Rank.setItemMeta(Rankk);
						inv.setItem(12, Rank);
						RankXp.remove(e.getWhoClicked().getName());
					}
					if (ClanTag.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Clan = new ItemStack(Material.NAME_TAG);
						ItemMeta Clann = Clan.getItemMeta();
						Clann.setDisplayName("§7Clan: §cDesativado");
						Clan.setItemMeta(Clann);
						inv.setItem(14, Clan);
						ClanTag.remove(e.getWhoClicked().getName());
					}
					if (!RankXp.contains(e.getWhoClicked().getName()) && !Xp.contains(e.getWhoClicked().getName())
							&& !ClanTag.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
						ItemMeta Rankk = Rank.getItemMeta();
						Rankk.setDisplayName("§7Rank: §aAtivado");
						Rank.setItemMeta(Rankk);
						inv.setItem(12, Rank);
						return;
					}
					Inventory inv = e.getClickedInventory();
					ItemStack Xp = new ItemStack(Material.DIAMOND);
					ItemMeta Xpo = Xp.getItemMeta();
					Xpo.setDisplayName("§7Xp: §aAtivado");
					Xp.setItemMeta(Xpo);
					inv.setItem(13, Xp);
					return;
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Rank: §aAtivado")
					| e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Rank: §cDesativado")) {
				if (!RankXp.contains(e.getWhoClicked().getName())) {
					RankXp.add(e.getWhoClicked().getName());
					if (Xp.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();

						ItemStack Score = new ItemStack(Material.DIAMOND);
						ItemMeta Scoree = Score.getItemMeta();
						Scoree.setDisplayName("§7Xp: §cDesativado");
						Score.setItemMeta(Scoree);
						inv.setItem(13, Score);
						Xp.remove(e.getWhoClicked().getName());
					}
					if (ClanTag.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Clan = new ItemStack(Material.NAME_TAG);
						ItemMeta Clann = Clan.getItemMeta();
						Clann.setDisplayName("§7Clan: §cDesativado");
						Clan.setItemMeta(Clann);
						inv.setItem(14, Clan);
						ClanTag.remove(e.getWhoClicked().getName());
					}
					Inventory inv = e.getClickedInventory();
					ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
					ItemMeta Rankk = Rank.getItemMeta();
					Rankk.setDisplayName("§7Rank: §aAtivado");
					Rank.setItemMeta(Rankk);
					inv.setItem(12, Rank);
					if (!RankXp.contains(e.getWhoClicked().getName()) && !Xp.contains(e.getWhoClicked().getName())
							&& !ClanTag.contains(e.getWhoClicked().getName())) {
						Rankk.setDisplayName("§7Rank: §aAtivado");
						Rank.setItemMeta(Rankk);
						inv.setItem(12, Rank);
						return;
					}
					return;
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Clan: §aAtivado")
					| e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Clan: §cDesativado")) {
				if (!SQLClan.tag.containsKey(e.getWhoClicked().getName())) {
					e.getWhoClicked().sendMessage(
							"§c§lERRO §fVocê precisa estar em um clan para poder utilizar essa configuração.");
					return;
				}
				if (!ClanTag.contains(e.getWhoClicked().getName())) {
					ClanTag.add(e.getWhoClicked().getName());
					if (RankXp.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
						ItemMeta Rankk = Rank.getItemMeta();
						Rankk.setDisplayName("§7Rank: §cDesativado");
						Rank.setItemMeta(Rankk);
						inv.setItem(12, Rank);
						RankXp.remove(e.getWhoClicked().getName());
					}

					if (Xp.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();

						ItemStack Score = new ItemStack(Material.DIAMOND);
						ItemMeta Scoree = Score.getItemMeta();
						Scoree.setDisplayName("§7Xp: §cDesativado");
						Score.setItemMeta(Scoree);
						inv.setItem(13, Score);
						Xp.remove(e.getWhoClicked().getName());
					}

					if (!RankXp.contains(e.getWhoClicked().getName()) && !Xp.contains(e.getWhoClicked().getName())
							&& !ClanTag.contains(e.getWhoClicked().getName())) {
						Inventory inv = e.getClickedInventory();
						ItemStack Rank = new ItemStack(Material.EXP_BOTTLE);
						ItemMeta Rankk = Rank.getItemMeta();
						Rankk.setDisplayName("§7Rank: §aAtivado");
						Rank.setItemMeta(Rankk);
						inv.setItem(12, Rank);
						return;
					}
					Inventory inv = e.getClickedInventory();
					ItemStack Clan = new ItemStack(Material.NAME_TAG);
					ItemMeta Clann = Clan.getItemMeta();
					Clann.setDisplayName("§7Clan: §aAtivado");
					Clan.setItemMeta(Clann);
					inv.setItem(14, Clan);
					return;
				}
			}
		}

	}
}
