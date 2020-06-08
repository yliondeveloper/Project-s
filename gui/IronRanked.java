package xyz.starmc.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.starmc.listener.APIListener;
import xyz.starmc.listener.Listener;
import xyz.starmc.system.FlagProtection;
import xyz.starmc.system.SystemAPI;

public class IronRanked implements org.bukkit.event.Listener {

	public static Map<String, String> party = new HashMap<String, String>();
	public static UUID randomFN = null;
	public static UUID randomFB = null;
	public static UUID randomFC = null;

	public static void FullIronActive(Player p) {
		Inventory inv = Bukkit.createInventory(p, InventoryType.HOPPER, "§eFull-Iron §7(§aLista - 2§7)");

		ItemStack FullIron = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta FullIronn = FullIron.getItemMeta();

		ArrayList<String> FullIronnn = new ArrayList<String>();
		FullIronnn.add("");
		if (randomFN == null) {
			FullIronnn.add("§a0 §fjogadores na fila.");
		} else {
			FullIronnn.add("§a1 §fjogador(es) na fila.");
		}
		FullIronnn.add("");
		FullIronn.setLore(FullIronnn);

		FullIronn.setDisplayName("§7Full-Iron §7(§aNormal/NoDebuff§7)");
		FullIron.setItemMeta(FullIronn);
		inv.addItem(new ItemStack[] { FullIron });

		ItemStack buff = new ItemStack(Material.POTION);
		ItemMeta bufff = buff.getItemMeta();

		ArrayList<String> buffff = new ArrayList<String>();
		buffff.add("");
		if (randomFB == null) {
			buffff.add("§a0 §fjogadores na fila.");
		} else {
			buffff.add("§a1 §fjogador(es) na fila.");
		}
		buffff.add("");
		bufff.setLore(buffff);

		bufff.setDisplayName("§7Full-Iron §7(§aBUFF§7)");
		buff.setItemMeta(bufff);
		inv.addItem(new ItemStack[] { buff });

		ItemStack combofly = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta comboflyy = combofly.getItemMeta();

		ArrayList<String> comboflyyy = new ArrayList<String>();
		comboflyyy.add("");
		if (randomFC == null) {
			comboflyyy.add("§a0 §fjogadores na fila.");
		} else {
			comboflyyy.add("§a1 §fjogador(es) na fila.");
		}
		comboflyyy.add("");
		comboflyy.setLore(comboflyyy);

		comboflyy.setDisplayName("§7Full-Iron §7(§aComboFly§7)");
		combofly.setItemMeta(comboflyy);
		inv.addItem(new ItemStack[] { combofly });

		ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta vidroo = vidro.getItemMeta();
		vidro.setDurability((short) 4);
		vidroo.setDisplayName("§e§lTeck§f§lMC");
		vidro.setItemMeta(vidroo);

		inv.setItem(0, vidro);
		inv.setItem(2, FullIron);
		inv.setItem(1, combofly);
		inv.setItem(3, buff);
		inv.setItem(4, vidro);

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClickEventPractice(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§eFull-Iron §7(§aLista - 2§7)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((randomFN != null) && (randomFN != id)) {
					Player p2 = Bukkit.getPlayer(randomFN);
					if (p2 != null) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage("§a§lSEARCH §fOponente encontrado, batalha iniciando...");
						p.sendMessage("§a§lSEARCH §fBatalha iniciada com sucesso, boa sorte!");

						APIListener.APITeleport(p, p2);
						APIListener.APIJoin(p2);
						APIListener.APIJoin(p);

						Listener.RunningParty.add(p);
						Listener.RunningParty.add(p2);
						Listener.Spawn.remove(p.getName());
						Listener.Spawn.remove(p2.getName());
						party.put(p.getName(), p2.getName());
						party.put(p2.getName(), p.getName());
						randomFN = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(org.bukkit.Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espada.setItemMeta(espadaM);
						// --------------------------
						ItemStack carne = new ItemStack(org.bukkit.Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ---------------------------
						ItemStack ender = new ItemStack(org.bukkit.Material.ENDER_PEARL);
						ItemMeta enderp = ender.getItemMeta();
						enderp.setDisplayName("§5Ender Pearl");
						ender.setItemMeta(enderp);
						ender.setAmount(16);
						// ---------------------------

						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);
						p.getInventory().setItem(1, ender);
						p2.getInventory().setItem(1, ender);
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
						SystemAPI.getPotion(p, 33);
						SystemAPI.getPotion(p2, 33);
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomFN = id;
				}

				return;
			}
			if (e.getCurrentItem().getType() == Material.POTION) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.sendMessage("");
				p.setAllowFlight(false);
				p.setFlying(false);

				if ((randomFB != null) && (randomFB != id)) {
					Player p2 = Bukkit.getPlayer(randomFB);
					if (p2 != null) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage("§a§lSEARCH §fOponente encontrado, batalha iniciando...");
						p.sendMessage("§a§lSEARCH §fBatalha iniciada com sucesso, boa sorte!");

						APIListener.APITeleport(p, p2);
						APIListener.APIJoin(p2);
						APIListener.APIJoin(p);

						Listener.RunningParty.add(p);
						Listener.RunningParty.add(p2);
						Listener.Spawn.remove(p.getName());
						Listener.Spawn.remove(p2.getName());

						party.put(p.getName(), p2.getName());
						party.put(p2.getName(), p.getName());
						randomFB = null;
						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espada.setItemMeta(espadaM);
						// --------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ---------------------------
						ItemStack ender = new ItemStack(Material.ENDER_PEARL);
						ItemMeta enderp = ender.getItemMeta();
						enderp.setDisplayName("§5Ender Pearl");
						ender.setItemMeta(enderp);
						ender.setAmount(16);
						// ---------------------------

						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);
						p.getInventory().setItem(1, ender);
						p2.getInventory().setItem(1, ender);
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
						// ---------------------------
						ItemStack speed = new ItemStack(Material.POTION, 1, (short) 16450);
						ItemMeta mspeed = speed.getItemMeta();
						mspeed.setDisplayName("§7Speed - Potion");
						speed.setItemMeta(mspeed);
						speed.setAmount(1);
						// ---------------------------
						ItemStack forca = new ItemStack(Material.POTION, 1, (short) 8201);
						ItemMeta mforca = forca.getItemMeta();
						mforca.setDisplayName("§cStrength - Potion");
						forca.setItemMeta(mforca);
						forca.setAmount(1);
						// ---------------------------
						p2.getInventory().setItem(3, speed);
						p.getInventory().setItem(3, speed);
						// ---------------------------
						p2.getInventory().setItem(2, forca);
						p.getInventory().setItem(2, forca);
						// ---------------------------
						SystemAPI.getPotion(p, 31);
						SystemAPI.getPotion(p2, 31);
						FlagProtection.setProtection(p2, false);
						FlagProtection.setProtection(p, false);
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomFB = id;
				}
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_POWDER) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((randomFC != null) && (randomFC != id)) {
					Player p2 = Bukkit.getPlayer(randomFC);
					if (p2 != null) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage("§a§lSEARCH §fOponente encontrado, batalha iniciando...");
						p.sendMessage("§a§lSEARCH §fBatalha iniciada com sucesso, boa sorte!");

						APIListener.APITeleport(p, p2);
						APIListener.APIJoin(p2);
						APIListener.APIJoin(p);

						Listener.RunningParty.add(p);
						Listener.RunningParty.add(p2);
						Listener.Spawn.remove(p.getName());
						Listener.Spawn.remove(p2.getName());
						party.put(p.getName(), p2.getName());
						party.put(p2.getName(), p.getName());
						randomFC = null;
						p.setMaxHealth(20.0D);
						p2.setMaxHealth(20.0D);
						p.setHealth(20.0D);
						p2.setHealth(20.0D);
						p2.setMaximumNoDamageTicks(5);
						p.setMaximumNoDamageTicks(5);

						ItemStack espada = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espada.setItemMeta(espadaM);
						// --------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ---------------------------
						ItemStack ender = new ItemStack(Material.ENDER_PEARL);
						ItemMeta enderp = ender.getItemMeta();
						enderp.setDisplayName("§5Ender Pearl");
						ender.setItemMeta(enderp);
						ender.setAmount(16);
						// ---------------------------
						ItemStack speed = new ItemStack(Material.POTION, 1, (short) 16450);
						ItemMeta mspeed = speed.getItemMeta();
						mspeed.setDisplayName("§7Speed - Potion");
						speed.setItemMeta(mspeed);
						speed.setAmount(1);
						// ---------------------------
						ItemStack golden = new ItemStack(Material.GOLDEN_APPLE, 64, (byte) 1);
						ItemMeta goldenn = golden.getItemMeta();
						goldenn.setDisplayName("§6Maçã dourada");
						golden.setItemMeta(goldenn);
						// ---------------------------
						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);
						// ---------------------------
						p2.getInventory().setItem(3, speed);
						p.getInventory().setItem(3, speed);
						// ---------------------------
						p.getInventory().setItem(1, ender);
						p2.getInventory().setItem(1, ender);
						// ---------------------------
						p2.getInventory().setItem(1, golden);
						p.getInventory().setItem(1, golden);
						// ---------------------------
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						// -----------------------------------------------------------
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						// -----------------------------------------------------------
						ItemStack colete = new ItemStack(Material.IRON_CHESTPLATE);
						// -----------------------------------------------------------
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						// -----------------------------------------------------------
						ItemStack botas = new ItemStack(Material.IRON_BOOTS);
						// -----------------------------------------------------------
						p2.getInventory().setHelmet(capacete);
						p2.getInventory().setChestplate(colete);
						p2.getInventory().setLeggings(calca);
						p2.getInventory().setBoots(botas);

						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(colete);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(botas);

						SystemAPI.getPotion(p, 32);
						SystemAPI.getPotion(p2, 32);

						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomFC = id;
				}

				return;
			}
		}
	}
}
