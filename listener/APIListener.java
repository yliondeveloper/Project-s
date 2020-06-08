package xyz.starmc.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import protocolsupport.api.ProtocolSupportAPI;
import xyz.starmc.clan.ClanCommand;
import xyz.starmc.commands.CageCMD;
import xyz.starmc.commands.EnsinarCMD;
import xyz.starmc.commands.ScoreCMD;
import xyz.starmc.commands.VanishCMD;
import xyz.starmc.gui.DiamondRanked;
import xyz.starmc.gui.DiamondUnRanked;
import xyz.starmc.gui.IronRanked;
import xyz.starmc.gui.IronUnRanked;
import xyz.starmc.gui.NullRanked;
import xyz.starmc.gui.NullUnRanked;
import xyz.starmc.gui.SelectionClassic;
import xyz.starmc.score.TeamScore;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLXp;
import xyz.starmc.system.FlagProtection;
import xyz.starmc.system.SystemAPI;

public class APIListener {

	public static void APISearch(Player p) {
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		// ---------------------------
		ItemStack redstone = new ItemStack(Material.REDSTONE);
		ItemMeta redstonee = redstone.getItemMeta();
		redstonee.setDisplayName("§eSair §7(§eClique§7)");
		redstone.setItemMeta(redstonee);
		redstone.setAmount(1);
		// ---------------------------
		p.getInventory().setItem(4, redstone);
		p.sendMessage("§e§lSEARCH §fAguardando um oponente entrar na mesma fila...");
	}

