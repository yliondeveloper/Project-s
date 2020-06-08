
package xyz.starmc.permssions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;
import xyz.starmc.score.TeamScore;
import xyz.starmc.sql.SQLXp;
import xyz.starmc.system.TagAPI;

public class PermEntry implements Listener {

	public static Map<String, String> Group = new HashMap<String, String>();
	HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
	public static Map<Player, String> ExtraPermissions = new HashMap<Player, String>();

	public static String getPermissions(Player p) {
		if (ExtraPermissions.containsKey(p)) {
			return ExtraPermissions.get(p);
		} else {
			return "Nenhuma";
		}
	}

	public static String getGroup(Player p) {
		if (Group.containsKey(p.getName())) {
			return Group.get(p.getName());
		} else {
			return "Membro";
		}
	}

	@EventHandler
	public void RemoveCache(PlayerQuitEvent e) {
		if (Group.containsKey(e.getPlayer().getName())) {
			Group.remove(e.getPlayer().getName());
		}
		if (ExtraPermissions.containsKey(e.getPlayer())) {
			PermEntry.ExtraPermissions.remove(e.getPlayer());
		}
		perms.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void RemoveCache(PlayerKickEvent e) {
		if (Group.containsKey(e.getPlayer().getName())) {
			Group.remove(e.getPlayer().getName());
		}
		if (ExtraPermissions.containsKey(e.getPlayer())) {
			ExtraPermissions.remove(e.getPlayer());
		}
		perms.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onLoginEvent(PlayerLoginEvent e) {
		new BukkitRunnable() {
			public void run() {
				String group = getGroup(e.getPlayer());
				Player p = (Player) e.getPlayer();
				PermissionAttachment attachment = e.getPlayer().addAttachment(Main.getInstance());
				perms.put(e.getPlayer().getUniqueId(), attachment);
				PermissionAttachment pperms = perms.get(e.getPlayer().getUniqueId());
				pperms.setPermission("litebans.notify.broadcast", true);
				if (group.equalsIgnoreCase("Dicloser") || group.toLowerCase().equalsIgnoreCase("discloser")) {
					pperms.setPermission("tag.discloser", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§3§lDISCLOSER §3" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§3§lDISCLOSER §3",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Spro") || group.toLowerCase().equalsIgnoreCase("spro")) {
					pperms.setPermission("tag.spro", true);
					pperms.setPermission("tag.normal", true);
					TagAPI.setNameTag(p.getName(), "team", "§e§lSPRO §e",  " §7(" + SQLXp.getRank(p) + "§7)");
					p.setDisplayName("§e§lSPRO §e" + p.getName());
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Pro") || group.toLowerCase().equalsIgnoreCase("pro")) {
					pperms.setPermission("tag.pro", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§6§lPRO §6" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§6§lPRO §6",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Youtuber") || group.toLowerCase().equalsIgnoreCase("youtuber")) {
					pperms.setPermission("potpvp.vip", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.youtuber", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§b§lYT §b" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§b§lYT §b",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Youtuber+") || group.toLowerCase().equalsIgnoreCase("youtuber+")) {
					pperms.setPermission("potpvp.vip", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.youtuber+", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§9§lYT+ §9" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§9§lYT+ §9",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Ultra") || group.toLowerCase().equalsIgnoreCase("ultra")) {
					pperms.setPermission("potpvp.vip", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.ultra", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§e§lULTRA §e" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§e§lULTRA §e",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Ultimate") || group.toLowerCase().equalsIgnoreCase("ultimate")) {
					pperms.setPermission("potpvp.vip", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.ultimate", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§d§lULTIMATE §d" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§d§lULTIMATE §d",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Crystal") || group.toLowerCase().equalsIgnoreCase("crystal")) {
					pperms.setPermission("potpvp.vip", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cosmetics", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("tag.crystal", true);
					pperms.setPermission("tag.normal", true);
					p.setDisplayName("§5§lCRYSTAL §5" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§5§lCRYSTAL §5",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if(group.equalsIgnoreCase("YoutuberManager") || group.equalsIgnoreCase("youtubermanager")) {
					ExtraPermissions.put(p, "Discloser , Spro , Pro , Youtuber , Youtuber+ , checkgroup ");
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("tag.youtubermanager", true);
					pperms.setPermission("litebans.tempmute", true);
					p.setDisplayName("§a§lYTMANAGER §a" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§a§lYTMANAGER §a", " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(e.getPlayer());
					return;
				}
				if (group.equalsIgnoreCase("Helper") || group.equalsIgnoreCase("helper") || group.equalsIgnoreCase("ajudante")) {
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.helper", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.warn", true);
					p.setDisplayName("§9§lAJUDANTE §9" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§9§lAJUDANTE §9",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Helper+") || group.equalsIgnoreCase("helper+")  || group.equalsIgnoreCase("ajudante+")) {
					ExtraPermissions.put(p, "Discloser , Spro , Pro , Youtuber , checkgroup ");
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.helper+", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.warn", true);
					p.setDisplayName("§9§lAJUDANTE+ §9" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§9§lAJUDANTE+ §9",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Trialgc") || group.toLowerCase().equalsIgnoreCase("trialgc")) {
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.trialgc", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					p.setDisplayName("§5§lTRIALGC §5" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§5§lTRIALGC §5",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Trial") || group.toLowerCase().equalsIgnoreCase("trial")) {
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.trial", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.ban", true);
					p.setDisplayName("§5§lTRIAL §5" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§5§lTRIAL §5",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Mod") || group.toLowerCase().equalsIgnoreCase("mod")) {
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.mod", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					p.setDisplayName("§5§lMOD §5" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§5§lMOD §5",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("ModGc") || group.toLowerCase().equalsIgnoreCase("modgc")) {
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.modgc", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					p.setDisplayName("§5§lMODGC §5" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§5§lMODGC §5",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Admin") || group.toLowerCase().equalsIgnoreCase("admin")) {
					ExtraPermissions.put(p, "Discloser , Spro , Pro , Youtuber , checkgroup , YoutuberManager");
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("potpvp.pvp", true);
					pperms.setPermission("potpvp.tpall", true);
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.broadcast", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.admin", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					pperms.setPermission("litebans.unban", true);
					pperms.setPermission("litebans.unmute", true);
					pperms.setPermission("litebans.unwarn", true);
					pperms.setPermission("litebans.lockdown", true);
					pperms.setPermission("litebans.geoip", true);
					pperms.setPermission("litebans.banlist", true);
					pperms.setPermission("litebans.checkban", true);
					p.setDisplayName("§c§lADMIN §c" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§c§lADMIN §c",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("AdminGc") || group.toLowerCase().equalsIgnoreCase("admingc")) {
					ExtraPermissions.put(p, "Discloser , Spro , Pro , Youtuber , checkgroup  , YoutuberManager");
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.tpall", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("potpvp.pvp", true);
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.broadcast", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.admingc", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					pperms.setPermission("litebans.lockdown", true);
					pperms.setPermission("litebans.unban", true);
					pperms.setPermission("litebans.unmute", true);
					pperms.setPermission("litebans.unwarn", true);
					pperms.setPermission("litebans.geoip", true);
					pperms.setPermission("litebans.banlist", true);
					pperms.setPermission("litebans.checkban", true);
					p.setDisplayName("§c§lADMINGC §c" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§c§lADMINGC §c",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Gerente") || group.toLowerCase().equalsIgnoreCase("gerente")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , Trial , Mod , Mod+ , Admin , Membro , checkgroup , YoutuberManager");
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.tpall", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.build", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("potpvp.pvp", true);
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.broadcast", true);
					pperms.setPermission("potpvp.stop", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.gerente", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					pperms.setPermission("litebans.lockdown", true);
					pperms.setPermission("litebans.unban", true);
					pperms.setPermission("litebans.unmute", true);
					pperms.setPermission("litebans.unwarn", true);
					pperms.setPermission("litebans.geoip", true);
					pperms.setPermission("litebans.banlist", true);
					pperms.setPermission("litebans.checkban", true);
					p.setDisplayName("§c§lGERENTE §c" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§c§lGERENTE §c",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("GerenteGc") || group.toLowerCase().equalsIgnoreCase("gerentegc")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , ModGC , AdminGC , Membro , checkgroup , YoutuberManager");
					pperms.setPermission("potpvp.sc", true);
					pperms.setPermission("potpvp.report", true);
					pperms.setPermission("potpvp.admin", true);
					pperms.setPermission("potpvp.tp", true);
					pperms.setPermission("potpvp.ip", true);
					pperms.setPermission("potpvp.go", true);
					pperms.setPermission("potpvp.gm", true);
					pperms.setPermission("potpvp.tpall", true);
					pperms.setPermission("potpvp.fly", true);
					pperms.setPermission("potpvp.staff", true);
					pperms.setPermission("potpvp.cage", true);
					pperms.setPermission("potpvp.chat", true);
					pperms.setPermission("potpvp.cor", true);
					pperms.setPermission("potpvp.chatoff", true);
					pperms.setPermission("potpvp.build", true);
					pperms.setPermission("potpvp.cc", true);
					pperms.setPermission("potpvp.pvp", true);
					pperms.setPermission("potpvp.spam", true);
					pperms.setPermission("potpvp.broadcast", true);
					pperms.setPermission("tag.normal", true);
					pperms.setPermission("tag.gerentegc", true);
					pperms.setPermission("litebans.mute", true);
					pperms.setPermission("litebans.tempmute", true);
					pperms.setPermission("litebans.warn", true);
					pperms.setPermission("litebans.kick", true);
					pperms.setPermission("litebans.ipban", true);
					pperms.setPermission("litebans.tempban", true);
					pperms.setPermission("litebans.tempipban", true);
					pperms.setPermission("litebans.ban", true);
					pperms.setPermission("litebans.lockdown", true);
					pperms.setPermission("litebans.unban", true);
					pperms.setPermission("litebans.unmute", true);
					pperms.setPermission("litebans.unwarn", true);
					pperms.setPermission("litebans.geoip", true);
					pperms.setPermission("litebans.banlist", true);
					pperms.setPermission("litebans.checkban", true);
					p.setDisplayName("§c§lGERENTEGC §c" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§c§lGERENTEGC §c",  " §7(" + SQLXp.getRank(p) + "§7)");
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Coordenador") || group.toLowerCase().equalsIgnoreCase("coordenador")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro , YoutuberManager");
					p.setDisplayName("§9§lCOORD §9" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§9§lCOORD §9",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
				}

				if (group.equalsIgnoreCase("Subdono") || group.toLowerCase().equalsIgnoreCase("subdono")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro , YoutuberManager");
					p.setDisplayName("§4§lSUBDONO §4" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§4§LSUBDONO §4",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Dono") || group.toLowerCase().equalsIgnoreCase("dono")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro ,  Crystal , Ultra , Ultimate , Diretor , demoted , tweet , YoutuberManager");
					p.setDisplayName("§4§lDONO §4" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§4§LDONO §4",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
					return;
				}

				if (p.getName().equalsIgnoreCase("yLionCodes")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro ,  Crystal , Ultra , Ultimate , Diretor , YoutuberManager");
					p.setDisplayName("§4§lDONO §4" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§4§LDONO §4",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
					return;
				}
				if (p.getName().equalsIgnoreCase("yNescauh_")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro , YoutuberManager");
					p.setDisplayName("§4§lDONO §4" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§4§LDONO §4",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
					return;
				}
				if (group.equalsIgnoreCase("Diretor") || group.toLowerCase().equalsIgnoreCase("diretor")) {
					ExtraPermissions.put(p,
							"Discloser , Spro , Pro , Youtuber , TrialGC , Trial , Mod , Mod+ , ModGC , AdminGC , Admin , Gerente , GerenteGC , Coordenador , checkgroup , Membro , tweet , YoutuberManager");
					p.setDisplayName("§1§lDIRETOR §1" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§1§lDIRETOR §1",  " §7(" + SQLXp.getRank(p) + "§7)");
					if (!e.getPlayer().isOp()) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + e.getPlayer().getName());
					}
					TeamScore.addScore(p);
					return;
				}
				TeamScore.addScore(p);
				p.setDisplayName("§7" + p.getName());
				TagAPI.setNameTag(p.getName(), "team", "§7",  " §7(" + SQLXp.getRank(p) + "§7)");
			}
		}.runTaskLater(Main.getPlugin(), 10 * 2);
	}

}
