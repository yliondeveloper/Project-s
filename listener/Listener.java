package xyz.starmc.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import xyz.starmc.commands.BuildCMD;
import xyz.starmc.commands.ChatCMD;
import xyz.starmc.extra.ExtraGUI;
import xyz.starmc.extra.Title;
import xyz.starmc.fileapi.FileAPI;
import xyz.starmc.gui.RankedSelection;
import xyz.starmc.gui.SelectionClassic;
import xyz.starmc.gui.UnRankedSelection;
import xyz.starmc.main.Main;
import xyz.starmc.npc.NPCLibrary;
import xyz.starmc.permssions.PermEntry;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLClan;
import xyz.starmc.sql.SQLPerms;
import xyz.starmc.sql.SQLXp;
import xyz.starmc.system.FlagProtection;

public class Listener implements org.bukkit.event.Listener {

	public static List<String> cooldown = new ArrayList<String>();
	public static List<String> Spawn = new ArrayList<String>();
	public static List<Player> RunningParty = new ArrayList<Player>();
	public static Map<String, String> Sumo = new HashMap<String, String>();
	public static Map<Player, Long> chat = new HashMap<Player, Long>();
	public static UUID sumorandom = null;

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		if (!e.isCancelled()) {
			if (Bukkit.getServer().getHelpMap().getHelpTopic(e.getMessage().split(" ")[0]) == null) {
				e.getPlayer().sendMessage("§c§lERRO §fComando não encontrado.");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (NPCLibrary.getNPC(e.getRightClicked()) != null) {
			if (NPCLibrary.getNPC(e.getRightClicked()).getName().equals("§7[§eBOT§7]")) {
				e.getPlayer().sendMessage(
						"§e§lBOT §fOlá, eu sou um novo projeto da network.. estou sendo desenvolvido diariamente para retirar suas dúvidas!");
				e.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void Login(AsyncPlayerPreLoginEvent e) {
		if (!PermEntry.Group.containsKey(e.getName())) {
			PermEntry.Group.put(e.getName(), SQLPerms.getGroupConnection(e.getName()));
		}
		if (!SQLXp.cachexp.containsKey(e.getName())) {
			SQLXp.cachexp.put(e.getName(), SQLXp.getXpConnection(e.getName()));
		}
		SQLClan.loadCache(e.getName());
		SQLCache.loadCache(e.getName());
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getPlayer().getItemInHand().getType() == Material.ENDER_PEARL) {
				if (cooldown.contains(e.getPlayer().getName())) {
					e.getPlayer().sendMessage("§c§lCOOLDOWN §fAguarde para usar sua enderpearl novamente...");
					e.setCancelled(true);
				} else {
					cooldown.add(e.getPlayer().getName());
					e.getPlayer().sendMessage("§c§lCOOLDOWN §fSua enderpearl entrou em um cooldown de 10 segundos!");
					new BukkitRunnable() {
						public void run() {
							cooldown.remove(e.getPlayer().getName());
							e.getPlayer()
									.sendMessage("§c§lCOOLDOWN §fAgora você já pode usar sua enderpearl novamente.");
						}
					}.runTaskLater(Main.getInstance(), 20 * 10);
				}

			}
		}

	}

	@EventHandler
	public void DamageLava(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.LAVA) {
			e.setCancelled(true);
			return;
		}
		if (e.getCause() == DamageCause.FIRE) {
			if (((Player) e.getEntity()).getHealth() <= 1.0) {
				e.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void onEventPlayerInteractEventJoinGui(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = (Player) e.getPlayer();
			UUID id = p.getUniqueId();
			if (e.getPlayer().getItemInHand().getType().equals(Material.APPLE)
					&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand()
							.getItemMeta().getDisplayName().equalsIgnoreCase("§eSumo - §7(§eClique§7)")) {
				e.setCancelled(true);

				if ((sumorandom != null) && (sumorandom != id)) {
					Player p2 = Bukkit.getPlayer(sumorandom);
					if (p2 != null) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage("§a§lSEARCH §fOponente encontrado, batalha iniciando...");
						p.sendMessage("§a§lSEARCH §fBatalha iniciada com sucesso, boa sorte!");

						p.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) -512.41374164630577,
								(double) 15.0, (double) -496.2558240248955));
						p2.teleport(new Location(Bukkit.getServer().getWorld("PotPvP"), (double) -512.41374164630577,
								(double) 15.0, (double) -484.2558240248955, (float) 179.0, (float) 2.5));
						RunningParty.add(p);
						RunningParty.add(p2);
						Spawn.remove(p.getName());
						Spawn.remove(p2.getName());
						for (Player todos : xyz.starmc.listener.Listener.RunningParty) {
							todos.hidePlayer(p);
							todos.hidePlayer(p2);
						}
						for (Player s : Bukkit.getOnlinePlayers()) {
							p.hidePlayer(s);
							p2.hidePlayer(s);
						}
						APIListener.removePlayer(p);
						APIListener.removePlayer(p2);
						Sumo.put(p.getName(), p2.getName());
						Sumo.put(p2.getName(), p.getName());
						sumorandom = null;
						APIListener.teleportPlayer(p2);
						APIListener.teleportPlayer(p);
						p.showPlayer(p2);
						p2.showPlayer(p);
						return;
					}
				} else {
					p.getInventory().setArmorContents(null);
					p.getInventory().clear();
					// ---------------------------
					ItemStack redstone = new ItemStack(org.bukkit.Material.REDSTONE);
					ItemMeta redstonee = redstone.getItemMeta();
					redstonee.setDisplayName("§eSair §7(§eClique§7)");
					redstone.setItemMeta(redstonee);
					redstone.setAmount(1);
					// ---------------------------
					p.getInventory().setItem(4, redstone);
					p.sendMessage("§a§lSEARCH §fVocê entrou na fila, buscando oponente...");
					sumorandom = id;
				}
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
				return;
			}
		}
		if (e.getPlayer().getItemInHand().getType().equals(Material.IRON_SWORD)
				&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand()
						.getItemMeta().getDisplayName().equalsIgnoreCase("§ePotPvP - §7(§eUnranked§7)")) {
			e.setCancelled(true);
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				UnRankedSelection.SelectionGuiActive(e.getPlayer());
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
				return;
			}
		} else {
			if (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_SWORD)
					&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand()
							.getItemMeta().getDisplayName().equalsIgnoreCase("§ePotPvP - §7(§eRanked§7)")) {
				e.setCancelled(true);
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					int Ping = ((CraftPlayer) e.getPlayer()).getHandle().ping;
					if (Ping >= 170) {
						e.getPlayer().sendMessage(
								"§c§lRANKED §fSeu ping deve estar abaixo de 130ms para participar de uma partida ranqueada.");
						return;
					}
					RankedSelection.SelectionGuiActive(e.getPlayer());
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
					return;
				}
			} else {
				if (e.getPlayer().getItemInHand().getType().equals(Material.GOLD_SWORD)
						&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand()
								.getItemMeta().getDisplayName().equalsIgnoreCase("§eClassic Modes (§7Unranked§e)")) {
					e.setCancelled(true);
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						SelectionClassic.Debuff(e.getPlayer());
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
						return;
					}
				} else {
					if (e.getPlayer().getItemInHand().getType() == Material.EMERALD) {
						ExtraGUI.ExtraGUIOpen(e.getPlayer());
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
						return;
					} else {
						if (e.getPlayer().getItemInHand().getType().equals(Material.REDSTONE)
								&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()
								&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName()
										.equalsIgnoreCase("§eSair §7(§eClique§7)")) {
							e.setCancelled(true);
							if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
								APIListener.removeCache(e.getPlayer());
								APIListener.removePlayer(e.getPlayer());
								e.getPlayer().getInventory().setArmorContents(null);
								e.getPlayer().getInventory().clear();
								if (!Spawn.contains(e.getPlayer().getName())) {
									Spawn.add(e.getPlayer().getName());
								}
								APIListener.getInventory(e.getPlayer());
								FlagProtection.setProtection(e.getPlayer(), true);
								e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
								return;
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onIteraction(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = (Player) e.getPlayer();
			if (e.getPlayer().getItemInHand().getType().equals(Material.IRON_INGOT)
					&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()
					&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cSair")) {
				e.setCancelled(true);
				p.setAllowFlight(false);
				p.setFlying(false);
				for (Player todos : Bukkit.getOnlinePlayers()) {
					todos.showPlayer(p);
					p.showPlayer(todos);
				}
				new BukkitRunnable() {
					public void run() {
						p.chat("/spawn");
					}
				}.runTaskLater(Main.getPlugin(), 20 * 1);
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		if(sumorandom == e.getPlayer().getUniqueId()) {
			sumorandom = null;
		}
		APIListener.quitPlayer(e.getPlayer());
		APIListener.removeCache(e.getPlayer());
		new BukkitRunnable() {
			public void run() {
				SQLClan.updateData(e.getPlayer());
				SQLCache.updateData(e.getPlayer());
				SQLXp.updateData(e.getPlayer());
			}
		}.runTaskAsynchronously(Main.getPlugin());
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		if(sumorandom == e.getPlayer().getUniqueId()) {
			sumorandom = null;
		}
		APIListener.quitPlayer(e.getPlayer());
		APIListener.removeCache(e.getPlayer());
		new BukkitRunnable() {
			public void run() {
				SQLClan.updateData(e.getPlayer());
				SQLCache.updateData(e.getPlayer());
				SQLXp.updateData(e.getPlayer());
			}
		}.runTaskAsynchronously(Main.getPlugin());
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}

	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		APIListener.joinEvent(e.getPlayer());

	}

	@EventHandler(priority = EventPriority.HIGH)
	public void aoconstruir(BlockBreakEvent e) {
		if (!BuildCMD.embuild.contains(e.getPlayer())) {
			if (!(e.getBlock().getType() == Material.BEDROCK)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void entityDamage(EntityDamageByEntityEvent e) {
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void entityDamageByVoid(EntityDamageEvent e) {
		if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
			if (Sumo.containsKey(e.getEntity().getName())) {
				APIListener.deathSumo((Player) e.getEntity());
			} else {
				e.getEntity().teleport(e.getEntity().getWorld().getSpawnLocation());
			}

		}
	}

	@EventHandler
	public void entityDamage(EntityDamageEvent e) {
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void danosSumo(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (Sumo.containsKey(e.getEntity().getName())) {
				e.setDamage(0);
			}
		}
	}

	@EventHandler
	public void danosSetLevelData(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();
			if (e.getDamage() > 1.0) {
				e.setDamage(e.getDamage() - 1.0);
			}
			if (e.getDamager() instanceof Player) {

				if (player.getItemInHand().getType() == Material.STONE_SWORD) {
					e.setDamage(3.0);
					return;
				}
				if (player.getItemInHand().getType() == Material.IRON_SWORD) {
					e.setDamage(4.0);
					return;
				}
				if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					e.setDamage(8.5);
					return;
				}
			}

		}
	}

	@EventHandler
	public void onSpam(AsyncPlayerChatEvent e) {
		if (!chat.containsKey(e.getPlayer())) {
			if (e.getPlayer().hasPermission("potpvp.spam")) {
				return;
			}
			chat.put(e.getPlayer(), Long.valueOf(System.currentTimeMillis() + 8000));
		} else if (((Long) chat.get(e.getPlayer())).longValue() <= System.currentTimeMillis()) {
			if (e.getPlayer().hasPermission("potpvp.spam")) {
				return;
			}
			chat.put(e.getPlayer(), Long.valueOf(System.currentTimeMillis() + 8000));
		} else {
			if ((((Long) chat.get(e.getPlayer())).longValue() < System.currentTimeMillis())
					|| (e.getPlayer().hasPermission("potpvp.spam"))) {
				return;
			}
			e.getPlayer().sendMessage("§c§lSPAM §fAguarde para enviar outra mensagem.");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void aoDropar(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType() != Material.POTION
				|| e.getItemDrop().getItemStack().getType() != Material.COOKED_BEEF
				|| e.getItemDrop().getItemStack().getType() != Material.GLASS_BOTTLE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEventAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		String ConvertMessage = e.getMessage().toLowerCase().replace("ú", "u").replace("á", "a").replace("í", "i")
				.replace("é", "e").replace("ó", "o").replace("ã", "a").replace("ê", "e").replace("ç", "c")
				.replace("como", "").replace("qual", "").replace("porque", "").replace("quando", "").replace("onde", "")
				.replace("quanto", "").replace("posso", "").replace("como", "").replace("por que", "").replace("?", "")
				.replace("eu", "").replace("tu", "").replace("ele", "").replace("ela", "").replace("nos", "")
				.replace("nois", "").replace("vos", "").replace("eles", "").replace("elas", "").replace("pq", "")
				.replace("posso", "").replace("ql", "").replace("vejo", "").replace("olho", "");
		if (FileAPI.botfunction.containsKey(ConvertMessage)) {
			e.getPlayer().sendMessage("§e§lBOT §f" + FileAPI.botfunction.get(ConvertMessage));
			e.setCancelled(true);
			return;
		}
		if (!e.getPlayer().hasPermission("potpvp.cor")) {
			if (Title.RankXp.contains(e.getPlayer().getName())) {
				e.setFormat("§7(" + SQLXp.getRank(e.getPlayer()) + "§7) " + "§7" + e.getPlayer().getDisplayName()
						+ " §7» §7" + e.getMessage().replace("%", ""));
			} else {
				if (Title.Xp.contains(e.getPlayer().getName())) {
					e.setFormat("§7(§a" + SQLXp.getXp(e.getPlayer()) + "§7) " + "§7" + e.getPlayer().getDisplayName()
							+ " §7» §7" + e.getMessage().replace("%", ""));
				} else {
					if (Title.ClanTag.contains(e.getPlayer().getName())) {
						e.setFormat("§7(§8" + SQLClan.tag.get(e.getPlayer().getName()) + "§7) " + "§7"
								+ e.getPlayer().getDisplayName() + " §7» §7" + e.getMessage().replace("%", ""));
					} else {
						e.setFormat("§7(" + SQLXp.getRank(e.getPlayer()) + "§7) " + "§7"
								+ e.getPlayer().getDisplayName() + " §7» §7" + e.getMessage().replace("%", ""));
					}
				}
			}
		} else {
			if (Title.RankXp.contains(e.getPlayer().getName())) {
				e.setFormat("§7(" + SQLXp.getRank(e.getPlayer()) + "§7) " + "§7" + e.getPlayer().getDisplayName()
						+ " §7» §f" + e.getMessage().replace("%", "").replace("&", "§"));
			} else {
				if (Title.Xp.contains(e.getPlayer().getName())) {
					e.setFormat("§7(§a" + SQLXp.getXp(e.getPlayer()) + "§7) " + "§7" + e.getPlayer().getDisplayName()
							+ " §7» §f" + e.getMessage().replace("%", "").replace("&", "§"));
				} else {
					if (Title.ClanTag.contains(e.getPlayer().getName())) {
						e.setFormat("§7(§8" + SQLClan.tag.get(e.getPlayer().getName()) + "§7) " + "§7"
								+ e.getPlayer().getDisplayName() + " §7» §f"
								+ e.getMessage().replace("%", "").replace("&", "§"));
					} else {
						e.setFormat(
								"§7(" + SQLXp.getRank(e.getPlayer()) + "§7) " + "§7" + e.getPlayer().getDisplayName()
										+ " §7» §f" + e.getMessage().replace("%", "").replace("&", "§"));
					}
				}
			}
		}
	}

	@EventHandler
	public void onEventAsyncPlayerChatEventBlock(AsyncPlayerChatEvent e) {
		if (!e.getPlayer().hasPermission("potpvp.chatoff") && !ChatCMD.chat) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§c§lCHAT §fO chat está desabilitado, não é possí­vel enviar mensagens agora.");
		}
	}

	@EventHandler
	public static void BlockArrow(ProjectileHitEvent e) {
		Entity ent = e.getEntity();
		if (ent instanceof Arrow) {
			ent.remove();
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Player)) {
			Player d = (Player) e.getDamager();
			if ((d.getItemInHand().getType() == Material.DIAMOND_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.WOOD_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.IRON_SWORD)
					|| (d.getItemInHand().getType() == Material.GOLD_SWORD)
					|| (d.getItemInHand().getType() == Material.FISHING_ROD)
					|| (d.getItemInHand().getType() == Material.DIAMOND_AXE)
					|| (d.getItemInHand().getType() == Material.GOLD_AXE)
					|| (d.getItemInHand().getType() == Material.STONE_AXE)
					|| (d.getItemInHand().getType() == Material.WOOD_AXE)
					|| (d.getItemInHand().getType() == Material.IRON_AXE)) {
				d.getItemInHand().setDurability((short) 0);
				d.updateInventory();
			}
		}
	}

	@EventHandler
	public void onBlockCommandsOnParty(PlayerCommandPreprocessEvent e) {
		if (RunningParty.contains(e.getPlayer())) {
			e.getPlayer().sendMessage("§c§lERRO §fVocê não pode usar comandos em uma batalha.");
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void aoconstruir(BlockPlaceEvent e) {
		if (!BuildCMD.embuild.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEventBlockExplodeEvent(BlockExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onCair(ItemSpawnEvent e) {
		if (e.getEntity().getItemStack().getType() != Material.POTION) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerBucket(PlayerBucketEmptyEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onItemDrop(ItemSpawnEvent e) {
		e.getEntity().remove();

	}

	@EventHandler
	public void onBlockedFoodLevel(FoodLevelChangeEvent e) {
		if (Spawn.contains(e.getEntity().getName()) | Sumo.containsKey(e.getEntity().getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockedWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockedEntityExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}

}
