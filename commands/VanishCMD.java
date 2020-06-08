
package xyz.starmc.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import xyz.starmc.main.Main;
import xyz.starmc.system.FlagProtection;
import xyz.starmc.system.MessageAPI;
import xyz.starmc.system.SystemAPI;

public class VanishCMD implements CommandExecutor, Listener {

	public static List<Player> emAdmin = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEventPlayerInteractEvent(PlayerInteractEvent e) {
		if (emAdmin.contains(e.getPlayer())
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& e.getPlayer().getItemInHand().getType() == Material.getMaterial(351)) {
			e.setCancelled(true);
			e.getPlayer().chat("/v");
			new BukkitRunnable() {
				public void run() {
					e.getPlayer().chat("/v");

				}
			}.runTaskLater(Main.getInstance(), 20);

		}
	}

	@EventHandler
	public void onBedrock(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.BEDROCK
				&& emAdmin.contains(e.getPlayer())) {
			Player t = (Player) e.getRightClicked();
			e.getPlayer().chat("/cage " + t.getName());
		}
	}

	@EventHandler
	public void click(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		final Player c = (Player) e.getRightClicked();
		if (emAdmin.contains(e.getPlayer()) && (e.getPlayer().getItemInHand().getType() == Material.ANVIL)) {
			c.setVelocity(new Vector(0, 2, 0).setY(2));
		}
	}

	@EventHandler
	public void Abririnv(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.AIR
				&& emAdmin.contains(e.getPlayer())) {
			e.getPlayer().openInventory(((Player) e.getRightClicked()).getInventory());
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(MessageAPI.onCommandConsoleBlock);
			return true;
		} else {
			if (sender.hasPermission("potpvp.admin")) {
				if (!emAdmin.contains(sender)) {
					new BukkitRunnable() {
						public void run() {
							emAdmin.add(p);
							FlagProtection.setProtection(p, true);
							p.sendMessage("§3§lADMIN §fVocê entrou no modo Vanish!");

						}
					}.runTaskAsynchronously(Main.getPlugin());
					p.setGameMode(GameMode.CREATIVE);
					p.getInventory().clear();
					p.setHealth(20.0);
					p.setFoodLevel(20);
					for (Player todos : Bukkit.getOnlinePlayers()) {
						if (!todos.hasPermission("potpvp.admin")) {
							todos.hidePlayer(p);
						}

					}
					SystemAPI.getItemStack(p, 1, "§eQuickAdmin", 4);
					SystemAPI.getItem(p, Material.ANVIL, 1, "§ENofall", 3);
					SystemAPI.getItem(p, Material.BEDROCK, 1, "§ECage", 5);
				} else {
					emAdmin.remove(p);
					p.getInventory().clear();
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage("§3§lADMIN §fVocê saiu do modo Vanish!");
					for (Player todos : Bukkit.getOnlinePlayers()) {
						todos.showPlayer(p);
					}
				}
			} else {
				p.sendMessage(MessageAPI.onCommandNoPerm);
			}
		}
		return false;
	}

}
