package xyz.starmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.commands.VanishCMD;
import xyz.starmc.gui.DiamondRanked;
import xyz.starmc.gui.DiamondUnRanked;
import xyz.starmc.gui.IronRanked;
import xyz.starmc.gui.IronUnRanked;
import xyz.starmc.gui.NullRanked;
import xyz.starmc.gui.NullUnRanked;
import xyz.starmc.gui.SelectionClassic;
import xyz.starmc.main.Main;
import xyz.starmc.score.TeamScore;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLXp;
import xyz.starmc.system.FlagProtection;

public class Death implements Listener {

	public static void getSpawn(Player p) {
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
		p.sendMessage("§e§lPOTPVP §fTeleportando ao spawn...");
		p.teleport(p.getWorld().getSpawnLocation());
		p.setFireTicks(0);
		p.sendMessage("§a§lPOTPVP §fVocê foi teleportado ao spawn.");
		FlagProtection.setProtection(p, true);
		p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
		if (!xyz.starmc.listener.Listener.Spawn.contains(p.getName())) {
			xyz.starmc.listener.Listener.Spawn.add(p.getName());
		}
	}

	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		e.getDrops().clear();
		e.getEntity().setAllowFlight(false);
		e.getEntity().setFlying(false);
		if (e.getEntity().getKiller() instanceof Player) {
			if (xyz.starmc.listener.Listener.RunningParty.contains(e.getEntity().getKiller())) {
				xyz.starmc.listener.Listener.RunningParty.remove(e.getEntity().getKiller());
			}
			if (xyz.starmc.listener.Listener.RunningParty.contains(e.getEntity())) {
				xyz.starmc.listener.Listener.RunningParty.remove(e.getEntity());
			}
			for (Player s : Bukkit.getOnlinePlayers()) {
				e.getEntity().showPlayer(s);
				e.getEntity().getKiller().showPlayer(s);
			}
			if (xyz.starmc.listener.Listener.RunningParty.contains(e.getEntity())) {
				for (Player todos : Bukkit.getOnlinePlayers()) {
					todos.showPlayer(e.getEntity());
					e.getEntity().showPlayer(todos);
				}
			}
			for (Player todos : Bukkit.getOnlinePlayers()) {
				if (VanishCMD.emAdmin.contains(todos)) {
					if (!e.getEntity().hasPermission("potpvp.admin")) {
						e.getEntity().hidePlayer(todos);
					}
					if (!e.getEntity().getKiller().hasPermission("potpvp.admin")) {
						e.getEntity().getKiller().hidePlayer(todos);
					}

				}
			}
			if (DiamondRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& DiamondRanked.party.containsKey(e.getEntity().getName())) {
				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Full-Diamond §7(§eRanqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7Full-Diamond §7(§eRanqueado§7)§f!");
				SQLXp.cachexp.replace(e.getEntity().getKiller().getName(),
						SQLXp.cachexp.get(e.getEntity().getKiller().getName()) + 8);
				SQLCache.addVictorys(e.getEntity().getKiller());
				SQLCache.addDeaths(e.getEntity());
				SQLXp.cachexp.replace(e.getEntity().getName(), SQLXp.cachexp.get(e.getEntity().getName()) - 4);

				DiamondRanked.party.remove(e.getEntity().getKiller().getName());
				DiamondRanked.party.remove(e.getEntity().getName());
			}
			if (NullUnRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& NullUnRanked.party.containsKey(e.getEntity().getName())) {

				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Default §7(§eNão Ranqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7Default §7(§eNão Ranqueado§7)§f!");

				NullUnRanked.party.remove(e.getEntity().getKiller().getName());
				NullUnRanked.party.remove(e.getEntity().getName());
			}

			if (SelectionClassic.party.containsKey(e.getEntity().getKiller().getName())
					&& SelectionClassic.party.containsKey(e.getEntity().getName())) {

				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7ClassicMode §7(§eNão Ranqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7ClassicMode §7(§eNão Ranqueado§7)§f!");

				SelectionClassic.party.remove(e.getEntity().getKiller().getName());
				SelectionClassic.party.remove(e.getEntity().getName());
			}

			if (IronUnRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& IronUnRanked.party.containsKey(e.getEntity().getName())) {
				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Full-Iron §7(§eNão Ranqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7Full-Iron §7(§eNão Ranqueado§7)§f!");
				IronUnRanked.party.remove(e.getEntity().getName());
				IronUnRanked.party.remove(e.getEntity().getKiller().getName());
			}

			if (DiamondUnRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& DiamondUnRanked.party.containsKey(e.getEntity().getName())) {
				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Full-Diamond §7(§eNão Ranqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + "§fno modo §7Full-Diamond §7(§eNão Ranqueado§7)§f!");
				DiamondUnRanked.party.remove(e.getEntity().getKiller().getName());
				DiamondUnRanked.party.remove(e.getEntity().getName());
			}

			if (NullRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& NullRanked.party.containsKey(e.getEntity().getName())) {
				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Default §7(§eRanqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7Default §7(§eRanqueado§7)§f!");

				SQLCache.addVictorys(e.getEntity().getKiller());
				SQLCache.addDeaths(e.getEntity());

				SQLXp.cachexp.replace(e.getEntity().getKiller().getName(),
						SQLXp.cachexp.get(e.getEntity().getKiller().getName()) + 3);
				SQLXp.cachexp.replace(e.getEntity().getName(), SQLXp.cachexp.get(e.getEntity().getName()) - 1);
				NullRanked.party.remove(e.getEntity().getKiller().getName());
				NullRanked.party.remove(e.getEntity().getName());
			}

			if (IronRanked.party.containsKey(e.getEntity().getKiller().getName())
					&& IronRanked.party.containsKey(e.getEntity().getName())) {
				e.getEntity().getKiller().sendMessage("§a§lPOTPVP §fVocê venceu a partida contra §a"
						+ e.getEntity().getName() + " §fno modo §7Full-Iron §7(§eRanqueado§7)§f!");
				e.getEntity().sendMessage("§c§lPOTPVP §fVocê perdeu a partida contra §c"
						+ e.getEntity().getKiller().getName() + " §fno modo §7Full-Iron §7(§eRanqueado§7)§f!");

				SQLCache.addVictorys(e.getEntity().getKiller());
				SQLCache.addDeaths(e.getEntity());

				SQLXp.cachexp.replace(e.getEntity().getKiller().getName(),
						SQLXp.cachexp.get(e.getEntity().getKiller().getName()) + 5);
				SQLXp.cachexp.replace(e.getEntity().getName(), SQLXp.cachexp.get(e.getEntity().getName()) - 2);
				IronRanked.party.remove(e.getEntity().getName());
				IronRanked.party.remove(e.getEntity().getKiller().getName());
			}

			getSpawn(e.getEntity().getKiller());
			TeamScore.updateScore(e.getEntity().getKiller());
		}
	}

	@EventHandler
	public void RespawnEvent(PlayerRespawnEvent e) {
		new BukkitRunnable() {
			public void run() {
				getSpawn(e.getPlayer());
				TeamScore.updateScore(e.getPlayer());
			}
		}.runTask(Main.getPlugin());
	}

	@EventHandler
	public void respawnar(PlayerDeathEvent e) {
		new BukkitRunnable() {
			public void run() {
				e.getEntity().spigot().respawn();
			}
		}.runTask(Main.getInstance());
	}

}
