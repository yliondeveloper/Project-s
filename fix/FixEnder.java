package xyz.starmc.fix;

import org.bukkit.event.Listener;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import xyz.starmc.main.Main;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryHolder;

public class FixEnder implements Listener {

	public ImmutableSet<Material> blockedPearlTypes;

	public FixEnder(Main plugin) {
		blockedPearlTypes = Sets.immutableEnumSet(Material.THIN_GLASS,
				new Material[] { Material.IRON_FENCE, Material.FENCE, Material.NETHER_FENCE, Material.FENCE_GATE,
						Material.ACACIA_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.BRICK_STAIRS,
						Material.COBBLESTONE_STAIRS, Material.DARK_OAK_STAIRS, Material.JUNGLE_WOOD_STAIRS,
						Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS, Material.SANDSTONE_STAIRS,
						Material.SMOOTH_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.WOOD_STAIRS });
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType().equals(EntityType.ENDER_PEARL)) {
			e.setDamage(0.0D);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.hasItem())
				&& (e.getItem().getType() == Material.ENDER_PEARL)) {
			if ((e.getClickedBlock().getType().isSolid())
					&& (!(e.getClickedBlock().getState() instanceof InventoryHolder))) {
				e.setCancelled(true);
				e.getPlayer().setItemInHand(e.getItem());
			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPearlClip(PlayerTeleportEvent e) {
		if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
			if (blockedPearlTypes.contains(e.getTo().getBlock().getType())) {
				e.setCancelled(true);
				return;
			}
			e.getTo().setX(e.getTo().getBlockX() + 0.5D);
			e.getTo().setZ(e.getTo().getBlockZ() + 0.5D);
			e.setTo(e.getTo());
		}
	}

}
