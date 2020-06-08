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
import xyz.starmc.system.SystemAPI;

public class NullRanked implements org.bukkit.event.Listener {

	public static void getItem(Inventory inv, Material mat, int quantidade, int modo, String nome, int lugar) {
		ItemStack item = new ItemStack(mat, quantidade, (short) modo);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(nome);
		item.setItemMeta(itemmeta);
		inv.setItem(lugar, item);
	}

	public static Map<String, String> party = new HashMap<String, String>();
	public static UUID randomB = null;
	public static UUID randomN = null;
	public static UUID randomC = null;

	public static void NoneActive(Player p) {
		Inventory inv = Bukkit.createInventory(p, InventoryType.HOPPER, "§eDefault §7(§aLista - 2§7)");

		ItemStack None = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemMeta Nonee = None.getItemMeta();

		ArrayList<String> Noneee = new ArrayList<String>();
		Noneee.add("");
		if (randomN == null) {
			Noneee.add("§a0 §fjogadores na fila.");
		} else {
			Noneee.add("§a1 §fjogador(es) na fila.");
		}
		Noneee.add("");
		Nonee.setLore(Noneee);

		Nonee.setDisplayName("§7Default §7(§aNormal/NoDebuff§7)");
		None.setItemMeta(Nonee);
		inv.addItem(new ItemStack[] { None });

		ItemStack buff = new ItemStack(Material.POTION);
		ItemMeta bufff = buff.getItemMeta();

		ArrayList<String> buffff = new ArrayList<String>();
		buffff.add("");
		if (randomB == null) {
			buffff.add("§a0 §fjogadores na fila.");
		} else {
			buffff.add("§a1 §fjogador(es) na fila.");
		}
		buffff.add("");
		bufff.setLore(buffff);

		bufff.setDisplayName("§7Default §7(§aBUFF§7)");
		buff.setItemMeta(bufff);
		inv.addItem(new ItemStack[] { buff });

		ItemStack combofly = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta comboflyy = combofly.getItemMeta();

		ArrayList<String> comboflyyy = new ArrayList<String>();
		comboflyyy.add("");
		if (randomC == null) {
			comboflyyy.add("§a0 §fjogadores na fila.");
		} else {
			comboflyyy.add("§a1 §fjogador(es) na fila.");
		}
		comboflyyy.add("");
		comboflyy.setLore(comboflyyy);

		comboflyy.setDisplayName("§7Default §7(§aComboFly§7)");
		combofly.setItemMeta(comboflyy);
		inv.addItem(new ItemStack[] { combofly });

		ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta vidroo = vidro.getItemMeta();
		vidro.setDurability((short) 4);
		vidroo.setDisplayName("§e§lTeck§f§lMC");
		vidro.setItemMeta(vidroo);

		inv.setItem(0, vidro);
		inv.setItem(2, None);
		inv.setItem(1, combofly);
		inv.setItem(3, buff);
		inv.setItem(4, vidro);

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClickEventPractice(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§eDefault §7(§aLista - 2§7)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((randomN != null) && (randomN != id)) {
					Player p2 = Bukkit.getPlayer(randomN);
					if (p2 != null) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage("§a§lSEARCH §fOponente encontrado, batalha iniciando...");

						APIListener.APITeleport(p, p2);
						APIListener.APIJoin(p2);
						APIListener.APIJoin(p);

						Listener.RunningParty.add(p);
						Listener.RunningParty.add(p2);
						Listener.Spawn.remove(p.getName());
						Listener.Spawn.remove(p2.getName());
						party.put(p.getName(), p2.getName());
						party.put(p2.getName(), p.getName());
						randomN = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(org.bukkit.Material.STONE_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 1, true);
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
						SystemAPI.getPotion(p, 33);
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomN = id;
				}

				return;
			}
			if (e.getCurrentItem().getType() == Material.POTION) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);

				if ((randomB != null) && (randomB != id)) {
					Player p2 = Bukkit.getPlayer(randomB);
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
						randomB = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(org.bukkit.Material.STONE_SWORD);
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
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomB = id;
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
				if ((randomC != null) && (randomC != id)) {
					Player p2 = Bukkit.getPlayer(randomC);
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
						randomC = null;

						p2.setMaximumNoDamageTicks(5);
						p.setMaximumNoDamageTicks(5);

						p.showPlayer(p2);
						p2.showPlayer(p);

						ItemStack espada = new ItemStack(org.bukkit.Material.STONE_SWORD);
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
						p.getInventory().setItem(1, ender);
						p2.getInventory().setItem(1, ender);
						// ---------------------------
						p2.getInventory().setItem(3, speed);
						p.getInventory().setItem(3, speed);
						// ---------------------------
						p2.getInventory().setItem(1, golden);
						p.getInventory().setItem(1, golden);
						// ---------------------------
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						// -----------------------------------------------------------
						ItemStack capacete = new ItemStack(Material.LEATHER_HELMET);
						// -----------------------------------------------------------
						ItemStack colete = new ItemStack(Material.LEATHER_CHESTPLATE);
						// -----------------------------------------------------------
						ItemStack calca = new ItemStack(Material.LEATHER_LEGGINGS);
						// -----------------------------------------------------------
						ItemStack botas = new ItemStack(Material.LEATHER_BOOTS);
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
					randomC = id;
				}

				return;
			}
		}
	}
}
