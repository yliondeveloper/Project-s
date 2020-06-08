package xyz.starmc.hologram;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.world.*;

public class HologramListeners implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		for (Hologram h : HologramAPI.getHolograms()) {
			if (h.isSpawned() && h.getLocation().getWorld().getName().equals(e.getTo().getWorld().getName())) {
				try {
					HologramAPI.spawn(h, new LinkedList<Player>(Collections.singletonList(p)));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onWorldChange(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		for (Hologram h : HologramAPI.getHolograms()) {
			if (h.isSpawned() && h.getLocation().getWorld().getName().equals(p.getWorld().getName())) {
				try {
					HologramAPI.spawn(h, new LinkedList<Player>(Collections.singletonList(p)));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChunkLoad(ChunkLoadEvent e) {
		for (Hologram h : HologramAPI.getHolograms()) {
			if (h.isSpawned() && h.getLocation().getChunk().equals(e.getChunk())) {
				try {
					HologramAPI.spawn(h, h.getLocation().getWorld().getPlayers());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
