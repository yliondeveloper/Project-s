package xyz.starmc.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

public class DiamondUnRanked implements org.bukkit.event.Listener {

	public static Map<String, String> party = new HashMap<String, String>();
	public static UUID randomDN = null;
	public static UUID randomDB = null;
	public static UUID randomDC = null;

	public static void DiamondActive(Player p) {
		Inventory inv = Bukkit.createInventory(p, InventoryType.HOPPER, "§eFull-Diamond §7(§aLista§7)");

		ItemStack FullDiamond = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta FullDiamondd = FullDiamond.getItemMeta();
		ArrayList<String> FullDiamonddd = new ArrayList<String>();
		FullDiamonddd.add("");
		if (randomDN == null) {
			FullDiamonddd.add("§a0 §fjogadores na fila.");
		} else {
			FullDiamonddd.add("§a1 §fjogador(es) na fila.");
		}
		FullDiamonddd.add("");
		FullDiamondd.setLore(FullDiamonddd);
		FullDiamondd.setDisplayName("§7Full-Diamond §7(§aNormal/NoDebuff§7)");
		FullDiamond.setItemMeta(FullDiamondd);
		inv.addItem(new ItemStack[] { FullDiamond });

		ItemStack buff = new ItemStack(Material.POTION);
		ItemMeta bufff = buff.getItemMeta();
		ArrayList<String> buffff = new ArrayList<String>();
		buffff.add("");
		if (randomDB == null) {
			buffff.add("§a0 §fjogadores na fila.");
		} else {
			buffff.add("§a1 §fjogador(es) na fila.");
		}
		buffff.add("");
		bufff.setLore(buffff);
		bufff.setDisplayName("§7Full-Diamond §7(§aBUFF§7)");
		buff.setItemMeta(bufff);
		inv.addItem(new ItemStack[] { buff });

		ItemStack combofly = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta comboflyy = combofly.getItemMeta();
		ArrayList<String> comboflyyyy = new ArrayList<String>();
		comboflyyyy.add("");
		if (randomDC == null) {
			comboflyyyy.add("§a0 §fjogadores na fila.");
		} else {
			comboflyyyy.add("§a1 §fjogador(es) na fila.");
		}
		comboflyyyy.add("");
		comboflyy.setLore(comboflyyyy);
		comboflyy.setDisplayName("§7Full-Diamond §7(§aComboFly§7)");
		combofly.setItemMeta(comboflyy);
		inv.addItem(new ItemStack[] { combofly });

		ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta vidroo = vidro.getItemMeta();
		vidro.setDurability((short) 4);
		vidroo.setDisplayName("§e§lTeck§f§lMC");
		vidro.setItemMeta(vidroo);

		inv.setItem(0, vidro);
		inv.setItem(2, FullDiamond);
		inv.setItem(1, combofly);
		inv.setItem(3, buff);
		inv.setItem(4, vidro);

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClickEventPractice(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§eFull-Diamond §7(§aLista§7)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((randomDN != null) && (randomDN != id)) {
					Player p2 = Bukkit.getPlayer(randomDN);
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
						randomDN = null;
						
						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(org.bukkit.Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
						espadaM.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
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
						ItemStack fire = new ItemStack(Material.POTION, 1, (short) 8195);
						ItemMeta firee = fire.getItemMeta();
						firee.setDisplayName("§cFire Resistance - Potion");
						fire.setItemMeta(firee);
						fire.setAmount(1);
						// ---------------------------
						p.getInventory().setItem(2, fire);
						p2.getInventory().setItem(2, fire);
						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);
						p.getInventory().setItem(1, ender);
						p2.getInventory().setItem(1, ender);
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						SystemAPI.getPotion(p, 32);
						SystemAPI.getPotion(p2, 32);
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomDN = id;
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

				if ((randomDB != null) && (randomDB != id)) {
					Player p2 = Bukkit.getPlayer(randomDB);
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
						randomDB = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
						espadaM.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
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
						p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
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
						ItemStack fire = new ItemStack(Material.POTION, 1, (short) 8195);
						ItemMeta firee = fire.getItemMeta();
						firee.setDisplayName("§cFire Resistance - Potion");
						fire.setItemMeta(firee);
						fire.setAmount(1);
						// ---------------------------
						p2.getInventory().setItem(4, fire);
						p.getInventory().setItem(4, fire);
						// ---------------------------
						p2.getInventory().setItem(3, speed);
						p.getInventory().setItem(3, speed);
						// ---------------------------
						p2.getInventory().setItem(2, forca);
						p.getInventory().setItem(2, forca);
						// ---------------------------
						SystemAPI.getPotion(p, 30);
						SystemAPI.getPotion(p2, 30);
						p.updateInventory();
						p2.updateInventory();

						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomDB = id;
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
				if ((randomDC != null) && (randomDC != id)) {
					Player p2 = Bukkit.getPlayer(randomDC);
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
						randomDC = null;
						p2.setMaximumNoDamageTicks(5);
						p.setMaximumNoDamageTicks(5);

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
						espadaM.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
						espada.setItemMeta(espadaM);
						// --------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ---------------------------
						ItemStack golden = new ItemStack(Material.GOLDEN_APPLE, 64, (byte) 1);
						ItemMeta goldenn = golden.getItemMeta();
						goldenn.setDisplayName("§6Maçã dourada");
						golden.setItemMeta(goldenn);
						// ---------------------------
						ItemStack ender = new ItemStack(Material.ENDER_PEARL);
						ItemMeta enderp = ender.getItemMeta();
						enderp.setDisplayName("§5Ender Pearl");
						ender.setItemMeta(enderp);
						ender.setAmount(16);
						// ---------------------------
						ItemStack fire = new ItemStack(Material.POTION, 1, (short) 8195);
						ItemMeta firee = fire.getItemMeta();
						firee.setDisplayName("§cFire Resistance - Potion");
						fire.setItemMeta(firee);
						fire.setAmount(1);
						// ---------------------------
						ItemStack speed = new ItemStack(Material.POTION, 1, (short) 16450);
						ItemMeta mspeed = speed.getItemMeta();
						mspeed.setDisplayName("§7Speed - Potion");
						speed.setItemMeta(mspeed);
						speed.setAmount(1);
						// ---------------------------
						p.getInventory().setItem(2, fire);
						p2.getInventory().setItem(2, fire);
						p.getInventory().setItem(8, carne);
						// ---------------------------
						p2.getInventory().setItem(3, speed);
						p.getInventory().setItem(3, speed);
						// ---------------------------
						p2.getInventory().setItem(8, carne);
						p.getInventory().setItem(4, ender);
						// ---------------------------
						p2.getInventory().setItem(4, ender);
						p2.getInventory().setItem(1, golden);
						p.getInventory().setItem(1, golden);
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						// -----------------------------------------------------------
						ItemStack capacete = new ItemStack(Material.DIAMOND_HELMET);
						// -----------------------------------------------------------
						ItemStack colete = new ItemStack(Material.DIAMOND_CHESTPLATE);
						// -----------------------------------------------------------
						ItemStack calca = new ItemStack(Material.DIAMOND_LEGGINGS);
						// -----------------------------------------------------------
						ItemStack botas = new ItemStack(Material.DIAMOND_BOOTS);
						// -----------------------------------------------------------
						p2.getInventory().setHelmet(capacete);
						p2.getInventory().setChestplate(colete);
						p2.getInventory().setLeggings(calca);
						p2.getInventory().setBoots(botas);

						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(colete);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(botas);
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					randomDC = id;
				}

				return;
			}
		}
	}
}