	public static void APIJoin(Player p) {
		p.setMaxHealth(20.0D);
		p.setHealth(20.0D);
		p.getInventory().clear();
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.getActivePotionEffects().clear();
		p.getInventory().setArmorContents(null);
		FlagProtection.setProtection(p, false);
		for (Player todos : Listener.RunningParty) {
			todos.hidePlayer(p);
		}
		for (Player s : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(s);
		}
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (VanishCMD.emAdmin.contains(todos)) {
				p.hidePlayer(todos);
			}
		}
	}

	public static void APITeleport(Player p, Player p2) {
		Random gerador = new Random();
		int carect = gerador.nextInt(4);
		if (carect == 0) {
			p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 10015.73381, (double) 22.060,
					(double) 9995));
			p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 9983.31935, (double) 22.060,
					(double) 9924.2558240248955));
		} else {
			if (carect == 1) {
				p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 8905.13783, (double) 21.060,
						(double) 8884.00344));
				p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 8871.98767, (double) 21.060,
						(double) 8813.64294));
			} else {
				if (carect == 2) {
					p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 7761.38273, (double) 13.021,
							(double) 77703.88397));
					p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 7791.14615,
							(double) 13.424, (double) 77772.67957));
				} else {
					if (carect == 3) {
						p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 3424.41374164630577,
								(double) 72.0, (double) 1794.2558240248955));
						p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 3424.41374164630577,
								(double) 72.0, (double) 1740.2558240248955));
					} else {
						if (carect == 4) {
							p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) 3983.41374164630577,
									(double) 84.0, (double) 1885.2558240248955));
							p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"),
									(double) 3983.41374164630577, (double) 84.0, (double) 1831.2558240248955));
						}
					}
				}
			}
		}
	}

	public static void deathSumo(Player p) {
		Player target = Bukkit.getPlayer(Listener.Sumo.get(p.getName()));
		FlagProtection.setProtection(p, true);
		FlagProtection.setProtection(target, true);
		if (!Listener.Spawn.contains(target.getName())) {
			Listener.Spawn.add(target.getName());
		}
		if (!Listener.Spawn.contains(p.getName())) {
			Listener.Spawn.add(p.getName());
		}
		if (Listener.RunningParty.contains(target)) {
			Listener.RunningParty.remove(target);
		}
		if (Listener.RunningParty.contains(p)) {
			Listener.RunningParty.remove(p);
		}

		target.sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a" + p.getName() + " §fno modo §7Sumo§f!");
		p.sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c" + Listener.Sumo.get(p.getName())
				+ " §fno modo §7Sumo§f!");
		SQLXp.cachexp.replace(target.getName(), SQLXp.cachexp.get(target.getName()) + 2);
		SQLXp.cachexp.replace(p.getName(), SQLXp.cachexp.get(p.getName()) - 1);
		TeamScore.updateScore(target);
		TeamScore.updateScore(p);
		for (Player todos : Bukkit.getOnlinePlayers()) {
			p.showPlayer(todos);
			target.showPlayer(todos);
		}
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setHealth(20);
		p.teleport(p.getWorld().getSpawnLocation());
		target.teleport(target.getWorld().getSpawnLocation());
		target.getInventory().setArmorContents(null);
		target.getInventory().clear();
		target.setFoodLevel(20);
		target.setHealth(20);
		target.teleport(target.getWorld().getSpawnLocation());
		target.getActivePotionEffects().forEach(potionEffect -> target.removePotionEffect(potionEffect.getType()));

		ItemStack ClassicMode = new ItemStack(Material.GOLD_SWORD);
		ItemMeta ClassicMode2 = ClassicMode.getItemMeta();
		ClassicMode2.setDisplayName("§eClassic Modes (§7Unranked§e)");
		ClassicMode.setItemMeta(ClassicMode2);

		ItemStack SelectionUnranked = new ItemStack(Material.IRON_SWORD);
		ItemMeta SelectionUnranked2 = SelectionUnranked.getItemMeta();
		SelectionUnranked2.setDisplayName("§ePotPvP - §7(§eUnranked§7)");
		SelectionUnranked.setItemMeta(SelectionUnranked2);

		ItemStack SelectionRanked = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta SelectionRanked2 = SelectionRanked.getItemMeta();
		SelectionRanked2.setDisplayName("§ePotPvP - §7(§eRanked§7)");
		SelectionRanked.setItemMeta(SelectionRanked2);

		ItemStack SelectionSumo = new ItemStack(Material.APPLE);
		ItemMeta SelectionSumo2 = SelectionSumo.getItemMeta();
		SelectionSumo2.setDisplayName("§eSumo - §7(§eClique§7)");
		SelectionSumo.setItemMeta(SelectionSumo2);

		ItemStack SelectionExtra = new ItemStack(Material.EMERALD);
		ItemMeta SelectionExtra2 = SelectionExtra.getItemMeta();
		SelectionExtra2.setDisplayName("§fExtra (§aClique§f)");
		SelectionExtra.setItemMeta(SelectionExtra2);

		target.getInventory().setItem(0, SelectionUnranked);
		target.getInventory().setItem(1, SelectionRanked);
		target.getInventory().setItem(3, SelectionSumo);
		target.getInventory().setItem(2, ClassicMode);
		target.getInventory().setItem(8, SelectionExtra);
		p.getInventory().setItem(0, SelectionUnranked);
		p.getInventory().setItem(1, SelectionRanked);
		p.getInventory().setItem(3, SelectionSumo);
		p.getInventory().setItem(2, ClassicMode);
		p.getInventory().setItem(8, SelectionExtra);
		Listener.Sumo.remove(target.getName());
		Listener.Sumo.remove(p.getName());

	}

	public static void quitPlayer(Player p) {
		if (ClanCommand.convite.containsKey(p)) {
			ClanCommand.convite.remove(p);
		}
		if (EnsinarCMD.pergunta.containsKey(p)) {
			EnsinarCMD.pergunta.remove(p);
		}
		if (EnsinarCMD.registerbot.remove(p)) {
			EnsinarCMD.registerbot.remove(p);
		}
		if (Listener.Spawn.contains(p.getName())) {
			Listener.Spawn.remove(p.getName());
		}
		if (ScoreCMD.Scoreboard.contains(p.getName())) {
			ScoreCMD.Scoreboard.remove(p.getName());
		}
		if (CageCMD.cage.containsKey(p)) {
			if (!p.hasPermission("potpvp.admin")) {
				CageCMD.cage.get(p).sendMessage(
						"\n§e§lCAGE §fO jogador §e" + p.getName() + " §fdeslogou, agora você pode bani-lo!");
				CageCMD.cage.get(p).sendMessage("");
			}
			CageCMD.cage.remove(p);
		}
		if (Listener.RunningParty.contains(p)) {
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.showPlayer(p);
				p.showPlayer(todos);
			}
			Listener.RunningParty.remove(p);
		}
		Player target;
		if (Listener.Sumo.containsKey(p.getName())) {
			target = Bukkit.getPlayer(Listener.Sumo.get(p.getName()));
			if (Listener.RunningParty.contains(target)) {
				Listener.RunningParty.remove(target);
			}
			if (Listener.RunningParty.contains(p)) {
				Listener.RunningParty.remove(p);
			}
			target.sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a" + p.getName() + " §fno modo §7Sumo§f!");
			SQLCache.addVictorys(target);
			SQLCache.addDeaths(p);
			SQLXp.cachexp.replace(target.getName(), SQLXp.cachexp.get(target.getName()) + 2);
			SQLXp.cachexp.replace(p.getName(), SQLXp.cachexp.get(p.getName()) - 1);
			
			Listener.Sumo.remove(target.getName());
			Listener.Sumo.remove(p.getName());
			target.teleport(target.getWorld().getSpawnLocation());
			target.getInventory().setArmorContents(null);
			target.getInventory().clear();
			target.setFoodLevel(20);
			target.setHealth(20);
			target.teleport(target.getWorld().getSpawnLocation());
			target.getActivePotionEffects().forEach(potionEffect -> target.removePotionEffect(potionEffect.getType()));

			ItemStack ClassicMode = new ItemStack(Material.GOLD_SWORD);
			ItemMeta ClassicMode2 = ClassicMode.getItemMeta();
			ClassicMode2.setDisplayName("§eClassic Modes (§7Unranked§e)");
			ClassicMode.setItemMeta(ClassicMode2);

			ItemStack SelectionUnranked = new ItemStack(Material.IRON_SWORD);
			ItemMeta SelectionUnranked2 = SelectionUnranked.getItemMeta();
			SelectionUnranked2.setDisplayName("§ePotPvP - §7(§eUnranked§7)");
			SelectionUnranked.setItemMeta(SelectionUnranked2);

			ItemStack SelectionRanked = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta SelectionRanked2 = SelectionRanked.getItemMeta();
			SelectionRanked2.setDisplayName("§ePotPvP - §7(§eRanked§7)");
			SelectionRanked.setItemMeta(SelectionRanked2);

			ItemStack SelectionSumo = new ItemStack(Material.APPLE);
			ItemMeta SelectionSumo2 = SelectionSumo.getItemMeta();
			SelectionSumo2.setDisplayName("§eSumo - §7(§eClique§7)");
			SelectionSumo.setItemMeta(SelectionSumo2);

			ItemStack SelectionExtra = new ItemStack(Material.EMERALD);
			ItemMeta SelectionExtra2 = SelectionExtra.getItemMeta();
			SelectionExtra2.setDisplayName("§fExtra (§aClique§f)");
			SelectionExtra.setItemMeta(SelectionExtra2);

			target.getInventory().setItem(0, SelectionUnranked);
			target.getInventory().setItem(1, SelectionRanked);
			target.getInventory().setItem(3, SelectionSumo);
			target.getInventory().setItem(2, ClassicMode);
			target.getInventory().setItem(8, SelectionExtra);
			TeamScore.updateScore(target);
			for (Player todos : Bukkit.getOnlinePlayers()) {
				todos.showPlayer(target);
				target.showPlayer(todos);
			}
			return;
		}
		if (IronUnRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(IronUnRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			IronUnRanked.party.remove(p.getName());
			return;
		}
		if (DiamondUnRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(DiamondUnRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			DiamondUnRanked.party.remove(p.getName());
			return;
		}
		if (NullUnRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(NullUnRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			NullUnRanked.party.remove(p.getName());
			return;
		}
		if (IronRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(IronRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			IronRanked.party.remove(p.getName());
			return;
		}
		if (DiamondRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(DiamondRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			DiamondRanked.party.remove(p.getName());
			return;
		}
		if (SelectionClassic.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(SelectionClassic.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			SelectionClassic.party.remove(p.getName());
			return;
		}
		if (NullRanked.party.containsKey(p.getName())) {
			target = Bukkit.getPlayer(NullRanked.party.get(p.getName()));
			p.setHealth(1.0D);
			p.damage(999999.0D, target);
			NullRanked.party.remove(p.getName());
			return;
		}
	}

	public static void joinEvent(Player p) {
		FlagProtection.setProtection(p, true);
		p.setMaximumNoDamageTicks(20);
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.setFoodLevel(20);
		p.setHealth(20);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		SystemAPI.onTabSenderPacket(p, "\n§e§lSTAR§f§lMC§f -§7 Rede de Servidores\n",
				"\n§eWebsite: §7mc-starpvp.com.br \n §eStore:§7 loja.starpvp.com.br\n §eDiscord:§7 https://discord.me/starmcofc \n");

		int id = ProtocolSupportAPI.getProtocolVersion(p).getId();
		if (id == 5 | id == 4 | id == 3) {
			p.setGameMode(GameMode.SURVIVAL);
		} else {
			p.setGameMode(GameMode.ADVENTURE);
		}
		ItemStack ClassicMode = new ItemStack(Material.GOLD_SWORD);
		ItemMeta ClassicMode2 = ClassicMode.getItemMeta();
		ClassicMode2.setDisplayName("§eClassic Modes (§7Unranked§e)");
		ClassicMode.setItemMeta(ClassicMode2);

		ItemStack SelectionUnranked = new ItemStack(Material.IRON_SWORD);
		ItemMeta SelectionUnranked2 = SelectionUnranked.getItemMeta();
		SelectionUnranked2.setDisplayName("§ePotPvP - §7(§eUnranked§7)");
		SelectionUnranked.setItemMeta(SelectionUnranked2);

		ItemStack SelectionRanked = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta SelectionRanked2 = SelectionRanked.getItemMeta();
		SelectionRanked2.setDisplayName("§ePotPvP - §7(§eRanked§7)");
		SelectionRanked.setItemMeta(SelectionRanked2);

		ItemStack SelectionSumo = new ItemStack(Material.APPLE);
		ItemMeta SelectionSumo2 = SelectionSumo.getItemMeta();
		SelectionSumo2.setDisplayName("§eSumo - §7(§eClique§7)");
		SelectionSumo.setItemMeta(SelectionSumo2);

		ItemStack SelectionExtra = new ItemStack(Material.EMERALD);
		ItemMeta SelectionExtra2 = SelectionExtra.getItemMeta();
		SelectionExtra2.setDisplayName("§fExtra (§aClique§f)");
		SelectionExtra.setItemMeta(SelectionExtra2);

		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setHealth(20);
		p.getInventory().setItem(0, SelectionUnranked);
		p.getInventory().setItem(1, SelectionRanked);
		p.getInventory().setItem(3, SelectionSumo);
		p.getInventory().setItem(2, ClassicMode);
		p.getInventory().setItem(8, SelectionExtra);
		p.setFireTicks(0);
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.teleport(p.getWorld().getSpawnLocation());
		p.updateInventory();
		for (Player todos : Listener.RunningParty) {
			todos.hidePlayer(p);
		}
		if (!Listener.Spawn.contains(p.getName())) {
			Listener.Spawn.add(p.getName());
		}

		if (!p.hasPermission("potpvp.admin")) {
			for (Player todos : Bukkit.getOnlinePlayers()) {
				if (VanishCMD.emAdmin.contains(todos)) {
					p.hidePlayer(todos);
				}
			}
		}
	}

	public static void getInventory(Player p) {
		ItemStack ClassicMode = new ItemStack(Material.GOLD_SWORD);
		ItemMeta ClassicMode2 = ClassicMode.getItemMeta();
		ClassicMode2.setDisplayName("§eClassic Modes (§7Unranked§e)");
		ClassicMode.setItemMeta(ClassicMode2);

		ItemStack SelectionUnranked = new ItemStack(Material.IRON_SWORD);
		ItemMeta SelectionUnranked2 = SelectionUnranked.getItemMeta();
		SelectionUnranked2.setDisplayName("§ePotPvP - §7(§eUnranked§7)");
		SelectionUnranked.setItemMeta(SelectionUnranked2);

		ItemStack SelectionRanked = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta SelectionRanked2 = SelectionRanked.getItemMeta();
		SelectionRanked2.setDisplayName("§ePotPvP - §7(§eRanked§7)");
		SelectionRanked.setItemMeta(SelectionRanked2);

		ItemStack SelectionSumo = new ItemStack(Material.APPLE);
		ItemMeta SelectionSumo2 = SelectionSumo.getItemMeta();
		SelectionSumo2.setDisplayName("§eSumo - §7(§eClique§7)");
		SelectionSumo.setItemMeta(SelectionSumo2);

		ItemStack SelectionExtra = new ItemStack(Material.EMERALD);
		ItemMeta SelectionExtra2 = SelectionExtra.getItemMeta();
		SelectionExtra2.setDisplayName("§fExtra (§aClique§f)");
		SelectionExtra.setItemMeta(SelectionExtra2);

		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setHealth(20);
		p.getInventory().setItem(0, SelectionUnranked);
		p.getInventory().setItem(1, SelectionRanked);
		p.getInventory().setItem(3, SelectionSumo);
		p.getInventory().setItem(2, ClassicMode);
		p.getInventory().setItem(8, SelectionExtra);
		p.setFireTicks(0);
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
	}

	public static void removeCache(Player p) {
		if (Listener.sumorandom == p.getUniqueId()) {
			Listener.sumorandom = null;
		}
		if (IronUnRanked.randomFB == p.getUniqueId()) {
			IronUnRanked.randomFB = null;
		}
		if (IronUnRanked.randomFC == p.getUniqueId()) {
			IronUnRanked.randomFC = null;
		}
		if (IronUnRanked.randomFN == p.getUniqueId()) {
			IronUnRanked.randomFN = null;
		}
		if (NullUnRanked.randomB == p.getUniqueId()) {
			NullUnRanked.randomB = null;
		}
		if (NullUnRanked.randomC == p.getUniqueId()) {
			NullUnRanked.randomC = null;
		}
		if (NullUnRanked.randomN == p.getUniqueId()) {
			NullUnRanked.randomN = null;
		}

		if (DiamondUnRanked.randomDB == p.getUniqueId()) {
			DiamondUnRanked.randomDB = null;
		}
		if (DiamondUnRanked.randomDC == p.getUniqueId()) {
			DiamondUnRanked.randomDC = null;
		}
		if (DiamondUnRanked.randomDN == p.getUniqueId()) {
			DiamondUnRanked.randomDN = null;
		}
		if (SelectionClassic.customBuild == p.getUniqueId()) {
			SelectionClassic.customBuild = null;
		}
		if (SelectionClassic.customArcher == p.getUniqueId()) {
			SelectionClassic.customArcher = null;
		}
		if (SelectionClassic.customGapple == p.getUniqueId()) {
			SelectionClassic.customGapple = null;
		}
		if (SelectionClassic.customBuild == p.getUniqueId()) {
			SelectionClassic.customBuild = null;
		}
		if (IronRanked.randomFB == p.getUniqueId()) {
			IronRanked.randomFB = null;
		}
		if (IronRanked.randomFC == p.getUniqueId()) {
			IronRanked.randomFC = null;
		}
		if (IronRanked.randomFN == p.getUniqueId()) {
			IronRanked.randomFN = null;
		}
		if (NullRanked.randomB == p.getUniqueId()) {
			NullRanked.randomB = null;
		}
		if (NullRanked.randomC == p.getUniqueId()) {
			NullRanked.randomC = null;
		}
		if (NullRanked.randomN == p.getUniqueId()) {
			NullRanked.randomN = null;
		}
		if (DiamondRanked.randomDB == p.getUniqueId()) {
			DiamondRanked.randomDB = null;
		}
		if (DiamondRanked.randomDC == p.getUniqueId()) {
			DiamondRanked.randomDC = null;
		}
		if (DiamondRanked.randomDN == p.getUniqueId()) {
			DiamondRanked.randomDN = null;
		}
	}

	public static void removePlayer(Player p) {
		if (DiamondRanked.party.containsKey(p.getName())) {
			DiamondRanked.party.remove(p.getName());
		}
		if (SelectionClassic.party.containsKey(p.getName())) {
			SelectionClassic.party.remove(p.getName());
		}
		if (DiamondUnRanked.party.containsKey(p.getName())) {
			DiamondUnRanked.party.remove(p.getName());
		}
		if (IronRanked.party.containsKey(p.getName())) {
			IronRanked.party.remove(p.getName());
		}
		if (IronUnRanked.party.containsKey(p.getName())) {
			IronUnRanked.party.remove(p.getName());
		}
		if (NullRanked.party.containsKey(p.getName())) {
			NullRanked.party.remove(p.getName());
		}
		if (NullUnRanked.party.containsKey(p.getName())) {
			NullUnRanked.party.remove(p.getName());
		}
		if (Listener.Sumo.containsKey(p.getName())) {
			Listener.Sumo.remove(p.getName());
		}
	}

	public static void teleportPlayer(Player p) {
		p.setMaxHealth(20.0D);
		p.setHealth(20.0D);
		p.setMaximumNoDamageTicks(20);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getActivePotionEffects().clear();
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		p.updateInventory();
		FlagProtection.setProtection(p, false);
	}

}
