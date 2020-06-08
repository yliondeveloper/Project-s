package xyz.starmc.anticheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;

public class AntiCheatAPI implements Listener {

	public static List<String> AntiCheat = new ArrayList<String>();
	public static List<String> Checking = new ArrayList<String>();
	public static Map<String, Integer> Reports = new HashMap<String, Integer>();

	public static void SyncAPI() {
		new BukkitRunnable() {
			public void run() {
				if (Bukkit.getOnlinePlayers().size() == 0) {
					return;
				}
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (Reports.containsKey(players.getName())) {
						if (Reports.get(players.getName()) >= 2) {
							AntiMacroAPI.MacroStart(players);
							if (!Checking.contains(players.getName())) {
								Checking.add(players.getName());
							}
						}
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 30);
	}

	@EventHandler
	public static void ConnectionAPI(PlayerJoinEvent e) {
		if (!AntiCheat.contains(e.getPlayer().getName())) {
			AntiCheat.add(e.getPlayer().getName());
			new BukkitRunnable() {
				public void run() {
					if (Bukkit.getPlayer(e.getPlayer().getName()) != null) {
						if (!AntiMacroAPI.Macro.contains(e.getPlayer())) {
							AntiMacroAPI.MacroStart(e.getPlayer());
						}
					} else {
						cancel();
					}
				}
			}.runTaskTimer(Main.getPlugin(), 20 * 20, 20 * 60);
		}
	}

	@EventHandler
	public static void PlayerQuit(PlayerQuitEvent e) {
		if (AntiCheat.contains(e.getPlayer().getName())) {
			AntiCheat.remove(e.getPlayer().getName());
			if (AntiMacroAPI.Macro.contains(e.getPlayer())) {
				AntiMacroAPI.Macro.remove(e.getPlayer());
			}
			if (Checking.contains(e.getPlayer().getName())) {
				Checking.remove(e.getPlayer().getName());
			}
			if (AntiRegenAPI.CacheReport.remove(e.getPlayer().getName())) {
				AntiRegenAPI.CacheReport.remove(e.getPlayer().getName());
			}
			if (AntiRegenAPI.FastHealTicks.containsKey(e.getPlayer().getUniqueId())) {
				AntiRegenAPI.FastHealTicks.remove(e.getPlayer().getUniqueId());
			}
			if (AntiRegenAPI.LastHeal.containsKey(e.getPlayer().getUniqueId())) {
				AntiRegenAPI.LastHeal.remove(e.getPlayer().getUniqueId());
			}
		}
	}

	@EventHandler
	public static void PlayerQuit(PlayerKickEvent e) {
		if (AntiCheat.contains(e.getPlayer().getName())) {
			AntiCheat.remove(e.getPlayer().getName());
			if (AntiMacroAPI.Macro.contains(e.getPlayer())) {
				AntiMacroAPI.Macro.remove(e.getPlayer());
			}
			if (Checking.contains(e.getPlayer().getName())) {
				Checking.remove(e.getPlayer().getName());
			}
			if (AntiRegenAPI.CacheReport.remove(e.getPlayer().getName())) {
				AntiRegenAPI.CacheReport.remove(e.getPlayer().getName());
			}
			if (AntiRegenAPI.FastHealTicks.containsKey(e.getPlayer().getUniqueId())) {
				AntiRegenAPI.FastHealTicks.remove(e.getPlayer().getUniqueId());
			}
			if (AntiRegenAPI.LastHeal.containsKey(e.getPlayer().getUniqueId())) {
				AntiRegenAPI.LastHeal.remove(e.getPlayer().getUniqueId());
			}
		}
	}

	public static void addReport(Player p) {
		if (Reports.containsKey(p.getName())) {
			Reports.replace(p.getName(), (Reports.get(p.getName()) + 1));
		} else {
			Reports.put(p.getName(), 1);
		}
	}
}
