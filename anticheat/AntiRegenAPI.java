package xyz.starmc.anticheat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;
import xyz.starmc.system.LagAPI;

public class AntiRegenAPI implements Listener {

	public static Map<String, Integer> ReportCache = new HashMap<String, Integer>();
	public static List<String> CacheReport = new ArrayList<String>();
	public static Map<UUID, Long> LastHeal = new HashMap<UUID, Long>();
	public static Map<UUID, Map.Entry<Integer, Long>> FastHealTicks = new HashMap<UUID, Map.Entry<Integer, Long>>();

	public boolean checkFastHeal(Player p) {
		if (LastHeal.containsKey(p.getUniqueId())) {
			final long l = LastHeal.get(p.getUniqueId());
			LastHeal.remove(p.getUniqueId());
			if (System.currentTimeMillis() - l < 3000L) {
				return true;
			}
		}
		return false;
	}

	public boolean elapse(long from, long required) {
		return System.currentTimeMillis() - from > required;
	}

	public boolean isStair(Block b) {
		Material type = b.getType();
		switch (type) {
		case COMMAND:
		case COBBLESTONE_STAIRS:
		case BRICK_STAIRS:
		case ACACIA_STAIRS:
		case BIRCH_WOOD_STAIRS:
		case DARK_OAK_STAIRS:
		case JUNGLE_WOOD_STAIRS:
		case NETHER_BRICK_STAIRS:
		case QUARTZ_STAIRS:
		case SANDSTONE_STAIRS:
		case SMOOTH_STAIRS:
		case SPRUCE_WOOD_STAIRS:
		case WOOD_STAIRS: {
			return true;
		}
		default: {
			return false;
		}
		}
	}

	public boolean isSlab(Block b) {
		Material type = b.getType();
		switch (type) {
		case STEP:
		case WOOD_STEP: {
			return true;
		}
		default: {
			return false;
		}
		}
	}

	public boolean PartiallyStuck(Player p) {
		if (p.getLocation().clone().getBlock() == null) {
			return false;
		}
		Block block = p.getLocation().clone().getBlock();
		return !isSlab(block) && !isStair(block)
				&& (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()
						|| p.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid()
						|| (p.getLocation().clone().add(0.0, 1.0, 0.0).getBlock().getRelative(BlockFace.DOWN).getType()
								.isSolid()
								|| p.getLocation().clone().add(0.0, 1.0, 0.0).getBlock().getRelative(BlockFace.UP)
										.getType().isSolid())
						|| block.getType().isSolid());
	}

	public static boolean FullStuck(Player p) {
		Block block1 = p.getLocation().clone().getBlock();
		Block block2 = p.getLocation().clone().add(0.0, 1.0, 0.0).getBlock();
		return (block1.getType().isSolid() && block2.getType().isSolid())
				|| (block1.getRelative(BlockFace.DOWN).getType().isSolid()
						|| (block1.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid()
								&& block2.getRelative(BlockFace.DOWN).getType().isSolid())
						|| block2.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid());
	}

	public long newSistem() {
		return System.currentTimeMillis();
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onHeal(EntityRegainHealthEvent e) {
		if (!e.getRegainReason().equals((Object) EntityRegainHealthEvent.RegainReason.SATIATED)
				|| !(e.getEntity() instanceof Player)) {
			return;
		}
		if (!AntiCheatAPI.AntiCheat.contains(e.getEntity().getName())) {
			return;
		}
		if (CacheReport.contains(e.getEntity().getName())) {
			return;
		}
		if (LagAPI.Tps < 19.30) {
			return;
		}
		Player p = (Player) e.getEntity();
		int Count = 0;
		long Time = System.currentTimeMillis();
		if (FastHealTicks.containsKey(p.getUniqueId())) {
			Count = FastHealTicks.get(p.getUniqueId()).getKey();
			Time = FastHealTicks.get(p.getUniqueId()).getValue();
		}
		if (checkFastHeal(p) && !FullStuck(p) && !PartiallyStuck(p)) {
			++Count;
		} else {
			Count = ((Count > 0) ? (Count - 1) : Count);
		}
		if (Count > 2) {
			if (!ReportCache.containsKey(p.getName())) {
				ReportCache.put(p.getName(), 1);
			} else {
				ReportCache.replace(p.getName(), (ReportCache.get(p.getName()) + 1));
			}
			CacheReport.add(e.getEntity().getName());
			new BukkitRunnable() {
				public void run() {
					CacheReport.remove(e.getEntity().getName());
				}
			}.runTaskLater(Main.getPlugin(), 20 * 20);
			if (ReportCache.get(p.getName()) >= 2) {
				AntiCheatAPI.addReport(p);
				if (!AntiCheatAPI.Reports.containsKey(p.getName())) {
					for (Player CMC : Bukkit.getOnlinePlayers()) {
						if (CMC.hasPermission("gladiator.admin")) {
							CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
									+ " §festa supostamente de §eRegen §fReports §e1");
						}
					}
				} else {
					for (Player CMC : Bukkit.getOnlinePlayers()) {
						if (CMC.hasPermission("gladiator.admin")) {
							CMC.sendMessage("§c§lATC §fO jogador §e" + p.getName()
									+ " §festa supostamente de §eRegen §fReports §e"
									+ AntiCheatAPI.Reports.get(p.getName()));
						}
					}
				}
			}
		}
		if (FastHealTicks.containsKey(p.getUniqueId()) && elapse(Time, 60000L)) {
			Count = 0;
			Time = newSistem();
		}
		LastHeal.put(p.getUniqueId(), System.currentTimeMillis());
		FastHealTicks.put(p.getUniqueId(), new AbstractMap.SimpleEntry<Integer, Long>(Count, Time));
	}

}
