package xyz.starmc.anticheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;

public class AntiMacroAPI implements Listener {

	public static List<Player> Macro = new ArrayList<Player>();
	public static Map<String, Integer> Clicks = new HashMap<String, Integer>();

	public static void MacroStart(Player p) {
		if (AntiCheatAPI.AntiCheat.contains(p.getName()) && !AntiCheatAPI.Checking.contains(p.getName())) {
			if (!Macro.contains(p)) {
				Macro.add(p);
				Clicks.put(p.getName(), 0);
				new BukkitRunnable() {
					public void run() {
						int cps = Clicks.get(p.getName()) / 10;
						int calc = Clicks.get(p.getName());
						if (AntiCheatAPI.Checking.contains(p.getName())) {
							AntiCheatAPI.Checking.remove(p.getName());
						}
						Macro.remove(p);
						if (calc >= 260) {
							if (!AntiCheatAPI.Reports.containsKey(p.getName())) {
								RunMacro(p);
								AntiCheatAPI.addReport(p);
							} else {
								AntiCheatAPI.addReport(p);
							}
							if (!AntiCheatAPI.Reports.containsKey(p.getName())) {
								for (Player CMC : Bukkit.getOnlinePlayers()) {
									if (CMC.hasPermission("gladiator.admin")) {
										CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
												+ " §festa supostamente de §eMacro §fCPS §a " + cps + "§f Reports §e1");
									}
								}
							} else {
								for (Player CMC : Bukkit.getOnlinePlayers()) {
									if (CMC.hasPermission("gladiator.admin")) {
										CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
												+ " §festa supostamente de §eMacro §fCPS §a " + cps + "§f Reports §e"
												+ AntiCheatAPI.Reports.get(p.getName()));
									}
								}
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 300L);
			}
		}
	}

	public static void RunMacro(Player p) {
		if (AntiCheatAPI.AntiCheat.contains(p.getName()) && !AntiCheatAPI.Checking.contains(p.getName())) {
			if (!Macro.contains(p)) {
				Macro.add(p);
				Clicks.put(p.getName(), 0);
				new BukkitRunnable() {
					public void run() {
						int cps = Clicks.get(p.getName()) / 10;
						int calc = Clicks.get(p.getName());
						if (AntiCheatAPI.Checking.contains(p.getName())) {
							AntiCheatAPI.Checking.remove(p.getName());
						}
						AntiCheatAPI.addReport(p);
						Macro.remove(p);
						if (calc >= 260) {
							if (!AntiCheatAPI.Reports.containsKey(p.getName())) {
								for (Player CMC : Bukkit.getOnlinePlayers()) {
									if (CMC.hasPermission("gladiator.admin")) {
										CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
												+ " §festa supostamente de §eMacro §fCPS §a " + cps + "§f Reports §e1");
									}
								}
							} else {
								for (Player CMC : Bukkit.getOnlinePlayers()) {
									if (CMC.hasPermission("gladiator.admin")) {
										CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
												+ " §festa supostamente de §eMacro §fCPS §a " + cps + "§f Reports §e"
												+ AntiCheatAPI.Reports.get(p.getName()));
									}
								}
							}
						}
					}
				}.runTaskLater(Main.getInstance(), 300L);
			}
		}
	}

	@EventHandler
	public static void ClickEvent(PlayerInteractEvent e) {
		if (AntiCheatAPI.AntiCheat.contains(e.getPlayer().getName())) {
			if (Macro.contains(e.getPlayer())) {
				Clicks.replace(e.getPlayer().getName(), Clicks.get(e.getPlayer().getName()) + 1);
			}
		}
	}
}
