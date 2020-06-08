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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.starmc.listener.APIListener;
import xyz.starmc.listener.Listener;
import xyz.starmc.system.SystemAPI;

public class SelectionClassic implements org.bukkit.event.Listener {

	public static UUID customBuild = null;
	public static UUID customGapple = null;
	public static UUID customArcher = null;

	public static Map<String, String> party = new HashMap<String, String>();

	@SuppressWarnings("deprecation")
	public static void Debuff(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "§eClassic §7(§aLista§7)");

		ItemStack Nodebuff = new ItemStack(Material.POTION, 1, (short) 16385);
		ItemMeta Nodebufff = Nodebuff.getItemMeta();
		ArrayList<String> Nodebuffff = new ArrayList<>();
		Nodebufff.setDisplayName("§eBuff");
		Nodebuffff.add("");
		if (DiamondUnRanked.randomDN == null) {
			Nodebuffff.add("§a0 §fjogadores na fila.");
		} else {
			Nodebuffff.add("§a1 §fjogador(es) na fila.");
		}
		Nodebuffff.add("");

		Nodebufff.setLore(Nodebuffff);
		Nodebuff.setItemMeta(Nodebufff);

		inv.setItem(0, Nodebuff);

		ItemStack Archer = new ItemStack(Material.BOW);
		ItemMeta Archerr = Archer.getItemMeta();
		ArrayList<String> Archerrr = new ArrayList<>();
		Archerr.setDisplayName("§eArcher");
		Archerrr.add("");
		if (customArcher == null) {
			Archerrr.add("§a0 §fjogadores na fila.");
		} else {
			Archerrr.add("§a1 §fjogador(es) na fila.");
		}
		Archerrr.add("");

		Archerr.setLore(Archerrr);
		Archer.setItemMeta(Archerr);
		inv.setItem(5, Archer);

		ItemStack Gapple = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta Gapplee = Gapple.getItemMeta();
		ArrayList<String> Gappleee = new ArrayList<>();
		Gapplee.setDisplayName("§eGapple");
		Gappleee.add("");
		if (customGapple == null) {
			Gappleee.add("§a0 §fjogadores na fila.");
		} else {
			Gappleee.add("§a1 §fjogador(es) na fila.");
		}
		Gappleee.add("");

		Gapplee.setLore(Gappleee);
		Gapple.setItemMeta(Gapplee);
		inv.setItem(4, Gapple);

		ItemStack Combo = new ItemStack(Material.getMaterial(349), 1);
		ItemMeta Comboo = Combo.getItemMeta();
		Combo.setDurability((short) 3);
		ArrayList<String> Combooo = new ArrayList<>();
		Comboo.setDisplayName("§eCombofly");
		Combooo.add("");
		if (DiamondUnRanked.randomDC == null) {
			Combooo.add("§a0 §fjogadores na fila.");
		} else {
			Combooo.add("§a1 §fjogador(es) na fila.");
		}
		Combooo.add("");

		Comboo.setLore(Combooo);
		Combo.setItemMeta(Comboo);
		inv.setItem(3, Combo);

		ItemStack Lava = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta Lavaa = Lava.getItemMeta();
		ArrayList<String> Lavaaa = new ArrayList<>();
		Lavaa.setDisplayName("§eUHC");
		Lavaaa.add("");
		if (customBuild == null) {
			Lavaaa.add("§a0 §fjogadores na fila.");
		} else {
			Lavaaa.add("§a1 §fjogador(es) na fila.");
		}
		Lavaaa.add("");

		Lavaa.setLore(Lavaaa);
		Lava.setItemMeta(Lavaa);
		inv.setItem(2, Lava);

		ItemStack Debuff = new ItemStack(Material.POTION, 1, (short) 16458);
		ItemMeta Debufff = Debuff.getItemMeta();
		ArrayList<String> Debuffff = new ArrayList<>();
		Debufff.setDisplayName("§eNoDeBuff");
		Debuffff.add("");
		if (DiamondUnRanked.randomDN == null) {
			Debuffff.add("§a0 §fjogadores na fila.");
		} else {
			Debuffff.add("§a1 §fjogador(es) na fila.");
		}
		Debuffff.add("");

		Debufff.setLore(Debuffff);
		Debuff.setItemMeta(Debufff);

		inv.setItem(1, Debuff);

