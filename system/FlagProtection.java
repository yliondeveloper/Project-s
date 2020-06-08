package xyz.starmc.system;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FlagProtection implements Listener {

	public enum Stats {
		OFF, ON;
	}

	private static Map<Player, Stats> flag = new HashMap<Player, Stats>();

	public static boolean getProtection(Player p) {
		if (flag.get(p) == Stats.ON) {
			return true;
		}
		return false;
	}

	@EventHandler
	public void onBlockedAtack(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player && e.getCause() != DamageCause.LAVA) {
			if (getProtection((Player) e.getEntity()))
				e.setCancelled(true);
		}

	}

	@EventHandler
	public void entityDamage(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (getProtection((Player) e.getDamager())) {
				e.setCancelled(true);
			}

		}
	}

	public static void setProtection(Player p, boolean active) {
		if (active) {
			flag.put(p, Stats.ON);
		} else {
			flag.put(p, Stats.OFF);
		}
	}

}