		p.openInventory(inv);
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void InteractInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§eClassic §7(§aLista§7)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {

			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.getWhoClicked().closeInventory();
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eNoDeBuff")) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((DiamondUnRanked.randomDN != null) && (DiamondUnRanked.randomDN != id)) {
					Player p2 = Bukkit.getPlayer(DiamondUnRanked.randomDN);
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
						DiamondUnRanked.party.put(p.getName(), p2.getName());
						DiamondUnRanked.party.put(p2.getName(), p.getName());
						DiamondUnRanked.randomDN = null;

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
					DiamondUnRanked.randomDN = id;
				}
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eBuff")) {

				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.sendMessage("");
				p.setAllowFlight(false);
				p.setFlying(false);

				if ((DiamondUnRanked.randomDB != null) && (DiamondUnRanked.randomDB != id)) {
					Player p2 = Bukkit.getPlayer(DiamondUnRanked.randomDB);
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
						DiamondUnRanked.party.put(p.getName(), p2.getName());
						DiamondUnRanked.party.put(p2.getName(), p.getName());
						DiamondUnRanked.randomDB = null;

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
					DiamondUnRanked.randomDB = id;
				}
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCombofly")) {

				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((DiamondUnRanked.randomDC != null) && (DiamondUnRanked.randomDC != id)) {
					Player p2 = Bukkit.getPlayer(DiamondUnRanked.randomDC);
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
						DiamondUnRanked.party.put(p.getName(), p2.getName());
						DiamondUnRanked.party.put(p2.getName(), p.getName());
						DiamondUnRanked.randomDC = null;

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
					DiamondUnRanked.randomDC = id;
				}
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eGapple")) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((customGapple != null) && (customGapple != id)) {
					Player p2 = Bukkit.getPlayer(customGapple);
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
						customGapple = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
						espadaM.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
						espada.setItemMeta(espadaM);
						// ---------------------------
						ItemStack golden = new ItemStack(Material.GOLDEN_APPLE, 64, (byte) 1);
						ItemMeta goldenn = golden.getItemMeta();
						goldenn.setDisplayName("§6Maçã dourada");
						golden.setAmount(64);
						golden.setItemMeta(goldenn);
						// ---------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ------------------------------
						ItemStack flecha = new ItemStack(Material.ARROW);
						ItemMeta flechaa = flecha.getItemMeta();
						flechaa.setDisplayName("§cFlecha");
						flecha.setItemMeta(flechaa);
						flecha.setAmount(48);
						// ---------------------------
						ItemStack pesca = new ItemStack(Material.FISHING_ROD);
						ItemMeta pescaa = pesca.getItemMeta();
						pesca.setItemMeta(pescaa);
						// --------------------------
						ItemStack arco = new ItemStack(Material.BOW);
						ItemMeta arcoo = arco.getItemMeta();
						arco.setItemMeta(arcoo);
						// --------------------------
						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);
						p.getInventory().setItem(1, golden);
						p2.getInventory().setItem(1, golden);

						ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
						ItemMeta legginss = leggins.getItemMeta();
						legginss.addEnchant(Enchantment.DURABILITY, 3, true);
						legginss.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						leggins.setItemMeta(legginss);

						ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS);
						ItemMeta Bootss = Boots.getItemMeta();
						Bootss.addEnchant(Enchantment.DURABILITY, 3, true);
						Bootss.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						Boots.setItemMeta(Bootss);

						ItemStack chetsplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
						ItemMeta chetsplatee = chetsplate.getItemMeta();
						chetsplatee.addEnchant(Enchantment.DURABILITY, 3, true);
						chetsplatee.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						chetsplate.setItemMeta(chetsplatee);

						ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
						ItemMeta helmett = helmet.getItemMeta();
						helmett.addEnchant(Enchantment.DURABILITY, 3, true);
						helmett.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						helmet.setItemMeta(helmett);

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

						p.getInventory().setItem(2, helmet);
						p2.getInventory().setItem(2, helmet);
						p.getInventory().setItem(3, chetsplate);
						p2.getInventory().setItem(3, chetsplate);
						p.getInventory().setItem(4, leggins);
						p2.getInventory().setItem(4, leggins);
						p.getInventory().setItem(5, Boots);
						p2.getInventory().setItem(5, Boots);

						p.getInventory().setItem(6, speed);
						p2.getInventory().setItem(6, speed);
						p.getInventory().setItem(7, forca);
						p2.getInventory().setItem(7, forca);

						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);

						p.getInventory().setHelmet(helmet);
						p.getInventory().setChestplate(chetsplate);
						p.getInventory().setLeggings(leggins);
						p.getInventory().setBoots(Boots);
						p2.getInventory().setHelmet(helmet);
						p2.getInventory().setChestplate(chetsplate);
						p2.getInventory().setLeggings(leggins);
						p2.getInventory().setBoots(Boots);

						p.updateInventory();
						p2.updateInventory();

						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					customGapple = id;
				}
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eUHC")) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((customBuild != null) && (customBuild != id)) {
					Player p2 = Bukkit.getPlayer(customBuild);
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
						customBuild = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta espadaM = espada.getItemMeta();
						espadaM.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
						espada.setItemMeta(espadaM);
						// --------------------------
						ItemStack maca = new ItemStack(Material.GOLDEN_APPLE);
						ItemMeta macaa = maca.getItemMeta();
						macaa.setDisplayName("§6Maçã dourada");
						maca.setItemMeta(macaa);
						maca.setAmount(7);
						// ------------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ------------------------------
						ItemStack flecha = new ItemStack(Material.ARROW);
						ItemMeta flechaa = flecha.getItemMeta();
						flechaa.setDisplayName("§cFilé");
						flecha.setItemMeta(flechaa);
						flecha.setAmount(48);
						// ---------------------------
						ItemStack pesca = new ItemStack(Material.FISHING_ROD);
						ItemMeta pescaa = pesca.getItemMeta();
						pesca.setItemMeta(pescaa);
						// --------------------------
						ItemStack arco = new ItemStack(Material.BOW);
						ItemMeta arcoo = arco.getItemMeta();
						arco.setItemMeta(arcoo);
						// --------------------------
						p.getInventory().setItem(3, carne);
						p2.getInventory().setItem(3, carne);
						p.getInventory().setItem(4, maca);
						p2.getInventory().setItem(4, maca);
						p.getInventory().setItem(1, pesca);
						p2.getInventory().setItem(1, pesca);
						p.getInventory().setItem(2, arco);
						p2.getInventory().setItem(2, arco);
						p.getInventory().setItem(0, espada);
						p2.getInventory().setItem(0, espada);
						p.getInventory().setItem(8, flecha);
						p2.getInventory().setItem(8, flecha);
						p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					customBuild = id;
				}
				return;

			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eArcher")) {
				UUID id = p.getUniqueId();
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
				p.setAllowFlight(false);
				p.setFlying(false);
				if ((customArcher != null) && (customArcher != id)) {
					Player p2 = Bukkit.getPlayer(customArcher);
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
						customArcher = null;

						p2.setMaximumNoDamageTicks(20);
						p.setMaximumNoDamageTicks(20);

						// ---------------------------
						ItemStack golden = new ItemStack(Material.GOLDEN_APPLE, 16);
						ItemMeta goldenn = golden.getItemMeta();
						goldenn.setDisplayName("§eMaçã dourada");
						golden.setAmount(16);
						golden.setItemMeta(goldenn);
						// ---------------------------
						ItemStack carne = new ItemStack(Material.COOKED_BEEF);
						ItemMeta carnee = carne.getItemMeta();
						carnee.setDisplayName("§cFilé");
						carne.setItemMeta(carnee);
						carne.setAmount(64);
						// ------------------------------
						ItemStack flecha = new ItemStack(Material.ARROW);
						ItemMeta flechaa = flecha.getItemMeta();
						flechaa.setDisplayName("§cFlecha");
						flecha.setItemMeta(flechaa);
						flecha.setAmount(1);
						// ---------------------------
						ItemStack arco = new ItemStack(Material.BOW);
						ItemMeta arcoo = arco.getItemMeta();
						arcoo.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
						arco.setItemMeta(arcoo);
						// --------------------------
						p.getInventory().setItem(0, arco);
						p2.getInventory().setItem(0, arco);
						p.getInventory().setItem(1, golden);
						p2.getInventory().setItem(1, golden);
						p.getInventory().setItem(2, flecha);
						p2.getInventory().setItem(2, flecha);
						p.getInventory().setItem(8, carne);
						p2.getInventory().setItem(8, carne);

						p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
						p2.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
						p2.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
						p2.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
						p2.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
						p.updateInventory();
						p2.updateInventory();
						p.showPlayer(p2);
						p2.showPlayer(p);
					}
				} else {
					APIListener.APISearch(p);
					customArcher = id;
				}
				return;
			}
		}
	}

}
